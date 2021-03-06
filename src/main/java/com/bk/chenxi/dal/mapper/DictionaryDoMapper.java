package com.bk.chenxi.dal.mapper;

import com.bk.chenxi.dal.model.DictionaryDo;
import com.bk.chenxi.dal.model.DictionaryDoExample;
import java.util.List;

public interface DictionaryDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dictionary
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    int countByExample(DictionaryDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dictionary
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    int deleteByPrimaryKey(DictionaryDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dictionary
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    int insertSelective(DictionaryDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dictionary
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    List<DictionaryDo> selectByExample(DictionaryDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dictionary
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    DictionaryDo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dictionary
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    int updateByPrimaryKeySelective(DictionaryDo record);
}