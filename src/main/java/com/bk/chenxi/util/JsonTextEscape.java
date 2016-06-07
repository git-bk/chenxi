package com.bk.chenxi.util;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * 描述：json转义函数
 */
public class JsonTextEscape {

    public enum EscapeType {
        // no escape
        RAW,
        // js encode
        JAVASCRIPT_Escape,
        // html code
        HTML_Escape
    };

    private static Class  stringEscapeUtil;
    private static Method method;

    static {
        init();
    }

    /**
     * 初始化函数，动态加载com.alibaba.citrus.util.StringEscapeUtil
     */
    private static void init() {
        try {
            stringEscapeUtil = Class.forName("com.alibaba.citrus.util.StringEscapeUtil");
            method = stringEscapeUtil.getMethod("escapeHtml", String.class);
        } catch (Throwable e) {
            method = null;
        }
        // do something
        jsonTextFilter("{}");
    }

    public static String escapeJson(String jsontext) {
        if (jsontext != null) {
            return jsonTextFilter(jsontext);
        } else {
            return null;
        }
    }

    /**
     * 封装escapeHtml 如果存在对webx的依赖，及能加载com.alibaba.citrus.util.StringEscapeUtil，则使用StringEscapeUtil.escapeHtml
     * 否则使用SecurityUtil.escapeHtml
     * 
     * @param str
     * @return
     */
    private static String escapeHtml(String str) {
        if (method != null) {
            try {
                return (String) method.invoke(null, str);
            } catch (Throwable e) {

            }
        }

        return StringEscapeUtils.escapeHtml(str);

    }

    /**
     * VauleFilter函数，转义key、value中的value值
     */
    private static ValueFilter vf = new ValueFilter() {

                                      @Override
                                      @SuppressWarnings({ "rawtypes", "unchecked" })
                                      public Object process(Object source, String name, Object value) {
                                          if (value instanceof String) {
                                              String v = (String) value;
                                              return escape(v);
                                          } else if (value instanceof List) {
                                              List list = (List) value;

                                              for (int i = 0; i < list.size(); i++) {
                                                  Object obj = list.get(i);

                                                  if (obj instanceof String) {
                                                      String str = (String) obj;
                                                      obj = escape(str);
                                                      list.set(i, obj);
                                                  }
                                                  // end of if
                                              }
                                              // end of for
                                              return list;
                                          }
                                          // end of if

                                          return value;
                                      }

                                      /**
                                       * @param str
                                       * @return
                                       */
                                      private String escape(String str) {

                                          return escapeHtml(str);
                                      }
                                  };

    /**
     * VauleFilter函数，转义key、value中的value值
     */
    private static NameFilter  nf = new NameFilter() {

                                      @Override
                                      public String process(Object source, String name, Object value) {

                                          return escapeHtml(name);
                                      }
                                  };

    /**
     * @param
     * @return
     */
    public static String jsonTextFilter(String jsonText, EscapeType escapeType) {

        if (StringUtil.isBlank(jsonText)) {
            return jsonText;
        }

        // long start = 0 ;
        // long end = 0;

        // parse from jsonText to fastjson
        String rt = null;
        try {
            // start = System.nanoTime();
            Object obj = JSON.parse(jsonText);
            // end = System.nanoTime();
            // System.out.println("JSON.parse CostTime: "+(end-start)+"ns");

            SerializeWriter out = new SerializeWriter();
            JSONSerializer serialzer = new JSONSerializer(out);

            // end = System.nanoTime();
            // System.out.println("new serialzer CostTime: "+(end-start)+"ns");

            // serialize filter function
            serialzer.getNameFilters().add(nf);
            serialzer.getValueFilters().add(vf);

            // end = System.nanoTime();
            // System.out.println("serialzer filter CostTime: "+(end-start)+"ns");

            serialzer.write(obj);

            // end = System.nanoTime();
            // System.out.println("serialzer.write CostTime: "+(end-start)+"ns");

            rt = out.toString();

            // end = System.nanoTime();
            // System.out.println("toString CostTime: "+(end-start)+"ns");
            out.close();
        } catch (Exception e) {
            return null;
        }

        // jsencode or not
        if (escapeType == EscapeType.HTML_Escape) {
            return rt;
        } else if ((escapeType == EscapeType.JAVASCRIPT_Escape)) {
            return jsEncode(rt);

        } else {
            return rt;
        }

    }

    public static String jsEncode(String s) {
        if (s != null) {
            return StringEscapeUtils.escapeJavaScript(s);
        } else {
            return null;
        }
    }

    public static String jsonTextFilter(String jsonText) {
        return jsonTextFilter(jsonText, EscapeType.HTML_Escape);
    }

    public static String jsonTextFilter(Object jsonObj, EscapeType escapeType) {
        return jsonTextFilter(jsonObj.toString(), escapeType);
    }

    public static String jsonTextFilter(Object jsonObj) {
        return jsonTextFilter(jsonObj.toString(), EscapeType.HTML_Escape);
    }

}
