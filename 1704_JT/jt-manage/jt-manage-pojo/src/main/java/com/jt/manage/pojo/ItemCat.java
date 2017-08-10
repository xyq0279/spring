package com.jt.manage.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jt.common.po.BasePojo;

/**
 * 
 * @author Administrator
 *
 */
@Table(name="tb_item_cat")
public class ItemCat extends BasePojo{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8125113990481242826L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;			//分类id
	private Long parentId;		//上级分类id ：父ID=0时，代表一级类目
	private String name;		//分类名称
	private Integer status;		//状态：默认值为1，可选值：1正常，2删除
	private Integer sortOrder;	//排序号
	private Boolean isParent;	//是否为上级目录 1表示是上级 目录  0表示不是
	
	/**
	 * 如果
	 * @return
	 */
	public String getState(){
		return isParent?"closed":"open";
	}
	
	//满足easyUI的树形结构，添加getText()方法
	public String getText(){
		return name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	@Override
	public String toString() {
		return "ItemCat [id=" + id + ", parentId=" + parentId + ", name=" + name + ", status=" + status + ", sortOrder="
				+ sortOrder + ", isParent=" + isParent + "]";
	}
	
}
