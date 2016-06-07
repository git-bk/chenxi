/*
 * Copyright 2015 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.bk.chenxi.biz.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import com.bk.chenxi.biz.FileHandleService;
import com.bk.chenxi.biz.UserBo;
import com.bk.chenxi.dal.mapper.ImageDoMapperExt;
import com.bk.chenxi.dal.model.ImageDoExample;
import com.bk.chenxi.dal.plugin.Page;
import com.bk.chenxi.dto.ImageDto;
import com.bk.chenxi.exception.ChanceValidateException;
import com.bk.chenxi.model.FileUploadModel;
import com.bk.chenxi.util.image.ImageUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
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
import com.bk.chenxi.biz.ImageBo;
import com.bk.chenxi.dal.model.ImageDo;
import com.bk.chenxi.dal.model.UserDo;
import com.bk.chenxi.enums.YesOrNo;
import com.bk.chenxi.util.PathUtil;

public class ImageBoImpl implements ImageBo {

    private final Logger      logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ImageDoMapperExt imageDoMapperExt;
    @Value("#{configProperties['max_image_size']}")
    private Integer           max_image_size;
    @Value("#{configProperties['image_upload_types']}")
    private String            image_upload_types;
    @Autowired
    private UserBo userBo;
    @Autowired
    private FileHandleService fileHandleService;

    @Override
    public ImageDto newImage(ImageDto imageDto) throws ChanceValidateException {
        if (imageDto == null) {
            throw new ChanceValidateException("newImage-001", "参数imageDto为空");
        }
        logger.info("if条件值：StringUtils.isBlank(imageDto.getType())= "
                    + JSON.toJSONString(StringUtils.isBlank(imageDto.getType())));
        if (StringUtils.isBlank(imageDto.getType())) {
            throw new ChanceValidateException("newImage-002", "图片类型不能为空");
        }
        ImageDo imageDo = new ImageDo();
        BeanUtils.copyProperties(imageDto, imageDo);
        UserDo user = userBo.getLocalUser();
        if (imageDo.getCreator() == null) {
            imageDo.setCreator(user.getId().toString());
        }
        if (imageDo.getModifier() == null) {
            imageDo.setModifier(user.getId().toString());
        }
        if (imageDo.getGmtCreate() == null) {
            imageDo.setGmtCreate(new Date());
        }
        if (imageDo.getGmtModified() == null) {
            imageDo.setGmtModified(new Date());
        }
        if (imageDo.getIsDeleted() == null) {
            imageDo.setIsDeleted("n");
        }
        logger.info("imageDoMapperExt.insertSelective-参数： imageDto=" + JSON.toJSONString(imageDto));
        imageDoMapperExt.insertSelective(imageDo);
        imageDto.setId(imageDo.getId());
        logger.info("结束执行：ImageBo.newImage");
        return imageDto;
    }

    @Override
    public ImageDto findImageById(Integer id) throws ChanceValidateException {
        logger.info("开始执行：ImageBo.newImage");
        logger.info("findImageById-参数： id=" + JSON.toJSONString(id));
        if (id == null) {
            throw new ChanceValidateException("findImageById-001", "imageId为空");
        }
        logger.info("imageDoMapperExt.selectByPrimaryKey-参数： id=" + JSON.toJSONString(id));
        ImageDo imageDo = imageDoMapperExt.selectByPrimaryKey(id);
        logger.info("if条件值：imageDo != null= " + JSON.toJSONString(imageDo != null));
        if (imageDo != null) {
            ImageDto imageDto = new ImageDto();
            BeanUtils.copyProperties(imageDo, imageDto);
            logger.info("结束执行：ImageBo.newImage");
            return imageDto;
        }
        logger.info("结束执行：ImageBo.newImage");
        return null;
    }

    @Override
    public List<ImageDto> findImageByDto(ImageDto imageDto) throws ChanceValidateException {
        if (imageDto == null) {
            throw new ChanceValidateException("findImageByDto-001", "参数对象imageDto不能为空"); // 参数对象不能为空
        }
        ImageDoExample example = new ImageDoExample();
        ImageDoExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(imageDto.getOriginalUrl())) {
            criteria.andOriginalUrlEqualTo(imageDto.getOriginalUrl());
        }
        if (StringUtils.isNotBlank(imageDto.getThumbnailUrl())) {
            criteria.andThumbnailUrlEqualTo(imageDto.getThumbnailUrl());
        }
        if (StringUtils.isNotBlank(imageDto.getType())) {
            criteria.andTypeEqualTo(imageDto.getType());
        }
        if (StringUtils.isNotBlank(imageDto.getName())) {
            criteria.andNameEqualTo(imageDto.getName());
        }
        if (imageDto.getAssociateId() != null) {
            criteria.andAssociateIdEqualTo(imageDto.getAssociateId());
        }
        example.setOrderByClause("my_order");
        Page page = new Page(0, 10000);
        example.setPage(page);
        List<ImageDo> list = imageDoMapperExt.selectByExample(example);
        List<ImageDto> result = null;
        if (list != null && !list.isEmpty()) {
            result = new ArrayList<ImageDto>();
            for (ImageDo tempDo : list) {
                ImageDto tempDto = new ImageDto();
                BeanUtils.copyProperties(tempDo, tempDto);
                result.add(tempDto);
            }
        }
        logger.info("结束执行：ImageBo.findImageByDto");
        return result;
    }

    @Override
    public void removeImageById(Integer id) throws ChanceValidateException {
        logger.info("开始执行：ImageBo.removeImageById");
        logger.info("removeImageById-参数:id=" + id);
        ImageDto imageDto = new ImageDto();
        imageDto.setId(id);
        imageDto.setIsDeleted(YesOrNo.YES.getValue());
        modifyImage(imageDto);
        logger.info("结束执行：ImageBo.removeImageById");
    }

    @Override
    public Integer modifyImage(ImageDto imageDto) throws ChanceValidateException {
        if (imageDto == null || imageDto.getId() == null) {
            throw new ChanceValidateException("modifyImage-001", "参数为空");
        }
        ImageDo imageDo = new ImageDo();
        BeanUtils.copyProperties(imageDto, imageDo);
        UserDo user = userBo.getLocalUser();
        if (imageDo.getModifier() == null) {
            imageDo.setModifier(user.getId().toString());
        }
        if (imageDo.getGmtModified() == null) {
            imageDo.setGmtModified(new Date());
        }
        return imageDoMapperExt.updateByPrimaryKeySelective(imageDo);

    }

    @Override
    public void removeImageByIdStrictly(Integer id) throws ChanceValidateException {
        if (id == null) {
            throw new ChanceValidateException("removeImageByIdStrictly-001", "参数ImageId为空");
        }
        imageDoMapperExt.deleteByPrimaryKeyPhysically(id);
        logger.info("结束执行：ImageBo.removeImageByIdStrictly");
    }

    @Override
    public List<ImageDto> selectInvalidImage() {
        logger.info("开始执行：ImageBo.selectInvalidImage");
        List<ImageDo> list;
        list = imageDoMapperExt.selectInvalidImage();
        if (list != null && !list.isEmpty()) {
            List<ImageDto> result = new ArrayList<ImageDto>();
            for (ImageDo imageDo : list) {
                ImageDto temp = new ImageDto();
                BeanUtils.copyProperties(imageDo, temp);
                result.add(temp);
            }
            logger.info("结束执行：ImageBo.selectInvalidImage");
            return result;
        }
        logger.info("结束执行：ImageBo.selectInvalidImage");
        return null;
    }

    @Override
    public ImageDto uploadImage(ImageDto imageDto, HttpServletRequest request) throws ChanceValidateException {
        // 用以上工厂实例化上传组件
        ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
        sfu.setHeaderEncoding("UTF-8");// 不然中文文件名会乱码
        int imageSize = 1;
        if (StringUtils.equals("project_intro", imageDto.getType())) {
            imageSize = 2;
        }
        // 设置最大上传尺寸
        sfu.setSizeMax(imageSize * 1024 * 1024);
        // 从request得到 所有 上传域的列表
        List<FileItem> fileList;
        try {
            fileList = sfu.parseRequest(request);
        } catch (SizeLimitExceededException e) {
            throw new ChanceValidateException("uploadImage-001", "上传图片大小不能超过" + imageSize + "M");
        } catch (FileUploadException e) {
            throw new ChanceValidateException("uploadImage-002", "request转FileItem对象失败");
        }
        if (fileList == null || fileList.size() == 0) {
            throw new ChanceValidateException("uploadImage-003", "没有找到文件对象fileItem");
        }
        // 循环处理所有文件
        for (FileItem fileItem : fileList) {
            // 忽略简单form字段而不是上传域的文件域(<input type="text" />等)
            if (fileItem == null || fileItem.isFormField()) {
                continue;
            }
            String fileName = fileItem.getName();
            if (StringUtils.isBlank(fileName) || fileItem.getSize() == 0) {
                throw new ChanceValidateException("uploadImage-004", "文件名或文件内容为空");
            }
            // 拒绝接受规定文件格式之外的文件类型
            if (!this.fileNameValidate(fileName)) {
                throw new ChanceValidateException("uploadImage-005", "暂不支持此类图片文件上传");
            }
            InputStream inputStream;
            try {
                inputStream = fileItem.getInputStream();
            } catch (IOException e) {
                throw new ChanceValidateException("uploadImage-006", "从fileItem获取文件流异常");
            }
            // 将上传文件直接部署在web服务器目录上
            fileHandleService.setNasDiskPosition(PathUtil.getWebRoot(request));
            // 文件上传操作
            FileUploadModel fum = fileHandleService.uplaod(fileName, inputStream);
            imageDto.setName(fileName);
            imageDto.setOriginalUrl(fum.getUrl());
            // 有一个操作成功直接返回
            return this.newImage(imageDto);
        }
        return null;
    }

    private boolean fileNameValidate(String fileName) {
        if (StringUtils.isBlank(image_upload_types)) {
            return true;
        }
        String[] allowedExt = image_upload_types.split(",");
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

    @Override
    public void associ(Integer imageId, Integer associId) throws ChanceValidateException {
        if (imageId == null || associId == null) {
            throw new ChanceValidateException("associ-001", "参数不能为空");
        }
        ImageDto image = findImageById(imageId);
        if (image == null) {
            throw new ChanceValidateException("associ-002", "未找到待裁剪原图片");
        }
        image.setAssociateId(associId);
        modifyImage(image);
    }

    @Override
    public ImageDto cutImage(ImageDto imageDto, HttpServletRequest request) throws Exception {
        if (imageDto == null || imageDto.getId() == null) {
            throw new ChanceValidateException("cutImage-001", "imageId参数不能为空");
        }
        if (imageDto.getHeight() == null || imageDto.getWidth() == null || imageDto.getX() == null
            || imageDto.getY() == null) {
            throw new ChanceValidateException("cutImage-002", "待裁剪尺寸和原点信息不能为空");
        }
        ImageDto image = findImageById(imageDto.getId());
        if (image == null || StringUtils.isBlank(image.getOriginalUrl())) {
            throw new ChanceValidateException("cutImage-003", "未找到待裁剪原图片");
        }
        // 将上传文件直接部署在web服务器目录上
        fileHandleService.setNasDiskPosition(PathUtil.getWebRoot(request));
        String processUrl = image.getOriginalUrl();// 默认处理原图
        if (StringUtils.equals("thumb", imageDto.getProcessObject())) {// 除非参数指定处理缩略图
            processUrl = image.getThumbnailUrl();
        }
        InputStream in = fileHandleService.getInputStreamByUrl(processUrl);
        InputStream in_ = ImageUtil.processImage(ImageIO.read(in), ImageUtil.getExtName(image.getName()),
                                                 imageDto.getX(), imageDto.getY(), imageDto.getWidth(),
                                                 imageDto.getHeight());
        FileUploadModel model = fileHandleService.uplaod("cut_" + image.getName(), in_);
        image.setThumbnailUrl(model.getUrl());
        imageDto.setThumbnailUrl(model.getUrl());
        modifyImage(imageDto);
        return image;
    }

    @Override
    public ImageDto zoomImage(ImageDto imageDto, HttpServletRequest request) throws Exception {
        if (imageDto == null || imageDto.getId() == null) {
            throw new ChanceValidateException("zoomImage-001", "imageId参数不能为空");
        }
        if (imageDto.getHeight() == null || imageDto.getWidth() == null) {
            throw new ChanceValidateException("zoomImage-002", "待缩放尺寸不能为空");
        }
        ImageDto image = findImageById(imageDto.getId());
        if (image == null || StringUtils.isBlank(image.getOriginalUrl())) {
            throw new ChanceValidateException("zoomImage-003", "未找到待缩放原图片");
        }
        // 将上传文件直接部署在web服务器目录上
        fileHandleService.setNasDiskPosition(PathUtil.getWebRoot(request));
        InputStream in = fileHandleService.getInputStreamByUrl(image.getOriginalUrl());
        InputStream in_ = ImageUtil.zoomImage(in, ImageUtil.getExtName(image.getName()), imageDto.getWidth(),
                                              imageDto.getHeight());
        FileUploadModel model = fileHandleService.uplaod("zoom_" + image.getName(), in_);
        image.setThumbnailUrl(model.getUrl());
        imageDto.setThumbnailUrl(model.getUrl());
        modifyImage(imageDto);
        return image;
    }

    @Override
    public ImageDto cutZoomImage(ImageDto imageDto, HttpServletRequest request) throws Exception {
        if (imageDto == null || imageDto.getId() == null) {
            throw new ChanceValidateException("cutZoomImage-001", "imageId参数不能为空");
        }
        if (imageDto.getHeight() == null || imageDto.getWidth() == null) {
            throw new ChanceValidateException("cutZoomImage-002", "待裁剪尺寸和原点信息不能为空");
        }
        ImageDto image = findImageById(imageDto.getId());
        if (image == null || StringUtils.isBlank(image.getOriginalUrl())) {
            throw new ChanceValidateException("cutZoomImage-003", "未找到待裁剪原图片");
        }
        // 将上传文件直接部署在web服务器目录上
        fileHandleService.setNasDiskPosition(PathUtil.getWebRoot(request));
        InputStream inputOriginalImage = null;
        InputStream inputCutImage = null;
        try {
            inputOriginalImage = fileHandleService.getInputStreamByUrl(image.getOriginalUrl());
            inputCutImage = ImageUtil.cutZoom(inputOriginalImage, image.getName(), imageDto.getWidth(),
                                              imageDto.getHeight());
            FileUploadModel model = fileHandleService.uplaod("cut_" + image.getName(), inputCutImage);
            image.setThumbnailUrl(model.getUrl());
            imageDto.setThumbnailUrl(model.getUrl());
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (inputOriginalImage != null) {
                inputOriginalImage.close();
            }
            if (inputCutImage != null) {
                inputCutImage.close();
            }
        }
        modifyImage(imageDto);
        return image;
    }
}
