package com.scm.scm10.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.scm10.Repository.UserRepo;
import com.scm.scm10.entity.User;
import com.scm.scm10.helper.ResourceNotFoundExeception;
import com.scm.scm10.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private PasswordEncoder	 passwordEncoder;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public User saveUser(User user) {
		
		String userId = UUID.randomUUID().toString();
		user.setUserid(userId);
		//password encoder
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		//set user role
		user.setRoleList(List.of(AppConstants.ROLE_USER));
		return userrepo.save(user);
	}

	@Override
	public Optional<User> getuserbyid(String id) {
		return userrepo.findById(id);
	}

	@Override
	public Optional<User> updateUser(User user) {
		User user2 = userrepo.findById(user.getUserid()).orElseThrow(()->new ResourceNotFoundExeception("user not found"));
	    
		user2.setName(user.getName());
		user2.setEmail(user.getEmail());
		user2.setPassword(user.getPassword());
		user2.setAbout(user.getAbout());
		user2.setPhonenumber(user.getPhonenumber());
		user2.setProfilepic(user.getProfilepic());
		user2.setEnabled(user.isEnabled());
		user2.setEmailvarified(user.isEmailvarified());
		user2.setPhoneverified(user.isPhoneverified());
		user2.setProvider(user.getProvider());
		user2.setProvideruserid(user.getProvideruserid());
		
		User save = userrepo.save(user2);
		
		return Optional.ofNullable(save);
		
	}

	@Override
	public void deleteUser(String id) {
		
		User user2 = userrepo.findById(id).orElseThrow(()->new ResourceNotFoundExeception("user not found"));
	}

	@Override
	public boolean isUserExist(String id) {
	    User user2 = userrepo.findById(id).orElse(null);
		
		return user2!=null ? true:false;
	}

	@Override
	public boolean isUserexistByEmail(String email) {
		User user1 = userrepo.findByEmail(email).orElse(null);
		
		return user1 != null ? true:false;
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userrepo.findAll();
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userrepo.findByEmail(email).orElse(null);
	}

	

	
}
