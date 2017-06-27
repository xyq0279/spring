package cn.tedu.dao;


import cn.tedu.domain.Order;
import cn.tedu.domain.OrderItem;

public interface OrderDao {
	/**
	 * 向order添加一条订单记录
	 * @param order 封装了订单信息的order类对象
	 */

	void addOrder(Order order);
	/**
	 * 向orderitem添加一条订单条目记录
	 * @param oi 封装了订单条目信息的orderitem类对象
	 */
	void addOrderItem(OrderItem oi);

}
