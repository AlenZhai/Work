package org.myhibernate.dialect;

import org.hibernate.dialect.OracleDialect;
@Deprecated
public class StandOracleDialect extends OracleDialect {
	 @Override
	public String getLimitString(String sql,int offset,int limit) {
		return SQLUtils.getLimitStringForOracle9i(sql, offset, limit);
	}
}
