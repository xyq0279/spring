package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.domain.Product;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdListService;

public class ProductShowServlet extends HttpServlet {
	private ProdListService service = BasicFactory.getFactory().getInstance(ProdListService.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pid = request.getParameter("pid");
		Product product = service.findProdById(pid);
		request.setAttribute("prod", product);
		request.getRequestDispatcher("/prod_info.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
