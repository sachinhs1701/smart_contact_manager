package com.scm.scm10.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.scm10.services.impl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {

	@Autowired
	private SecurityCustomUserDetailService userDetailService;
	
	@Autowired
	private OAuthenticationSucessHandler handler;
	
	@Bean
	public SecurityFilterChain securityfilterchain(HttpSecurity httpsecurity) throws Exception
	{
		//urls are configured which will be protected and which will public
		httpsecurity.authorizeHttpRequests(authorize ->{
			authorize.requestMatchers("/user/*").authenticated();//requests from all /user/** is protected
			authorize.anyRequest().permitAll();//apart from anbov url all are now public
		});
		
		httpsecurity.formLogin(formLogin->{
			
			formLogin.loginPage("/login");
			formLogin.loginProcessingUrl("/authenticate");
			formLogin.defaultSuccessUrl("/user/dashboard", true);
			//formLogin.failureForwardUrl("/login?error=true");
			formLogin.usernameParameter("email");
			formLogin.passwordParameter("password");
			
		});
		
		httpsecurity.csrf(AbstractHttpConfigurer::disable);
		httpsecurity.logout(logoutForm->{
			logoutForm.logoutUrl("/do-logout");
			logoutForm.logoutSuccessUrl("/login?logout=true");
		});
		
		httpsecurity.oauth2Login(oauth -> {
	        oauth.loginPage("/login");
	        oauth.successHandler(handler);
	        
	    });
		
		return httpsecurity.build();
		
	}
	
	//configuration of authentication provider spring security 
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider dao =new DaoAuthenticationProvider(userDetailService);
		//password encoder object
		dao.setPasswordEncoder(passwordEncoder());
		return dao;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
		
	}
	//for github login
	

	
}
