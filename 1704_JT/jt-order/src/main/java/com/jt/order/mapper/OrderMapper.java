package com.jt.order.mapper;

import java.util.Date;

import com.jt.common.mapper.SysMapper;
import com.jt.order.pojo.Orders;

public interface OrderMapper extends SysMapper<Orders>{
	
	public void saveOrder(Orders orders);
	
	public Orders queryOrderByOrderId(String orderId);
	
	public void paymentOrderScan(Date date);
}
