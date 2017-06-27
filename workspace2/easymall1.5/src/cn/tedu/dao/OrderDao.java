package cn.tedu.dao;


import java.util.List;

import cn.tedu.domain.Order;
import cn.tedu.domain.OrderItem;
import cn.tedu.domain.SaleInfo;

public interface OrderDao extends Dao{
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
	/**
	 * 
	 * @param uid
	 * @return
	 */
	List<Order> findOrderByUid(int uid);
	/**
	 * 
	 * @param uid
	 * @return
	 */
	List<OrderItem> findOrderItemByUid(int uid);
	/**
	 * 
	 * @param oid
	 * @return
	 */
	Order findOrderByOid(String oid);
	/**
	 * 
	 * @param oid
	 */
	boolean delOrderByOid(String oid);
	/**
	 * 
	 * @param oid
	 */
	boolean delOrderitemByOid(String oid);
	
	void updatePaystate(String r6_Order, int i);
	
	Order findOrderByOidForUpdate(String r6_Order);
	
	List<SaleInfo> findSaleInfo();
	

}
