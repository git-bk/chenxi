package com.bk.chenxi.biz;

import com.bk.chenxi.biz.model.InstanceInfo;
import com.bk.chenxi.biz.model.EntityInfo;

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
