package com.bk.chenxi.biz.impl;

import com.bk.chenxi.biz.UserBo;
import com.bk.chenxi.dal.model.UserDo;
import com.bk.chenxi.dto.UserDto;
import com.bk.chenxi.exception.ChanceValidateException;
import com.bk.chenxi.model.User;

import java.util.List;

/**
 * Created by biki on 16/6/6.
 */
public class CxUserBoImpl implements UserBo{
    @Override
    public void registerUser(String account, String password, String validateCode, String type) throws ChanceValidateException {

    }

    @Override
    public void ChancePassword(String account, String password, String validateCode) throws ChanceValidateException {

    }

    @Override
    public void roleAuthorize(Integer roleId, String account) throws ChanceValidateException {

    }

    @Override
    public void sendMobileValidateCode(String mobile) throws ChanceValidateException {

    }

    @Override
    public UserDo findUserbyAccount(String account) {
        return null;
    }

    @Override
    public UserDo getLocalUser() {
        return null;
    }

    @Override
    public Integer modifyUser(UserDto user) throws ChanceValidateException {
        return null;
    }

    @Override
    public UserDto findUserById(Integer id) throws ChanceValidateException {
        return null;
    }

    @Override
    public List<User> findUsersByUser(User User) throws ChanceValidateException {
        return null;
    }

    @Override
    public User newUser(User User) throws ChanceValidateException {
        return null;
    }

    @Override
    public Integer removeUserById(Integer id) throws ChanceValidateException {
        return null;
    }

    @Override
    public UserDto getLocalUserDto() throws ChanceValidateException {
        return null;
    }
}
