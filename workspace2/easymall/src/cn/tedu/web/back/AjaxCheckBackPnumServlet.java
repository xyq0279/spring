package cn.tedu.web.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdListService;

public class AjaxCheckBackPnumServlet extends HttpServlet {
	private ProdListService service = BasicFactory.getFactory().getInstance(ProdListService.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pnum = Integer.parseInt(request.getParameter("pnum"));
		String pid = request.getParameter("pid");
		boolean b = service.updatePnum(pnum,pid);
		response.getWriter().write(b+"");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
