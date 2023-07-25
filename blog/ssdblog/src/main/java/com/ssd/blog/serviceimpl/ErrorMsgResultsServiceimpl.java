package com.ssd.blog.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ErrorMsgResultsServiceimpl {
	
public ResponseEntity<Map<String, String>> errorMsgResultMap(BindingResult errorResult){
	Map<String , String> errorResultMap=new HashMap<>();
	for (FieldError errors : errorResult.getFieldErrors()) {
		errorResultMap.put(errors.getField(), errors.getDefaultMessage());
	}
	return new ResponseEntity<Map<String,String>> (errorResultMap,HttpStatus.BAD_REQUEST);
	
}
}
