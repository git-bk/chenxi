package com.bk.chenxi.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bk.chenxi.model.ResultModel;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.alibaba.fastjson.JSON;

public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {

    public AjaxAuthenticationFailureHandler(){
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        // JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(response.getOutputStream(),
        // JsonEncoding.UTF8);
        try {
            // 失败为1
            ResultModel<String> rs = new ResultModel<String>();
            rs.setReturnValue("onAuthenticationFailure");
            // objectMapper.writeValue(jsonGenerator, rs);
            response.getWriter().print("jsonpCallback(" + JSON.toJSON(rs).toString() + ")");
        } catch (JsonProcessingException ex) {
            throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
        }
    }

}
