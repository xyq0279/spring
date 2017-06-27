package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.Utils.VerifyCode;


public class VerifyCodeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setDateHeader("Expires", -1);
		
		response.setHeader("Cache-control", "no-cache");
		
		VerifyCode vc = new VerifyCode();
		
		vc.drawImage(response.getOutputStream());
		
		String code = vc.getCode();
		
		request.getSession().setAttribute("code", code);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
