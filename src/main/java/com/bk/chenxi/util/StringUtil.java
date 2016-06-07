/*
 * Copyright 2015 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.bk.chenxi.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类StringUtil.java的实现描述：TODO 类实现描述
 * 
 * @author luobin 2015年6月18日 下午5:51:58
 */
public class StringUtil extends StringUtils {

    private static final Logger logger           = LoggerFactory.getLogger(StringUtil.class);

    // 一个空的字符串。
    public static final String  EMPTY_STRING     = "";

    // ================================将符号也作为常量，避免半角全角符号导致的狗血异常======================================
    // 逗号
    public static final String  SYMBOL_COMMA     = ",";

    // 等于号
    public static final String  SYMBOL_EQUAL     = "=";

    // 点号
    public static final String  SYMBOL_DOT       = ".";

    // 问号
    public static final String  SYMBOL_QUESTION  = "?";

    // 分号
    public static final String  SYMBOL_SEMICOLON = ";";

    /**
     * 如果传入的值是null，返回空字符串，如果不是null，返回本身。
     * 
     * @param word 传入的源字符串。
     * @return
     */
    public static String getNotNullValue(String word) {
        return (word == null || word.equalsIgnoreCase("null")) ? "" : word;
    }

    /**
     * 如果传入的值是null，返回空字符串，如果不是null，返回本身。
     * 
     * @param word 传入的源字符串。
     * @return
     */
    public static String getNotNullValue(String word, String defaultWord) {
        return (word == null || word.equalsIgnoreCase("null")) ? defaultWord : word;
    }

    /**
     * 根据分隔符从一段字符串拿到对应的列表。应用于以下场景。 2,3,4,5 ==> [2,3,4,5]
     * 
     * @param originWord
     * @param symbol
     * @return
     */
    public static List<String> getSplitListFromString(String originWord, String symbol) {
        List<String> result = new ArrayList<String>();
        if (isBlank(originWord)) {
            return result;
        }

        String[] splitData = originWord.split(symbol);
        if (splitData == null || splitData.length == 0) {
            return result;
        }

        for (String word : splitData) {
            if (isNotBlank(word)) {
                result.add(word);
            }
        }
        return result;
    }

    /**
     * @param originalStr
     * @param symbol
     * @return
     */
    public static List<Long> getLongListFromString(String originalStr, String symbol) {
        List<Long> result = new ArrayList<Long>();
        if (isBlank(originalStr)) {
            return result;
        }

        String[] splitData = originalStr.split(symbol);

        for (String word : splitData) {
            if (isNotBlank(word)) {
                result.add(Long.parseLong(word));
            }
        }
        return result;
    }

    /**
     * 移除左边的0, eg：00000jakjdkf89000988000 转换之后变为 jakjdkf89000988000
     * 
     * @param str
     * @return
     */
    public static String removeLeftZero(String str) {
        int start = 0;
        if (isNotEmpty(str)) {
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] != '0') {
                    start = i;
                    break;
                }
            }
            return str.substring(start);
        }
        return "";
    }

    /**
     * UTF8字符长度(汉字占3个字符长度)
     * 
     * @param str
     * @return
     */
    public static int lengthBytes(String str) {
        try {
            return str == null ? 0 : str.getBytes("utf8").length;
        } catch (UnsupportedEncodingException e) {
            return 0;
        }
    }

    /**
     * 验证邮箱格式
     * 
     * @param String email
     * @return boolean
     */
    public static boolean isEmail(String email) {
        Pattern pattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher matcher = pattern.matcher(email);
        if (isBlank(email)) {
            // System.out.println("邮箱为空");
            return false;
        } else if (!matcher.matches()) {
            // System.out.println("邮箱格式验证不通过：" + matcher.matches());
            return false;
        } else {
            // System.out.println("邮箱格式验证通过：" + matcher.matches());
            return true;
        }
    }

    /**
     * 验证手机号码 中国电信发布中国3G号码段:中国联通185,186;中国移动188,187;中国电信189,180共6个号段。 3G业务专属的180-189号段已基本分配给各运营商使用,
     * 其中180、189分配给中国电信,187、188归中国移动使用,185、186属于新联通。
     * 中国移动拥有号码段：139、138、137、136、135、134、159、158、157（3G）、152、151、150、188（3G）、187（3G）;14个号段
     * 中国联通拥有号码段：130、131、132、155、156（3G）、186（3G）、185（3G）;6个号段 中国电信拥有号码段：133、153、189（3G）、180（3G）;4个号码段 移动:
     * 2G号段(GSM网络)有139,138,137,136,135,134(0-8),159,158,152,151,150 3G号段(TD-SCDMA网络)有157,188,187 147是移动TD上网卡专用号段. 联通:
     * 2G号段(GSM网络)有130,131,132,155,156 3G号段(WCDMA网络)有186,185 电信: 2G号段(CDMA网络)有133,153 3G号段(CDMA网络)有189,180
     * 
     * @param String mobileNo
     * @return boolean
     */
    public static boolean isMobileNO(String mobileNo) {
        Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(mobileNo);
        if (isEmpty(mobileNo)) {
            // System.out.println("手机号码为空");
            return false;
        } else if (!matcher.matches()) {
            // System.out.println("手机号码验证不通过" + matcher.matches());
            return false;
        } else {
            // System.out.println("手机号码验证通过" + matcher.matches());
            return true;
        }

    }

    /**
     * 将BigDecimal格式化成千分位格式字符串
     * 
     * @param BigDecimal money
     * @return String
     */
    public static String formatBigDecimal(BigDecimal number) {
        if (number == null) {
            return null;
        }
        NumberFormat currency = NumberFormat.getCurrencyInstance();// 建立货币格式
        String formatNumber = currency.format(number);
        formatNumber = formatNumber.replace("￥", "");
        return formatNumber;
    }

    /**
     * 将Long格式化成千分位格式字符串
     * 
     * @param Long number
     * @return String
     */
    public static String formatLong(Long number) {
        if (number == null) {
            return null;
        }
        DecimalFormat format = new DecimalFormat("##0,000");// 格式化成千分位
        String numberStr = number.toString();
        String formatNumber = "";// 返回的格式化字符串
        long numberAbs = Math.abs(number);// 求绝对值
        // 如果大于1000转换成千分位格式
        if (numberAbs >= 1000) {
            formatNumber = format.format(number);
        } else {
            formatNumber = numberStr;
        }
        return formatNumber;
    }

    /**
     * 千分位格式化浮点数
     * 
     * @param number
     * @return String
     */
    public static String formatRandowNumber(BigDecimal number) {
        String formatNumber = null;
        // 数据为空
        if (number == null) {
            return formatNumber;
        }
        // 获取小数点后位数
        String dbNum = number.toString();
        int length = 0;
        if (dbNum.indexOf(".") > 0) {
            length = dbNum.substring(dbNum.indexOf(".") + 1).length();
        }
        // 获取格式化字符串
        StringBuilder formatStr = new StringBuilder(",###,##0");
        if (length > 0) {
            formatStr.append(".");
            for (int i = 0; i < length; i++) {
                formatStr.append("0");
            }
        }
        // 格式化数据
        DecimalFormat format = new DecimalFormat(formatStr.toString());
        formatNumber = format.format(number);
        return formatNumber;
    }

    /**
     * 判断小数位数是否符合，支持大数，整数，负数，小数，不支持科学记数法
     * 
     * @param number 数字(String)
     * @param bit 位数
     * @return
     */
    public static boolean isNumberDecimal(String number, int bit) {
        boolean flag = false;
        if (bit < 0 || StringUtil.isBlank(number)) {
            return false;
        }
        BigDecimal bigDecimal = new BigDecimal(number);
        // 格式化位数+1的值
        bigDecimal = bigDecimal.setScale((bit + 1), BigDecimal.ROUND_UP);
        String decimal = String.valueOf(bigDecimal.toPlainString());
        // 判断位数
        if (decimal.indexOf(".") != -1) {// 判断为小数
            String pointString = decimal.split("\\.")[1];// 截取小数位后面的值
            // 去除字符末尾的零
            pointString = pointString.replaceAll("(0)*$", "");
            if (pointString.length() < (bit + 1)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 计算字符串的字节长度
     * 
     * @param s
     * @return
     */
    public static int getStrByteLength(String s) {
        int length = 0;
        if (isBlank(s)) {
            return 0;
        }
        for (int i = 0; i < s.length(); i++) {
            int ascii = Character.codePointAt(s, i);
            if (ascii >= 0 && ascii <= 255) {
                length++;
            } else {
                length += 2;
            }
        }
        return length;
    }

    /**
     * 通过字节长度截取字符串
     * 
     * @param str
     * @param length
     * @return
     */
    public static String getStringByBytes(String str, int length) {
        if (isBlank(str) || length == 0) return "";

        StringBuffer result = new StringBuffer();
        if (StringUtil.getStrByteLength(str) > length) {
            int count = 0;
            char[] chars = str.toCharArray();
            int charLength = chars.length;
            for (int i = 0; i < charLength; i++) {
                int ascii = Character.codePointAt(chars, i);
                if (ascii >= 0 && ascii <= 255) {
                    count++;
                } else {
                    count += 2;
                }
                if (count < length) {
                    result.append(chars[i]);
                }
            }
        } else {
            return str;
        }
        return result.toString();
    }

    /**
     * 验证网址
     * 
     * @param String website
     * @return boolean
     */
    public static boolean isWebsite(String website) {
        Pattern pattern = Pattern.compile("[a-zA-z]+://[^\\s]*");
        Matcher matcher = pattern.matcher(website);
        if (isBlank(website)) {
            logger.info("网址为空");
            return false;
        } else if (!matcher.matches()) {
            logger.info("网址格式验证不通过：" + matcher.matches());
            return false;
        } else {
            logger.info("网址格式验证通过：" + matcher.matches());
            return true;
        }
    }

    public static void main(String[] args) {
        String email = "11.2@163.com";
        logger.info("isEmail:{}", isEmail(email));
        email = "11_2@mail.alibaba-inc.com";
        logger.info("isEmail:{}", isEmail(email));
        email = "11_2ddfs.com";
        logger.info("isEmail:{}", isEmail(email));

        String str = "1221.1";
        logger.info("验证小数1到2位数：{}", str.matches("^[0-9]+(.[0-9]{1,2})?$"));

        str = "http://www.www.com";
        logger.info("{}", isWebsite(str));
        str = "www.www.com";
        logger.info("{}", isWebsite(str));
        str = "https://www.163-cn.com";
        logger.info("{}", isWebsite(str));
    }
}
