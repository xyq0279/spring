 package cn.tedu.domain;

import java.io.Serializable;

public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;//商品ID
	private String name;//商品名称
	private double price;//商品价格
	private String category;//商品种类
	private String imgurl;//图片路径
	private int pnum;//库存数量
	private String description;//商品描述
	
	//重写hashcode方法和equals方法
	@Override
	public int hashCode() {
		return id==null?0:id.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}
		if(this == obj){
			return true;
		}
		if(!(obj instanceof Product)){
			return false;
		}
		Product other = (Product) obj;
		if(id!=null&&id.equals(other.getId())){
			return true;
		}
		return false;
	}
	
	public Product(){}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price
				+ ", category=" + category + ", imgurl=" + imgurl + ", pnum="
				+ pnum + ", description=" + description + "]";
	}
	
}
