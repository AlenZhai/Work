package org.myhibernate.dialect;

import org.hibernate.dialect.Oracle10gDialect;

public class StandOracle10gDialect extends Oracle10gDialect   {
	 @Override
	public String getLimitString(String sql,int offset,int limit) {
		return SQLUtils.getLimitStringForOracle9i(sql, offset, limit);
	}
}
