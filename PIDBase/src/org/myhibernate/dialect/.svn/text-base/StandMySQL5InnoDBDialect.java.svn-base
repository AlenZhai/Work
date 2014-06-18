package org.myhibernate.dialect;

import org.hibernate.dialect.MySQL5InnoDBDialect;

public class StandMySQL5InnoDBDialect extends MySQL5InnoDBDialect{
	 @Override
	public String getLimitString(String sql,int offset, int limit) {
		return SQLUtils.getLimitStringForMySQL(sql, offset, limit);
	}
}
