package com.driverdata.chenxi.dal.model;

import java.util.Date;

public class DictionaryDo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dictionary.id
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dictionary.gmt_create
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dictionary.gmt_modified
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dictionary.value
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    private String value;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dictionary.dic_key
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    private String dicKey;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dictionary.dic_order
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    private Integer dicOrder;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dictionary.is_deleted
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dictionary.creator
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dictionary.modifier
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dictionary.is_system
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    private String isSystem;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dictionary.id
     *
     * @return the value of dictionary.id
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dictionary.id
     *
     * @param id the value for dictionary.id
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dictionary.gmt_create
     *
     * @return the value of dictionary.gmt_create
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dictionary.gmt_create
     *
     * @param gmtCreate the value for dictionary.gmt_create
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dictionary.gmt_modified
     *
     * @return the value of dictionary.gmt_modified
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dictionary.gmt_modified
     *
     * @param gmtModified the value for dictionary.gmt_modified
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dictionary.value
     *
     * @return the value of dictionary.value
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    public String getValue() {
        return value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dictionary.value
     *
     * @param value the value for dictionary.value
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dictionary.dic_key
     *
     * @return the value of dictionary.dic_key
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    public String getDicKey() {
        return dicKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dictionary.dic_key
     *
     * @param dicKey the value for dictionary.dic_key
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    public void setDicKey(String dicKey) {
        this.dicKey = dicKey == null ? null : dicKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dictionary.dic_order
     *
     * @return the value of dictionary.dic_order
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    public Integer getDicOrder() {
        return dicOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dictionary.dic_order
     *
     * @param dicOrder the value for dictionary.dic_order
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    public void setDicOrder(Integer dicOrder) {
        this.dicOrder = dicOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dictionary.is_deleted
     *
     * @return the value of dictionary.is_deleted
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dictionary.is_deleted
     *
     * @param isDeleted the value for dictionary.is_deleted
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dictionary.creator
     *
     * @return the value of dictionary.creator
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dictionary.creator
     *
     * @param creator the value for dictionary.creator
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dictionary.modifier
     *
     * @return the value of dictionary.modifier
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dictionary.modifier
     *
     * @param modifier the value for dictionary.modifier
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dictionary.is_system
     *
     * @return the value of dictionary.is_system
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    public String getIsSystem() {
        return isSystem;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dictionary.is_system
     *
     * @param isSystem the value for dictionary.is_system
     *
     * @mbggenerated Thu Nov 05 17:28:11 GMT+08:00 2015
     */
    public void setIsSystem(String isSystem) {
        this.isSystem = isSystem == null ? null : isSystem.trim();
    }
}