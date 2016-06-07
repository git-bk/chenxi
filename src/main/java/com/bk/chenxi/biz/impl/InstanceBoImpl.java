package com.bk.chenxi.biz.impl;

import com.bk.chenxi.biz.InstanceBo;
import com.bk.chenxi.dal.mapper.InstanceDoMapperExt;
import com.bk.chenxi.dal.model.InstanceDo;
import com.bk.chenxi.dto.InstanceDto;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by wb-yaobingke on 2016/5/13.
 */
public class InstanceBoImpl implements InstanceBo {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private InstanceDoMapperExt instanceDoMapperExt;

    @Override
    public InstanceDto addInstance(InstanceDto instance) {
        Assert.notNull(instance);
        Assert.notNull(instance.getEntityId());
        Assert.notNull(instance.getEntityKey());
        instanceDoMapperExt.insertSelective(instance);
        return instance;
    }

    @Override
    public void delInstance(InstanceDto instance) {
        Assert.notNull(instance);
        Assert.notNull(instance.getId());
        instanceDoMapperExt.deleteByPrimaryKey(instance);
    }

    @Override
    public void updateInstance(InstanceDto instance) {
        Assert.notNull(instance);
        Assert.notNull(instance.getId());
        instanceDoMapperExt.updateByPrimaryKeySelective(instance);
    }

    @Override
    public InstanceDto findById(Integer id) {
        Assert.notNull(id);
        InstanceDo instanceDo =instanceDoMapperExt.selectByPrimaryKey(id);
        if(instanceDo!=null){
            InstanceDto instanceDto = new InstanceDto();
            try {
                BeanUtils.copyProperties(instanceDto,instanceDo);
            } catch (IllegalAccessException e) {
                logger.info("",e);
            } catch (InvocationTargetException e) {
                logger.info("",e);
            }
            return instanceDto;
        }
        return null;
    }
}
