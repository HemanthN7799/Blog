package com.ssd.blog.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.ALREADY_REPORTED)
public class TitleAlreadyExistedException extends RuntimeException{
	
	private String title;
	private String ExcepMsg;
	
	public TitleAlreadyExistedException() {
		super();
	}
	public TitleAlreadyExistedException(String title, String excepMsg) {
		super();
		this.title = title;
		ExcepMsg = excepMsg;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getExcepMsg() {
		return ExcepMsg;
	}
	public void setExcepMsg(String excepMsg) {
		ExcepMsg = excepMsg;
	}
	
	

}
