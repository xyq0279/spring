package com.jt.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.web.pojo.Cart;

@Service
public class CartService{
	
	@Autowired
	private HttpClientService httpClientService;
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	/**
	 * 查找购物车列表
	 * @param userId 用户id
	 * @return
	 * @throws Exception 
	 */
	public List<Cart> findCartList(Long userId) throws Exception {
		String url = "http://cart.jt.com/cart/query/"+userId;
		
		String jsonData = httpClientService.doGet(url);
		JsonNode jsonNode = MAPPER.readTree(jsonData);
		JsonNode cartListNodes = jsonNode.get("data");
		Object obj = null;
		if(cartListNodes.isArray()&&cartListNodes.size()>0) {
			obj = MAPPER.readValue(cartListNodes.traverse(),
					MAPPER.getTypeFactory().constructCollectionType(List.class, Cart.class));
		}
		return (List<Cart>) obj;
	}
	/**
	 * 添加商品到购物车
	 * @param cart
	 * @throws Exception 
	 */
	public void addCart(Cart cart) throws Exception {
		
		String url = "http://cart.jt.com/cart/save";
		Map<String,String> map = new HashMap<String,String>();
		map.put("userId", cart.getUserId()+"");
		map.put("itemId", cart.getItemId()+"");
		map.put("itemTitle", cart.getItemTitle());
		map.put("itemImage", cart.getItemImage());
		map.put("itemPrice", cart.getItemPrice()+"");
		map.put("num", cart.getNum()+"");
		System.out.println(cart);
		httpClientService.doPost(url, map, "utf-8");
	}
	/**
	 * 
	 * @param userId
	 * @param itemId
	 * @param num
	 * @throws Exception
	 */
	public void updateCart(Long userId, Long itemId, Integer num) throws Exception {
		String url = "http://cart.jt.com/cart/update/num/"+userId+"/"+itemId+"/"+num;
		httpClientService.doGet(url);
	}
	public void deleteCart(Long itemId, Long userId) throws Exception {
		String url = "http://cart.jt.com/cart/delete/"+userId+"/"+itemId;
		httpClientService.doGet(url);
	}
	
}
