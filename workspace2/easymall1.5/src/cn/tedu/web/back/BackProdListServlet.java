package cn.tedu.web.back;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.domain.Product;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdListService;

public class BackProdListServlet extends HttpServlet {
	
	private ProdListService service = BasicFactory.getFactory().getInstance(ProdListService.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1、查询所有的商品信息组成一个list集合并返回
		List<Product> list = service.findAll();
		//2、将商品list存入request域，通过转发将商品数据带到商品列表页
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/backend/prod_list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
