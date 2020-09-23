package com.example.demo.bean;

import java.util.Optional;

import org.springframework.context.annotation.Bean;

public interface ApplicationUserDAO {
	
	 Optional<ApplicationUser> selectUserByUssername(String username);

}
