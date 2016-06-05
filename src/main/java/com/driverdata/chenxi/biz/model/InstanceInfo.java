package com.driverdata.chenxi.biz.model;

import com.driverdata.chenxi.dto.InstanceDto;

/**
 * 实体完整信息
 * Created by wb-yaobingke on 2016/5/20.
 */
public class InstanceInfo {


    private InstanceDto instance;
    /**
     * 实体信息
     */
    private EntityInfo entityInfo;

    /**
     * 父亲实例Id
     */
    private Integer parentInstanceId;


    public void setInstance(InstanceDto instance) {
        this.instance = instance;
    }

    public EntityInfo getEntityInfo() {
        return entityInfo;
    }

    public InstanceDto getInstance() {
        return instance;
    }

    public void setEntityInfo(EntityInfo entityInfo) {
        this.entityInfo = entityInfo;
    }

    public Integer getParentInstanceId() {
        return parentInstanceId;
    }

    public void setParentInstanceId(Integer parentInstanceId) {
        this.parentInstanceId = parentInstanceId;
    }
}
