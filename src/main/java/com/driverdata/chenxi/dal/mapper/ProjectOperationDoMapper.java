package com.driverdata.chenxi.dal.mapper;

import java.util.List;

public interface ProjectOperationDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_operation
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    int countByExample(ProjectOperationDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_operation
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    int deleteByPrimaryKey(ProjectOperationDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_operation
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    int insertSelective(ProjectOperationDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_operation
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    List<ProjectOperationDo> selectByExample(ProjectOperationDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_operation
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    ProjectOperationDo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_operation
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(ProjectOperationDo record);
}