package com.scm.scm10.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scm.scm10.Repository.UserRepo;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepo userrepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userrepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found with this email "+username));
	}

}
