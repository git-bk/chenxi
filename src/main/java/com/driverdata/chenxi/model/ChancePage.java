package com.driverdata.chenxi.model;

import java.io.Serializable;

/**
 * 类Page.java的实现描述：TODO 类实现描述
 * 
 * @author 杨冬 2014年8月21日 下午1:19:25
 */
public class ChancePage<T> implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 8104777827863916550L;
	/** 每页显示记录数 **/
	private Integer pageSize;
	/** 查询的集合 **/
	private T data;
	/** 总页数 **/
	private int totalPage;
	/** 当前页，第几页 **/
	private Integer pageIndex;
	/** 总记录数 **/
	private Integer totalNum;
	/** 是否有下页 **/
	private boolean hasNextPage = false;
	/** 是否有上页 **/
	private boolean hasPreviousPage = false;

	/**
	 * @param pageSize
	 *            每页条数
	 * @param pageIndex
	 *            当前页，第几页
	 * @param totalNum
	 *            总记录数
	 * @param data
	 *            查询的集合
	 */
	public ChancePage(Integer pageSize, Integer pageIndex, Integer totalNum,
			T data) {
		super();
		if (pageSize == null || pageSize == 0) {
			pageSize = 5;
		}
		if (pageIndex == null || pageIndex == 0) {
			pageIndex = 1;
		}
		if (totalNum == null || totalNum == 0) {
			totalNum = 0;
		}
		this.pageSize = pageSize;
		this.data = data;
		// pageSize==0会报错
		this.totalPage = (totalNum % pageSize == 0) ? (totalNum / pageSize)
				: (totalNum / pageSize + 1);
		this.pageIndex = (pageIndex == 0) ? (1) : (pageIndex);
		this.totalNum = totalNum;
		this.hasNextPage = (this.totalPage > 1 && this.totalPage > this.pageIndex);
		this.hasPreviousPage = (this.pageIndex > 1);

	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * @param totalPage
	 *            the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * @return the pageIndex
	 */
	public Integer getPageIndex() {
		return pageIndex;
	}

	/**
	 * @param pageIndex
	 *            the pageIndex to set
	 */
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * @return the totalNum
	 */
	public Integer getTotalNum() {
		return totalNum;
	}

	/**
	 * @param totalNum
	 *            the totalNum to set
	 */
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	/**
	 * @return the hasNextPage
	 */
	public boolean isHasNextPage() {
		return hasNextPage;
	}

	/**
	 * @param hasNextPage
	 *            the hasNextPage to set
	 */
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	/**
	 * @return the hasPreviousPage
	 */
	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	/**
	 * @param hasPreviousPage
	 *            the hasPreviousPage to set
	 */
	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
