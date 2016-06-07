package com.bk.chenxi.dto;

import java.io.Serializable;
import java.util.List;

import com.bk.chenxi.dal.model.RoleDo;
import com.bk.chenxi.dal.model.UserDo;

public class UserDto extends UserDo implements Serializable {

    private static final long serialVersionUID = -269520442592987081L;
    private Integer           imageId;
    private Integer           newImageId;
    private ImageDto          headPortrait;

    public ImageDto getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(ImageDto headPortrait) {
        this.headPortrait = headPortrait;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getNewImageId() {
        return newImageId;
    }

    public void setNewImageId(Integer newImageId) {
        this.newImageId = newImageId;
    }

    private List<RoleDo> roles;

    public List<RoleDo> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDo> roles) {
        this.roles = roles;
    }

}
