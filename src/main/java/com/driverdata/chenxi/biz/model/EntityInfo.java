package com.driverdata.chenxi.biz.model;

import com.driverdata.chenxi.dto.*;

import java.util.List;

/**
 * 实体完整信息
 * Created by wb-yaobingke on 2016/5/20.
 */
public class EntityInfo {
    /**
     * 实体信息
     */
    private EntityDto entity;
    /**
     * 字段信息
     */
    private List<FieldDto> fields;

    private EntityDto parentEntity;

    private List<DictionaryDto> associEntityList;

    public List<DictionaryDto> getAssociEntityList() {
        return associEntityList;
    }

    public void setAssociEntityList(List<DictionaryDto> associEntityList) {
        this.associEntityList = associEntityList;
    }

    public EntityDto getParentEntity() {
        return parentEntity;
    }

    public void setParentEntity(EntityDto parentEntity) {
        this.parentEntity = parentEntity;
    }

    public EntityDto getEntity() {
        return entity;
    }

    public void setEntity(EntityDto entity) {
        this.entity = entity;
    }

    public List<FieldDto> getFields() {
        return fields;
    }

    public void setFields(List<FieldDto> fields) {
        this.fields = fields;
    }


}
