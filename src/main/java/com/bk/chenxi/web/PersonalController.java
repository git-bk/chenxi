package com.bk.chenxi.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bk.chenxi.biz.UserBo;
import com.bk.chenxi.dto.UserDto;
import com.bk.chenxi.exception.ChanceValidateException;
import com.bk.chenxi.model.ResultModel;

@Controller
public class PersonalController {

    @Autowired
    private UserBo    userBo;

    @RequestMapping(value = "/personal/personal.htm")
    public String personal(Model model, HttpServletRequest request) throws ChanceValidateException {
        UserDto userDto = userBo.getLocalUserDto();
        String supplement = request.getParameter("supplement");
        model.addAttribute("showInfoForNewUser", StringUtils.equals(supplement, "true"));
        return "pro/personal";
    }

    @RequestMapping(value = "/personal/showUserInfo.json")
    @ResponseBody
    public Object showUserInfo() {
        ResultModel<UserDto> rsModel = new ResultModel<UserDto>();
        try {
            UserDto user = userBo.getLocalUserDto();
            if (user != null && user.getId() != null) {
                // 对用户信息进行处理
                rsModel.setReturnValue(withoutSensitive(user));
                rsModel.setSuccess(true);
            } else {
                throw new ChanceValidateException("showUserInfo-001", "当前用户不存在");
            }
        } catch (ChanceValidateException e) {
            rsModel.setSuccess(false);
            rsModel.setChanceError(e);
        }
        return rsModel;
    }



    @RequestMapping(value = "/personal/updateUserInfo.json")
    @ResponseBody
    public Object updateUserInfo(HttpServletRequest request) {
        ResultModel<UserDto> rsModel = new ResultModel<UserDto>();
        String phone = request.getParameter("phone");
        String phone_change = request.getParameter("phone_change");
        String email = request.getParameter("email");
        String email_change = request.getParameter("email_change");
        String realname = request.getParameter("realname");
        String realname_change = request.getParameter("realname_change");
        try {
            UserDto User = userBo.getLocalUserDto();
            if (StringUtils.isNotBlank(request.getParameter("newImageId"))) {
                User.setNewImageId(Integer.parseInt(request.getParameter("newImageId").trim()));
            }
            if (StringUtils.isNotBlank(request.getParameter("oldImageId"))) {
                User.setImageId(Integer.parseInt(request.getParameter("oldImageId").trim()));
            }
            if (request.getParameter("weibo") != null) {
                String weibo = request.getParameter("weibo").trim();
                User.setWeibo(weibo.substring(0, weibo.length() - 1));
            }
            if (request.getParameter("weixin") != null) {
                String weixin = request.getParameter("weixin").trim();
                User.setWeixin(weixin.substring(0, weixin.length() - 1));
            }
            if (request.getParameter("intro") != null) {
                String intro = request.getParameter("intro").trim();
                User.setIntro(intro.substring(0, intro.length() - 1));
            }
            if (StringUtils.isNotBlank(request.getParameter("signature"))) {
                User.setSignature(request.getParameter("signature").trim());
            }
            if (StringUtils.isNotBlank(phone) && StringUtils.equals(phone_change, phone)) {
                phone_change = null;
            }
            if (StringUtils.isNotBlank(email) && StringUtils.equals(email_change, email)) {
                email_change = null;
            }
            if (StringUtils.isNotBlank(realname) && StringUtils.equals(realname_change, realname)) {
                realname_change = null;
            }
            if (StringUtils.isNotBlank(phone_change)) {
                User.setPhone(phone_change);
            }
            if (StringUtils.isNotBlank(email_change)) {
                User.setEmail(email_change);
            }
            if (StringUtils.isNotBlank(realname_change)) {
                User.setRealname(realname_change);
            }
            int rs = userBo.modifyUser(User);
            // 更新session
            UserDto u = userBo.getLocalUserDto();
            u.setPassword(null);
            request.getSession().setAttribute("userInfo", u);
            rsModel.setSuccess(rs == 1);
        } catch (ChanceValidateException e) {
            rsModel.setSuccess(false);
            rsModel.setChanceError(e);
        }
        return rsModel;
    }

    /*
     * 对用户信息进行处理
     */
    public UserDto withoutSensitive(UserDto user) {
        // 真实姓名：隐去第一个字
        // 账号：只留第一个和最后一个
        // 电话：留后4位
        // 邮箱：留前三位+@。。。。
        if (user != null) {
            if (StringUtils.isNotBlank(user.getRealname())) {
                String name = user.getRealname();
                user.setRealname("*" + name.substring(1));
            }
            if (StringUtils.isNotBlank(user.getAccount())) {
                String account = user.getAccount();
                StringBuffer sb = new StringBuffer();
                sb.append(account.subSequence(0, 1)).append("****").append(account.subSequence(account.length() - 1,
                                                                                               account.length()));
                user.setAccount(sb.toString());
            }
            if (StringUtils.isNotBlank(user.getPhone())) {
                String phone = user.getPhone();
                if (phone.length() > 4) {
                    user.setPhone("****" + phone.substring(phone.length() - 4));
                } else {
                    user.setPhone("****" + phone.substring(1));
                }
            }
            if (StringUtils.isNotBlank(user.getEmail())) {
                String email = user.getEmail();
                String[] arr = email.split("@");
                if (arr != null && arr.length > 1) {
                    int i = 3;
                    if (arr[0].length() < 3) {
                        i = arr[0].length();
                    }
                    email = email.replace(email.subSequence(i, email.length()), "****");
                    user.setEmail(email + "@" + arr[1]);
                }
            }
        }
        return user;
    }
}
