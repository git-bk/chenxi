package com.driverdata.chenxi.web.lannister;

import com.driverdata.chenxi.biz.EntityBo;
import com.driverdata.chenxi.dto.EntityDto;
import com.driverdata.chenxi.exception.ChanceValidateException;
import com.driverdata.chenxi.model.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Handles requests for the application home page.
 */
@Controller
public class EntityController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private EntityBo entityBo;

    @RequestMapping(value = "/lannister/entity_pool.htm")
    public String proMain(Locale locale, Model model) throws ChanceValidateException {
        return "lannister/entity/entity_pool";
    }

    /*
     * 通过数据字典的key（例：阶段），获得该key的数据集合
     */
    @RequestMapping(value = "/lannister/newEntity.json")
    @ResponseBody
    public Object newEntity(HttpServletRequest request,EntityDto entityDto) {
        ResultModel resultModel = new ResultModel();
        try {
            entityBo.addEntity(entityDto);
            resultModel.setSuccess(true);
        } catch (IllegalArgumentException e) {
            resultModel.setArgumentError(e);
            return resultModel;
        }
        return resultModel;
    }



}
