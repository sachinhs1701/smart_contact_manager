package com.scm.scm10.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
public class Contacts {
    @Id
	private String id;
	private String fullname;
	private String email;
	private String phonenumber;
	private String address;
	private String pricture;
	@Column(length = 1000)
	private String description;
	private Boolean favorite=false;
	private String websitelink;
	private String linkedinlink;
	private String cloudinarypublicid;
	@ManyToOne
	private User user;
	
	
}
