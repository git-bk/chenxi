package com.bk.chenxi.web.upload;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bk.chenxi.biz.FileBo;
import com.bk.chenxi.biz.UserBo;
import com.bk.chenxi.dto.FileDto;
import com.bk.chenxi.exception.ChanceValidateException;
import com.bk.chenxi.model.ResultModel;
import com.bk.chenxi.util.JSONUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class FileController {

    @Autowired
    private UserBo userBo;
    @Autowired
    private FileBo fileBo;

    @RequestMapping(value = "/file/upload.htm")
    public String home(Locale locale, Model model) {
        return "upload/upload";
    }

    /**
     * 上传文件
     * 
     * @param type 文件类型
     * @param response
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/file/upload.json", method = RequestMethod.POST)
    @ResponseBody
    public Object upload(HttpServletRequest request,HttpServletResponse response) throws IOException {
        ResultModel<FileDto> rs = new ResultModel<FileDto>();
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        try {
            rs.setReturnValue(fileBo.uploadFile(getFileDto(request), request));
        } catch (ChanceValidateException e) {
            rs.setChanceError(e);
        }
        out.write(JSONUtils.beanToJSON(rs));
        return null;
    }

    private FileDto getFileDto(HttpServletRequest request) {
        FileDto file = new FileDto();
        if (StringUtils.isNotBlank(request.getParameter("fileId"))) {
            file.setId(Integer.parseInt(request.getParameter("fileId")));
        }
        if (StringUtils.isNotBlank(request.getParameter("fType"))) {
            file.setType(request.getParameter("fType"));
        }
        return file;
    }
}
