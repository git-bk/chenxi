package com.bk.chenxi.biz.impl;

import com.bk.chenxi.biz.*;
import com.bk.chenxi.biz.model.EntityInfo;
import com.bk.chenxi.biz.model.InstanceInfo;
import com.bk.chenxi.dto.EntityDto;
import com.bk.chenxi.dto.FieldDto;
import com.bk.chenxi.dto.InstanceDto;
import com.bk.chenxi.util.LannisterUtil;
import com.bk.chenxi.enums.FieldTypeEnum;
import com.bk.chenxi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by wb-yaobingke on 2016/5/22.
 */
public class LannisterBoImpl implements LannisterBo {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private EntityBo entityBo;

    @Autowired
    private FieldBo fieldBo;

    @Autowired
    private InstanceAssociBo instanceAssociBo;

    @Autowired
    private InstanceDependBo instanceDependBo;

    @Autowired
    private InstanceBo instanceBo;


    @Override
    public void addInstance(InstanceInfo instanceInfo) {

    }

    @Override
    public EntityInfo findEntityInfo(Integer entityId) {
        if (entityId == null) {
            return null;
        }
        EntityDto entity = entityBo.findById(entityId);
        if (entity == null) {
            return null;
        }
        List<FieldDto> f_list = fieldBo.findFieldList(entityId);
        EntityInfo entityInfo = new EntityInfo();
        entityInfo.setEntity(entity);
        entityInfo.setFields(f_list);
        if (entity.getParent() != null) {
            entityInfo.setParentEntity(entityBo.findById(entity.getParent()));
        }
        entityInfo.setAssociEntityList(instanceAssociBo.listEntityAssociTypes(entityId));
        return entityInfo;
    }

    @Override
    public InstanceInfo findInstanceInfo(Integer instanceId) {
        if (instanceId == null) {
            return null;
        }
        InstanceDto instanceDto = instanceBo.findById(instanceId);
        if (instanceDto == null) {
            return null;
        }
        InstanceInfo instanceInfo = new InstanceInfo();
        instanceInfo.setInstance(instanceDto);
        EntityInfo entityInfo = this.findEntityInfo(instanceDto.getEntityId());
        instanceInfo.setEntityInfo(entityInfo);
        if (entityInfo.getEntity() != null && entityInfo.getEntity().getParent() != null) {
            instanceInfo.setParentInstanceId(instanceDependBo.findParentInstanceId(instanceDto.getId()));
        }
        return instanceInfo;
    }



    public InstanceDto convertRequestToInstance(String entityKey, HttpServletRequest request) {
        Assert.notNull(request);
        Assert.hasText(entityKey);
        Map map = request.getParameterMap();
        InstanceDto dto = new InstanceDto();
        EntityDto entityDto = entityBo.findByEntityKey(entityKey);
        dto.setEntityId(entityDto.getId());
        List<FieldDto> fieldList = fieldBo.findFieldList(entityDto.getId());
        //遍历实体的所有字段
        for (FieldDto fieldDto : fieldList) {
                if (StringUtil.equals(fieldDto.getFieldTypeInfo().getName(), FieldTypeEnum.list.name())) {
                    //处理列表类型字段
                    String[] array = ((String[]) map.get(fieldDto.getColumnName() + "[]"));
                    if (array != null && array.length > 0) {
                        LannisterUtil.setColumnValue(dto, fieldDto.getColumnName(), LannisterUtil.arrToStr(array));
                    }
                }else {
                    //处理其他单值类型字段
                    if (StringUtil.isNotBlank(request.getParameter(fieldDto.getColumnName()))) {
                        LannisterUtil.setColumnValue(dto, fieldDto.getColumnName(),
                                request.getParameter(fieldDto.getColumnName()).trim());
                    } else {
                        //注意：没有传递值过来的字段会被置空
                        LannisterUtil.setColumnValue(dto, fieldDto.getColumnName(), "");
                    }
                }
        }
        return dto;
    }

}
