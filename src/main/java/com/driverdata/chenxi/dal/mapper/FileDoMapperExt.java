package com.driverdata.chenxi.dal.mapper;

import java.util.List;

import javax.annotation.Resource;

import com.driverdata.chenxi.dal.model.FileDo;

@Resource
public interface FileDoMapperExt extends FileDoMapper {

    public void deleteInvalidFile();

    /**
     * @return
     */
    public List<FileDo> selectInvalidFile();

    /**
     * @param id
     */
    public void deleteByPrimaryKeyPhysically(Integer id);

    /**
     * 根据项目ID和类型删除附件
     */
    public void deleteByProjectIdAndType(FileDo fileDo);
}
