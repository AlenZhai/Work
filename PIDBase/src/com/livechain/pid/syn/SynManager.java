package com.livechain.pid.syn;
/**
 * 同步管理器
 * @author alenzhai 2013-05-16
 *
 */
public interface SynManager {
	/**
	 * 开启同步
	 */
     public void startSyn();
     /**
      * 中止同步
      * 注意 在中止同步时一定要确保正在同步的数据处理完毕
      */
     public void endSyn();
}
