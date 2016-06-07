package com.bk.chenxi.biz;

import java.util.List;

import com.bk.chenxi.dal.model.UserDo;
import com.bk.chenxi.dto.UserDto;
import com.bk.chenxi.exception.ChanceValidateException;
import com.bk.chenxi.model.User;

public interface UserBo {

    /**
     * 注册
     * 
     * @param account
     * @param password
     * @param validateCode
     * @param type
     * @throws ChanceValidateException
     */
    public void registerUser(String account, String password, String validateCode, String type)
                                                                                               throws ChanceValidateException;

    /**
     * 改密
     * 
     * @param account
     * @param password
     * @param validateCode
     * @throws ChanceValidateException
     */
    public void ChancePassword(String account, String password, String validateCode) throws ChanceValidateException;

    /**
     * 用户授权
     * 
     * @param roleId
     * @param account
     * @throws ChanceValidateException
     */
    public void roleAuthorize(Integer roleId, String account) throws ChanceValidateException;

    /**
     * 发送手机验证码
     * 
     * @param mobile
     * @throws ChanceValidateException
     */
    public void sendMobileValidateCode(String mobile) throws ChanceValidateException;

    /**
     * 根据用户账号查询用户
     * 
     * @param account
     * @return
     */
    public UserDo findUserbyAccount(String account);

    /**
     * 查询当前登录用户
     * 
     * @return
     */
    public UserDo getLocalUser();

    /**
     * 编辑用户
     * 
     * @param User 用户对象
     * @return Integer
     * @throws ChanceValidateException
     */
    public Integer modifyUser(UserDto user) throws ChanceValidateException;

    /**
     * 查询用户
     * 
     * @param id 主键ID
     * @return User
     * @throws ChanceValidateException
     */
    public UserDto findUserById(Integer id) throws ChanceValidateException;

    /**
     * 查询用户列表
     * 
     * @param User 用户对象
     * @return List<User>
     * @throws ChanceValidateException
     */
    public List<User> findUsersByUser(User User) throws ChanceValidateException;

    /**
     * 创建新用户
     * 
     * @param User 用户对象
     * @return User
     * @throws ChanceValidateException
     */
    public User newUser(User User) throws ChanceValidateException;

    /**
     * 删除用户
     * 
     * @param id 主键ID
     * @return Integer
     * @throws ChanceValidateException
     */
    public Integer removeUserById(Integer id) throws ChanceValidateException;

    UserDto getLocalUserDto() throws ChanceValidateException;

}
