package cn.tedu.web.back;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.domain.SaleInfo;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;

public class SaleInfoListServlet extends HttpServlet {
	private OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<SaleInfo> list = service.findSaleInfo();
		//将list保存request中
		request.setAttribute("list", list);
		//转发
		request.getRequestDispatcher("/backend/sale_list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
