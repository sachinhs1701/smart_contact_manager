package com.scm.scm10.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.scm.scm10.entity.Contacts;
import com.scm.scm10.entity.User;

public interface ContactService {

	Contacts save(Contacts contact);
	
	Contacts update(Contacts contact);
	
	List<Contacts> getall();
	
	Contacts getById(String id);
	
	void deletcontact(String id);
	
	List<Contacts> search(String name,String email,String phonenumber);
	
	List<Contacts> getByUserId(String contactid);
	
	Page<Contacts> getByUser(User user,int page,int size,String sortby,String direction);
	
}
