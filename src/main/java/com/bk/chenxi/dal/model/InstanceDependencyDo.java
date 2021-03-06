package com.bk.chenxi.dal.model;

import java.util.Date;

public class InstanceDependencyDo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column i_dependency.id
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column i_dependency.gmt_create
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column i_dependency.gmt_modified
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column i_dependency.is_deleted
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column i_dependency.creator
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column i_dependency.modifier
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column i_dependency.parent
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    private Integer parent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column i_dependency.child
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    private Integer child;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column i_dependency.id
     *
     * @return the value of i_dependency.id
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column i_dependency.id
     *
     * @param id the value for i_dependency.id
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column i_dependency.gmt_create
     *
     * @return the value of i_dependency.gmt_create
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column i_dependency.gmt_create
     *
     * @param gmtCreate the value for i_dependency.gmt_create
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column i_dependency.gmt_modified
     *
     * @return the value of i_dependency.gmt_modified
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column i_dependency.gmt_modified
     *
     * @param gmtModified the value for i_dependency.gmt_modified
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column i_dependency.is_deleted
     *
     * @return the value of i_dependency.is_deleted
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column i_dependency.is_deleted
     *
     * @param isDeleted the value for i_dependency.is_deleted
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column i_dependency.creator
     *
     * @return the value of i_dependency.creator
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column i_dependency.creator
     *
     * @param creator the value for i_dependency.creator
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column i_dependency.modifier
     *
     * @return the value of i_dependency.modifier
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column i_dependency.modifier
     *
     * @param modifier the value for i_dependency.modifier
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column i_dependency.parent
     *
     * @return the value of i_dependency.parent
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    public Integer getParent() {
        return parent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column i_dependency.parent
     *
     * @param parent the value for i_dependency.parent
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    public void setParent(Integer parent) {
        this.parent = parent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column i_dependency.child
     *
     * @return the value of i_dependency.child
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    public Integer getChild() {
        return child;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column i_dependency.child
     *
     * @param child the value for i_dependency.child
     *
     * @mbggenerated Fri May 13 15:55:56 CST 2016
     */
    public void setChild(Integer child) {
        this.child = child;
    }
}