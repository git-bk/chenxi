package com.driverdata.chenxi.dal.mapper;

import java.util.List;

import javax.annotation.Resource;

@Resource
public interface ProDeliveryLogDoMapperExt extends ProDeliveryLogDoMapper {

    /**
     * 查询项目投递记录列表
     * 
     * @param privDeliveryLogDto
     * @return list
     */
    public List<ProDeliveryLogDto> getProDeliveryLogs(ProDeliveryLogDto privDeliveryLogDto);
}
