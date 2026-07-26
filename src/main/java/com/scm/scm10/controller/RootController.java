package com.scm.scm10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.scm10.entity.User;
import com.scm.scm10.helper.Helper;
import com.scm.scm10.services.UserService;

@ControllerAdvice
public class RootController {

	@Autowired
	private UserService service;
	@ModelAttribute
	public void addLoggedInUserInformation(Model model,Authentication auth)
	{
		if(auth==null)
		{
			return;
		}
		System.out.println("adding loggedin user info to the model");
		 String username=Helper.getEmailofLoggedinUser(auth);
			// logger.info("user logged in :{}",username);
			 User user =service.getUserByEmail(username);
			 
			 if(user==null)
			 {
				 model.addAttribute("loggedInUser", null);
			 }
			 else {
			 System.out.println(user.getName());
			 System.out.println(user.getEmail());
			 model.addAttribute("loggedInUser", user);
			 }
	}
}
