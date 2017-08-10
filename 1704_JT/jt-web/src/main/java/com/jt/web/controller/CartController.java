package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.util.CookieUtils;
import com.jt.web.pojo.Cart;
import com.jt.web.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	//查看购物车
	@RequestMapping("/show")
	public String showCart(Model model) throws Exception{
		Long userId = 1L;
		List<Cart> cartList = cartService.findCartList(userId);
		model.addAttribute("cartList", cartList);
		
		return "cart";
	}
	//增加商品到购物车
	@RequestMapping("/add/{itemId}")
	public String addCart(Cart cart) throws Exception{
		Long userId = 1L;
		cart.setUserId(userId);
		System.out.println(cart);
		cartService.addCart(cart);
		return "redirect:/cart/show.html";
	}
	@RequestMapping("/update/num/{itemId}/{num}")
	@ResponseBody
	public String updateCart(@PathVariable Long itemId,@PathVariable Integer num) throws Exception{
		Long userId = 1L;
		cartService.updateCart(userId,itemId,num);
		return "";
	}
	
	@RequestMapping("/delete/{itemId}")
	public String deleteCart(@PathVariable Long itemId) throws Exception{
		Long userId = 1L;
		cartService.deleteCart(itemId,userId);
		return "redirect:/cart/show.html";
	}
}
