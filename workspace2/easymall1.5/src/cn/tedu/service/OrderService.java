package cn.tedu.service;

import java.util.List;

import cn.tedu.anno.Tran;
import cn.tedu.domain.Order;
import cn.tedu.domain.OrderInfo;
import cn.tedu.domain.OrderItem;
import cn.tedu.domain.SaleInfo;
import cn.tedu.exception.MsgException;

public interface OrderService extends Service{
	/**
	 * 
	 * @param order
	 * @param oiList
	 * @throws MsgException
	 */
	@Tran
	void addOrder(Order order, List<OrderItem> oiList) throws MsgException;
	/**
	 * 
	 * @param uid
	 * @return
	 */
	List<OrderInfo> findOrderInfoByUid(int uid);
	/**
	 * 
	 * @param oid
	 * @throws MsgException
	 */
	@Tran
	void delOrderByOid(String oid) throws MsgException;
	/**
	 * 
	 * @param oid
	 * @return
	 */
	Order findOrderByOid(String oid);
	
	void updatePaystate(String r6_Order, int i);
	
	List<SaleInfo> findSaleInfo();


}
