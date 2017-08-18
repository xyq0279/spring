package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;
import com.jt.web.pojo.Orders;
import com.jt.web.service.OrderService;
import com.jt.web.threadlocal.UserThreadLocal;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/create")
	public String showOrder(Model model) throws Exception{
		Long userId = UserThreadLocal.getUserId();
		List<Cart> cartList = orderService.queryCartByUserId(userId);
		model.addAttribute("carts", cartList);
		return "order-cart";
	}
	@RequestMapping("/submit")
	@ResponseBody
	public SysResult submitOrder(Orders orders) throws Exception{
		Long userId = UserThreadLocal.getUserId();
		orders.setUserId(userId);
		String orderId =  orderService.submitOrder(orders);
		return SysResult.oK(orderId);
	}
	@RequestMapping("/success")
	public String success(String id,Model model) throws Exception{
		Orders order = orderService.queryOrderByOrderId(id);
		model.addAttribute("order", order);
		return "success";
	}
}
