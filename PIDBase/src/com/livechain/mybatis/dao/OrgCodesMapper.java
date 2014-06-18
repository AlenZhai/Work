package com.livechain.mybatis.dao;


import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.livechain.mybatis.model.OrgCodes;
import com.livechain.mybatis.model.OrgCodesExample;
import com.livechain.mybatis.model.OrgCodesKey;
@MyBatisRepository
public interface OrgCodesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table empi_orgcodes
     *
     * @mbggenerated Thu Aug 08 16:15:11 CST 2013
     */
    int countByExample(OrgCodesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table empi_orgcodes
     *
     * @mbggenerated Thu Aug 08 16:15:11 CST 2013
     */
    int deleteByExample(OrgCodesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table empi_orgcodes
     *
     * @mbggenerated Thu Aug 08 16:15:11 CST 2013
     */
    int deleteByPrimaryKey(OrgCodesKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table empi_orgcodes
     *
     * @mbggenerated Thu Aug 08 16:15:11 CST 2013
     */
    int insert(OrgCodes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table empi_orgcodes
     *
     * @mbggenerated Thu Aug 08 16:15:11 CST 2013
     */
    int insertSelective(OrgCodes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table empi_orgcodes
     *
     * @mbggenerated Thu Aug 08 16:15:11 CST 2013
     */
    List<OrgCodes> selectByExample(OrgCodesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table empi_orgcodes
     *
     * @mbggenerated Thu Aug 08 16:15:11 CST 2013
     */
    OrgCodes selectByPrimaryKey(OrgCodesKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table empi_orgcodes
     *
     * @mbggenerated Thu Aug 08 16:15:11 CST 2013
     */
    int updateByExampleSelective(@Param("record") OrgCodes record, @Param("example") OrgCodesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table empi_orgcodes
     *
     * @mbggenerated Thu Aug 08 16:15:11 CST 2013
     */
    int updateByExample(@Param("record") OrgCodes record, @Param("example") OrgCodesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table empi_orgcodes
     *
     * @mbggenerated Thu Aug 08 16:15:11 CST 2013
     */
    int updateByPrimaryKeySelective(OrgCodes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table empi_orgcodes
     *
     * @mbggenerated Thu Aug 08 16:15:11 CST 2013
     */
    int updateByPrimaryKey(OrgCodes record);
}