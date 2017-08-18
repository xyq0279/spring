package com.jt.order.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.vo.SysResult;
import com.jt.order.pojo.Orders;
import com.jt.order.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	/**
	 * 用@RequestBody接收json串数据
	 * @param json
	 * @return
	 * @throws Exception
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping("/create")
	@ResponseBody
	public SysResult createOrder(@RequestBody String json) throws Exception, JsonMappingException, IOException{
		Orders orders =  MAPPER.readValue(json, Orders.class);
		String orderId = orderService.saveOrder(orders);
		return SysResult.oK(orderId);
	}
	@RequestMapping("/query/{orderId}")
	@ResponseBody
	public Orders queryOrderByOrderId(@PathVariable String orderId){
		return orderService.queryOrderByOrderId(orderId);
	}
}
