package com.jt.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.web.pojo.Cart;
import com.jt.web.pojo.Orders;

@Service
public class OrderService {
	
	@Autowired
	private HttpClientService httpClientService;
	
	private static final ObjectMapper MAPPER = new ObjectMapper();

	public List<Cart> queryCartByUserId(Long userId) throws Exception {
		String url = "http://cart.jt.com/cart/query/"+userId;
		String jsonDate = httpClientService.doGet(url);
		JsonNode node = MAPPER.readTree(jsonDate);
		JsonNode array = node.get("data");
		Object obj = null;
		if(array.isArray()&&array.size()>0){
			obj = MAPPER.readValue(array.traverse(),MAPPER.getTypeFactory().constructCollectionType(List.class, Cart.class));
		}
		return (List<Cart>) obj;
	}

	public String submitOrder(Orders orders) throws Exception {
		String url = "http://order.jt.com/order/create";
		String json = MAPPER.writeValueAsString(orders);
		String jsonData = httpClientService.doPostJson(url, json);
		JsonNode node = MAPPER.readTree(jsonData);
		String orderId = node.get("data").asText();
		return orderId;
	}

	public Orders queryOrderByOrderId(String orderId) throws Exception {
		String url = "http://order.jt.com/order/query/"+orderId;
		String jsonData = httpClientService.doGet(url);
		Orders order = MAPPER.readValue(jsonData, Orders.class);
		return order;
	}
	
}
