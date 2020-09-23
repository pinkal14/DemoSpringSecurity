package com.example.demo.bean;

import java.util.Collection;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class ApplicationUser implements UserDetails {

	
	
	private  final String password;
	private final String username;
	private  final Set<? extends GrantedAuthority> grantedAuthorities;
	private boolean isAccountNonExpired;
	private boolean isCredentialsNonExpired;
	private boolean isAccountNonLocked;
	private boolean isEnabled;
	
	
	
	
	public ApplicationUser( String password, 
			               String username,
			               Set<? extends GrantedAuthority> grantedAuthorities,
			               boolean isAccountNonExpired,
			               boolean isCredentialsNonExpired,
			               boolean isAccountNonLocked, 
			               boolean isEnabled) {
	
		this.grantedAuthorities = grantedAuthorities;
		this.password = password;
		this.username = username;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isEnabled = isEnabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		
		return isEnabled;
	}

	
}