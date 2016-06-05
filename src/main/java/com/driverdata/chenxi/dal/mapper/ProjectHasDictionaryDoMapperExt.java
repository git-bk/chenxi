package com.driverdata.chenxi.dal.mapper;

import javax.annotation.Resource;

@Resource
public interface ProjectHasDictionaryDoMapperExt extends ProjectHasDictionaryDoMapper {

    /**
     * 通过项目ID与数据字典类型删除关联关系
     * 
     * @param projectHasDictionaryDo
     */
    public void deleteByProjectIdAndDicKey(ProjectHasDictionaryDo projectHasDictionaryDo);
}
