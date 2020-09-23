package com.example.demo.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="application.jwt")
@Configuration
public class JwtConfig {

	
	private String secretKey;
	private String tokenPrefix;
	private Integer tokenExpirationAfterDays;
	
		
	
	public JwtConfig() {
		System.out.println("I am JWT CONFIG");
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getTokenPrefix() {
		return tokenPrefix;
	}
	public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}
	public Integer getTokenExpirationAfterDays() {
		return tokenExpirationAfterDays;
	}
	public void setTokenExpirationAfterDays(Integer tokenExpirationAfterDays) {
		this.tokenExpirationAfterDays = tokenExpirationAfterDays;
	}
	
	
	
	
	public String getAuthorizationHeader()
	{
		return org.springframework.http.HttpHeaders.AUTHORIZATION;
	}
	
	
	
	
	
	
	
}
