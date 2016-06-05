package com.driverdata.chenxi.enums;

/**
 * 类FileType.java的实现描述：TODO 类实现描述
 * 
 * @author lmc 2015年6月9日 下午8:09:50
 */
public enum RoleType {
	/**
	 * 普通融资用户
	 */
	ROLE_USER(1), ROLE_ADMIN(2), ROLE_APPROVAL(3), ROLE_INVESTOR(4);
	private Integer value;

	private RoleType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
}
