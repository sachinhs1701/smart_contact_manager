package com.scm.scm10.Config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.scm10.Repository.UserRepo;
import com.scm.scm10.entity.User;
import com.scm.scm10.entity.providers;
import com.scm.scm10.services.impl.AppConstants;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class OAuthenticationSucessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private UserRepo userrepo;
	
	private static final Logger logger = LoggerFactory.getLogger(OAuthenticationSucessHandler.class);
	
	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request, 
			HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		logger.info("AuthenticationSucessHandler");
		
		DefaultOAuth2User user = (DefaultOAuth2User)authentication.getPrincipal();
		logger.info(user.getName());
		user.getAttributes().forEach((key,value)->{logger.info("{}-->{}",key,value);
		});
		
		logger.info(user.getAuthorities().toString());

		String email= user.getAttribute("email");
		if(email==null)
		{
			email =user.getAttribute("email")!=null? user.getAttribute("email").toString()
					:user.getAttribute("login").toString()+"@gmail.com";
		}
		String name =user.getAttribute("name");
		if(name==null)
		{
			name=user.getAttribute("login");
		}
		String picture= user.getAttribute("avatar_url").toString();
		
		User user1=new User();
		
		user1.setEmail(email);
		user1.setName(name);
		user1.setProfilepic(picture);
		user1.setUserid(UUID.randomUUID().toString());
		user1.setProvider(providers.GITHUB);
		user1.setEnabled(true);
		user1.setEmailvarified(true);
		user1.setProvideruserid(name);
		user1.setRoleList(List.of(AppConstants.ROLE_USER));
		user1.setAbout("this account is created by github");
		user1.setPassword("Sachinhs@12345");
		
		User user2=userrepo.findByEmail(email).orElse(null);
		
		if(user2==null)
		{
			userrepo.save(user1);
			logger.info("user saved"+email);
		}
		
		
		
		new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
		
	}

}
