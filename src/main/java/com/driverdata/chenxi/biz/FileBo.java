/*
 * Copyright 2015 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.driverdata.chenxi.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.driverdata.chenxi.dto.FileDto;
import com.driverdata.chenxi.exception.ChanceValidateException;

/**
 * 类FileBo.java的实现描述：TODO 类实现描述
 */
public interface FileBo {

    public FileDto newFile(FileDto fileDto) throws ChanceValidateException;

    public FileDto findFileById(Integer id) throws ChanceValidateException;

    public FileDto uploadFile(FileDto fileDto, HttpServletRequest request) throws ChanceValidateException;

    public List<FileDto> findFileByDto(FileDto fileDto) throws ChanceValidateException;

    public void removeFileById(Integer id) throws ChanceValidateException;

    public Integer modifyFile(FileDto fileDto) throws ChanceValidateException;

    /**
     * @return
     */
    List<FileDto> selectInvalidFile();

    /**
     * @param id
     * @throws ChanceValidateException
     */
    void removeFileByIdStrictly(Integer id) throws ChanceValidateException;
}
