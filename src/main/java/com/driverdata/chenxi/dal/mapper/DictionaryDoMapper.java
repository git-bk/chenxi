package com.driverdata.chenxi.dal.mapper;

import com.driverdata.chenxi.dal.model.DictionaryDo;
import com.driverdata.chenxi.dal.model.DictionaryDoExample;
import java.util.List;

public interface DictionaryDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dictionary
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    int countByExample(DictionaryDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dictionary
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    int deleteByPrimaryKey(DictionaryDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dictionary
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    int insertSelective(DictionaryDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dictionary
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    List<DictionaryDo> selectByExample(DictionaryDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dictionary
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    DictionaryDo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dictionary
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(DictionaryDo record);
}