/*
 * Copyright 2015 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.driverdata.chenxi.biz;


import com.driverdata.chenxi.dto.DictionaryDto;
import com.driverdata.chenxi.dto.InstanceAssociDto;
import com.driverdata.chenxi.dto.InstanceDto;

import java.util.List;
import java.util.Map;

/**
 * 类AboutBo.java的实现描述：TODO 类实现描述
 * 
 * @author zude 2015年8月5日 上午9:52:01
 */
public interface InstanceAssociBo {

    /**
     * 添加实例之间 的关系
     * 
     */
    public InstanceAssociDto addAssoci(InstanceAssociDto associDto);

    /**
     * （指定关联类型）查询一个实例所有关联信息
     * 
     */
    public Map<String,List<InstanceDto>> findInstanceAssociInfo(Integer nodeId);

    /**
     * （指定关联类型）查询一个实例关联的实例列表
     *
     */
    public List<InstanceDto> findAssociNodes(Integer nodeId, String associTypeId);

    /**
     * 查询实体存在的所有关联关系
     *
     */
    public List<DictionaryDto> listEntityAssociTypes(Integer entityId);

    /**
     * 根据实例id删除实例关联关系
     * 
     */
    public void removeAssoci(InstanceAssociDto associDto);



}
