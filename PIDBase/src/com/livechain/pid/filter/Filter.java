package com.livechain.pid.filter;
/**
 * 根据一定的规则对数据进行过虑
 * @author alenzhai 2013-05-16
 *
 */
public interface Filter {
	/**
	 * 
	 * @param filterdata 需要过虑的数据
	 * @param rule 过虑规则
	 */
   public void execute(Object filterdata,Object rule);
   /**
    * 
    * @param filterdata 需要过虑的数据
    */
   public void execute(Object filterdata);
}
