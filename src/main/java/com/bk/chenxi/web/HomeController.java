package com.bk.chenxi.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.bk.chenxi.biz.UserBo;
import com.bk.chenxi.dal.model.UserDo;
import com.bk.chenxi.dto.UserDto;
import com.bk.chenxi.exception.ChanceValidateException;
import com.bk.chenxi.model.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    @Autowired
    private UserBo userBo;

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
