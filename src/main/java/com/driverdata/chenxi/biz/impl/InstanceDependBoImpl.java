package com.driverdata.chenxi.biz.impl;

import com.driverdata.chenxi.biz.InstanceDependBo;
import com.driverdata.chenxi.dal.mapper.InstanceDependDoMapperExt;
import com.driverdata.chenxi.dto.InstanceDependDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by wb-yaobingke on 2016/5/13.
 */
public class InstanceDependBoImpl implements InstanceDependBo {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private InstanceDependDoMapperExt instanceDependDoMapperExt;

    @Override
    public InstanceDependDto addChild(InstanceDependDto dependDto) {
        Assert.notNull(dependDto.getParent());
        Assert.notNull(dependDto.getChild());
        Assert.isNull(findParentInstanceId(dependDto.getChild()));
        instanceDependDoMapperExt.insertSelective(dependDto);
        return dependDto;
    }

    @Override
    public Integer findParentInstanceId(Integer childId)  {
        if(childId==null){
            return null;
        }
        List<Integer> list=instanceDependDoMapperExt.findParent(childId);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Integer> findChildrenInstanceIdList(Integer parentId)  {
        if(parentId==null){
            return null;
        }
        return instanceDependDoMapperExt.findChildrenInstanceIdList(parentId);
    }


}
