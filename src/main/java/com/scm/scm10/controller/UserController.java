package com.scm.scm10.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.scm10.Config.OAuthenticationSucessHandler;
import com.scm.scm10.entity.User;
import com.scm.scm10.helper.Helper;
import com.scm.scm10.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private final OAuthenticationSucessHandler OAuthenticationSucessHandler;
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService service;
	
	
	UserController(OAuthenticationSucessHandler OAuthenticationSucessHandler) {
		this.OAuthenticationSucessHandler = OAuthenticationSucessHandler;
	}
	
	
	//user dashboard page
	@GetMapping("/dashboard")
	public String userDashboard()
	{
		System.out.println("i am here");
		return "/user/dashboard";
	}
	
	 @GetMapping("/profile")
	    public String profile(Model model,Authentication auth) {
		 
		 
	        return "user/profile";
	    }

}
