package com.bk.chenxi.biz;

import com.bk.chenxi.dto.EntityDto;

import java.util.List;

/**
 * Created by wb-yaobingke on 2016/5/13.
 */
public interface EntityBo {
    /*新增实体*/
    EntityDto addEntity(EntityDto entity) ;

    /*删除一个实体*/
    void delEntity(EntityDto entity);

    /*更新实体*/
    void updateEntity(EntityDto entity);

    /*根据id查询实体*/
    EntityDto findById(Integer id) ;

    /*根据id查询实体*/
    EntityDto findByEntityKey(String entityKey);
    /**
     * 查询子实体定义列表1
     */
    public List<EntityDto> findChildrenEntitys(Integer parentEntityId);
    /**
     * 查询子实体定义列表2
     */
    public List<EntityDto> findChildrenEntitys(String entityKey);
}
