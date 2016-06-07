package com.bk.chenxi.exception;

public class ChanceValidateException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final String      code;
    private final String      info;

    public String getInfo() {
        return info;
    }

    /**
     * @param code
     */
    public ChanceValidateException(String code, String info){
        super();
        this.code = code;
        this.info = info;
    }

    public ChanceValidateException(String code){
        super();
        this.code = code;
        this.info = null;
    }

    public String getCode() {
        return code;
    }
}
