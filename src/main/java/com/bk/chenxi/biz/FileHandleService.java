package com.bk.chenxi.biz;

import java.io.InputStream;

import com.bk.chenxi.model.FileUploadModel;

public interface FileHandleService {

    /**
     * 设置文件存放根目录
     * 
     * @param path
     */
    public void setNasDiskPosition(String path);

    /**
     * 上传文件
     * 
     * @param
     * @return
     */
    public FileUploadModel uplaod(String fileName, InputStream input);

    /**
     * 根据文件url获取文件流
     * 
     * @param
     * @return
     * @throws Exception
     */
    public InputStream getInputStreamByUrl(String url) throws Exception;

}
