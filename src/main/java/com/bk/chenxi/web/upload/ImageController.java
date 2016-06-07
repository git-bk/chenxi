package com.bk.chenxi.web.upload;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bk.chenxi.biz.UserBo;
import com.bk.chenxi.dto.ImageDto;
import com.bk.chenxi.exception.ChanceValidateException;
import com.bk.chenxi.model.ResultModel;
import com.bk.chenxi.util.JSONUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bk.chenxi.biz.ImageBo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ImageController {

    @Autowired
    private UserBo userBo;
    @Autowired
    private ImageBo imageBo;

    @RequestMapping(value = "/image/upload.htm")
    public String home(Locale locale, Model model) {
        return "upload/image";
    }

    @RequestMapping(value = "/image/cut.json", method = RequestMethod.POST)
    @ResponseBody
    public Object cut(HttpServletRequest request) {
        ResultModel<ImageDto> rs = new ResultModel<ImageDto>();
        try {
            rs.setReturnValue(imageBo.cutImage(getImageDto(request), request));
        } catch (ChanceValidateException e) {
            rs.setChanceError(e);
        } catch (Exception e) {
            rs.setErrorDesc(e.getMessage());
        }
        return rs;
    }

    @RequestMapping(value = "/image/zoom.json", method = RequestMethod.POST)
    @ResponseBody
    public Object zoom(HttpServletRequest request) {
        ResultModel<ImageDto> rs = new ResultModel<ImageDto>();
        try {
            rs.setReturnValue(imageBo.zoomImage(getImageDto(request), request));
        } catch (ChanceValidateException e) {
            rs.setChanceError(e);
        } catch (Exception e) {
            rs.setErrorDesc(e.getMessage());
        }
        return rs;
    }

    @RequestMapping(value = "/image/cutZoom.json")
    @ResponseBody
    public Object cutZoom(HttpServletRequest request) {
        ResultModel<ImageDto> rs = new ResultModel<ImageDto>();
        try {
            rs.setReturnValue(imageBo.cutZoomImage(getImageDto(request), request));
        } catch (ChanceValidateException e) {
            rs.setChanceError(e);
        } catch (Exception e) {
            rs.setErrorDesc(e.getMessage());
        }
        return rs;
    }

    private ImageDto getImageDto(HttpServletRequest request) {
        ImageDto image = new ImageDto();
        if (StringUtils.isNotBlank(request.getParameter("imageId"))) {
            image.setId(Integer.parseInt(request.getParameter("imageId")));
        }
        if (StringUtils.isNotBlank(request.getParameter("x"))) {
            image.setX(Integer.parseInt(request.getParameter("x")));
        }
        if (StringUtils.isNotBlank(request.getParameter("y"))) {
            image.setY(Integer.parseInt(request.getParameter("y")));
        }
        if (StringUtils.isNotBlank(request.getParameter("width"))) {
            image.setWidth(Integer.parseInt(request.getParameter("width")));
        }
        if (StringUtils.isNotBlank(request.getParameter("height"))) {
            image.setHeight(Integer.parseInt(request.getParameter("height")));
        }
        if (StringUtils.isNotBlank(request.getParameter("type"))) {
            image.setType(request.getParameter("type"));
        }
        if (StringUtils.isNotBlank(request.getParameter("processObject"))) {
            image.setProcessObject(request.getParameter("processObject"));
        }
        return image;
    }

    /**
     * 上传图片
     * 
     * @param type 图片类型
     * @param response
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/image/upload.json", method = RequestMethod.POST)
    @ResponseBody
    public Object upload(@RequestParam(value = "type") String type, HttpServletResponse response,
                         HttpServletRequest request) throws IOException {
        ResultModel<ImageDto> rs = new ResultModel<ImageDto>();
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        try {
            rs.setReturnValue(imageBo.uploadImage(getImageDto(request), request));
        } catch (ChanceValidateException e) {
            rs.setChanceError(e);
        }
        out.write(JSONUtils.beanToJSON(rs));
        return null;
    }
}
