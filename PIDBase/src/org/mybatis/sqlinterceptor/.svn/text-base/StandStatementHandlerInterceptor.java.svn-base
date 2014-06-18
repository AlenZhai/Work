package org.mybatis.sqlinterceptor;

import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.plugin.Signature;
import org.hibernate.dialect.Dialect;


/**
 * ��ҳSQL�޸�9����
 * 1��9�� StatementHandler.class �ࣨ�ӿڣ� �� ��������ΪConnection.class ��prepare����
 * 2���ж�SQL�������Ƿ��з�ҳ����
 * 3�����޷�ҳ���� ���޸�SQLֱ��ִ��Mybatis�Լ���ʵ��
 * 4�����з�ҳ���� �޸�SQLΪ��Ӧ��ݿ�ķ�ҳSQL
 * 5�����޸ĺ��SQL����Mbatis�Լ���ʵ�ּ���ִ��
 * @author Alen
 *
 */
@SuppressWarnings("deprecation")
@Intercepts( {@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class StandStatementHandlerInterceptor implements Interceptor {
	private static String dialectClassName;	//��ݿⷽ���� ����������ɶ�Ӧ��ݵķ�ҳSQL
	private static final String DIALECT = "org.myhibernate.dialect.StandMySQL5Dialect";//��ָ��Ĭ��ΪMySQL
	@SuppressWarnings("deprecation")
	public Object intercept(Invocation invocation) throws Throwable {
		// TODO Auto-generated method stub	
		//��ȡ Mybatis�е�statement ����SQL�����ݺͲ������ص�������б���
		RoutingStatementHandler statement = (RoutingStatementHandler)invocation.getTarget();
		 PreparedStatementHandler handler = (PreparedStatementHandler)ReflectUtil.getFieldValue(statement,
         "delegate");
		 RowBounds rowBounds = (RowBounds)ReflectUtil.getFieldValue(handler,
         "rowBounds");
         //System.out.println("==============================");
         BoundSql boundSql = statement.getBoundSql();
         if (rowBounds.getLimit() > 0
                 && rowBounds.getLimit() < RowBounds.NO_ROW_LIMIT)
         {
				    //���зֲ����� ��SQL�����޸�    
				        	   //��ȡԭʼSQL
	             String sql = boundSql.getSql();	           	            
	             Dialect dialect=null;
	             if(dialectClassName!=null && !dialectClassName.equals(""))
	             {    
	            	 dialect= (Dialect)Class.forName(dialectClassName).newInstance(); 
	             }
	             else
	             {
	               dialect= (Dialect)Class.forName(DIALECT).newInstance(); 
	            	 
	             } 
	             //������õķ�������ɶ�Ӧ �ķ�ҳSQL
	             sql = dialect.getLimitString(sql,
	                     rowBounds.getOffset(),
	                    rowBounds.getLimit());
	             System.out.println(sql);
	             //���޸Ĺ��SQL������Mybatis
	            ReflectUtil.setFieldValue(boundSql, "sql", sql);
          }  
         //ִ��Mybatis�ķ���
         return invocation.proceed();		
	}
	
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		//ע��9����
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties p) {
		// TODO Auto-generated method stub
		//��ȡ����
		dialectClassName=p.getProperty("dialectClassName");
	}

}
