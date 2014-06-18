package com.livechain.pid.exception;

public class NoDataException extends CommonException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2366858553295906461L;
	public NoDataException(){
		this.code="no data ";
	}
	public NoDataException(String message) {
		super(message);
		
		// TODO Auto-generated constructor stub
	}

	
}
