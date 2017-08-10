package com.jt.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.common.mapper.SysMapper;
import com.jt.manage.pojo.Item;

public interface ItemMapper extends SysMapper<Item>{
	/**
	 * 
	 * @return
	 */
	public List<Item> findItemList();
	/**
	 * 查询全部商品日期，根据日期倒序排列
	 * @param rows 
	 * @param page 
	 * @return
	 */
	public List<Item> findPageInfoList(@Param("startNum")int startNum,@Param("rows") int rows);
	/**
	 * 查询所有商品总数
	 * @return
	 */
	public int selectItemCount();
	/**
	 * 
	 * @param itemCatId
	 * @return
	 */
	public String findItemCatName(Long itemCatId);
}
