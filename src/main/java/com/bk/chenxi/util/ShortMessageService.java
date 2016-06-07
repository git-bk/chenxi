package com.bk.chenxi.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

public class ShortMessageService {

    // @Value("#{configProperties['sms_sign']}")
    // public String sms_sign = "chenxi";

    public static String getRandomCode() {
        Random random = new java.util.Random();// 定义随机类
        int result = random.nextInt(9) + 1;// 返回[0,10)集合中的整数，注意不包括10
        int i = (int) ((Math.random() + result) * 100000);
        return new Integer(i).toString();
    }

    public static String seneSms(String mobile, String content) throws Exception {

        String sign = "chenxi";

        StringBuffer sb = new StringBuffer("http://sms.1xinxi.cn/asmx/smsservice.aspx?");
        sb.append("name=datadriversms");

        sb.append("&pwd=9C3D2E42C9487B23B46370C3DCBE");

        sb.append("&mobile=" + mobile);

        sb.append("&content=" + URLEncoder.encode(content, "UTF-8"));

        sb.append("&stime=");

        sb.append("&sign=" + URLEncoder.encode(sign, "UTF-8"));

        sb.append("&type=pt&extno=");
        URL url = new URL(sb.toString());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");

        InputStream is = url.openStream();

        String returnStr = ShortMessageService.convertStreamToString(is);
        System.out.println("+++++++++++++++++++++++" + returnStr + "手机：" + mobile);
        return returnStr;
    }

    /**
     * @param is
     * @return
     */
    public static String convertStreamToString(InputStream is) {
        StringBuilder sb1 = new StringBuilder();
        byte[] bytes = new byte[4096];
        int size = 0;

        try {
            while ((size = is.read(bytes)) > 0) {
                String str = new String(bytes, 0, size, "UTF-8");
                sb1.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb1.toString();
    }

    public static void main(String[] args) {
        try {
            // ShortMessageService ser = new ShortMessageService();
            System.out.println(ShortMessageService.seneSms("13764494179", "hi, biki"));
            System.out.println(getRandomCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
