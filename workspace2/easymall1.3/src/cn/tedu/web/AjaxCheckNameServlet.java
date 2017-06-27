package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.factory.BasicFactory;
import cn.tedu.service.UserService;


public class AjaxCheckNameServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置乱码问题
		// 请求参数乱码问题
		request.setCharacterEncoding("utf-8");
		// 正文乱码问题
		response.setContentType("text/html;charset=utf-8");
		// 请求获取数据（用户数据）
		String username = request.getParameter("username").trim();
		
		//3、检查用户名是否存在（调用service层的方法）
		UserService service = BasicFactory.getFactory().getInstance(UserService.class);
		
		if(service.hasUser(username)){
			//TRUE--用户已存在
			response.getWriter().write("用户名已经被占用！！");
		}else{
			response.getWriter().write("恭喜！用户名可以使用！");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
