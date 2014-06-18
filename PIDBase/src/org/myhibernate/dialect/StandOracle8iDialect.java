package org.myhibernate.dialect;

import org.hibernate.dialect.Oracle8iDialect;

public class StandOracle8iDialect extends Oracle8iDialect {
	public String getLimitString(String sql,int offset,int limit) {
		return SQLUtils.getLimitStringForOracle9i(sql, offset, limit);
	}
}
