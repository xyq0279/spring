package com.jt.web.pojo;


//import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jt.common.po.BasePojo;

/**
 * 商品信息
 * @author Administrator
 */
public class Item extends BasePojo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1044674607080460156L;
	private Long id;			//商品id
	private String title;		//商品标题
	private String sellPoint;	//商品买点
	private Long price;			//商品价格，单位为分，后期由js计算  价格/100
	private Integer num;		//商品库存数量
	private String barcode;		//扫描码
	private String image;		//商品图片
	private Long cid;			//分类号
	private Integer status;		//商品状态     默认值为1，可选值：1正常，2下架，3删除
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSellPoint() {
		return sellPoint;
	}
	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String[] getImages(){
		return this.image.split(",");
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", title=" + title + ", sellPoint=" + sellPoint + ", price=" + price + ", num=" + num
				+ ", barcode=" + barcode + ", image=" + image + ", cid=" + cid + ", status=" + status + "]";
	}
	
}
