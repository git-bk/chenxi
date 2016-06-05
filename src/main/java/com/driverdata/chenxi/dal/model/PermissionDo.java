package com.driverdata.chenxi.dal.model;

import java.util.Date;

public class PermissionDo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.id
     *
     * @mbggenerated Tue Nov 10 18:23:38 GMT+08:00 2015
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.name
     *
     * @mbggenerated Tue Nov 10 18:23:38 GMT+08:00 2015
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.descr
     *
     * @mbggenerated Tue Nov 10 18:23:38 GMT+08:00 2015
     */
    private String descr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.gmt_create
     *
     * @mbggenerated Tue Nov 10 18:23:38 GMT+08:00 2015
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.gmt_modified
     *
     * @mbggenerated Tue Nov 10 18:23:38 GMT+08:00 2015
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.is_deleted
     *
     * @mbggenerated Tue Nov 10 18:23:38 GMT+08:00 2015
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.creator
     *
     * @mbggenerated Tue Nov 10 18:23:38 GMT+08:00 2015
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.modifier
     *
     * @mbggenerated Tue Nov 10 18:23:38 GMT+08:00 2015
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.id
     *
     * @return the value of permission.id
     *
     * @mbggenerated Tue Nov 10 18:23:38 GMT+08:00 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.id
     *
     * @param id the value for permission.id
     *
     * @mbggenerated Tue Nov 10 18:23:38 GMT+08:00 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.name
     *
     * @return the value of permission.name
     *
     * @mbggenerated Tue Nov 10 18:23:38 GMT+08:00 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.name
     *
     * @param name the value for permission.name
     *
     * @mbggenerated Tue Nov 10 18:23:38 GMT+08:00 2015
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.descr
     *
     * @return the value of permission.descr
     *
     * @mbggenerated Tue Nov 10 18:23:38 GMT+08:00 2015
     */
    public String getDescr() {
        return descr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.descr
     *
     * @param descr the value for permission.descr
     *
     * @mbggenerated Tue Nov 10 18:23:38 GMT+08:00 2015
     */
    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.gmt_create
     *
     * @return the value of permission.gmt_create
     *
     * @mbggenerated Tue Nov 10 18:23:38 GMT+08:00 2015
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.gmt_create
     *
     * @param gmtCreate the value for permission.gmt_create
     *
     * @mbggenerated Tue Nov 10 18:23:38 GMT+08:00 2015
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.gmt_modified
     *
     * @return the value of permission.gmt_modified
     *
     * @mbggenerated Tue Nov 10 18:23:38 GMT+08:00 2015
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.gmt_modified
     *
     * @param gmtModified the value for permission.gmt_modified
     *
     * @mbggenerated Tue Nov 10 18:23:38 GMT+08:00 2015
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.is_deleted
     *
     * @return the value of permission.is_deleted
     *
     * @mbggenerated Tue Nov 10 18:23:38 GMT+08:00 2015
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.is_deleted
     *
     * @param isDeleted the value for permission.is_deleted
     *
     * @mbggenerated Tue Nov 10 18:23:38 GMT+08:00 2015
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.creator
     *
     * @return the value of permission.creator
     *
     * @mbggenerated Tue Nov 10 18:23:38 GMT+08:00 2015
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.creator
     *
     * @param creator the value for permission.creator
     *
     * @mbggenerated Tue Nov 10 18:23:38 GMT+08:00 2015
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.modifier
     *
     * @return the value of permission.modifier
     *
     * @mbggenerated Tue Nov 10 18:23:38 GMT+08:00 2015
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.modifier
     *
     * @param modifier the value for permission.modifier
     *
     * @mbggenerated Tue Nov 10 18:23:38 GMT+08:00 2015
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}