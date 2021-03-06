package com.bk.chenxi.dal.mapper;

import com.bk.chenxi.dal.model.InstanceDependencyDo;
import com.bk.chenxi.dal.model.InstanceDependencyDoExample;
import java.util.List;

public interface InstanceDependencyDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table i_dependency
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    int countByExample(InstanceDependencyDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table i_dependency
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    int deleteByPrimaryKey(InstanceDependencyDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table i_dependency
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    int insertSelective(InstanceDependencyDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table i_dependency
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    List<InstanceDependencyDo> selectByExample(InstanceDependencyDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table i_dependency
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    InstanceDependencyDo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table i_dependency
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    int updateByPrimaryKeySelective(InstanceDependencyDo record);
}