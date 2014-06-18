package org.mybatis.sqlinterceptor;

import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.FastResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.session.RowBounds;




@Intercepts( {@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})})
public class StandResultSetHandlerInterceptor implements Interceptor {
	
	public Object intercept(Invocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		   FastResultSetHandler resultSet = (FastResultSetHandler)invocation.getTarget();
	        
	         RowBounds rowBounds = (RowBounds)ReflectUtil.getFieldValue(resultSet,
	                 "rowBounds");
		if (rowBounds.getLimit() > 0 && rowBounds.getLimit() < RowBounds.NO_ROW_LIMIT)
        {
            ReflectUtil.setFieldValue(resultSet, "rowBounds", new RowBounds());
        }
        return invocation.proceed();       
	}

	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub

	}

}
