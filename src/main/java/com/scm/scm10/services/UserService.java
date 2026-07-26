package com.scm.scm10.services;


import java.util.List;
import java.util.Optional;

import com.scm.scm10.entity.User;

public interface UserService {

	User saveUser(User user);
	Optional<User> getuserbyid(String id);
	Optional<User> updateUser(User user);
	void deleteUser(String id);
	boolean isUserExist(String id);
	boolean isUserexistByEmail(String email);
	List<User> getAllUser();
	User getUserByEmail(String emial);
	
	
}
