package com.livechain.pid.exception;

public class CommonException extends Exception implements
		StandException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1383259470466966713L;
	protected String code;
    public void setCode(String code) {
		this.code = code;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	protected String msg;
    public CommonException()
    {
    	this.msg="no message";
    }
    public CommonException(String message)
    {
    	this.msg=message;
    }
	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return code;
	}
    
	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return this.msg;
	}

}
