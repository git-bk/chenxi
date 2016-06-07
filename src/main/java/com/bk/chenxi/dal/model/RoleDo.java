package com.bk.chenxi.dal.model;

import java.util.Date;

public class RoleDo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.id
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.name
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.gmt_create
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.gmt_modified
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.is_deleted
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.creator
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.modifier
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.descr
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    private String descr;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.id
     *
     * @return the value of role.id
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.id
     *
     * @param id the value for role.id
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.name
     *
     * @return the value of role.name
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.name
     *
     * @param name the value for role.name
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.gmt_create
     *
     * @return the value of role.gmt_create
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.gmt_create
     *
     * @param gmtCreate the value for role.gmt_create
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.gmt_modified
     *
     * @return the value of role.gmt_modified
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.gmt_modified
     *
     * @param gmtModified the value for role.gmt_modified
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.is_deleted
     *
     * @return the value of role.is_deleted
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.is_deleted
     *
     * @param isDeleted the value for role.is_deleted
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.creator
     *
     * @return the value of role.creator
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.creator
     *
     * @param creator the value for role.creator
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.modifier
     *
     * @return the value of role.modifier
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.modifier
     *
     * @param modifier the value for role.modifier
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.descr
     *
     * @return the value of role.descr
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    public String getDescr() {
        return descr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.descr
     *
     * @param descr the value for role.descr
     *
     * @mbggenerated Mon Jun 06 01:23:01 CST 2016
     */
    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }
}