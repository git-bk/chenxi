package com.bk.chenxi.biz.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bk.chenxi.biz.FileHandleService;
import com.bk.chenxi.model.FileUploadModel;
import com.bk.chenxi.util.StringUtil;
import com.bk.chenxi.util.image.ImageUtil;

public class NasFileHandleServiceImpl implements FileHandleService {

    private static Logger logger = LoggerFactory.getLogger(NasFileHandleServiceImpl.class);

    /**
     * 文件根目录
     */
    private String        nasDiskPosition;

    @Override
    public void setNasDiskPosition(String nasDiskPosition) {
        this.nasDiskPosition = nasDiskPosition;
    }

    // 给予默认值
    public String getNasDiskPosition() {
        return StringUtil.isBlank(nasDiskPosition) ? "D:/tmp" : nasDiskPosition;
    }

    @Override
    public FileUploadModel uplaod(String fileName, InputStream input) {
        FileOutputStream os = null;
        FileUploadModel uploadFile = new FileUploadModel();
        String name;
        try {
            name = createFile(fileName);
        } catch (IOException e) {
            // 如果文件名称都创建不了，就没有走下去的必要，异常直接抛出
            throw new RuntimeException("创建nas目录异常！", e);
        }
        try {
            os = new FileOutputStream(getNasDiskPosition() + name);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0)
                os.write(buffer, 0, length);

        } catch (IOException e) {
            logger.error("IO异常!", e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    logger.error("IO异常,filename=" + name, e);
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.error("IO异常,filename=" + name, e);
                }
            }
        }

        uploadFile.setFileName(getFilename(fileName));
        uploadFile.setUrl(name);
        return uploadFile;
    }

    @Override
    public InputStream getInputStreamByUrl(String url) throws Exception {
        String fileUrl = getNasDiskPosition() + File.separator + url;
        try {
            File file = new File(fileUrl);
            if (!file.exists()) {
                file = new File(fileUrl);
            }
            FileInputStream inputStream = new FileInputStream(file);
            return inputStream;
        } catch (FileNotFoundException e) {
            throw new Exception("文件流读取失败，url=" + url, e);
        }
    }

    private String createFile(String fileName) throws IOException {
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        int year = 0;
        int month = 0;
        int day = 0;

        File nasDirectory = new File(getNasDiskPosition());
        if (!nasDirectory.exists()) {
            nasDirectory.mkdirs();
        }

        year = today.get(Calendar.YEAR);
        File yearDirectory = new File(nasDirectory.getAbsolutePath(), String.valueOf(year));
        if (!yearDirectory.exists()) {
            yearDirectory.mkdir();
        }

        month = today.get(Calendar.MONTH) + 1;
        File monthDirectory = new File(yearDirectory.getAbsolutePath(), String.valueOf(month));
        if (!monthDirectory.exists()) {
            monthDirectory.mkdir();
        }

        day = today.get(Calendar.DAY_OF_MONTH);
        File dayDirectory = new File(monthDirectory.getAbsolutePath(), String.valueOf(day));
        if (!dayDirectory.exists()) {
            dayDirectory.mkdir();
        }
        String extName = ImageUtil.getExtName(fileName);
        String fileName_ = UUID.randomUUID().toString();
        if (StringUtils.isNotBlank(extName)) {
            fileName_ = fileName_ + "." + extName;
        }

        File file = new File(dayDirectory.getAbsolutePath(), fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        return File.separator + year + File.separator + month + File.separator + day + File.separator + fileName_;
    }

    /**
     * 得到文件名称。如果文件名长度大于200的话，截取处理。 ps: 不同环境下对于中文的单字长度规定不一样，可能是2或者3.
     * 
     * @author yingchao.zyc
     * @return
     */
    private static String getFilename(String name) {
        if (StringUtils.isBlank(name)) {
            return StringUtil.EMPTY_STRING;
        }

        String prefix = "";
        String suffix = "";
        if (name.contains(".")) {
            prefix = name.substring(0, name.lastIndexOf("."));
            suffix = name.substring(name.lastIndexOf(".") + 1, name.length());
        } else {
            prefix = name;
        }

        if ((prefix.length() * 3) > (199 - suffix.length())) {
            int pos = (199 - suffix.length()) / 3;
            if (StringUtils.isNotBlank(suffix)) {
                name = prefix.substring(0, pos) + "." + suffix;
            } else {
                name = prefix.substring(0, pos);
            }
        }

        return name;
    }
}
