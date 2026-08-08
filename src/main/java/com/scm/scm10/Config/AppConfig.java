package com.scm.scm10.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class AppConfig {

	@Value("${cloudinary.cloud.name}")
	private String cloudname;
	@Value("${cloudinary.api.key}")
	private String apikey;
	@Value("${cloudinary.api.seceret}")
	private String apisecert;
	@Bean
	public Cloudinary cloudniary()
	{
		System.out.println(cloudname+" "+apikey+" "+apisecert);
		return new Cloudinary(ObjectUtils.asMap("cloud_name",cloudname,
				             					"api_key",apikey,
				             					"api_secret",apisecert));
		
		
	}
}
