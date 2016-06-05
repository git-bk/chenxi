package com.driverdata.chenxi.dto;

import java.io.Serializable;

import com.driverdata.chenxi.dal.model.ImageDo;

public class ImageDto extends ImageDo implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -6598110820959590104L;
    private Integer           x;
    private Integer           y;
    private Integer           width;
    private Integer           height;
    // 指定zoom、cut等操作的对象是原图，还是缩略图
    private String            processObject;

    /**
     * 修改标记
     */
    private String            isModified;

    public String getIsModified() {
        return isModified;
    }

    public void setIsModified(String isModified) {
        this.isModified = isModified;
    }

    public String getProcessObject() {
        return processObject;
    }

    public void setProcessObject(String processObject) {
        this.processObject = processObject;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

}
