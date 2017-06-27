package cn.tedu.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.domain.Product;

public class CartEditServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取购物车商品ID和修改后的数量
		String pid = request.getParameter("pid");
		int newNum = Integer.parseInt(request.getParameter("newNum"));
		//判断session域中是否存在cart属性
		Object obj = request.getSession().getAttribute("cart");
		if(obj == null){//不存在，session超时失效
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		//存在则获取cart的map集合
		Map<Product,Integer> cart = (Map<Product, Integer>) obj;
		Product prod = new Product();
		prod.setId(pid);
		cart.put(prod, newNum);
		//转发回cart.jsp
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
