package com.driverdata.chenxi.dal.mapper;

import java.util.List;

import javax.annotation.Resource;

@Resource
public interface ProjectTeamMemberDoMapperExt extends ProjectTeamMemberDoMapper {

    public List<ProjectTeamMemberDo> selectTeamMembersByDo(ProjectTeamMemberDo teamMemberDo);

    public int updateTeamMemberById(ProjectTeamMemberDo teamMemberDo);
}
