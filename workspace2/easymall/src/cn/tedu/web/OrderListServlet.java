package cn.tedu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.domain.OrderInfo;
import cn.tedu.domain.User;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;

public class OrderListServlet extends HttpServlet {
	private OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		//检查用户是否登录
		Object userObj = request.getSession().getAttribute("user");
		if(userObj == null){
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		//获得用户ID
		int uid = ((User)userObj).getId();
		
		List<OrderInfo> list = service.findOrderInfoByUid(uid);
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/order_list.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
