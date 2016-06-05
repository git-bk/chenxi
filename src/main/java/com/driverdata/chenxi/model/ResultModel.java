package com.driverdata.chenxi.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.driverdata.chenxi.exception.ChanceValidateException;

public class ResultModel<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 9041530013023432967L;
    private Boolean           success          = true;
    private T                 returnValue;
    private String            errorCode;
    private String            errorDesc;
    private String            exceptionDesc;
    private String            returnClassName;

    public static <E extends Serializable> ResultModel<E> newInstance() {
        return new ResultModel<E>();
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getExceptionDesc() {
        return exceptionDesc;
    }

    public void setExceptionDesc(String exceptionDesc) {
        this.exceptionDesc = exceptionDesc;
    }

    public T getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(T returnValue) {
        this.returnValue = returnValue;
        if (returnValue != null) {
            this.returnClassName = returnValue.getClass().getCanonicalName();
        }
    }

    public String getReturnClassName() {
        return returnClassName;
    }

    public void setReturnClassName(String returnClassName) {
        this.returnClassName = returnClassName;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        success = false;
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        success = false;
        this.errorDesc = errorDesc;
    }

    public void setChanceError(ChanceValidateException e) {
        success = false;
        this.errorCode = e.getCode();
        this.errorDesc = e.getInfo();
    }

    public void setArgumentError(IllegalArgumentException e) {
        success = false;
        this.errorDesc = e.getMessage();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
