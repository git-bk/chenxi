package com.driverdata.chenxi.dto;

public class BusinessDataDto extends ProjectBusinessDataDo {

    /**
     * 统计日期字符串
     */
    private String dateStatStr;

    /**
     * 修改标记
     */
    private String isModified;

    public String getDateStatStr() {
        return dateStatStr;
    }

    public void setDateStatStr(String dateStatStr) {
        this.dateStatStr = dateStatStr;
    }

    public String getIsModified() {
        return isModified;
    }

    public void setIsModified(String isModified) {
        this.isModified = isModified;
    }
}
