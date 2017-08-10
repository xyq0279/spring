package com.jt.web.pojo;


import com.jt.common.po.BasePojo;

public class ItemDesc extends BasePojo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5255617862082613577L;
	private Long ItemId;
	private String itemDesc;
	public Long getItemId() {
		return ItemId;
	}
	public void setItemId(Long itemId) {
		ItemId = itemId;
	}
	
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	@Override
	public String toString() {
		return "ItemDesc [ItemId=" + ItemId + ", itemDesc=" + itemDesc + "]";
	}
	
}
