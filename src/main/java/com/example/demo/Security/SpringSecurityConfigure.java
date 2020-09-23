package com.example.demo.Security;

import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.example.demo.bean.ApplicationUserService;
import com.example.demo.jwt.JWTUsernameAndPasswordAuthenticationFilter;
import com.example.demo.jwt.JwtConfig;
import com.example.demo.jwt.JwtTokenVerifier;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SpringSecurityConfigure extends WebSecurityConfigurerAdapter	{
	
	
	private  final PasswordEncoder passwordEncoder;
	private  final ApplicationUserService applicationUserService;
	private  final SecretKey secretKey;
	private  final JwtConfig jwtConfig;
	
	@Autowired
	public SpringSecurityConfigure(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService,
			SecretKey secretKey, JwtConfig jwtConfig) {
		
		this.passwordEncoder = passwordEncoder;
		this.applicationUserService = applicationUserService;
		this.secretKey = secretKey;
		this.jwtConfig = jwtConfig;
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		System.out.println("Is it coming here");
		http
		    .csrf().disable()
		    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		    .and()
		    .addFilter( new JWTUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
		    .addFilterAfter(new JwtTokenVerifier( jwtConfig, secretKey), JWTUsernameAndPasswordAuthenticationFilter.class)
		    //  .csrf(csrf-> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
		    //  .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		      .authorizeRequests()
		      .antMatchers("/","/index","/css/*","/js/*").permitAll()
		    // .antMatchers("/management/api/**").hasRole(ApplicationUserRole.ADMIN_TRAINEE.name())
		      .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(ApplicationUserPermissions.COURSE_WRITE.getPermission())
		      .antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(ApplicationUserPermissions.COURSE_WRITE.getPermission())
		      .antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(ApplicationUserPermissions.COURSE_WRITE.getPermission())
		      .antMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(ApplicationUserRoles.ADMIN.name(),ApplicationUserRoles.ADMIN_TRAINEE.name())
		      .anyRequest()
		      .authenticated();
		      /*.and()
		      //.httpBasic();
		       .formLogin().permitAll()
		         .loginPage("/login")
		         .defaultSuccessUrl("/courses")
		         .passwordParameter("password")
		         .usernameParameter("username")
		         .and()
		      .rememberMe()
		        .tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(24))
		        .key("something very secured")
		        .rememberMeParameter("remember-me")
		        .and()
		        .logout()
		           .logoutUrl("/logout")
		           .logoutRequestMatcher( new AntPathRequestMatcher("/logout","GET"))
		           .clearAuthentication(true)
		           .invalidateHttpSession(true)
		           .deleteCookies("JSESSIONID","remembe-rme")
		           .logoutSuccessUrl("/login");*/
		           
		      
	}

	

	/*@Override
	@Bean
	protected UserDetailsService userDetailsService()
	{
	UserDetails rahulUser =User
		   .builder()
		   .username("rahul")
		   .password(passwordEncoder.encode("password"))
		   .roles(ApplicationUserRole.STUDENT.name()) // Internaly, spring will treat it asROLE_STUDENT
//		   .authorities(ApplicationUserRole.STUDENT.getGrantedAuthorities())
		
		   .build();
		UserDetails ronUser = User
				                .builder()
				                .username("puru")
				                .password(passwordEncoder.encode("password123"))
  			                //    .roles(ApplicationUserRole.ADMIN.name())
			                    .authorities(ApplicationUserRole.ADMIN
			                    		.getGrantedAuthorities())
				                .build();
		//System.out.println("And the password is :"+ronUser.getPassword());
		//System.out.println("And the authorities are :"+ronUser.getAuthorities());
		
		UserDetails ronTrainee = User
                .builder()
                .username("Tom")
                .password(passwordEncoder.encode("password123"))
               // .roles(ApplicationUserRole.ADMIN_TRAINEE.name())
                .authorities(ApplicationUserRole.ADMIN_TRAINEE.getGrantedAuthorities())
                .build();
		return new InMemoryUserDetailsManager(
		
				 ronUser, ronTrainee
				);*/
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoauthenticationProvider());
		
	}
	
	
	@Bean
	public DaoAuthenticationProvider daoauthenticationProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(applicationUserService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}
		
}
