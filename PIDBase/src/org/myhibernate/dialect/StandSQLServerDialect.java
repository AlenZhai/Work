package org.myhibernate.dialect;

import org.hibernate.dialect.SQLServerDialect;

public class StandSQLServerDialect extends SQLServerDialect {
	@Override
    public String getLimitString(String querySelect, int offset, int limit) {
		return SQLUtils.getLimitStringForMSSQL(querySelect, offset, limit);
	}
}
