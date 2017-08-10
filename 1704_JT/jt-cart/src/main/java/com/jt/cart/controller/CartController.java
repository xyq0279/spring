package com.jt.cart.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.cart.pojo.Cart;
import com.jt.cart.service.CartService;
import com.jt.common.vo.SysResult;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	private static final Logger log = Logger.getLogger(CartController.class);
	
	@Autowired
	private CartService cartService;
	//查看购物车数据
	@RequestMapping("/query/{userId}")
	@ResponseBody
	public SysResult findCartList(@PathVariable Long userId){
		try{
			List<Cart> cartList = cartService.findCartList(userId);
			return SysResult.oK(cartList);
		}catch(Exception e){
			log.error(e.getMessage());
			return SysResult.build(201, e.getMessage());
		}
	}
	//保存购物车数据
	@RequestMapping("/save")
	@ResponseBody
	public SysResult addCart(Cart cart){
		try{
			System.out.println(cart);
			cartService.addCart(cart);
			return SysResult.oK();
		}catch(Exception e){
			log.error(e.getMessage());
			return SysResult.build(201, e.getMessage());
		}
	}
	//修改购物车商品数量
	
	// 保存购物车数据
	@RequestMapping("/update/num/{userId}/{itemId}/{num}")
	@ResponseBody
	public SysResult updateCart(Cart cart) {
		try {
			cartService.updateCart(cart);
			return SysResult.oK();
		} catch (Exception e) {
			log.error(e.getMessage());
			return SysResult.build(201, e.getMessage());
		}
	}

	// 删除购物车数据
	@RequestMapping("/delete/{userId}/{itemId}")
	@ResponseBody
	public SysResult deleteCart(Cart cart) {
		try {
			cartService.deleteCart(cart);
			return SysResult.oK();
		} catch (Exception e) {
			log.error(e.getMessage());
			return SysResult.build(201, e.getMessage());
		}
	}
}
