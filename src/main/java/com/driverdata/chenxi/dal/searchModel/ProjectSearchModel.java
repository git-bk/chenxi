package com.driverdata.chenxi.dal.searchModel;

import java.io.Serializable;

public class ProjectSearchModel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 9192166828705833896L;
    private String            keyWord;                                // 项目关键词
    private Integer[]         fields;                                 // 领域（多个）
    private Integer[]         phases;                                 // 阶段（多个）
    private Integer[]         areaIds;                                // 地域(多个)
    private String[]          areas;                                  // 地域（多个）
    private Integer[]         status;                                 // 项目审核状态（多个）
    private String            focusMark        = "0";                 // 关注过滤条件（true：表示选中，false：未选中）
    private String            praiseMark       = "0";                 // 赞过滤条件（true：表示选中，false：未选中）
    private Integer           pageSize         = 10;                  // 单页条数
    private Integer           pageIndex        = 1;                   // 页码
    // 第一排序：所有当前用户不感兴趣的项目，排序优先级小于其他项目
    // 第二排序：项目审核通过时间从大到小
    // 第三排序：项目名称字符排序，从小到大
    private String            orderColumn;                            // 排序列
    private String            orderType;                              // 排序类型（desc,asc）
    private Integer           userId;
    private Boolean           isSaveCondition  = false;               // 是否保存搜索记录

    public Integer[] getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(Integer[] areaIds) {
        this.areaIds = areaIds;
    }

    public void setIsSaveCondition(Boolean isSaveCondition) {
        this.isSaveCondition = isSaveCondition;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer[] getFields() {
        return fields;
    }

    public void setFields(Integer[] fields) {
        this.fields = fields;
    }

    public Integer[] getPhases() {
        return phases;
    }

    public void setPhases(Integer[] phases) {
        this.phases = phases;
    }

    public String[] getAreas() {
        return areas;
    }

    public void setAreas(String[] areas) {
        this.areas = areas;
    }

    public Integer[] getStatus() {
        return status;
    }

    public void setStatus(Integer[] status) {
        this.status = status;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public String getFocusMark() {
        return focusMark;
    }

    public void setFocusMark(String focusMark) {
        this.focusMark = focusMark;
    }

    public String getPraiseMark() {
        return praiseMark;
    }

    public void setPraiseMark(String praiseMark) {
        this.praiseMark = praiseMark;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Boolean getIsSaveCondition() {
        return isSaveCondition;
    }
}
