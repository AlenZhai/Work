package com.livechain.pid.dao;
/**
 * 1.将数据持久化到数据库或磁盘
 * 2.查询持久化过的数据
 * @author alenzhai 2013-05-16
 *
 */
public interface DaoSupport {
	/**
	 * 持久化数据
	 * @param params 需要持久化的数据
	 * @param rtn 返回处理结果
	 */
   public boolean saveInfo(Object params,Object rtn);
   /**
    * 查询持久化过的数据 查询条件为 or 关系
    * @param params 查询参数
    * @param rtn   
    */
   public void getInfo(Object params,Object rtn);
   /**
    * 查询持久化过的数据 查询条件为 and 关系
    * @param params
    * @param rtn
    */
   public void getInfoByAnd(Object params, Object rtn);
   /**
    * 更新数据
    * @param params 需要更新的数据
    * @param rtn  操作结果
    */
   public boolean updateInfo(Object params,Object rtn);
   /**
    * 判断记录是否存在
    * @param params
    * @param rtn
    * @return
    */
   public boolean isExsit(Object params,Object rtn);
   /**
    * 根据参数删除一条记录
    * 这里并不是真正的删除而是在isdel的值改为1
    * @param params
    * @param rtn
    * @return
    */
   public boolean delete(Object params,Object rtn);
   /**
    * 按照PID更新指定字段
    * @param pid 
    * @param field 指定字段名
    * @param obj   字段值
    * @return
    */
   public boolean updateByPid(String pid,String field,Object obj);
}
