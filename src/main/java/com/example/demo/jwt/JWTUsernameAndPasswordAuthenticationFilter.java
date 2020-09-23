package com.example.demo.jwt;

import java.sql.Date;
import java.time.LocalDate;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.IOException;

public class JWTUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter  {

	private final AuthenticationManager authenticationManager;
	private final JwtConfig jwtConfig;
	private final SecretKey secretKey;
	
     @Autowired
	public JWTUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig,
			SecretKey secretKey) {
		
		this.authenticationManager = authenticationManager;
		this.jwtConfig = jwtConfig;
		this.secretKey = secretKey;
	}


	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		
		try{
	UsernameAndPasswordAuthenticationRequest authenticationRequest	=new ObjectMapper().readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);
	
	Authentication authentication= new UsernamePasswordAuthenticationToken(
			authenticationRequest.getUsername(),
			authenticationRequest.getPassword()
			);
	
	Authentication authenticate =	authenticationManager.authenticate(authentication);
	return authenticate;
		}
		
	catch (java.io.IOException e) {
			throw new RuntimeException(e);
		}
		
	
	}
	
	
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response,
			FilterChain chain, 
			Authentication authResult)
			throws IOException, ServletException {
	//	String key ="weweweeewwwwwwwwwwwewewcdweweweeewwwwwwwwwwwewewcdweweweeewwwwwwwwwwwewewcd";
		
	String token=	Jwts
		     .builder()
		     .setSubject(authResult.getName())
		     .claim("authorities", authResult.getAuthorities())
		     .setIssuedAt(new Date(0))
		   //  .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
		//     .signWith(Keys.hmacShaKeyFor(key.getBytes()))
		     .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(jwtConfig.getTokenExpirationAfterDays())))
		     .signWith(secretKey)
		     .compact();
		
		
		//response.addHeader("authorization","Bearer " +token);
	
	response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix()+token);
		}
	}
	
	
	

