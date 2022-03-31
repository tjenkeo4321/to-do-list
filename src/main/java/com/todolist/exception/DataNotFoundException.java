package com.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException  extends BadRequestException {

	private static final long serialVersionUID = 1L;

	public DataNotFoundException(String message) {
		super(message);
	}

	public DataNotFoundException( String code, String field,Object id) {
		super(code, field,id);
	}
	
	public DataNotFoundException(String ObjectName, String code, String field,Object id) {
		super(ObjectName,code, field,id);
	}
	
}