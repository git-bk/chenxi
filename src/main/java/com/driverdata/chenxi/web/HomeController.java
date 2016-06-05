package com.driverdata.chenxi.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.driverdata.chenxi.biz.UserBo;
import com.driverdata.chenxi.dal.model.UserDo;
import com.driverdata.chenxi.dto.UserDto;
import com.driverdata.chenxi.exception.ChanceValidateException;
import com.driverdata.chenxi.model.ResultModel;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    @Autowired
    private UserBo    userBo;

    @RequestMapping(value = "/index.htm")
    public String index(HttpServletResponse response) throws IOException {
        // 没有项目跳到项目创建页面，有项目但没有审核通过跳到我的项目页面，有审核通过的项目跳到项目主页。

            UserDo user = userBo.getLocalUser();

            response.sendRedirect("/chenxi/personal/personal.htm");

            return "error";

    }

    @RequestMapping(value = "/getLocalUserInfo.json")
    @ResponseBody
    public Object getLocalUserInfo() {
        ResultModel<UserDto> rs = new ResultModel<UserDto>();
        UserDto user;
        try {
            user = userBo.getLocalUserDto();
            rs.setReturnValue(user);
        } catch (ChanceValidateException e) {
            rs.setSuccess(false);
            rs.setChanceError(e);
        }
        return rs;
    }

}
