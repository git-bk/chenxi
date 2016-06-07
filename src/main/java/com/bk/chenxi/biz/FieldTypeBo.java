package com.bk.chenxi.biz;

import com.bk.chenxi.dto.FieldTypeDto;

import java.util.List;

/**
 * Created by wb-yaobingke on 2016/5/20.
 */
public interface FieldTypeBo {

    List<FieldTypeDto> listAllFieldTypes();

    /**
     *获取下一个可分配的列名（列名格式：column + index）
     * 按index顺序分配，如果没有可分配的位置，返回null；
     */
    String getNextColumnName(Integer entityId,Integer fieldTypeId);

    FieldTypeDto findById(Integer fieldType);

}
