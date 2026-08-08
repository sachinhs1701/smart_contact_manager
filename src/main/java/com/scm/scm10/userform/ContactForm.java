package com.scm.scm10.userform;

import org.springframework.web.multipart.MultipartFile;

import com.scm.scm10.validators.ValidFile;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ContactForm {
	
	public ContactForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	@NotBlank(message="username is required")
	@Size(min=3,message="min 3 charecters required")
	private String name;
	
	@NotBlank(message="email is required")
	@Email(message="Invalid email address")
	private String email;
	
	@NotBlank(message="phonenumber is required")
	@Size(min=10,max=12,message="invalid phone numbers")
	private String phonenumber;
	
	@NotBlank(message="address is required")
	private String address;
	
	@NotBlank(message="description is required")
	private String description;
	
	
	private boolean favorite;
	
	private String websitelink;
	
	private String linkedinlink;
	
	@ValidFile
	private MultipartFile contactimage;

	@Override
	public String toString() {
		return "ContactForm [name=" + name + ", email=" + email + ", phonenumber=" + phonenumber + ", address="
				+ address + ", description=" + description + ", favorite=" + favorite + ", websitelink=" + websitelink
				+ ", linkedinlink=" + linkedinlink + ", profileimage=" + contactimage + "]";
	}
	
	

}
