package com.scm.scm10.validators;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<ValidFile,MultipartFile> {

	private static final long MAX_FILE_SIZE=1024*1024*1;// 5MB file size
	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
		if(file==null||file.isEmpty())
		{
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("file cannot be empty").addConstraintViolation();
			return false;
		}
		
		if(file.getSize()>MAX_FILE_SIZE)
		{
			context.disableDefaultConstraintViolation();
			System.out.println("file is large");
			context.buildConstraintViolationWithTemplate("file size should be less than 2MB").addConstraintViolation();
			return false;
		}
		return true;
	}

}
