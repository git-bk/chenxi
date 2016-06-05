package com.driverdata.chenxi.dal.mapper;

import javax.annotation.Resource;

@Resource
public interface ProjectOperationDoMapperExt extends ProjectOperationDoMapper {

    int findOperateCount(ProjectOperationDo record);

    ProjectOperationDo findOperateByProId(ProjectOperationDo record);
}
