package com.scm.scm10.services.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.scm10.Repository.ContactsRepo;
import com.scm.scm10.controller.ContactController;
import com.scm.scm10.entity.Contacts;
import com.scm.scm10.entity.User;
import com.scm.scm10.helper.ResourceNotFoundExeception;
import com.scm.scm10.services.ContactService;

@Service
public class ContactsServiceimpl implements ContactService {

	Logger logger=org.slf4j.LoggerFactory.getLogger(ContactsServiceimpl.class);
	@Autowired
	private ContactsRepo contactrepo;
	@Override
	public Contacts save(Contacts contact) {
		
		
		String contactid=UUID.randomUUID().toString();
		contact.setId(contactid);
		contactrepo.save(contact);
		return contactrepo.save(contact);
	}

	@Override
	public Contacts update(Contacts contact) {
		Contacts contactnew=contactrepo.findById(contact.getId()).orElseThrow(()->new ResourceNotFoundExeception("invalid data entry"));
		
		return null;
	}

	@Override
	public List<Contacts> getall() {
		// TODO Auto-generated method stub
		return contactrepo.findAll();
	}

	@Override
	public Contacts getById(String id) {
		// TODO Auto-generated method stub
		return contactrepo.findById(id).orElseThrow(()->new ResourceNotFoundExeception("contact not found"));
	}

	@Override
	public void deletcontact(String id) {
		contactrepo.deleteById(id);
	}

	@Override
	public List<Contacts> search(String name, String email, String phonenumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contacts> getByUserId(String contactid) {
		
		return contactrepo.findByUserId(contactid);
	}

	@Override
	public List<Contacts> getByUser(User user) {
		
		return contactrepo.findByUser(user);
	}
	
	

}
