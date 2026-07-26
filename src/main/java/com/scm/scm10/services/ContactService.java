package com.scm.scm10.services;

import java.util.List;

import com.scm.scm10.entity.Contacts;

public interface ContactService {

	Contacts save(Contacts contact);
	
	Contacts update(Contacts contact);
	
	List<Contacts> getall();
	
	Contacts getById(String id);
	
	void deletcontact(String id);
	
	List<Contacts> search(String name,String email,String phonenumber);
	
	List<Contacts> getUser(String contactid);
}
