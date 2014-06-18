package org.myhibernate.dialect;

import org.hibernate.dialect.MySQL5Dialect;

public class StandMySQL5Dialect extends MySQL5Dialect{
     @Override
	public String getLimitString(String sql,int offset, int limit) {
		return SQLUtils.getLimitStringForMySQL(sql, offset, limit);
	}
}
