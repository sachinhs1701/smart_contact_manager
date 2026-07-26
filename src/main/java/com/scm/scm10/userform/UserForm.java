package com.scm.scm10.userform;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserForm {
	
	@NotBlank(message = "Username is required")
	@Size(min=3,message="min 3 charaters required")
	private String name;
	@Email(message = "Invalid email address")
	@NotBlank(message="Email is required")
	private String email;
	@NotBlank(message ="Password is required")
	@Size(min=6,message="min 6 charaters is required")
	private String password;
	@NotBlank(message="about is required")
	private String about;
	@Size(min=8,max=12,message="Invalid phone number")
	private String phoneNumber;
	private String profilepice;
	
	public String getProfilepice() {
		return profilepice;
	}
	public void setProfilepice(String profilepice) {
		this.profilepice = profilepice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public UserForm(String name, String email, String password, String about, String phoneNumber) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.phoneNumber = phoneNumber;
	}
	public UserForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserForm [name=" + name + ", email=" + email + ", password=" + password + ", about=" + about
				+ ", phonenumber=" + phoneNumber + "]";
	}
	
		
}
