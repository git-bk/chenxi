package com.bk.chenxi.enums;

/** 
 * @author meicheng.lmc
 * @version 创建时间：2015年5月16日 上午10:30:38 
 * 是、否枚举
 */
public enum YesOrNo {
    YES("y"),NO("n");
    
    private String value;
    
    private YesOrNo(String value){
        this.value=value;
    }
    
    public String getValue(){
        return value;
    }
}
