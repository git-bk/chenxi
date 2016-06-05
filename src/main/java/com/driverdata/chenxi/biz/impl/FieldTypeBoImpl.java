package com.driverdata.chenxi.biz.impl;

import com.driverdata.chenxi.biz.FieldBo;
import com.driverdata.chenxi.biz.FieldTypeBo;
import com.driverdata.chenxi.biz.LannisterBo;
import com.driverdata.chenxi.biz.model.EntityInfo;
import com.driverdata.chenxi.dal.mapper.FieldTypeDoMapperExt;
import com.driverdata.chenxi.dal.model.FieldTypeDo;
import com.driverdata.chenxi.dal.model.FieldTypeDoExample;
import com.driverdata.chenxi.dto.FieldDto;
import com.driverdata.chenxi.dto.FieldTypeDto;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wb-yaobingke on 2016/5/20.
 */
@Resource
public class FieldTypeBoImpl implements FieldTypeBo {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private FieldTypeDoMapperExt fieldTypeDoMapperExt;
    @Autowired
    private LannisterBo lannisterBo;
    @Autowired
    private FieldBo fieldBo;

    @Override
    public List<FieldTypeDto> listAllFieldTypes() {
        FieldTypeDoExample example = new FieldTypeDoExample();
        example.createCriteria();
        List<FieldTypeDo> list=fieldTypeDoMapperExt.selectByExample(example);
        List<FieldTypeDto> rsList = new ArrayList<FieldTypeDto>();
        for (FieldTypeDo fieldTypeDo:list){
            FieldTypeDto fieldTypeDto = new FieldTypeDto();
            try {
                BeanUtils.copyProperties(fieldTypeDto,fieldTypeDo);
            } catch (IllegalAccessException e) {
                logger.info("",e);
            } catch (InvocationTargetException e) {
                logger.info("",e);
            }
            rsList.add(fieldTypeDto);
        }
        return rsList;
    }

    @Override
    public String getNextColumnName(Integer entityId,Integer fieldTypeId) {
        //获取实体下所有当前字段类型的字段记录
        EntityInfo entityInfo= lannisterBo.findEntityInfo(entityId);
        if(entityInfo==null){
            return null;
        }
        FieldTypeDto fieldType=findById(fieldTypeId);
        List<FieldDto> fieldList=fieldBo.findFieldList(entityId);
        if(fieldList==null || fieldList.size()==0){
            return "column"+fieldType.getFieldDataTypeDto().getStartIndex();
        }else if(fieldList.size()>=(fieldType.getFieldDataTypeDto().getEndIndex()-fieldType.getFieldDataTypeDto().getStartIndex()+1)){
            logger.info("实体："+entityInfo.getEntity().getName()+"下"+fieldType.getName()+"类型的列已分配完！");
            return null;
        }else{
            return "column"+(fieldType.getFieldDataTypeDto().getStartIndex()+fieldList.size());
        }
    }

    @Override
    public FieldTypeDto findById(Integer fieldTypeId) {
            FieldTypeDo fieldTypeDo=fieldTypeDoMapperExt.selectByPrimaryKey(fieldTypeId);
        FieldTypeDto fieldTypeDto = null;
            if(fieldTypeDo!=null){
                fieldTypeDto = new FieldTypeDto();
                try {
                    BeanUtils.copyProperties(fieldTypeDto,fieldTypeDo);
                } catch (IllegalAccessException e) {
                    logger.info("",e);
                } catch (InvocationTargetException e) {
                    logger.info("",e);
                }
            }
            return fieldTypeDto;
    }
}
