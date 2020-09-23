package com.example.demo.Security;



import java.util.EnumSet;

import java.util.Set;
import java.util.stream.Collectors;

import org.assertj.core.util.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;



public enum ApplicationUserRoles  {

//	STUDENT(EnumSet.of(ApplicationUserPermission.STUDENT_READ)),
	ADMIN_TRAINEE(EnumSet.of(ApplicationUserPermissions.COURSE_READ,ApplicationUserPermissions.STUDENT_READ)),
	ADMIN(EnumSet.of(ApplicationUserPermissions.COURSE_READ,ApplicationUserPermissions.COURSE_WRITE,ApplicationUserPermissions.STUDENT_READ,ApplicationUserPermissions.STUDENT_WRITE));
	
	
	private final Set<ApplicationUserPermissions> permissions;
	
	
	ApplicationUserRoles(Set<ApplicationUserPermissions> permissions) {
		this.permissions = permissions;
	}

	public Set<ApplicationUserPermissions> getPermissions()
	{
		return permissions;
	}
	
	  public Set<SimpleGrantedAuthority> getGrantedAuthorities()
	  {
		    Set<SimpleGrantedAuthority> permissions=  getPermissions().stream()
		                      .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
		                      .collect(Collectors.toSet());
		    permissions.add(new SimpleGrantedAuthority("ROLE_"+ this.name()));
		 
		    System.out.println("And the permissions are:"+permissions); 
		    return permissions;
		                    
	                          
	  }
}
