package com.scm.scm10.helper;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

	public static String getEmailofLoggedinUser(Authentication auth)
	{
		//AuthenticationPrincipal principal =(AuthenticationPrincipal)auth.getPrincipal();
		if(auth instanceof OAuth2AuthenticationToken)
		{
			var aOAuth2AuthenticationToken =(OAuth2AuthenticationToken)auth;
			var clientid=aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
			var oauth2User=(OAuth2User)auth.getPrincipal();
			String username="";
			if(clientid.equalsIgnoreCase("github"))
			{
				System.out.println("getting email from github");
				username =oauth2User.getAttribute("email")!=null? oauth2User.getAttribute("email").toString()
						:oauth2User.getAttribute("login").toString()+"@gmail.com";
				System.out.println(username);
				
			}
			System.out.println(username);
			return username;
			
		}
		else
		{
			System.out.println("getting logged in using our database ");
			return 	auth.getName();
		}
		
	}
}
