package com.bk.chenxi.dto;

import com.bk.chenxi.dal.model.FieldDataTypeDo;

/**
 * Created by wb-yaobingke on 2016/5/20.
 */
public class FieldDataTypeDto extends FieldDataTypeDo {
    /**
     * 剩余的列数
     */
    private Integer restPosition;
    /**
     * 总共提供的列数
     */
    private Integer sumPosition;

    public Integer getRestPosition() {
        return restPosition;
    }

    public void setRestPosition(Integer restPosition) {
        this.restPosition = restPosition;
    }

    public Integer getSumPosition() {
        return super.getEndIndex()-super.getStartIndex()+1;
    }

}
