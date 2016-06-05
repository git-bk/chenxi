package com.driverdata.chenxi.util;

import javax.servlet.http.HttpServletRequest;

import com.driverdata.chenxi.exception.ChanceValidateException;

public class PathUtil {

    /**
     * 获取当前部署的服务器的主目录地址，并去除应用名部分（eclipse tomcat插件的主目录是wtpwebapps，需要替换成webapps才行，直接放在wtpwebapps下访问不到）
     * 
     * @param request
     * @return
     * @throws ChanceValidateException
     */
    public static String getWebRoot(HttpServletRequest request) throws ChanceValidateException {
        try {
            // D:\workspace_uyuni\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\chenxi
            String webRoot = request.getSession().getServletContext().getRealPath("");
            webRoot = webRoot.substring(0, webRoot.lastIndexOf("\\"));
            if (webRoot.contains("wtpwebapps")) {
                webRoot = webRoot.replace("wtpwebapps", "webapps");
            }
            return webRoot;
        } catch (Exception e) {
            throw new ChanceValidateException("getWebRoot-001", "获取web跟目录失败");
        }
    }
}
