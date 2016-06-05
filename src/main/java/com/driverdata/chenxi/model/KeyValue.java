package com.driverdata.chenxi.model;

import java.io.Serializable;

/**
 * 
 */
public class KeyValue implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4325977987975955626L;

    /**
     * 
     */
    public KeyValue(){
    }

    /**
     * 
     */
    public Integer id;

    /**
     * 
     */
    public String  text;

    private String isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

}
