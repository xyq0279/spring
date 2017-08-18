package com.jt.order.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.service.BaseService;
import com.jt.order.mapper.OrderMapper;
import com.jt.order.pojo.Orders;

@Service
public class OrderService extends BaseService<Orders>{
	
	@Autowired
	private OrderMapper orderMapper;
	
	public String saveOrder(Orders orders) {
		String orderId = orders.getUserId()+""+System.currentTimeMillis();
		orders.setOrderId(orderId);	
		System.out.println(orders);
		orderMapper.saveOrder(orders);
		return orderId;
	}
	
	public Orders queryOrderByOrderId(String orderId){
		Orders order =  orderMapper.queryOrderByOrderId(orderId);
		System.out.println(order);
		return order;
	}
	public void paymentOrderScan(){
		orderMapper.paymentOrderScan(new DateTime().minusDays(2).toDate());
	}
}
