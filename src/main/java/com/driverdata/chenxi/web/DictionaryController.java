package com.driverdata.chenxi.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.driverdata.chenxi.biz.DictionaryBo;
import com.driverdata.chenxi.dto.DictionaryDto;
import com.driverdata.chenxi.enums.DictionaryType;
import com.driverdata.chenxi.exception.ChanceValidateException;
import com.driverdata.chenxi.model.KeyValue;
import com.driverdata.chenxi.model.ResultModel;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DictionaryController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private DictionaryBo       dictionaryBo;

    /*
     * 通过数据字典的key（例：阶段），获得该key的数据集合
     */
    @RequestMapping(value = "/dic/findDataByKey.json")
    @ResponseBody
    public Object findDataByKey(HttpServletRequest request) {
        ResultModel<ArrayList<DictionaryDto>> resultModel = new ResultModel<ArrayList<DictionaryDto>>();
        String key = request.getParameter("key");
        if (StringUtils.isBlank(key)) {
            resultModel.setErrorCode("RPC_PARAM_NULL");// RPC参数为空
            resultModel.setSuccess(false);
            return resultModel;
        }
        DictionaryDto dicDto = new DictionaryDto();
        List<DictionaryDto> list;
        dicDto.setDicKey(key);
        try {
            list = dictionaryBo.findEntityByDto(dicDto);
            resultModel.setSuccess(true);
        } catch (ChanceValidateException e) {
            resultModel.setErrorCode(e.getCode());
            resultModel.setSuccess(false);
            return resultModel;
        }
        resultModel.setReturnValue((ArrayList<DictionaryDto>) list);
        return resultModel;
    }

    /*
     * 融资伦次
     */
    @RequestMapping(value = "/pro/listAllFinancingRounds.json")
    @ResponseBody
    public Object listAllFinancingRounds(HttpServletRequest request) {
        ResultModel<ArrayList<KeyValue>> resultModel = new ResultModel<ArrayList<KeyValue>>();
        DictionaryDto dicDto = new DictionaryDto();
        dicDto.setDicKey(DictionaryType.FINANCING_ROUND.getValue());
        ArrayList<DictionaryDto> dicList = new ArrayList<DictionaryDto>();
        try {
            dicList = (ArrayList<DictionaryDto>) dictionaryBo.findEntityByDto(dicDto);
            resultModel.setSuccess(true);
        } catch (ChanceValidateException e) {
            resultModel.setErrorCode(e.getCode());
            resultModel.setSuccess(false);
            return resultModel;
        }
        ArrayList<KeyValue> result = new ArrayList<KeyValue>();
        if (dicList != null && !dicList.isEmpty()) {
            for (DictionaryDto temp : dicList) {
                KeyValue kv = new KeyValue();
                kv.setId(temp.getId());
                kv.setText(temp.getValue());
                result.add(kv);
            }
        }
        resultModel.setReturnValue(result);
        return resultModel;
    }

    /*
     * 所处领域
     */
    @RequestMapping(value = "/pro/listAllFields.json")
    @ResponseBody
    public Object listAllFields(HttpServletRequest request) {
        ResultModel<ArrayList<KeyValue>> resultModel = new ResultModel<ArrayList<KeyValue>>();
        DictionaryDto dicDto = new DictionaryDto();
        dicDto.setDicKey(DictionaryType.Field.getValue());
        dicDto.setValue(request.getParameter("q"));
        ArrayList<DictionaryDto> dicList = new ArrayList<DictionaryDto>();
        try {
            dicList = (ArrayList<DictionaryDto>) dictionaryBo.findEntityByDtoFuzzy(dicDto);
            resultModel.setSuccess(true);
        } catch (ChanceValidateException e) {
            resultModel.setErrorCode(e.getCode());
            resultModel.setSuccess(false);
            return resultModel;
        }
        ArrayList<KeyValue> result = new ArrayList<KeyValue>();
        if (dicList != null && !dicList.isEmpty()) {
            for (DictionaryDto temp : dicList) {
                if ("全部".equals(temp.getValue())) continue;
                KeyValue kv = new KeyValue();
                kv.setId(temp.getId());
                kv.setText(temp.getValue());
                result.add(kv);
            }
        }

        resultModel.setReturnValue(result);
        return resultModel;
    }

    /*
     * 阶段
     */
    @RequestMapping(value = "/pro/listAllPhases.json")
    @ResponseBody
    public Object listAllPhases(HttpServletRequest request) {
        ResultModel<ArrayList<KeyValue>> resultModel = new ResultModel<ArrayList<KeyValue>>();
        DictionaryDto dicDto = new DictionaryDto();
        // 设置项目数据字典类型（项目所处阶段）
        dicDto.setDicKey(DictionaryType.PHASE.getValue());
        ArrayList<DictionaryDto> dicList = new ArrayList<DictionaryDto>();
        try {
            dicList = (ArrayList<DictionaryDto>) dictionaryBo.findEntityByDto(dicDto);
            resultModel.setSuccess(true);
        } catch (ChanceValidateException e) {
            resultModel.setErrorCode(e.getCode());
            resultModel.setSuccess(false);
            return resultModel;
        }
        ArrayList<KeyValue> result = new ArrayList<KeyValue>();
        if (dicList != null && !dicList.isEmpty()) {
            for (DictionaryDto temp : dicList) {
                if ("全部".equals(temp.getValue())) {
                    continue;
                }
                KeyValue kv = new KeyValue();
                kv.setId(temp.getId());
                kv.setText(temp.getValue());
                result.add(kv);
            }
        }

        resultModel.setReturnValue(result);
        return resultModel;
    }

    @RequestMapping(value = "/pro/conditions.json")
    @ResponseBody
    public Object listAllAreas(HttpServletRequest request, @RequestParam(value = "") String key) {
        ResultModel<ArrayList<KeyValue>> resultModel = new ResultModel<ArrayList<KeyValue>>();
        DictionaryDto dicDto = new DictionaryDto();
        dicDto.setDicKey(key);
        ArrayList<DictionaryDto> dicList = new ArrayList<DictionaryDto>();
        try {
            dicList = (ArrayList<DictionaryDto>) dictionaryBo.findEntityByDto(dicDto);
            resultModel.setSuccess(true);
        } catch (ChanceValidateException e) {
            resultModel.setErrorCode(e.getCode());
            resultModel.setSuccess(false);
            return resultModel;
        }
        ArrayList<KeyValue> result = new ArrayList<KeyValue>();
        if (dicList != null && !dicList.isEmpty()) {
            for (DictionaryDto temp : dicList) {
                KeyValue kv = new KeyValue();
                kv.setId(temp.getId());
                kv.setText(temp.getValue());
                result.add(kv);
            }
        }
        resultModel.setReturnValue(result);
        return resultModel;
    }

    @RequestMapping(value = "/open/conditions.json")
    @ResponseBody
    public Object listAllAreas_open(HttpServletRequest request, @RequestParam(value = "") String key) {
        ResultModel<ArrayList<KeyValue>> resultModel = new ResultModel<ArrayList<KeyValue>>();
        DictionaryDto dicDto = new DictionaryDto();
        dicDto.setDicKey(key);
        ArrayList<DictionaryDto> dicList = new ArrayList<DictionaryDto>();
        try {
            dicList = (ArrayList<DictionaryDto>) dictionaryBo.findEntityByDto(dicDto);
            resultModel.setSuccess(true);
        } catch (ChanceValidateException e) {
            resultModel.setErrorCode(e.getCode());
            resultModel.setSuccess(false);
            return resultModel;
        }
        ArrayList<KeyValue> result = new ArrayList<KeyValue>();
        if (dicList != null && !dicList.isEmpty()) {
            for (DictionaryDto temp : dicList) {
                KeyValue kv = new KeyValue();
                kv.setId(temp.getId());
                kv.setText(temp.getValue());
                result.add(kv);
            }
        }
        resultModel.setReturnValue(result);
        return resultModel;
    }

}
