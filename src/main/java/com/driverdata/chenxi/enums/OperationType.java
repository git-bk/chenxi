package com.driverdata.chenxi.enums;

/**
 * 类CheckStatus.java的实现描述：TODO 类实现描述
 * 
 * @author lmc 2015年6月9日 上午10:18:52
 */
public enum OperationType {
    /**
     * 赞
     */
    PRAISE("0"),
    /**
     * 关注
     */
    FOCUS("1"),
    /**
     * 不感兴趣
     */
    NOT_INTEREST("2"),
    /**
     * 完善项目
     */
    PERFECT("3"),
    /**
     * 审核通过
     */
    AUDIT_PASS("4"),
    /**
     * 审核不通过
     */
    AUDIT_NOT_PASS("5"),
    /**
     * 推荐项目
     */
    RECOMMEND_CREATOR("6"),
    /**
     * 推荐项目给投资经理
     */
    RECOMMEND_INVEST("7"),
    /**
     * 查看项目
     */
    VIEW("8"),
    /**
     * 发布出错
     */
    RELEASE_FAIL("release_fail");

    private String value;

    private OperationType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
