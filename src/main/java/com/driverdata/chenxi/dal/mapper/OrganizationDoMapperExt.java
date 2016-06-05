package com.driverdata.chenxi.dal.mapper;

import java.util.List;

import javax.annotation.Resource;

@Resource
public interface OrganizationDoMapperExt extends OrganizationDoMapper {

    /**
     * 查询机构列表
     * 
     * @param organDto 机构参数
     * @return List
     */
    public List<OrganizationDo> searchOrgans(OrganDto organDto);

    /**
     * 查询机构列表总数
     * 
     * @param queryOrganExtDo 搜索参数
     * @return Integer
     */
    public Integer searchOrgansPageCount(QueryOrganExtDo queryOrganExtDo);

    /**
     * 分页查询机构列表
     * 
     * @param queryOrganExtDo 搜索参数
     * @return list
     */
    public List<OrganizationDo> searchOrgansPage(QueryOrganExtDo queryOrganExtDo);
}
