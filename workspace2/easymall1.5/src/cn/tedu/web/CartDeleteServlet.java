package cn.tedu.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.domain.Product;

public class CartDeleteServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1、获取商品的ID
		String pid = request.getParameter("pid");
		//2、获取session域中的cart属性
		Object obj = request.getSession().getAttribute("cart");
		//3、判断session域中是否存在cart属性
		if(obj == null){//不存在：session失效
			//转发回首页
			request.getRequestDispatcher("/index.jsp");
			return;
		}
		//获得cart对象的map集合
		Map<Product,Integer> cart = (Map<Product, Integer>) obj;
		//4、根据商品ID将商品从cart的map集合中移除
		Product prod = new Product();
		prod.setId(pid);
		cart.remove(prod);
		//5、转发到cart.jsp
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
