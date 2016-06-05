package com.driverdata.chenxi.enums;

/**
 * @author meicheng.lmc
 * @version 创建时间：2015年5月16日 上午10:30:38 是、否枚举
 */
public enum MessageType {
    /**
     * 其它需求
     */
    EMAIL("email"), SMS("sms");

    private String value;

    private MessageType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
