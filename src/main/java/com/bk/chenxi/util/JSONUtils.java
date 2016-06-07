/*
 * Copyright 2015 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.bk.chenxi.util;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类JSONUtils.java的实现描述：TODO 类实现描述
 * 
 * @author luobin 2015年6月12日 下午7:31:08
 */
public class JSONUtils {

    private static final Logger logger       = LoggerFactory.getLogger(JSONUtils.class);

    public static ObjectMapper  objectMapper = new ObjectMapper();

    public static String beanToJSON(Object obj) {
        StringWriter str = new StringWriter();
        try {
            objectMapper.writeValue(str, obj);
            logger.info(str.toString());
            return str.toString();
        } catch (Exception e) {
            logger.error("对象转化为json格式时报错:{}", e.getMessage());
        }
        return null;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Object jsonToObject(String json, Class clasz) {
        try {
            return objectMapper.readValue(json, clasz);
        } catch (Exception e) {
            logger.error("json数据转化为对象时报错:{}", e.getMessage());
        }
        return null;
    }

    /**
     * json数组转化为java集合对象
     * 
     * @param jsonArray
     * @param e
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List jsonToList(String jsonArrayStr, Class clasz) {
        List list = new ArrayList();
        try {
            if (StringUtils.isBlank(jsonArrayStr)) {
                return list;
            }
            JSONArray datas = JSONArray.fromObject(jsonArrayStr);
            for (int i = 0; i < datas.size(); i++) {
                if (datas.get(i) != null) {
                    Object data = JSONUtils.jsonToObject(datas.get(i).toString(), clasz);
                    if (data != null) list.add(data);
                }
            }
        } catch (Exception ex) {
            logger.error("json数据转化为对象时报错:{}", ex.getMessage());
        }
        return list;
    }
}
