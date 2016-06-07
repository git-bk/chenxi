package com.bk.chenxi.dal.mapper;

import java.util.List;

import javax.annotation.Resource;

import com.bk.chenxi.dal.model.RoleDo;
import com.bk.chenxi.dal.model.UserDo;

@Resource
public interface UserDoMapperExt extends UserDoMapper {

	List<RoleDo> selectRolesByAccount(String account);

	public int updateByPrimaryKeySelectiveExt(UserDo record);
}
