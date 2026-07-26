package com.scm.scm10.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.scm10.entity.Contacts;
import com.scm.scm10.entity.User;
import com.scm.scm10.helper.Helper;
import com.scm.scm10.helper.Message;
import com.scm.scm10.helper.MessageType;
import com.scm.scm10.services.ContactService;
import com.scm.scm10.services.UserService;
import com.scm.scm10.userform.ContactForm;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("user/contacts")
public class ContactController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(ContactController.class);
	@Autowired
	ContactService contactservice;
	@Autowired
	UserService userservice;
	@GetMapping("/add")
	public String addContactView(Model model)
	{
		ContactForm contactform=new ContactForm();
		model.addAttribute("contactform",contactform);
		
		return "user/add_contact";
	}
	 @RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveform(@Valid @ModelAttribute("contactform") ContactForm contactform,BindingResult result,HttpSession session,Authentication authenticatio)
	{
		
		if (result.hasErrors()) {

            result.getAllErrors().forEach(error -> logger.info(error.toString()));

            session.setAttribute("message", Message.builder()
                    .content("Please correct the following errors")
                    .type(MessageType.red)
                    .build());
            return "user/add_contact";
        }
		System.out.println(contactform);
		String username=Helper.getEmailofLoggedinUser(authenticatio);
		User user=userservice.getUserByEmail(username);
		Contacts contactnew =new Contacts();
		contactnew.setFullname(contactform.getName());
		contactnew.setEmail(contactform.getEmail());
		contactnew.setAddress(contactform.getAddress());
		contactnew.setDescription(contactform.getDescription());
		contactnew.setPhonenumber(contactform.getPhonenumber());
		contactnew.setFavorite(contactform.isFavorite());
		contactnew.setUser(user);	
		contactnew.setLinkedinlink(contactform.getLinkedinlink());
		contactnew.setWebsitelink(contactform.getWebsitelink());
		
		 session.setAttribute("message",
	                Message.builder()
	                        .content("You have successfully added a new contact")
	                        .type(MessageType.green)
	                        .build());
		return "redirect:/user/contacts/add";
	}
}
