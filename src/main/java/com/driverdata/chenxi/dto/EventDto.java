package com.driverdata.chenxi.dto;

public class EventDto extends ProjectEventDo {

    /**
     * 里程碑日期字符串
     */
    private String dateMilestoneStr;

    /**
     * 修改标记
     */
    private String isModified;

    public String getDateMilestoneStr() {
        return dateMilestoneStr;
    }

    public void setDateMilestoneStr(String dateMilestoneStr) {
        this.dateMilestoneStr = dateMilestoneStr;
    }

    public String getIsModified() {
        return isModified;
    }

    public void setIsModified(String isModified) {
        this.isModified = isModified;
    }

}
