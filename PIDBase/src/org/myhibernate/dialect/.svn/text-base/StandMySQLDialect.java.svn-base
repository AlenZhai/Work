package org.myhibernate.dialect;


import org.hibernate.dialect.MySQLDialect;

public class StandMySQLDialect extends MySQLDialect {
	@Override
	public String getLimitString(String sql,int offset, int limit) {
		return SQLUtils.getLimitStringForMySQL(sql, offset, limit);
	}
}
