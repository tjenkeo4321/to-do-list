package com.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

	private String objectName;
	private String code;
	private String field;
	private Object id;
	
	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
		super(message);
	}
	
	public BadRequestException(String code, String field) {
		super();
		this.code = code;
		this.field = field;
	}
	
	public BadRequestException(String code, String field,Object id) {
		super();
		this.code = code;
		this.field = field;
		this.id=id;
	}

	public BadRequestException(String objectName ,String code, String field,Object id) {
		super();
		this.objectName = objectName;
		this.code = code;
		this.field = field;
		this.id=id;
	}
	
	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}
	
	
}