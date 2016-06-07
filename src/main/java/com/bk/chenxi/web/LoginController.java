package com.bk.chenxi.web;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bk.chenxi.biz.UserBo;
import com.bk.chenxi.dal.model.UserDo;
import com.bk.chenxi.exception.ChanceValidateException;
import com.bk.chenxi.model.ResultModel;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {

    @Autowired
    private UserBo    userBo;

    @RequestMapping(value = "/login/login.htm")
    public String login(Locale locale, Model model) {
        return "login/login";
    }

    @RequestMapping(value = "/login/ajaxLogin.htm")
    public String ajaxLogin(Locale locale, Model model) {
        return "login/ajaxLogin";
    }

    @RequestMapping(value = "/login/403.htm")
    public String noPermission(Locale locale, Model model) {
        return "login/403";
    }

    @RequestMapping(value = "/login/register.htm")
    public String register(Locale locale, Model model) {
        return "login/register";
    }

    @RequestMapping(value = "/login/findPassword.htm")
    public String findPassword(Locale locale, Model model) {
        return "login/findPassword";
    }

    @RequestMapping(value = "/login/register.json")
    @ResponseBody
    public Object doRegister(@RequestParam(value = "account") String account,
                             @RequestParam(value = "password") String password,
                             @RequestParam(value = "validateCode") String validateCode,
                             @RequestParam(value = "type") String type) {
        @SuppressWarnings("rawtypes")
        ResultModel rsModel = new ResultModel();
        try {
            MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder("MD5");
            userBo.registerUser(account, encoder.encodePassword(password, null), validateCode, type);
            rsModel.setSuccess(true);
        } catch (ChanceValidateException e) {
            rsModel.setChanceError(e);
            rsModel.setSuccess(false);
        }
        return rsModel;
    }

    @RequestMapping(value = "/login/changePassword.json")
    @ResponseBody
    public Object changePassword(@RequestParam(value = "account") String account,
                                 @RequestParam(value = "password") String password,
                                 @RequestParam(value = "validateCode") String validateCode) {
        @SuppressWarnings("rawtypes")
        ResultModel rsModel = new ResultModel();
        try {
            MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder("MD5");
            userBo.ChancePassword(account, encoder.encodePassword(password, null), validateCode);
            rsModel.setSuccess(true);
        } catch (ChanceValidateException e) {
            rsModel.setChanceError(e);
            rsModel.setSuccess(false);
        }
        return rsModel;
    }

    @RequestMapping(value = "/login/sendCode.json")
    @ResponseBody
    public Object sendCode(@RequestParam(value = "mobile") String mobile) {
        @SuppressWarnings("rawtypes")
        ResultModel rsModel = new ResultModel();
        try {
            userBo.sendMobileValidateCode(mobile);
            rsModel.setSuccess(true);
        } catch (ChanceValidateException e) {
            rsModel.setChanceError(e);
            rsModel.setSuccess(false);
        }
        return rsModel;
    }

    @RequestMapping(value = "/login/validAccount.json")
    @ResponseBody
    public Object validAccount(@RequestParam(value = "account") String account) {
        @SuppressWarnings("rawtypes")
        ResultModel rsModel = new ResultModel();
        try {
            UserDo user = userBo.findUserbyAccount(account);
            if (user == null) {
                rsModel.setSuccess(true);
            } else {
                rsModel.setSuccess(false);
                rsModel.setErrorDesc("该手机号已被注册，请换个手机号吧");
            }
        } catch (Exception e) {
            rsModel.setSuccess(false);
        }
        return rsModel;
    }
}
