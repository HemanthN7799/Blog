package com.ssd.blog.Exceptions;

import java.util.Date;

public class ExceptionDetails {
	
	private Date timeStamp;
	private String field;
	private String messgade;
	public ExceptionDetails(Date timeStamp, String field, String messgade) {
		super();
		this.timeStamp = timeStamp;
		this.field = field;
		this.messgade = messgade;
	}
	public ExceptionDetails() {
		super();
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getMessgade() {
		return messgade;
	}
	public void setMessgade(String messgade) {
		this.messgade = messgade;
	}
	
	

}
