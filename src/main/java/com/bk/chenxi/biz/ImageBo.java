/*
 * Copyright 2015 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.bk.chenxi.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bk.chenxi.dto.ImageDto;
import com.bk.chenxi.exception.ChanceValidateException;

/**
 * 类ImageBo.java的实现描述：TODO 类实现描述
 * 
 * @author 李素林 2015年6月9日 上午9:53:42
 */
public interface ImageBo {

    public ImageDto newImage(ImageDto imageDto) throws ChanceValidateException;

    public void associ(Integer imageId, Integer associId) throws ChanceValidateException;

    /**
     * 只支持单个图片上传
     * 
     * @param imageDto
     * @param request
     * @return
     * @throws ChanceValidateException
     */
    public ImageDto uploadImage(ImageDto imageDto, HttpServletRequest request) throws ChanceValidateException;

    /**
     * 严格按照x,y,width,height裁剪
     * 
     * @param imageDto
     * @return
     * @throws Exception
     */
    public ImageDto cutImage(ImageDto imageDto, HttpServletRequest request) throws Exception;

    public ImageDto zoomImage(ImageDto imageDto, HttpServletRequest request) throws Exception;

    /**
     * 智能裁剪：根据要求的长宽比例裁剪图片多余的部分（会导致横向或纵向缺失），然后按照长宽值缩放
     * 
     * @param imageDto
     * @return
     * @throws Exception
     */
    public ImageDto cutZoomImage(ImageDto imageDto, HttpServletRequest request) throws Exception;

    public ImageDto findImageById(Integer id) throws ChanceValidateException;

    public List<ImageDto> findImageByDto(ImageDto imageDto) throws ChanceValidateException;

    public void removeImageById(Integer id) throws ChanceValidateException;

    public Integer modifyImage(ImageDto imageDto) throws ChanceValidateException;

    public void removeImageByIdStrictly(Integer id) throws ChanceValidateException;

    public List<ImageDto> selectInvalidImage();

}
