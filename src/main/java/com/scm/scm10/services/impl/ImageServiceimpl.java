package com.scm.scm10.services.impl;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.scm10.services.ImageService;

@Service
public class ImageServiceimpl implements ImageService{

	@Autowired
	private Cloudinary cloudinary;
	@Override
	public String uploadimage(MultipartFile contactimage,String filename) {
		
		
		
		try {
			byte[] data=new byte[contactimage.getInputStream().available()];
			contactimage.getInputStream().read(data);
			cloudinary.uploader().upload(data,ObjectUtils.asMap("public_id",filename ));
			return this.getUrlFomPublicId(filename);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return null;
		}
		
		

	}
	@Override
	public String getUrlFomPublicId(String publicid) {
		return cloudinary.url()
				.transformation(new Transformation<>()
						.width(AppConstants.CONTACT_IMAGE_WIDTH)
						.height(AppConstants.CONTACT_IMAGE_HEIGHT)
						.crop(AppConstants.CONTACT_IMAGE_CROP))
				.generate(publicid);
	}

}
