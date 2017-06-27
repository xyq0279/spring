package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;
import cn.tedu.utils.webUtils;

public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//1、解决乱码问题
			//解决请求参数乱码问题
			request.setCharacterEncoding("utf-8");
			//响应正文乱码问题
			response.setContentType("text/html;charset=utf-8");
			//2、获取用户注册信息
			String valistr = request.getParameter("valistr");
			//3、校验用户信息
			
			//用户信息不能为空
			
			User user = new User();
			user.checkData();
			
			response.getWriter().write("<h1 style='color:red,text-align:center>亲，您已经注册成功，页面将在3秒后跳转回首页!!!</h1>");
			response.setHeader("refresh","3;url="+request.getContextPath()+"/index.jsp");
			
		} catch (MsgException e) {
			
			request.setAttribute("msg", e.getMessage());
			
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
