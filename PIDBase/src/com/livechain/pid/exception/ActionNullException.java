package com.livechain.pid.exception;

public class ActionNullException extends CommonException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8336867124455237222L;

	public ActionNullException(String message) {
		super(message);
		
		// TODO Auto-generated constructor stub
	}
    public ActionNullException() {
		// TODO Auto-generated constructor stub
    	this.code="action is null";
	}

}

