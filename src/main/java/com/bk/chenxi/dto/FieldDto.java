package com.bk.chenxi.dto;

import com.bk.chenxi.dal.model.FieldDo;

/**
 * Created by wb-yaobingke on 2016/5/13.
 */
public class FieldDto extends FieldDo {


    public FieldTypeDto getFieldTypeInfo() {
        return fieldTypeInfo;
    }

    public void setFieldTypeInfo(FieldTypeDto fieldTypeInfo) {
        this.fieldTypeInfo = fieldTypeInfo;
    }

    private FieldTypeDto fieldTypeInfo;
}
