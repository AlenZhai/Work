package com.livechain.pid.exception;

public class WeightException extends CommonException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8956626138109382362L;
    public WeightException(){
    	this.code="weight exception";
    }
    public WeightException(String msg){
    	super(msg);
    }
	

}
