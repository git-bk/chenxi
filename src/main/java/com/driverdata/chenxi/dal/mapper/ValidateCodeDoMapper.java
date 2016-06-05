package com.driverdata.chenxi.dal.mapper;

import com.driverdata.chenxi.dal.model.ValidateCodeDo;
import com.driverdata.chenxi.dal.model.ValidateCodeDoExample;
import java.util.List;

public interface ValidateCodeDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table validate_code
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    int countByExample(ValidateCodeDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table validate_code
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    int deleteByPrimaryKey(ValidateCodeDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table validate_code
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    int insertSelective(ValidateCodeDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table validate_code
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    List<ValidateCodeDo> selectByExample(ValidateCodeDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table validate_code
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    ValidateCodeDo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table validate_code
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(ValidateCodeDo record);
}