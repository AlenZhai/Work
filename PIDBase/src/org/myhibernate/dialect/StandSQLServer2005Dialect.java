package org.myhibernate.dialect;

import org.hibernate.dialect.SQLServer2005Dialect;

public class StandSQLServer2005Dialect extends SQLServer2005Dialect {
	@Override
    public String getLimitString(String querySelect, int offset, int limit) {
	   return SQLUtils.getLimitStringForMSSQL(querySelect, offset, limit);
	}
}
