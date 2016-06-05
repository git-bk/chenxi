package com.driverdata.chenxi.dal.mapper;

import java.util.List;

import com.driverdata.chenxi.dal.model.DictionaryDo;

public interface ProjectDoMapperExt extends ProjectDoMapper {

    Integer searchProjectsPageCount(QueryProjectPoolExtDo queryProjectPoolExtDo);

    List<ProjectPoolRo> searchProjectsPage(QueryProjectPoolExtDo queryProjectPoolExtDo);

    /**
     * 计算已完善的数据字典字段百分比之和
     * 
     * @param valuList
     * @return
     */
    public int calculateInputWeightTotal(List<String> valuList);

    /**
     * 获取项目字段的总权重百分比
     * 
     * @return
     */
    public int getTotalProjectWeights();

    public List<DictionaryDo> searchFieldsByProjectId(Integer projectId);

}
