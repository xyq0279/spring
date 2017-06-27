package cn.tedu.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxCheckCodeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置乱码问题
		// 请求参数乱码问题
		request.setCharacterEncoding("utf-8");
		// 正文乱码问题
		response.setContentType("text/html;charset=utf-8");
		// 请求获取数据（验证码数据）
		String valistr = request.getParameter("valistr").trim();
		
		if(request.getSession(false) != null){
			String code = (String) request.getSession().getAttribute("code");
			if(valistr.equalsIgnoreCase(code)){
				response.getWriter().write("验证码正确");
				return;
			}else{
				response.getWriter().write("验证码错误");
				return;
			}
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
