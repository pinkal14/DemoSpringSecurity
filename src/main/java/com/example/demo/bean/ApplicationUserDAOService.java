package com.example.demo.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.Security.ApplicationUserRoles;



@Repository("fake")
public class ApplicationUserDAOService implements ApplicationUserDAO {


	private final PasswordEncoder encoder;
	
	@Autowired
	public ApplicationUserDAOService(PasswordEncoder encoder)
	{
		System.out.println("I am inside repositoryd dao service");
		this.encoder=encoder;
	}
	@Override
	public Optional<ApplicationUser> selectUserByUssername(String username) {
		
		System.out.println("I am in repository");
		return getAppUsers()
				  .stream()
				    .filter(appUser-> username.equals(appUser.getUsername()))
				     .findFirst();
	}
	
	public List<ApplicationUser> getAppUsers()
	{
		
				/* Lists.newArrayList(
				       new ApplicationUser(encoder.encode("password"), "rahul" ,
				    		   ApplicationUserRoles.ADMIN.getGrantedAuthorities(),
				    		     true, true, true, true),
				       
				       new ApplicationUser(encoder.encode("password"), "tom" , 
				    		   ApplicationUserRoles.ADMIN_TRAINEE.getGrantedAuthorities(),
				    		      true, true, true, true)   
				);
		return li;*/
		
		List<ApplicationUser> li =new ArrayList<>();
		          li.add( new ApplicationUser(encoder.encode("password"), "rahul" ,
				    		   ApplicationUserRoles.ADMIN.getGrantedAuthorities(),
				    		     true, true, true, true));
		          li.add( new ApplicationUser(encoder.encode("password"), "tom" ,
			    		   ApplicationUserRoles.ADMIN_TRAINEE.getGrantedAuthorities(),
			    		     true, true, true, true));
		System.out.println("And the list is" +li);
		return li;
		
		
	}

}
