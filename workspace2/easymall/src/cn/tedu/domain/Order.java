package cn.tedu.domain;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;//订单ID
	private double money;//订单金额
	private String receiverinfo;//收件人信息
	private int paystate;//支付状态，0表示未支付，1表示已支付
	private Date ordertime;//订单时间
	private int user_id;//用户ID
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getReceiverinfo() {
		return receiverinfo;
	}
	public void setReceiverinfo(String receiverinfo) {
		this.receiverinfo = receiverinfo;
	}
	public int getPaystate() {
		return paystate;
	}
	public void setPaystate(int paystate) {
		this.paystate = paystate;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}
