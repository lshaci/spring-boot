package com.lshaci.exception;

/**
 * Excel表格处理相关的异常
 * 
 * @author lshaci
 *
 */
public class ExcelHanlderException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;

	public ExcelHanlderException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
