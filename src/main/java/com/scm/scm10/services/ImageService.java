package com.scm.scm10.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	String uploadimage(MultipartFile contactimage,String filename);
	String getUrlFomPublicId(String publicid);
}
