package org.myhibernate.dialect;

import org.hibernate.dialect.SQLServer2008Dialect;

public class StandSQLServer2008Dialect extends SQLServer2008Dialect {
	@Override
    public String getLimitString(String querySelect, int offset, int limit) {
		return SQLUtils.getLimitStringForMSSQL(querySelect, offset, limit);
	}
}
