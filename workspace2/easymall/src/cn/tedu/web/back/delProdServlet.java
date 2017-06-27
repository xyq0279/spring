package cn.tedu.web.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdListService;

public class delProdServlet extends HttpServlet {
	private ProdListService service = BasicFactory.getFactory().getInstance(ProdListService.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取pid
		String pid = request.getParameter("pid");
		//调用service中的方法根据ID删除商品
		boolean b = service.delProdByID(pid);
		//将删除结果的boolean值传回
		response.getWriter().write(b+"");		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
