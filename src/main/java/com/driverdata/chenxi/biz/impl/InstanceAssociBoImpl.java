package com.driverdata.chenxi.biz.impl;

import com.driverdata.chenxi.biz.InstanceAssociBo;
import com.driverdata.chenxi.dto.DictionaryDto;
import com.driverdata.chenxi.dto.InstanceAssociDto;
import com.driverdata.chenxi.dto.InstanceDto;

import java.util.List;
import java.util.Map;

/**
 * Created by wb-yaobingke on 2016/5/13.
 */
public class InstanceAssociBoImpl implements InstanceAssociBo {
    @Override
    public InstanceAssociDto addAssoci(InstanceAssociDto associDto)  {
        return null;
    }

    @Override
    public Map<String,List<InstanceDto>> findInstanceAssociInfo(Integer nodeId)  {
        return null;
    }

    @Override
    public List<InstanceDto> findAssociNodes(Integer nodeId, String associTypeId)  {
        return null;
    }

    @Override
    public List<DictionaryDto> listEntityAssociTypes(Integer instanceId)  {
        return null;
    }

    @Override
    public void removeAssoci(InstanceAssociDto associDto)  {

    }
}
