package com.bk.chenxi.dto;

import com.bk.chenxi.dal.model.FieldTypeDo;

/**
 * Created by wb-yaobingke on 2016/5/20.
 */
public class FieldTypeDto extends FieldTypeDo {
    private FieldDataTypeDto fieldDataTypeDto;

    public FieldDataTypeDto getFieldDataTypeDto() {
        return fieldDataTypeDto;
    }

    public void setFieldDataTypeDto(FieldDataTypeDto fieldDataTypeDto) {
        this.fieldDataTypeDto = fieldDataTypeDto;
    }
}
