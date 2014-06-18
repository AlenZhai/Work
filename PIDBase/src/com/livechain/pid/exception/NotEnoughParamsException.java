package com.livechain.pid.exception;

public class NotEnoughParamsException extends CommonException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7922589853451725600L;
	public NotEnoughParamsException(){
		this.code="params is not enough";
	}
	public NotEnoughParamsException(String msg){
		super(msg);		
	}

}
