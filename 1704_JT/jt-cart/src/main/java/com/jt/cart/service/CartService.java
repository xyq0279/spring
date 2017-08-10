package com.jt.cart.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.user.UserDestinationMessageHandler;
import org.springframework.stereotype.Service;

import com.jt.cart.mapper.CartMapper;
import com.jt.cart.pojo.Cart;
import com.jt.common.service.BaseService;

@Service
public class CartService extends BaseService<Cart>{
	
	@Autowired
	private CartMapper  cartMapper;
	/**
	 * 根据用户id查看购物车数据
	 * @param userId
	 * @return
	 */
	public List<Cart> findCartList(Long userId) {
		Cart cart = new Cart();
		cart.setUserId(userId);
		List<Cart> cartList = cartMapper.select(cart);
		return cartList;
	}
	/**
	 * 添加上商品进入购物车
	 * @param cart
	 */
	public void addCart(Cart cart) {
		cart.setCreated(new Date());
		cart.setUpdated(cart.getCreated());
		Cart param = new Cart();
		param.setUserId(cart.getUserId());
		param.setItemId(cart.getItemId());
		Cart curCart = super.queryByWhere(param);
		if(curCart!=null){
			curCart.setUpdated(cart.getCreated());
			curCart.setNum(cart.getNum()+curCart.getNum());
			cartMapper.updateByPrimaryKey(curCart);
		}else {
			cartMapper.insertSelective(cart);
		}
	}
	/**
	 * 修改商品数量
	 * @param cart
	 */
	public void updateCart(Cart cart) {
		cartMapper.updateNum(cart);
	}
	/**
	 * 删除商品
	 * @param cart
	 */
	public void deleteCart(Cart cart) {
		cartMapper.delete(cart);
	}
	
}
