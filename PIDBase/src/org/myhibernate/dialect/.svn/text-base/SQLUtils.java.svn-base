package org.myhibernate.dialect;

public class SQLUtils {
	public static String getLimitStringForMySQL(String sql,int offset, int limit) {
		boolean hasOffset=offset>0;
		return sql + (hasOffset ? " limit "+offset+","+limit : " limit "+limit);
	}
	public static String getLimitStringForOracle9i(String sql,int offset, int limit) {
		boolean hasOffset=offset>0;
		sql = sql.trim();
		String forUpdateClause = null;
		boolean isForUpdate = false;
		final int forUpdateIndex = sql.toLowerCase().lastIndexOf( "for update") ;
		if ( forUpdateIndex > -1 ) {
			// save 'for update ...' and then remove it
			forUpdateClause = sql.substring( forUpdateIndex );
			sql = sql.substring( 0, forUpdateIndex-1 );
			isForUpdate = true;
		}

		StringBuilder pagingSelect = new StringBuilder( sql.length() + 100 );
		if (hasOffset) {
			pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		}
		else {
			pagingSelect.append("select * from ( ");
		}
		pagingSelect.append(sql);
		if (hasOffset) {
			pagingSelect.append(" ) row_ where rownum <= "+(offset+limit)+") where rownum_ > "+offset);
		}
		else {
			pagingSelect.append(" ) where rownum <= "+limit);
		}

		if ( isForUpdate ) {
			pagingSelect.append( " " );
			pagingSelect.append( forUpdateClause );
		}

		return pagingSelect.toString();		
	}
	public static String getLimitStringForMSSQL(String sql,int offset, int limit) {
          
		int afterselect=getAfterSelectPoint(sql);
		int beforefrom=getBeforeFromPoint(sql);
		int orderby=sql.toLowerCase().lastIndexOf("order");
		String orderbycloumn=null;
		if(orderby>-1)
		{
			String orderByStr="";
			String sorderby=sql.substring(orderby);
			int orderby2=sorderby.lastIndexOf("by");
			orderbycloumn=sorderby.substring(orderby2+2);
			int m=orderbycloumn.split(",").length;
			if(m>0){
				for(int i=0;i<orderbycloumn.split(",").length;i++){
					
					if(orderbycloumn.split(",")[i].indexOf(".")>-1){
						if(orderByStr==""){
						    orderByStr= "row_"+orderbycloumn.split(",")[i].substring(orderbycloumn.split(",")[i].lastIndexOf("."),orderbycloumn.split(",")[i].length());
						}else{
							orderByStr= orderByStr+","+"row_"+orderbycloumn.split(",")[i].substring(orderbycloumn.split(",")[i].lastIndexOf("."),orderbycloumn.split(",")[i].length());
						}
						
					}else{
						if(orderByStr==""){
						    orderByStr= orderbycloumn.split(",")[i];
						}else{
							orderByStr= orderByStr+","+orderbycloumn.split(",")[i];
						}
					}
				}
			}
			orderbycloumn="";
			orderbycloumn=orderByStr;
		}

		String column=sql.substring(afterselect,beforefrom);
		int colindex=column.indexOf(",");
		if(colindex>-1)
		{
			column=column.substring(0, colindex);
		}
		if(column.indexOf(" as ")>-1){
			column="row_."+column.substring(column.lastIndexOf(" as ")+4,column.length());
		}else if(column.indexOf(".")>-1){
			column="row_"+column.substring(column.lastIndexOf("."),column.length());
		}
		boolean hasOffset=offset>0;
		StringBuilder pagingSelect = new StringBuilder( sql.length() + 100 );
		if (hasOffset) {
			//有order by 的情况
			if(orderby>-1&&null!=orderbycloumn&&!orderbycloumn.equals(""))
			{  
				pagingSelect.append("select * from ( select  row_.*, ROW_NUMBER()over(order by "+orderbycloumn+") rownum_ from ( ");
			}else//没有order by 的情况
			{
				pagingSelect.append("select * from ( select  row_.*, ROW_NUMBER()over(order by "+column+") rownum_ from ( ");
		    }
			}
		else {
			pagingSelect.append("select top "+limit+" * from ( ");
		}
		pagingSelect.append(sql.substring(0,afterselect)).append(" top "+(offset+limit)).append(sql.substring(afterselect));
		if (hasOffset) {
			pagingSelect.append(" ) row_ ) r where rownum_ > "+offset);
		}
		else {
			pagingSelect.append(" ) r");
		}

		
		
      return pagingSelect.toString();
	}
	private static int getAfterSelectPoint(String sql) {
		int selectIndex = sql.toLowerCase().indexOf( "select" );
		int distinctIndex=sql.toLowerCase().indexOf("distinct");		
		return selectIndex + ( distinctIndex >0 ? 8+distinctIndex : 6 );
	}
	private static int getBeforeFromPoint(String sql) {
				
		return sql.toLowerCase().indexOf( "from" );
	}
	public static void main(String[] args){
		String sql="SELECT [TYPECODE],[TYPENAME],[EXPLICATE],[STATUS] FROM [EHR].[dbo].[VA000401] where status='0'";
		/*int afterselect=getAfterSelectPoint(sql);
		int beforefrom=getBeforeFromPoint(sql);
		String column=sql.substring(afterselect,beforefrom);
		System.out.println(sql.toLowerCase().indexOf( "distinct" ));
		System.out.println(afterselect);
		System.out.println(column);*/
		System.out.println(getLimitStringForMSSQL(sql,3,4));
	}
}
