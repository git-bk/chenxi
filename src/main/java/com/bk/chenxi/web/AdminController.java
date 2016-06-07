package com.bk.chenxi.web;

import com.bk.chenxi.biz.UserBo;
import com.bk.chenxi.exception.ChanceValidateException;
import com.bk.chenxi.model.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminController {

    @Autowired
    private UserBo userBo;

    /**
     * 用户授权
     * 
     * @param account
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/admin/doAuthorize.json")
    @ResponseBody
    public Object doAuthorize(@RequestParam(value = "account") String account,
                              @RequestParam(value = "roleId") Integer roleId) {
        ResultModel<Integer> rsModel = new ResultModel<Integer>();
        try {
            userBo.roleAuthorize(roleId, account);
            rsModel.setSuccess(true);
        } catch (ChanceValidateException e) {
            rsModel.setChanceError(e);
            rsModel.setSuccess(false);
        }
        return rsModel;
    }

}
