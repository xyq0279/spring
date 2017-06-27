package cn.tedu.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.domain.Order;
import cn.tedu.domain.OrderItem;
import cn.tedu.domain.Product;
import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;

public class OrderAddServlet extends HttpServlet {
	
	private OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//判断用户是否已经登录
		Object userObj = request.getSession().getAttribute("user");
		if(userObj == null){
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		//强转成user对象
		User user = (User) userObj;
		//判断session中是否存在cart
		Object cartObj = request.getSession().getAttribute("cart");
		if(cartObj == null){
			request.getRequestDispatcher("/prod_list.jsp").forward(request, response);
			return;
		}
		//强转成cart的map对象
		Map<Product,Integer> cart = (Map<Product, Integer>) cartObj;
		//新建一个order对象
		Order order = new Order();
		//新建一个新的订单列表
		List<OrderItem> oiList = new ArrayList<OrderItem>();
		
		order.setId(UUID.randomUUID().toString());
		order.setOrdertime(new Date());
		order.setPaystate(0);//0表示未支付
		order.setReceiverinfo(request.getParameter("receiverinfo"));
		order.setUser_id(user.getId());
		
		double money = 0;
		for(Map.Entry<Product, Integer> entry : cart.entrySet()){
			OrderItem oi = new OrderItem();
			oi.setOrder_id(order.getId());
			oi.setProduct_id(entry.getKey().getId());
			oi.setBuynum(entry.getValue());
			money = money+entry.getKey().getPrice()*entry.getValue();
			oiList.add(oi);
		}
		order.setMoney(money);
		
		try{//如果订单添加成功
			service.addOrder(order,oiList);
			response.getWriter().write("订单添加成功！！");
			response.setHeader("refresh", "3;url="+request.getContextPath()+"/OrderListServlet");
		}catch(MsgException e){//如果订单添加失败
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
