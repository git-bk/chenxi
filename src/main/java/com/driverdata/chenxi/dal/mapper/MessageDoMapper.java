package com.driverdata.chenxi.dal.mapper;

import com.driverdata.chenxi.dal.model.MessageDo;
import com.driverdata.chenxi.dal.model.MessageDoExample;
import java.util.List;

public interface MessageDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated Wed Nov 18 09:55:56 GMT+08:00 2015
     */
    int countByExample(MessageDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated Wed Nov 18 09:55:56 GMT+08:00 2015
     */
    int deleteByPrimaryKey(MessageDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated Wed Nov 18 09:55:56 GMT+08:00 2015
     */
    int insertSelective(MessageDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated Wed Nov 18 09:55:56 GMT+08:00 2015
     */
    List<MessageDo> selectByExampleWithBLOBs(MessageDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated Wed Nov 18 09:55:56 GMT+08:00 2015
     */
    List<MessageDo> selectByExample(MessageDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated Wed Nov 18 09:55:56 GMT+08:00 2015
     */
    MessageDo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated Wed Nov 18 09:55:56 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(MessageDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
     *
     * @mbggenerated Wed Nov 18 09:55:56 GMT+08:00 2015
     */
    int updateByPrimaryKeyWithBLOBs(MessageDo record);
}