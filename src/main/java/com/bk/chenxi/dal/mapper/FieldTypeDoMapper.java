package com.bk.chenxi.dal.mapper;

import com.bk.chenxi.dal.model.FieldTypeDo;
import com.bk.chenxi.dal.model.FieldTypeDoExample;
import java.util.List;

public interface FieldTypeDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table field_type
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    int countByExample(FieldTypeDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table field_type
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    int deleteByPrimaryKey(FieldTypeDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table field_type
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    int insertSelective(FieldTypeDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table field_type
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    List<FieldTypeDo> selectByExample(FieldTypeDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table field_type
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    FieldTypeDo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table field_type
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    int updateByPrimaryKeySelective(FieldTypeDo record);
}