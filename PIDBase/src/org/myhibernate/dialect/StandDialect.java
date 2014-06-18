package org.myhibernate.dialect;
/**
 * 获取分页SQL统一接口
 * @author Alen
 *
 */
public interface StandDialect {
	//
	public  String getLimitString(String query, int offset, int limit);	
}
