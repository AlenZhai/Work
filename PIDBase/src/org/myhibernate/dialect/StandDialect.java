package org.myhibernate.dialect;
/**
 * ��ȡ��ҳSQLͳһ�ӿ�
 * @author Alen
 *
 */
public interface StandDialect {
	//
	public  String getLimitString(String query, int offset, int limit);	
}
