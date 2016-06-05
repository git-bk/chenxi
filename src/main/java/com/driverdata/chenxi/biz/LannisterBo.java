package com.driverdata.chenxi.biz;

import com.driverdata.chenxi.biz.model.EntityInfo;
import com.driverdata.chenxi.biz.model.InstanceInfo;

/**
 * Created by wb-yaobingke on 2016/5/20.
 */
public interface LannisterBo {
    /**
     * 新增实例
     */
    void addInstance(InstanceInfo instanceInfo);

    /**
     * 查询实体定义信息（仅包含entity、field信息）
     * @param entityId
     * @return
     */
    EntityInfo findEntityInfo(Integer entityId);


    /**
     * 查询实体详细信息
     * @param entityId
     * @return
     */
    InstanceInfo findInstanceInfo(Integer entityId);




}
