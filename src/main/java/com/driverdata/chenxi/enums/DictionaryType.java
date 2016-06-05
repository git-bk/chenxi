package com.driverdata.chenxi.enums;

/**
 * @author meicheng.lmc
 * @version 创建时间：2015年5月16日 上午10:30:38 是、否枚举
 */
public enum DictionaryType {
    /**
     * 其它需求
     */
    OTHER_REQUEST("otherRequest"),
    /**
     * 项目所下阶段
     */
    PHASE("phase"),
    /**
     * 领域
     */
    Field("field"),
    /**
     * 融资轮次
     */
    FINANCING_ROUND("financingRound"),
    /**
     * 地域
     */
    AREA("areas"),
    /**
     * 关于
     */
    ABOUT("about"),
    /**
     * 文章类型
     */
    ARTICLE_CATEGORY("article_categroy"),
    /**
     * 首页推广数量
     */
    HOME_PROMOTION_COUNT("home_promotion_count"),
    /**
     * 热门推广数量
     */
    POPULAR_PROMOTION_COUNT("popular_promotion_count"),
    /**
     * 发布失败发送邮箱
     */
    RELEASE_FAIL_EMAIL("release_fail_email");

    private String value;

    private DictionaryType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
