package org.myhibernate.dialect;

import org.hibernate.dialect.MySQLMyISAMDialect;

public class StandMySQLMyISAMDialect extends MySQLMyISAMDialect  {
	 @Override
	public String getLimitString(String sql,int offset, int limit) {
		return SQLUtils.getLimitStringForMySQL(sql, offset, limit);
	}
}
