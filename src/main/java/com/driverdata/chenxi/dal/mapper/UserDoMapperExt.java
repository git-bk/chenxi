package com.driverdata.chenxi.dal.mapper;

import java.util.List;

import javax.annotation.Resource;

import com.driverdata.chenxi.dal.model.RoleDo;
import com.driverdata.chenxi.dal.model.UserDo;

@Resource
public interface UserDoMapperExt extends UserDoMapper {

	List<RoleDo> selectRolesByAccount(String account);

	public List<ProjectDo> findProjectsByUserId(Integer userId);

	public int updateByPrimaryKeySelectiveExt(UserDo record);
}
