package com.bk.chenxi.biz.impl;

import com.bk.chenxi.dal.mapper.FieldDoMapperExt;
import com.bk.chenxi.biz.FieldBo;
import com.bk.chenxi.biz.FieldTypeBo;
import com.bk.chenxi.dal.model.FieldDo;
import com.bk.chenxi.dal.model.FieldDoExample;
import com.bk.chenxi.dto.FieldDto;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wb-yaobingke on 2016/5/13.
 */
@Resource
public class FieldBoImpl implements FieldBo {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private FieldDoMapperExt fieldDoMapperExt;
    @Autowired
    private FieldTypeBo fieldTypeBo;

    @Override
    public FieldDto addField(FieldDto fieldDto)  {
        Assert.notNull(fieldDto);
        Assert.notNull(fieldDto.getEntityId());
        Assert.notNull(fieldDto.getFieldKey());
        Assert.notNull(fieldDto.getFieldType());
        Assert.notNull(fieldDto.getName());
        List<FieldDto> list=findFieldList(fieldDto.getEntityId());
        for (FieldDto field:list) {
            Assert.isTrue(!StringUtils.equals(field.getFieldKey(),fieldDto.getFieldKey()),"fieldKey已存在");
        }
        fieldDto.setColumnName(fieldTypeBo.getNextColumnName(fieldDto.getEntityId(),fieldDto.getFieldType()));
        fieldDoMapperExt.insertSelective(fieldDto);
        return fieldDto;
    }

    @Override
    public void delField(FieldDto fieldDto)  {
        Assert.notNull(fieldDto);
        Assert.notNull(fieldDto.getId());
        fieldDoMapperExt.deleteByPrimaryKey(fieldDto);
    }

    @Override
    public void updateField(FieldDto fieldDto)  {
        Assert.notNull(fieldDto);
        Assert.notNull(fieldDto.getId());
        Assert.isNull(fieldDto.getFieldKey(),"实体key不能修改");
        fieldDoMapperExt.updateByPrimaryKeySelective(fieldDto);
    }

    @Override
    public FieldDto findById(Integer id)  {
        FieldDo fieldDo=fieldDoMapperExt.selectByPrimaryKey(id);
        FieldDto fieldDto = null;
        if(fieldDo!=null){
            fieldDto = new FieldDto();
            try {
                BeanUtils.copyProperties(fieldDto,fieldDo);
            } catch (IllegalAccessException e) {
                logger.info("",e);
            } catch (InvocationTargetException e) {
                logger.info("",e);
            }
        }
        return fieldDto;
    }

    @Override
    public List<FieldDto> findFieldList(Integer entityId) {
        if(entityId!=null){
            FieldDoExample example = new FieldDoExample();
            example.createCriteria().andEntityIdEqualTo(entityId);
            List<FieldDo> list=fieldDoMapperExt.selectByExample(example);
            List<FieldDto> rsList = new ArrayList<FieldDto>();
            for (FieldDo fieldDo:list){
                FieldDto fieldDto = new FieldDto();
                try {
                    BeanUtils.copyProperties(fieldDto,fieldDo);
                    fieldDto.setFieldTypeInfo(fieldTypeBo.findById(fieldDo.getFieldType()));
                } catch (IllegalAccessException e) {
                    logger.info("",e);
                } catch (InvocationTargetException e) {
                    logger.info("",e);
                }
                rsList.add(fieldDto);
            }
            return rsList;
        }else{
            return null;
        }
    }
}
