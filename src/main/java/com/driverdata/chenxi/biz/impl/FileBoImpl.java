/*
 * Copyright 2015 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.driverdata.chenxi.biz.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.fastjson.JSON;
import com.driverdata.chenxi.biz.FileBo;
import com.driverdata.chenxi.biz.FileHandleService;
import com.driverdata.chenxi.biz.UserBo;
import com.driverdata.chenxi.dal.mapper.FileDoMapperExt;
import com.driverdata.chenxi.dal.model.FileDo;
import com.driverdata.chenxi.dal.model.FileDoExample;
import com.driverdata.chenxi.dal.model.UserDo;
import com.driverdata.chenxi.dto.FileDto;
import com.driverdata.chenxi.exception.ChanceValidateException;
import com.driverdata.chenxi.model.FileUploadModel;
import com.driverdata.chenxi.util.EncryptUtils;
import com.driverdata.chenxi.util.PathUtil;
import com.driverdata.chenxi.util.image.ImageUtil;

/**
 * 类FileBoImpl.java的实现描述：TODO 类实现描述
 */
public class FileBoImpl implements FileBo {

    private final Logger      logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private FileDoMapperExt   fileDoMapperExt;
    @Value("#{configProperties['max_file_size']}")
    private Integer           max_file_size;
    @Value("#{configProperties['file_upload_types']}")
    private String            file_upload_types;
    @Autowired
    private UserBo            userBo;
    @Autowired
    private FileHandleService fileHandleService;

    /**
     * 新增文件
     */
    @Override
    public FileDto newFile(FileDto fileDto) throws ChanceValidateException {
        if (fileDto == null) {
            throw new ChanceValidateException("newFile-001", "对象不能为空"); // 对象不能为空
        }
        if (StringUtils.isBlank(fileDto.getType())) {
            throw new ChanceValidateException("newFile-002", "文件类型不能为空"); // 文件类型不能为空
        }
        if (StringUtils.isBlank(fileDto.getUrl())) {
            throw new ChanceValidateException("newFile-003", "文件内容不能为空"); // 文件内容不能为空
        }
        FileDo fileDo = new FileDo();
        BeanUtils.copyProperties(fileDto, fileDo);
        UserDo user = userBo.getLocalUser();
        fileDo.setCreator(user.getId().toString());
        fileDo.setModifier(user.getId().toString());
        fileDoMapperExt.insertSelective(fileDo);
        try {
            fileDto.setEncodeId(EncryptUtils.getInstance().encrypt(fileDo.getId().toString()));
        } catch (Exception e) {
            throw new ChanceValidateException("newFile-004", "加密文件id失败");
        }
        return fileDto;
    }

    /**
     * 通过ID查询文件
     */
    @Override
    public FileDto findFileById(Integer id) throws ChanceValidateException {
        logger.info("开始执行：FileBo.findFileById。。。。。");
        logger.info("findFileById-参数： id=" + JSON.toJSONString(id));
        logger.info("if条件值：id == null = " + JSON.toJSONString(id == null));
        if (id == null) {
            throw new ChanceValidateException("findFileById-001", "id不能为空"); // id不能为空
        }
        logger.info("fileDoMapperExt.selectByPrimaryKey-参数： id=" + JSON.toJSONString(id));
        FileDo fileDo = fileDoMapperExt.selectByPrimaryKey(id);
        logger.info("if条件值：fileDo != null = " + JSON.toJSONString(fileDo != null));
        if (fileDo != null) {
            FileDto fileDto = new FileDto();
            BeanUtils.copyProperties(fileDo, fileDto);
            logger.info("结束执行：FileBo.findFileById。。。。。");
            return fileDto;
        }
        logger.info("结束执行：FileBo.findFileById。。。。。");
        return null;
    }

    @Override
    public List<FileDto> findFileByDto(FileDto fileDto) throws ChanceValidateException {
        logger.info("开始执行：FileBo.findFileByDto。。。。。");
        logger.info("findFileByDto-参数： fileDto=" + JSON.toJSONString(fileDto));
        logger.info("if条件值：fileDto == null = " + JSON.toJSONString(fileDto == null));
        if (fileDto == null) {
            throw new ChanceValidateException("findFileByDto-001", "参数对象不能为空"); // 参数对象不能为空
        }
        FileDoExample example = new FileDoExample();
        FileDoExample.Criteria criteria = null;
        logger.info("if条件值：StringUtils.isNotBlank(fileDto.getUrl()) = "
                    + JSON.toJSONString(StringUtils.isNotBlank(fileDto.getUrl())));
        if (StringUtils.isNotBlank(fileDto.getUrl())) {
            if (criteria == null) {
                criteria = example.createCriteria();
            }
            criteria.andUrlEqualTo(fileDto.getUrl());
        }
        logger.info("if条件值：StringUtils.isNotBlank(fileDto.getName()) = "
                    + JSON.toJSONString(StringUtils.isNotBlank(fileDto.getName())));
        if (StringUtils.isNotBlank(fileDto.getName())) {
            if (criteria == null) {
                criteria = example.createCriteria();
            }
            criteria.andNameEqualTo(fileDto.getName());
        }
        logger.info("if条件值：StringUtils.isNotBlank(fileDto.getType()) = "
                    + JSON.toJSONString(StringUtils.isNotBlank(fileDto.getType())));
        if (StringUtils.isNotBlank(fileDto.getType())) {
            if (criteria == null) {
                criteria = example.createCriteria();
            }
            criteria.andTypeEqualTo(fileDto.getType());
        }
        logger.info("if条件值：fileDto.getAssociateId() != null = " + JSON.toJSONString(fileDto.getAssociateId() != null));
        if (fileDto.getAssociateId() != null) {
            if (criteria == null) {
                criteria = example.createCriteria();
            }
            criteria.andAssociateIdEqualTo(fileDto.getAssociateId());
        }
        logger.info(" fileDoMapperExt.selectByExample-参数： example=" + JSON.toJSONString(example));
        List<FileDo> list = fileDoMapperExt.selectByExample(example);
        List<FileDto> result = null;
        if (list != null && !list.isEmpty()) {
            result = new ArrayList<FileDto>();
            for (FileDo tempDo : list) {
                FileDto tempDto = new FileDto();
                BeanUtils.copyProperties(tempDo, tempDto);
                result.add(tempDto);
            }
        }
        logger.info("结束执行：FileBo.findFileByDto。。。。。");
        return result;
    }

    /**
     * 通过ID删除文件
     */
    @Override
    public void removeFileById(Integer id) throws ChanceValidateException {
        logger.info("开始执行：FileBo.removeFileById。。。。。");
        logger.info(" removeFileById-参数： id=" + JSON.toJSONString(id));

        if (id == null) {
            throw new ChanceValidateException("removeFileById-001", "ID不能为空"); // ID不能为空
        }
        FileDo record = new FileDo();
        record.setId(id);
        fileDoMapperExt.deleteByPrimaryKey(record);
    }

    /**
     * 修改文件
     */
    @Override
    public Integer modifyFile(FileDto fileDto) throws ChanceValidateException {
        logger.info("开始执行：FileBo.modifyFile。。。。。");
        logger.info(" modifyFile-参数： fileDto=" + JSON.toJSONString(fileDto));
        logger.info("if条件值：fileDto == null || fileDto.getId() == null = "
                    + JSON.toJSONString(fileDto == null || fileDto.getId() == null));
        if (fileDto == null || fileDto.getId() == null) {
            throw new ChanceValidateException("modifyFile-001", "参数对象不能为空");
        }
        FileDo fileDo = new FileDo();
        BeanUtils.copyProperties(fileDto, fileDo);
        UserDo user = userBo.getLocalUser();
        fileDo.setModifier(user.getId().toString());
        logger.info(" fileDoMapperExt.updateByPrimaryKeySelective-参数： fileDto=" + JSON.toJSONString(fileDto));
        logger.info("结束执行：FileBo.modifyFile。。。。。");
        return fileDoMapperExt.updateByPrimaryKeySelective(fileDo);
    }

    @Override
    public List<FileDto> selectInvalidFile() {
        logger.info("开始执行：FileBo.selectInvalidFile。。。。。");
        List<FileDo> list;
        list = fileDoMapperExt.selectInvalidFile();
        logger.info("if条件值：list != null && !list.isEmpty() = " + JSON.toJSONString(list != null && !list.isEmpty()));
        if (list != null && !list.isEmpty()) {
            List<FileDto> result = new ArrayList<FileDto>();
            for (FileDo fileDo : list) {
                FileDto temp = new FileDto();
                BeanUtils.copyProperties(fileDo, temp);
                result.add(temp);
            }
            logger.info("结束执行：FileBo.selectInvalidFile。。。。。");
            return result;
        }
        logger.info("结束执行：FileBo.selectInvalidFile。。。。。");
        return null;
    }

    @Override
    public void removeFileByIdStrictly(Integer id) throws ChanceValidateException {
        logger.info("开始执行：FileBo.removeFileByIdStrictly。。。。。");
        logger.info(" removeFileByIdStrictly-参数： id=" + JSON.toJSONString(id));
        if (id == null) {
            throw new ChanceValidateException("removeFileByIdStrictly-001", "文件id不能为空");
        }
        logger.info(" fileDoMapperExt.deleteByPrimaryKeyPhysically-参数： id=" + JSON.toJSONString(id));
        fileDoMapperExt.deleteByPrimaryKeyPhysically(id);
        logger.info("结束执行：FileBo.removeFileByIdStrictly。。。。。");
    }

    @Override
    public FileDto uploadFile(FileDto fileDto, HttpServletRequest request) throws ChanceValidateException {
    	// 用以上工厂实例化上传组件
        ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
        sfu.setHeaderEncoding("UTF-8");// 不然中文文件名会乱码
        // 设置最大上传尺寸
        sfu.setSizeMax(max_file_size * 1024 * 1024);
        // 从request得到 所有 上传域的列表
        List<FileItem> fileList;
        try {
            fileList = sfu.parseRequest(request);
        } catch (FileUploadException e) {
            throw new ChanceValidateException("uploadFile-001", "上传文件大小不能超过" + max_file_size + "M");
        }
        if (fileList == null || fileList.size() == 0) {
            throw new ChanceValidateException("uploadFile-002", "没有找到文件对象fileItem");
        }
        // 循环处理所有文件
        for (FileItem fileItem : fileList) {
            // 忽略简单form字段而不是上传域的文件域(<input type="text" />等)
            if (fileItem == null || fileItem.isFormField()) {
                continue;
            }
            String fileName = fileItem.getName();
            if (StringUtils.isBlank(fileName) || fileItem.getSize() == 0) {
                throw new ChanceValidateException("uploadFile-003", "文件名为空或文件内容为空");
            }
            // 拒绝接受规定文件格式之外的文件类型
            if (!this.fileNameValidate(fileName)) {
                throw new ChanceValidateException("uploadFile-004", "赞不支持此类文件上传");
            }
            InputStream inputStream;
            try {
                inputStream = fileItem.getInputStream();
            } catch (IOException e) {
                throw new ChanceValidateException("uploadFile-005", "从fileItem获取文件流异常");
            }
            // 将上传文件直接部署在web服务器目录上
            fileHandleService.setNasDiskPosition(PathUtil.getWebRoot(request));
            // 文件上传操作
            FileUploadModel fum = fileHandleService.uplaod(fileName, inputStream);
            fileDto.setName(fileName);
            fileDto.setUrl(fum.getUrl());
            // 有一个操作成功直接返回
            return this.newFile(fileDto);
        }
        return null;
    }
    
    private boolean fileNameValidate(String fileName) {
        if (StringUtils.isBlank(file_upload_types)) {
            return true;
        }
        String[] allowedExt = file_upload_types.split(",");
        // 得到文件的扩展名(无扩展名时将得到全名)
        String extName = ImageUtil.getExtName(fileName);
        // 没有扩展名
        if (StringUtils.isBlank(extName)) {
            return false;
        }
        int allowFlag = 0;
        int allowedExtCount = allowedExt.length;
        for (; allowFlag < allowedExtCount; allowFlag++) {
            if (allowedExt[allowFlag].equals(extName)) break;
        }
        if (allowFlag == allowedExtCount) {
            return false;
        }
        return true;
    }

}
