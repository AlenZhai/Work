package org.spring.converter;

public class ConverUtils {
   public static void handleCallBack(CallBack call,String callback)
   {
	   if(null != callback)
		{
			int index=callback.indexOf("(");
			if(index>0)
			{
			callback=callback.substring(0,index);
			}
			call.setCallback(callback);
		}
   }
}
