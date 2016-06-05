package com.driverdata.chenxi.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.driverdata.chenxi.dal.mapper.DicSettingDoMapperExt;
import com.driverdata.chenxi.dal.model.DicSettingDo;
import com.driverdata.chenxi.dal.model.DicSettingDoExample;
import com.driverdata.chenxi.dto.DicSettingDto;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.driverdata.chenxi.biz.DictionaryBo;
import com.driverdata.chenxi.dal.mapper.DictionaryDoMapperExt;
import com.driverdata.chenxi.dal.model.DictionaryDo;
import com.driverdata.chenxi.dal.model.DictionaryDoExample;
import com.driverdata.chenxi.dal.plugin.Page;
import com.driverdata.chenxi.dto.DictionaryDto;
import com.driverdata.chenxi.enums.YesOrNo;
import com.driverdata.chenxi.exception.ChanceValidateException;

/**
 * @author meicheng.lmc
 * @version 创建时间：2015年5月15日 下午7:35:33 类说明
 */
public class DictionaryBoImpl implements DictionaryBo {

    private final Logger          logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DictionaryDoMapperExt dictionaryDoMapperExt;
    @Autowired
    private DicSettingDoMapperExt dicSettingDoMapperExt;

    @Override
    public DictionaryDto insertEntity(DictionaryDto entity) throws ChanceValidateException {
        logger.info("开始执行：DictionaryBo.insertEntity。。。。。。");
        logger.info("insertEntity-参数： entity=" + JSON.toJSONString(entity));
        logger.info("if条件值：entity == null= " + JSON.toJSONString(entity == null));
        if (entity == null) {
            throw new ChanceValidateException("insertEntity-001", "参数对象不能为空"); // 参数对象不能为空
        }
        logger.info("if条件值：StringUtils.isBlank(entity.getDicKey())= "
                    + JSON.toJSONString(StringUtils.isBlank(entity.getDicKey())));
        if (StringUtils.isBlank(entity.getDicKey())) {
            throw new ChanceValidateException("insertEntity-002", "字典类型不能为空"); // 字典类型不能为空
        }
        logger.info("if条件值：StringUtils.isBlank(entity.getValue())= "
                    + JSON.toJSONString(StringUtils.isBlank(entity.getValue())));
        if (StringUtils.isBlank(entity.getValue())) {
            throw new ChanceValidateException("insertEntity-003", "字典名称不能为空"); // 字典名称不能为空
        }
        DictionaryDto temp = new DictionaryDto();
        temp.setDicKey(entity.getDicKey());
        temp.setValue(entity.getValue());
        logger.info("findEntityByDto-参数： dictionaryDto=" + JSON.toJSONString(temp));
        List<DictionaryDto> dicDtoList = findEntityByDto(temp);
        if (dicDtoList != null && !dicDtoList.isEmpty()) {
            throw new ChanceValidateException("insertEntity-006", "名称重复");// 名称重复
        }

        entity.setIsSystem(YesOrNo.NO.getValue()); // 插入数据字典的数据都是可维护的

        DictionaryDo dicDo = new DictionaryDo();
        BeanUtils.copyProperties(entity, dicDo);
        logger.info("dictionaryDoMapperExt.insertSelective-参数： dicDo=" + JSON.toJSONString(dicDo));
        dictionaryDoMapperExt.insertSelective(dicDo);
        entity.setId(dicDo.getId());
        logger.info("结束执行：DictionaryBo.insertEntity。。。。。。");

        return entity;
    }

    @Override
    public void updateEntity(DictionaryDto entity) throws ChanceValidateException {
        logger.info("开始执行：DictionaryBo.updateEntity。。。。。。");
        logger.info("updateEntity-参数： entity=" + JSON.toJSONString(entity));
        if (entity == null) {
            throw new ChanceValidateException("updateEntity-001", " 参数对象不能为空"); // 参数对象不能为空
        }
        if (entity.getId() == null) {
            throw new ChanceValidateException("updateEntity-002", "字典ID不能为空"); // 字典ID不能为空
        }
        logger.info("dictionaryDoMapperExt.selectByPrimaryKey-参数： entity.getId()=" + JSON.toJSONString(entity.getId()));
        DictionaryDo dicDo1 = dictionaryDoMapperExt.selectByPrimaryKey(entity.getId());
        if (dicDo1 == null) {
            throw new ChanceValidateException("updateEntity-003", "对象不存在");// 对象不存在
        }
        if (entity.getValue() != null) {
            DictionaryDo dicDo2 = new DictionaryDo();
            dicDo2.setId(entity.getId());
            dicDo2.setDicKey(dicDo1.getDicKey());
            dicDo2.setValue(entity.getValue());
            logger.info("dictionaryDoMapperExt.countTheSameTypeAndNameEntity-参数： dicDo2=" + JSON.toJSONString(dicDo2));
            int count = dictionaryDoMapperExt.countTheSameTypeAndNameEntity(dicDo2);
            if (count > 0) {
                throw new ChanceValidateException("updateEntity-005", " 名称重复");// 名称重复
            }
        }

        DictionaryDo dicDo = new DictionaryDo();
        BeanUtils.copyProperties(entity, dicDo);
        logger.info("dictionaryDoMapperExt.updateByPrimaryKeySelective-参数： dicDo=" + JSON.toJSONString(dicDo));
        dictionaryDoMapperExt.updateByPrimaryKeySelective(dicDo);
        logger.info("结束执行：DictionaryBo.updateEntity。。。。。。");
    }

    @Override
    public void removeEntity(DictionaryDto entity) throws ChanceValidateException {
        logger.info("开始执行：DictionaryBo.removeEntity。。。。。。");
        logger.info("removeEntity-参数： entity=" + JSON.toJSONString(entity));
        if (entity == null) {
            throw new ChanceValidateException("removeEntity-001", "参数对象不能为空"); // 参数对象不能为空
        }
        if (entity.getId() == null) {
            throw new ChanceValidateException("removeEntity-002", "字典ID不能为空"); // 字典ID不能为空
        }

        DictionaryDo dicDo = new DictionaryDo();
        BeanUtils.copyProperties(entity, dicDo);
        int count = dictionaryDoMapperExt.deleteByPrimaryKey(dicDo);
        logger.info("if条件值：count > 0 = " + JSON.toJSONString(count > 0));
        if (count > 0) entity.setIsDeleted(YesOrNo.YES.getValue());
        else throw new ChanceValidateException("removeEntity-003", "对象不存在");// 对象不存在
        logger.info("结束执行：DictionaryBo.removeEntity。。。。。。");
    }

    @Override
    public List<DictionaryDto> findEntityByDto(DictionaryDto entity) throws ChanceValidateException {
        if (entity == null) {
            throw new ChanceValidateException("findEntityByDto-001", " 参数对象不能为空"); // 参数对象不能为空
        }
        DictionaryDoExample example = new DictionaryDoExample();
        DictionaryDoExample.Criteria criteria = null;
        if (StringUtils.isNotBlank(entity.getDicKey())) {
            if (criteria == null) {
                criteria = example.createCriteria();
            }
            criteria.andDicKeyEqualTo(entity.getDicKey());
        }
        if (StringUtils.isNotBlank(entity.getValue())) {
            if (criteria == null) {
                criteria = example.createCriteria();
            }
            criteria.andValueEqualTo(entity.getValue());
        }
        if (entity.getId() != null) {
            if (criteria == null) {
                criteria = example.createCriteria();
            }
            criteria.andIdEqualTo(entity.getId());
        }
        example.setOrderByClause("DIC_ORDER");
        example.setPage(new Page(0, 10000000));
        List<DictionaryDo> list = dictionaryDoMapperExt.selectByExample(example);
        List<DictionaryDto> result = null;
        if (list != null && !list.isEmpty()) {
            result = new ArrayList<DictionaryDto>();
            for (DictionaryDo tempDo : list) {
                DictionaryDto tempDto = new DictionaryDto();
                BeanUtils.copyProperties(tempDo, tempDto);
                result.add(tempDto);
            }
        }
        return result;
    }

    @Override
    public DictionaryDto findEntityById(Integer id) throws ChanceValidateException {
        if (id == null || id <= 0) {
            throw new ChanceValidateException("findEntityById-001", "参数为空");
        }
        DictionaryDo dicDo = new DictionaryDo();
        dicDo = dictionaryDoMapperExt.selectByPrimaryKey(id);
        if (dicDo != null) {
            DictionaryDto dicDto = new DictionaryDto();
            BeanUtils.copyProperties(dicDo, dicDto);
            return dicDto;
        }
        return null;
    }

    @Override
    public List<DictionaryDto> findEntityByDtoFuzzy(DictionaryDto entity) throws ChanceValidateException {
        if (entity == null) {
            throw new ChanceValidateException("findEntityByDtoFuzzy-001", "");
        }
        if (StringUtils.isBlank(entity.getDicKey())) {
            throw new ChanceValidateException("findEntityByDtoFuzzy-002", "字典类型不能为空"); // 字典类型不能为空
        }
        DictionaryDoExample example = new DictionaryDoExample();
        DictionaryDoExample.Criteria criteria = example.createCriteria();
        criteria.andDicKeyEqualTo(entity.getDicKey());
        if (StringUtils.isNotBlank(entity.getValue())) {
            criteria.andValueLike("%" + entity.getValue().trim() + "%");
        }
        List<DictionaryDo> list = dictionaryDoMapperExt.selectByExample(example);
        List<DictionaryDto> result = null;
        if (list != null && !list.isEmpty()) {
            result = new ArrayList<DictionaryDto>();
            for (DictionaryDo tempDo : list) {
                DictionaryDto tempDto = new DictionaryDto();
                BeanUtils.copyProperties(tempDo, tempDto);
                result.add(tempDto);
            }
        }
        return result;
    }

    @Override
    public List<DicSettingDto> findDicSettingByDto(DicSettingDto entity) throws ChanceValidateException {
        logger.info("开始执行：DicSettingBo.findEntityByDto");
        logger.info("if条件值：entity == null= " + JSON.toJSONString(entity == null));
        if (entity == null) {
            throw new ChanceValidateException("DSB001");
        }
        logger.info("DicSettingBo.findEntityByDto-参数： dicSettingDto=" + JSON.toJSONString(entity));
        DicSettingDoExample example = new DicSettingDoExample();
        if (entity != null) {
            DicSettingDoExample.Criteria criteria = null;
            logger.info("if条件值：StringUtils.isNotBlank(entity.getKeyType())= "
                    + JSON.toJSONString(StringUtils.isNotBlank(entity.getKeyType())));
            if (StringUtils.isNotBlank(entity.getKeyType())) {
                logger.info("if条件值：criteria == null= " + JSON.toJSONString(criteria == null));
                if (criteria == null) {
                    criteria = example.createCriteria();
                }
                logger.info(" criteria.andKeyTypeEqualTo-参数： entity.getKeyType()="
                        + JSON.toJSONString(entity.getKeyType()));
                criteria.andKeyTypeEqualTo(entity.getKeyType());
            }
            logger.info("if条件值：entity.getParentId() != null= " + JSON.toJSONString(entity.getParentId() != null));
            if (entity.getParentId() != null) {
                logger.info("if条件值：criteria == null= " + JSON.toJSONString(criteria == null));
                if (criteria == null) {
                    criteria = example.createCriteria();
                }
                logger.info(" criteria.andParentIdEqualTo-参数： entity.getParentId()="
                        + JSON.toJSONString(entity.getParentId()));
                criteria.andParentIdEqualTo(entity.getParentId());
            }
            logger.info("if条件值：StringUtils.isNotBlank(entity.getKeyTypeName()) = "
                    + JSON.toJSONString(StringUtils.isNotBlank(entity.getKeyTypeName())));
            if (StringUtils.isNotBlank(entity.getKeyTypeName())) {
                logger.info("if条件值：criteria == null= " + JSON.toJSONString(criteria == null));
                if (criteria == null) {
                    criteria = example.createCriteria();
                }
                logger.info(" criteria.andKeyTypeNameEqualTo-参数： entity.getKeyTypeName()="
                        + JSON.toJSONString(entity.getKeyTypeName()));
                criteria.andKeyTypeNameEqualTo(entity.getKeyTypeName());
            }
        }
        logger.info(" example.setOrderByClause-参数：KEY_TYPE_NAME");
        example.setOrderByClause("KEY_TYPE_NAME");
        logger.info("if条件值：entity.getPage() != null && entity.getPage().getBegin() > 0 && entity.getPage().getLength() > 0 = "
                + JSON.toJSONString(entity.getPage() != null && entity.getPage().getBegin() > 0
                && entity.getPage().getLength() > 0));
        if (entity.getPage() != null && entity.getPage().getBegin() > 0 && entity.getPage().getLength() > 0) {
            logger.info("example.setPage-参数：entity.getPage()=" + JSON.toJSONString(entity.getPage()));
            example.setPage(entity.getPage());
        } else {
            example.setPage(new Page(0, 10000000));
        }
        logger.info(" dicSettingDoMapperExt.selectByExample-参数：example=" + JSON.toJSONString(example));
        List<DicSettingDo> list = dicSettingDoMapperExt.selectByExample(example);
        List<DicSettingDto> result = null;
        if (list != null && !list.isEmpty()) {
            result = new ArrayList<DicSettingDto>();
            for (DicSettingDo tempDo : list) {
                DicSettingDto tempDto = new DicSettingDto();
                BeanUtils.copyProperties(tempDo, tempDto);
                result.add(tempDto);
            }
        }
        logger.info("结束执行：DicSettingBoImpl.findEntityByDto");
        return result;
    }

    @Override
    public DicSettingDto findDicSettingById(Integer id) throws ChanceValidateException {
        logger.info("开始执行: DicSettingBoImpl.findEntityById......");
        logger.info("findEntityById -参数： id {} ", id);
        logger.info("if条件值：id == null = " + JSON.toJSONString(id == null));
        if (id == null) {
            throw new ChanceValidateException("DIC001"); // 参数对象不能为空
        }
        DicSettingDto dicSettingDto = new DicSettingDto();
        logger.info(" dicSettingDoMapperExt.selectByPrimaryKey -参数： id {} ", id);
        DicSettingDo dicSettingDo = dicSettingDoMapperExt.selectByPrimaryKey(id);
        logger.info("if条件值：dicSettingDo != null = " + JSON.toJSONString(dicSettingDo != null));
        if (dicSettingDo != null) {
            BeanUtils.copyProperties(dicSettingDo, dicSettingDto);
        } else {
            dicSettingDto = null;
        }
        logger.info("结束执行： DicSettingBoImpl.findEntityById......");
        return dicSettingDto;
    }

    @Override
    public void updateDicSetting(DicSettingDto entity) throws ChanceValidateException {
        logger.info("开始执行：DicsettingBo.updateEntity。。。。。。");
        logger.info("updateEntity-参数： entity=" + JSON.toJSONString(entity));
        if (entity == null) {
            throw new ChanceValidateException("DICSET001"); // 参数对象不能为空
        }
        if (entity.getId() == null) {
            throw new ChanceValidateException("DICSET002"); // 字典ID不能为空
        }

        DicSettingDo dicSettingDo = new DicSettingDo();
        BeanUtils.copyProperties(entity, dicSettingDo);
        logger.info("dicSettingDoMapperExt.updateByPrimaryKeySelective-参数： dicDo=" + JSON.toJSONString(dicSettingDo));
        dicSettingDoMapperExt.updateByPrimaryKeySelective(dicSettingDo);
        logger.info("结束执行：DicsettingBo.updateEntity。。。。。。");

    }

}
