package cn.tedu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.tedu.dao.OrderDao;
import cn.tedu.dao.ProdDao;
import cn.tedu.domain.Order;
import cn.tedu.domain.OrderInfo;
import cn.tedu.domain.OrderItem;
import cn.tedu.domain.Product;
import cn.tedu.domain.SaleInfo;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;

public class OrderServiceImpl implements OrderService{
	private ProdDao prodDao = BasicFactory.getFactory().getInstance(ProdDao.class);
	private OrderDao orderDao = BasicFactory.getFactory().getInstance(OrderDao.class);
	public void addOrder(Order order, List<OrderItem> oiList) throws MsgException{
		// 向orders表中添加一个订单
		orderDao.addOrder(order);
		for (OrderItem oi : oiList) {
			// 判断库存是否充足
			Product prod = prodDao.findProdById(oi.getProduct_id());
			if (prod.getPnum() < oi.getBuynum()) {
				throw new MsgException("库存数量不足，库存数量仅为：" + prod.getPnum()
						+ "件，请重新修改商品数量");
			}
			//如果库存充足，通过ID修改库存数量
			prodDao.updatePnum(prod.getPnum()-oi.getBuynum(), oi.getProduct_id());
			// 向OrderItem中添加一个订单条目
			orderDao.addOrderItem(oi);	
		}
	}
	public List<OrderInfo> findOrderInfoByUid(int uid) {
		
		List<OrderInfo> oilist = new ArrayList<OrderInfo>();
		
		List<Order> olist = orderDao.findOrderByUid(uid);
		
		List<OrderItem> oitem = orderDao.findOrderItemByUid(uid);
		
		for(Order order: olist){
			
			OrderInfo orderInfo = new OrderInfo();
			
			orderInfo.setOrder(order);
			
			Map<Product, Integer> map = new HashMap<Product, Integer>();
			
			for(OrderItem oi:oitem){
				if(oi.getOrder_id().equals(order.getId())){
					Product prod = prodDao.findProdById(oi.getProduct_id());
					map.put(prod, oi.getBuynum());
				}
			}
			
			orderInfo.setMap(map);
			oilist.add(orderInfo);
		}
		return oilist;
	}
	public void delOrderByOid(String oid) throws MsgException {
		Order order = orderDao.findOrderByOid(oid);
		if(order.getPaystate()==1){
			throw new MsgException("该订单已支付成功，不能删除");
		}
		List<OrderItem> list = orderDao.findOrderItemByUid(order.getUser_id());
		for(OrderItem oi:list){
			prodDao.updatePnumById(oi.getBuynum(),oi.getProduct_id());
		}
		if(!(orderDao.delOrderitemByOid(oid)|orderDao.delOrderByOid(oid))){
			throw new MsgException("查找不到该订单");
		}
		
	}
	public Order findOrderByOid(String oid) {
		// TODO Auto-generated method stub
		return orderDao.findOrderByOid(oid);
	}
	public void updatePaystate(String r6_Order, int i) {
		// TODO Auto-generated method stub
		Order order = orderDao.findOrderByOidForUpdate(r6_Order);
		if(order.getPaystate()==0&&order!=null){
			orderDao.updatePaystate(r6_Order,i);
		}
		
	}
	public List<SaleInfo> findSaleInfo() {
		
		return orderDao.findSaleInfo();
	}
	
	
}
