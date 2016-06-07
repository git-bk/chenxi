package com.bk.chenxi.biz.impl;

import com.bk.chenxi.biz.EntityBo;
import com.bk.chenxi.dal.mapper.EntityDoMapperExt;
import com.bk.chenxi.dal.model.EntityDo;
import com.bk.chenxi.dal.model.EntityDoExample;
import com.bk.chenxi.dto.EntityDto;
import org.apache.commons.beanutils.BeanUtils;
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
public class EntityBoImpl implements EntityBo {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private EntityDoMapperExt entityDoMapperExt;

    @Override
    public EntityDto addEntity(EntityDto entity) {
        // 实体key不能重复
        Assert.notNull(entity.getAppId(),"appId为空");
        Assert.hasText(entity.getEntityKey(),"实体key空白");
        Assert.isNull(this.findByEntityKey(entity.getEntityKey()),"实体key已存在");
        if(entity.getParent()!=null){
            Assert.notNull(this.findById(entity.getParent()),"没有找到指定的父实体");
        }
        entityDoMapperExt.insertSelective(entity);
        return entity;
    }

    @Override
    public void delEntity(EntityDto entity)  {
        Assert.notNull(entity);
        Assert.notNull(entity.getId());
        entityDoMapperExt.deleteByPrimaryKey(entity);
    }

    @Override
    public void updateEntity(EntityDto entity)  {
        Assert.notNull(entity);
        Assert.notNull(entity.getId());
        Assert.isNull(entity.getAppId(),"appId不能修改");
        Assert.isNull(entity.getEntityKey(),"实体key不能修改");
        entityDoMapperExt.updateByPrimaryKeySelective(entity);
    }

    @Override
    public EntityDto findById(Integer id)   {
        EntityDo entityDo=entityDoMapperExt.selectByPrimaryKey(id);
        EntityDto entityDto = null;
        if(entityDo!=null){
            entityDto = new EntityDto();
            try {
                BeanUtils.copyProperties(entityDto,entityDo);
            } catch (IllegalAccessException e) {
                logger.info("",e);
            } catch (InvocationTargetException e) {
                logger.info("",e);
            }
        }
        return entityDto;
    }

    @Override
    public EntityDto findByEntityKey(String entityKey) {
        EntityDo entityDo=entityDoMapperExt.selectByEntityKey(entityKey);
        EntityDto entityDto = null;
        if(entityDo!=null){
            entityDto = new EntityDto();
            try {
                BeanUtils.copyProperties(entityDto,entityDo);
            } catch (IllegalAccessException e) {
                logger.info("",e);
            } catch (InvocationTargetException e) {
                logger.info("",e);
            }
        }
        return entityDto;
    }

    @Override
    public List<EntityDto> findChildrenEntitys(String entityKey)   {
        EntityDto entity=findByEntityKey(entityKey);
        if(entity!=null){
            return findChildrenEntitys(entity.getId());
        }
        return null;
    }

    @Override
    public List<EntityDto> findChildrenEntitys(Integer parentEntityId)   {
        EntityDoExample example = new EntityDoExample();
        example.createCriteria().andParentEqualTo(parentEntityId);
        List<EntityDo> doList=entityDoMapperExt.selectByExample(example);
        List<EntityDto> rsDtoList= null;
        if(doList!=null){
            rsDtoList = new ArrayList<EntityDto>();
            for (EntityDo entityDo:doList) {
                EntityDto entityDto = new EntityDto();
                try {
                    BeanUtils.copyProperties(entityDto,entityDo);
                } catch (IllegalAccessException e) {
                     logger.info("",e);
                } catch (InvocationTargetException e) {
                     logger.info("",e);
                }
                rsDtoList.add(entityDto);
            }
        }
        return rsDtoList;
    }


}
