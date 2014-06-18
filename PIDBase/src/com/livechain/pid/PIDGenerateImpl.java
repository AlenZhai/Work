package com.livechain.pid;

import java.util.UUID;
/**
 * PID生成器
 * @author alenzhai 2013-05-22
 *
 */
public class PIDGenerateImpl implements PIDGenerate {
    /**
     * 生成一个PID
     */
	@Override
	public String getPID() {
		// TODO Auto-generated method stub
		
		return UUID.randomUUID().toString().replace("-", "");
	}

}
