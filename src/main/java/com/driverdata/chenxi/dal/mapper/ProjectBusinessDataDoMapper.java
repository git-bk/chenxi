package com.driverdata.chenxi.dal.mapper;

import java.util.List;

public interface ProjectBusinessDataDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_business_data
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    int countByExample(ProjectBusinessDataDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_business_data
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    int deleteByPrimaryKey(ProjectBusinessDataDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_business_data
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    int insertSelective(ProjectBusinessDataDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_business_data
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    List<ProjectBusinessDataDo> selectByExample(ProjectBusinessDataDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_business_data
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    ProjectBusinessDataDo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_business_data
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(ProjectBusinessDataDo record);
}