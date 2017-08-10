package com.jt.manage.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jt.common.po.BasePojo;

@Table(name="tb_item_desc")
public class ItemDesc extends BasePojo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5255617862082613577L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
