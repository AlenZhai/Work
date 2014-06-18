package org.myhibernate.dialect;

import org.hibernate.dialect.Oracle9Dialect;
@Deprecated
public class StandOracle9Dialect extends Oracle9Dialect  {
	 @Override
	public String getLimitString(String sql,int offset,int limit) {
		return SQLUtils.getLimitStringForOracle9i(sql, offset, limit);
	}
}
