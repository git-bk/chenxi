package com.bk.chenxi.dal.mapper;

import javax.annotation.Resource;

@Resource
public interface FieldDoMapperExt extends FieldDoMapper {
    void deletePhysicallyByCreator(String creator);
}