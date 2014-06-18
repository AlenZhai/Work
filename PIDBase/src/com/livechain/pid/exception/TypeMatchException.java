package com.livechain.pid.exception;

public class TypeMatchException extends CommonException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2872075942822350509L;
	public TypeMatchException(){
		this.code="type not match";
	}
	public TypeMatchException(String msg){
		super(msg);
	}

}
