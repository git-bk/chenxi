package com.bk.chenxi.biz;

import com.bk.chenxi.dto.FieldDto;

import java.util.List;

/**
 * Created by wb-yaobingke on 2016/5/13.
 */
public interface FieldBo {

    /*新增字段*/
    FieldDto addField(FieldDto entity) ;

    /*删除一个字段*/
    void delField(FieldDto entity);

    /*更新字段，不能修改字段类型（会面临历史数据问题）*/
    void updateField(FieldDto entity);

    /*根据id查询实例*/
    FieldDto findById(Integer id) ;

    /*查询实体的所有字段列表*/
    List<FieldDto> findFieldList(Integer entityId);
    
}
