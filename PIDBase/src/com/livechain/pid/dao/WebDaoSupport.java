package com.livechain.pid.dao;

public interface WebDaoSupport extends DaoSupport {
	/**
	 * 计算总数据
	 */
	public void getCount(Object params, Object rtn);
	public void search(Object params, Object rtn);
	public void realDel(Object params,Object rtn);
	public void searchlog(Object params, Object rtn);
	public boolean updateByCondition(Object params,Object rtn);
	public boolean changeStatus(Object params,Object rtn);
}
