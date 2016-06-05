package com.driverdata.chenxi.util;

import com.driverdata.chenxi.dto.InstanceDto;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wb-yaobingke on 2016/5/23.
 */
public class LannisterUtil {
    public static Logger logger       = LoggerFactory.getLogger(LannisterUtil.class);

    public static ObjectMapper objectMapper = new ObjectMapper();

    public static String captureName(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }

    public static String arrToStr(String[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public static <T> String getFieldValueByDataKey(T instance, String dataKey)  {
        if (instance == null || StringUtils.isBlank(dataKey)) {
            return null;
        }
        String rs = null;
        try {
            Method method = instance.getClass().getDeclaredMethod("get" + LannisterUtil.captureName(dataKey));
            rs = (String) method.invoke(instance);
        } catch (NoSuchMethodException e) {
            logger.info("",e);
        } catch (SecurityException e) {
            logger.info("",e);
        } catch (IllegalAccessException e) {
            logger.info("",e);
        } catch (IllegalArgumentException e) {
            logger.info("",e);
        } catch (InvocationTargetException e) {
            logger.info("",e);
        }
        return rs;
    }

    public static <T> Object setDtoByKey(T dto, String met, String data)  {
        if (dto == null || StringUtils.isBlank(met)) {
            return null;
        }
        Object rs = null;
        try {
            Method method = null;
            if (StringUtil.isBlank(data)) {
                method = dto.getClass().getDeclaredMethod(met);
                rs = method.invoke(dto);
            } else {
                method = dto.getClass().getDeclaredMethod(met, String.class);
                rs = method.invoke(dto, data);
            }
        } catch (NoSuchMethodException e) {
            logger.info("",e);
        } catch (SecurityException e) {
            logger.info("",e);
        } catch (IllegalAccessException e) {
            logger.info("",e);
        } catch (IllegalArgumentException e) {
            logger.info("",e);
        } catch (InvocationTargetException e) {
            logger.info("",e);
        }
        return rs;
    }

    /**
     * @param dto
     * @param data
     * @return
     * @
     */
    public static <T> Object setListByKey(T dto, String meth, List<Long> data)  {
        if (dto == null || StringUtils.isBlank(meth)) {
            return null;
        }
        Object rs = null;
        try {
            Method method = null;
            if (data == null || data.isEmpty()) {
                method = dto.getClass().getDeclaredMethod(meth);
                rs = method.invoke(dto);
            } else {
                method = dto.getClass().getDeclaredMethod(meth, List.class);
                rs = method.invoke(dto, data);
            }
        } catch (NoSuchMethodException e) {
            logger.info("",e);
        } catch (SecurityException e) {
            logger.info("",e);
        } catch (IllegalAccessException e) {
            logger.info("",e);
        } catch (IllegalArgumentException e) {
            logger.info("",e);
        } catch (InvocationTargetException e) {
            logger.info("",e);
        }
        return rs;
    }

    /**
     * 将instance转换为定制的dto时，fieldKey-->dto属性（aaa_bbb ----> aaaBbb ）
     *
     * @param instanceDto
     * @param columnName
     * @param columnValue
     * @
     */
    public static <T> void setColumnValue(T instanceDto, String columnName, String columnValue)  {
        try {
            Integer index = columnName.indexOf("_");

            if (index != -1 && index != columnName.length() - 1) {
                String str = columnName.substring(index, index + 2);
                String strReplace = columnName.substring(index + 1, index + 2).toUpperCase();
                columnName = columnName.replace(str, strReplace);
            }
            Field field = null;
            try {
                field = instanceDto.getClass().getDeclaredField(columnName);
            } catch (NoSuchFieldException e) {
                return;// 找不到对应的属性，直接返回
            }
            field.setAccessible(true);
            field.set(instanceDto, columnValue);
        } catch (SecurityException e) {
            logger.info("",e);
        } catch (IllegalArgumentException e) {
            logger.info("",e);
        } catch (IllegalAccessException e) {
            logger.info("",e);
        }
    }

    /**
     * 反射执行T的set属性方法
     *
     * @param instance
     * @param dataKey
     * @param data
     * @
     */
    public static <T> void setInstanceKey(T instance, String dataKey, String data)  {
        if (instance == null || StringUtils.isBlank(dataKey)) {
            return;
        }
        try {
            Method method = null;
            method = instance.getClass().getDeclaredMethod("set" + LannisterUtil.captureName(dataKey), String.class);
            method.invoke(instance, data);
        } catch (NoSuchMethodException e) {
            logger.info("",e);
        } catch (SecurityException e) {
            logger.info("",e);
        } catch (IllegalAccessException e) {
            logger.info("",e);
        } catch (IllegalArgumentException e) {
            logger.info("",e);
        } catch (InvocationTargetException e) {
            logger.info("",e);
        }
    }

    public static <T> void reflectInvoke(T obj, String methodName, String param){
        if (obj == null || StringUtils.isBlank(methodName)) {
            return;
        }
        try {
            Method method = null;
            if (StringUtils.isBlank(param)) {
                method = obj.getClass().getDeclaredMethod(methodName);
                method.invoke(obj);
            } else {
                method = obj.getClass().getDeclaredMethod(methodName, String.class);
                method.invoke(obj, param);
            }
        } catch (NoSuchMethodException e) {
            logger.info("",e);
        } catch (SecurityException e) {
            logger.info("",e);
        } catch (IllegalAccessException e) {
            logger.info("",e);
        } catch (IllegalArgumentException e) {
            logger.info("",e);
        } catch (InvocationTargetException e) {
            logger.info("",e);
        }
    }

    public static void main(String[] args) {

        try {
            InstanceDto instanceDto = new InstanceDto();
            List<Long> list = new ArrayList<Long>();
            list.add(111l);
            setListByKey(instanceDto, "setEnterpriseIds", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
