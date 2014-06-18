package com.livechain.pid.util;
/**
 * 身份证处理器
 * 
 * @author alenzhai 2013-06-03
 *
 */
public class IdCardUtil {
	/**
	 * 将15位身份证转换成18位身份证
	 * @param idCard
	 * @return
	 */
   public static String old2New(String idCard)
   {
	   if(idCard.length()==15)
	   {
	    int[] arrint=new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
		char[] arrch= new char[]{'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
		int ntmp=0,i=0;
		idCard=idCard.substring(0,6)+"19"+idCard.substring(6);
		
		for(;i<idCard.length();i++)
		{
			int v=Integer.valueOf(idCard.substring(i, i+1)).intValue();
			ntmp+=v*arrint[i];
		}
		idCard+=arrch[ntmp%11];
		return idCard;
	   }else
	   {
		   return null;
	   }
   }
}
