package org.myhibernate.dialect;

import org.hibernate.dialect.Oracle9iDialect;

public class StandOracle9iDialect extends Oracle9iDialect  {
	 @Override
	public String getLimitString(String sql,int offset,int limit) {
		return SQLUtils.getLimitStringForOracle9i(sql, offset, limit);
	}
}
