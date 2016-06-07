package com.bk.chenxi.dal.mapper;

import java.util.List;

import com.bk.chenxi.dal.model.ImageDo;

public interface ImageDoMapperExt extends ImageDoMapper {
	/**
	 * 根据关联关系ID与类型删除图片
	 * 
	 * @return
	 */
	public int deleteByAssociateIdAndType(ImageDo imageDo);

	public void deleteInvalidImage();

	/**
	 * @param id
	 */
	public void deleteByPrimaryKeyPhysically(Integer id);

	public List<ImageDo> selectInvalidImage();
}