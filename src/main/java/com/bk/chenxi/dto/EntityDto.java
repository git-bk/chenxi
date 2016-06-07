package com.bk.chenxi.dto;

import com.bk.chenxi.dal.model.EntityDo;

/**
 * Created by wb-yaobingke on 2016/5/13.
 */
public class EntityDto extends EntityDo {
    public EntityDto(){

    }
    public EntityDto(String creator,String name,String key,String desc,Integer parent,Integer appId){
        super();
        super.setAppId(appId);
        super.setCreator(creator);
        super.setName(name);
        super.setEntityKey(key);
        super.setDescription(desc);
        super.setParent(parent);
    }
}
