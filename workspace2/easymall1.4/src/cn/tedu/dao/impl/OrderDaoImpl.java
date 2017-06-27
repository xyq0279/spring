package cn.tedu.dao.impl;

import java.sql.SQLException;

import cn.tedu.Utils.DaoUtils;
import cn.tedu.dao.OrderDao;
import cn.tedu.domain.Order;
import cn.tedu.domain.OrderItem;

public class OrderDaoImpl implements OrderDao {
	/**
	 * 向order添加一条订单记录
	 * @param order 封装了订单信息的order类对象
	 */
	public void addOrder(Order order) {
		String sql = "insert into orders(id,money,receiverinfo,paystate,ordertime,user_id) values(?,?,?,?,?,?)";
		try {
			DaoUtils.update(sql, order.getId(),order.getMoney(),order.getReceiverinfo(),order.getPaystate(),order.getOrdertime(),order.getUser_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 向orderitem添加一条订单条目记录
	 * @param oi 封装了订单条目信息的orderitem类对象
	 */
	public void addOrderItem(OrderItem oi) {
		String sql = "insert into orderitem(order_id,product_id,buynum) values(?,?,?)";
		try {
			DaoUtils.update(sql, oi.getOrder_id(),oi.getProduct_id(),oi.getBuynum());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
