package cn.tedu.service;

import java.util.List;

import cn.tedu.domain.Order;
import cn.tedu.domain.OrderItem;
import cn.tedu.exception.MsgException;

public interface OrderAddService extends Service{

	void addOrder(Order order, List<OrderItem> oiList) throws MsgException;

}
