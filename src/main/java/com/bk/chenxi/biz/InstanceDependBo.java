/*
 * Copyright 2015 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.bk.chenxi.biz;


import com.bk.chenxi.dto.InstanceDependDto;

import java.util.List;

/**
 * 类AboutBo.java的实现描述：TODO 类实现描述
 * 
 * @author zude 2015年8月5日 上午9:52:01
 */
public interface InstanceDependBo {

    /**
     * 添加实例之间 的关系
     *
     */
    public InstanceDependDto addChild(InstanceDependDto dependDto) ;

    /**
     * 查询父亲实例Id
     *
     */
    public Integer findParentInstanceId(Integer childId);

    /**
     * 查询子实例列表
     *
     */
    public List<Integer> findChildrenInstanceIdList(Integer parentId) ;



}
