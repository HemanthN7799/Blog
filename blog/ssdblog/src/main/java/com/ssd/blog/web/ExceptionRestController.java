package com.ssd.blog.web;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ssd.blog.Exceptions.ExceptionDetails;
import com.ssd.blog.Exceptions.TitleAlreadyExistedException;

@ControllerAdvice
public class ExceptionRestController {
	
	@ExceptionHandler(value =TitleAlreadyExistedException.class)
	public ResponseEntity<ExceptionDetails> titleAlreadyExistedExceptionMessage(TitleAlreadyExistedException exception){
		ExceptionDetails exceptionDetails=new ExceptionDetails(new Date(),exception.getTitle(),exception.getExcepMsg());
		return new ResponseEntity<ExceptionDetails>(exceptionDetails,HttpStatus.ALREADY_REPORTED);
				
		
	}

}
