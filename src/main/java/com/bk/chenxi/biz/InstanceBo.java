package com.bk.chenxi.biz;

import com.bk.chenxi.dto.InstanceDto;

/**
 * Created by wb-yaobingke on 2016/5/13.
 */
public interface InstanceBo {
    /*新增实例*/
    InstanceDto addInstance(InstanceDto instance) ;

    /*删除一个实例*/
    void delInstance(InstanceDto instance);

    /*更新实例*/
    void updateInstance(InstanceDto instance);

    /*根据id查询实例*/
    InstanceDto findById(Integer id) ;

}
