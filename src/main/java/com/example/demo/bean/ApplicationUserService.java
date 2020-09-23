package com.example.demo.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {
    
	
	private final ApplicationUserDAO aud;
	
	@Autowired
	public ApplicationUserService(@Qualifier("fake") ApplicationUserDAO aud)
	{
		System.out.println("Fuck You Service class");
		this.aud=aud;
		
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return aud.selectUserByUssername(username)
				.orElseThrow(()->
		                      new UsernameNotFoundException(String.format("username %s not found", username)));
				                                                 
	}

}
