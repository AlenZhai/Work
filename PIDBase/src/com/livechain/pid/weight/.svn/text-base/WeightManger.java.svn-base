package com.livechain.pid.weight;

import java.util.List;
import java.util.Map;

import javax.ws.rs.DELETE;

import org.aspectj.lang.annotation.DeclareAnnotation;
import org.json.JSONObject;

import com.livechain.pid.model.Field;
import com.livechain.pid.model.WeightResult;

/**
 * 打分器
 * @author alenzhai 2013-05-16
 *
 */
public interface WeightManger {
	/**
	 * 根据打分机制得出两个记录相似度的打分
	 * @param data 输入数据
	 * @param srcdata 查询出的数据
	 * @return 两个数据的相似分值
	 */
    public float getWeight(JSONObject data,JSONObject srcdata);
    /**
     * 根据打分机制从srclist中找到
     * 与data得分最高的记录
     * @param data 输入数据
     * @param srclist 查询出的数据<多条>
     * @return 相似度最高的记录<srclist中的记录>
     */
    public WeightResult getMaxWeightObj(JSONObject data,List<JSONObject> srclist);
    /**
     * 重新加载字段权重
     */
    public  void refresh();
    /**
     * 初始化
     */
    public  void init();
    /**
     * 获取 上限值
     * @return
     */
    public float getMax();
    /**
     * 获取 下限值
     * @return
     */
    public float getMin();
    /**
     * 获取 唯一判断字段
     * 唯一判断字段 就是 只要这个字段 的值对应上了就判定为同一个人
     * @return
     */
    @DELETE
    public Map<String, Field> getUniquefields();
    /**
     * 获取 配置了权重 的普通字段和权重值
     * @return
     */
    public Map<String, Field> getFields();
}
