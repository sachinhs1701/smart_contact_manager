package com.scm.scm10.entity;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.Builder;


@Entity(name="user")
@Table(name="users")

public class User implements UserDetails {
    @Id
	private String userid;
    @Column(name="user_name",nullable=false)
	private String name;
    @Column(unique= true,nullable=false)
	private String email;
    @Column(nullable=false)
	private String password;
    @Column(length=1000)
	private String about;
    @Column(length=1000)
	private String profilepic;
	private String phonenumber;
	
	private boolean enabled=false;
	private boolean emailvarified=false;
	private boolean phoneverified =false;
	@Enumerated(EnumType.STRING)
	private providers provider=providers.SELF;
	private String provideruserid;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(String userid, String name, String email, String password, String about, String profilepic,
			String phonenumber, boolean enabled, boolean emailvarified, boolean phoneverified, providers provider,
			String provideruserid, List<Contacts> contacts) {
		super();
		this.userid = userid;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.profilepic = profilepic;
		this.phonenumber = phonenumber;
		this.enabled = enabled;
		this.emailvarified = emailvarified;
		this.phoneverified = phoneverified;
		this.provider = provider;
		this.provideruserid = provideruserid;
		this.contacts = contacts;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
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


	public String getProfilepic() {
		return profilepic;
	}


	public void setProfilepic(String profilepic) {
		this.profilepic = profilepic;
	}


	public String getPhonenumber() {
		return phonenumber;
	}


	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public boolean isEmailvarified() {
		return emailvarified;
	}


	public void setEmailvarified(boolean emailvarified) {
		this.emailvarified = emailvarified;
	}


	public boolean isPhoneverified() {
		return phoneverified;
	}


	public void setPhoneverified(boolean phoneverified) {
		this.phoneverified = phoneverified;
	}


	public providers getProvider() {
		return provider;
	}


	public void setProvider(providers provider) {
		this.provider = provider;
	}


	public String getProvideruserid() {
		return provideruserid;
	}


	public void setProvideruserid(String provideruserid) {
		this.provideruserid = provideruserid;
	}


	public List<Contacts> getContacts() {
		return contacts;
	}


	public void setContacts(List<Contacts> contacts) {
		this.contacts = contacts;
	}

	

	@Override
	public String toString() {
		return "User [userid=" + userid + ", name=" + name + ", email=" + email + ", password=" + password + ", about="
				+ about + ", profilepic=" + profilepic + ", phonenumber=" + phonenumber + ", enabled=" + enabled
				+ ", emailvarified=" + emailvarified + ", phoneverified=" + phoneverified + ", provider=" + provider
				+ ", provideruserid=" + provideruserid + ", contacts=" + contacts + "]";
	}



	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private List<Contacts> contacts = new ArrayList<>();

	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> rolelist=new ArrayList();
	public List<String> getRoleList() {
	    return rolelist;
	}

	public void setRoleList(List<String> rolelist) {
	    this.rolelist = rolelist;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> roles=rolelist.stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList());
		return roles;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	
	
	
}
