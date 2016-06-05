package com.driverdata.chenxi.security;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.driverdata.chenxi.dal.mapper.UserDoMapperExt;
import com.driverdata.chenxi.dal.model.RoleDo;
import com.driverdata.chenxi.dal.model.UserDo;
import com.driverdata.chenxi.dal.model.UserDoExample;
import com.driverdata.chenxi.model.User;

public class SecurityManagerBoImpl implements UserDetailsService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserDoMapperExt userDoMapperExt;

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserDoExample example = new UserDoExample();
		example.createCriteria().andAccountEqualTo(userName).andIsDeletedEqualTo("n");
		List<UserDo> users = userDoMapperExt.selectByExample(example);
		if (users.isEmpty()) {
			throw new UsernameNotFoundException("User " + userName + "has no GrantedAuthority");
		}
		User user = new User();
		try {
			BeanUtils.copyProperties(user, users.get(0));
		} catch (IllegalAccessException e) {
			logger.error("", e);
		} catch (InvocationTargetException e) {
			logger.error("", e);
		}
		List<RoleDo> roles = userDoMapperExt.selectRolesByAccount(user.getAccount());

		user.setRoles(roles);

		return user;
	}

}
