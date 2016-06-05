package com.driverdata.chenxi.dal.mapper;

import com.driverdata.chenxi.dal.model.EntityDo;

import javax.annotation.Resource;

@Resource
public interface EntityDoMapperExt extends EntityDoMapper {
   /**
    * 物理删除记录（仅单元测试使用）
    * @param creator
     */
   void deletePhysicallyByCreator(String creator);

   EntityDo selectByEntityKey(String entityKey);
}