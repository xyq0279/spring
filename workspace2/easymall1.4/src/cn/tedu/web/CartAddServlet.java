package cn.tedu.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.domain.Product;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdListService;

public class CartAddServlet extends HttpServlet {
	private ProdListService service = BasicFactory.getFactory().getInstance(ProdListService.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取商品ID
		String pid = request.getParameter("pid");
		//创建一个cart对象
		Map<Product,Integer> cart = null;
		//获取存放在session域中的cart属性
		Object obj = request.getSession().getAttribute("cart");
		//判断session域中是否存在购物车cart信息
		if(obj == null){//不存在：从未购买过该商品，或者session超时失效
			cart = new HashMap<Product, Integer>();
			request.getSession().setAttribute("cart", cart);
		}else{//存在
			cart = (Map<Product, Integer>) obj;
		}
		//通过service层的方法根据ID查询商品信息并返回
		Product prod = service.findProdById(pid);
		if(cart.containsKey(prod)){//购买过该商品
			cart.put(prod, cart.get(prod)+1);
		}else{//未购买过该商品
			cart.put(prod, 1);
		}
//		//转发到cart页面
//		request.getRequestDispatcher("/cart.jsp").forward(request, response);
		//重定向，避免重复提交
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
