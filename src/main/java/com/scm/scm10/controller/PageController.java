package com.scm.scm10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.engine.AttributeNames;

import com.scm.scm10.entity.User;
import com.scm.scm10.helper.Message;
import com.scm.scm10.helper.MessageType;
import com.scm.scm10.services.UserService;
import com.scm.scm10.userform.UserForm;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {

	@Autowired
	private UserService userservice;
	
	@GetMapping("/")
	public String index(){
		return "redirect:home";
	}
	
	@GetMapping("/home")
	public String home(Model model)
	{
		model.addAttribute("name","spring boot");
		model.addAttribute("youtube_channel", "dugesh");
		model.addAttribute("link","https://www.youtube.com/watch?v=SAqi7zmW1fY&t=4716s");
		return "home";
	}
	
	@PostMapping("/login")
	public String login()
	{
		return new String("login");
	}
	
	@GetMapping("/login")
	public String loginPage() {
	    return "login";
	}
	
	@GetMapping("/register")
	public String singnup(Model model)
	{
		UserForm user=new UserForm();
		model.addAttribute("userForm",user);
		return "register";
	}
	
	//proccessing form
	@PostMapping("/do-register")
	public String ProcessRegister(@Valid @ModelAttribute UserForm user,BindingResult rbindresult,HttpSession session)
	{
		//System.out.println(user);
		
//		User user1 = User.builder()
//				.name(user.getName())
//				.email(user.getEmail())
//				.password(user.getPassword())
//				.about(user.getAbout())
//				.phonenumber(user.getPhoneNumber())
//				.profilepic(user.getProfilepice())
//				.build();
		
		
		if(rbindresult.hasErrors())
		{
			System.out.println(rbindresult.hasErrors());
			return "register";
		}
		User user1 = new User();
		user1.setName(user.getName());
		user1.setEmail(user.getEmail());
		user1.setPassword(user.getPassword());
		user1.setAbout(user.getAbout());
		user1.setPhonenumber(user.getPhoneNumber());
		user1.setProfilepic("/images/profile-picture.png");
		user1.setEnabled(true);
		
		User saveduser = userservice.saveUser(user1);
		
		System.out.println("user saved "+saveduser);
		
		Message msg = Message.builder().content("Registration Sucessful").type(MessageType.blue).build();
		session.setAttribute("message",msg);
		return "redirect:/register";
	}
	
}
