package com.driverdata.chenxi.dal.mapper;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface InstanceDependDoMapperExt extends InstanceDependDoMapper {
    List<Integer> findParent(Integer childId);

    List<Integer> findChildrenInstanceIdList(Integer parentId);
}