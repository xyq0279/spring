package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;

public class OrderDeleteServlet extends HttpServlet {
	private OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取订单ID
		String oid = request.getParameter("id");
		try{
			//根据订单ID删除orders和orderitem表中对应的订单和订单条目
			service.delOrderByOid(oid);
			//恢复products表中对应商品的库存数量
		
			response.getWriter().write("订单删除成功,3秒后自动跳转回订单页，如果没有跳转请<a href=" +request.getContextPath()+
					"'/OrderListServlet'>点击这里</a>");
		}catch(MsgException e){
			response.getWriter().write(e.getMessage()+",3秒后自动跳转回订单页，如果没有跳转请<a href=" +request.getContextPath()+
					"'/OrderListServlet'>点击这里</a>");
		}
		response.setHeader("refresh", "3;url="+request.getContextPath()+"/OrderListServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
