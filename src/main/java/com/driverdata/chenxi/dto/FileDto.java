package com.driverdata.chenxi.dto;

import java.io.Serializable;

import com.driverdata.chenxi.dal.model.FileDo;

public class FileDto extends FileDo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3473930630888925555L;
    private String            encodeId;

    public String getEncodeId() {
        return encodeId;
    }

    public void setEncodeId(String encodeId) {
        this.encodeId = encodeId;
    }

}
