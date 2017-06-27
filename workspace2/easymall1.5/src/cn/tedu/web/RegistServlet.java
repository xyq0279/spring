package cn.tedu.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.tedu.Utils.webUtils;
import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.UserService;

public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 1、解决乱码问题
			// 请求参数乱码问题
			request.setCharacterEncoding("utf-8");
			// 正文乱码问题
			response.setContentType("text/html;charset=utf-8");
			
			// 2、获取用户注册数据
			String valistr = request.getParameter("valistr").trim();
			String code = (String) request.getSession().getAttribute("code");
			// 3、 验证码校验
			if (webUtils.isNull(valistr)) {
				// 校验不通过，将提示消息存入request域中
				request.setAttribute("msg", "验证码不能为空");
				// 通过转发跳转至注册页面，并取出提示消息进行显示
				request.getRequestDispatcher("/regist.jsp").forward(request,
						response);
				return;
			}
			if (!valistr.equalsIgnoreCase(code)) {
				// 校验不通过，将提示消息存入request域中
				request.setAttribute("msg", "验证码错误");
				// 通过转发跳转至注册页面，并取出提示消息进行显示
				request.getRequestDispatcher("/regist.jsp").forward(request,
						response);
				return;
			}
			// 4、将数据封装进JavaBean（user）
			User user = new User();
	
			// 利用BeanUtils工具类封装数据到JavaBean
			BeanUtils.populate(user, request.getParameterMap());
			
			
			// 5、调用JavaBean中的方法校验数据
			user.checkData();
			// 6、 调用service层的方法进行注册
			
			UserService service = BasicFactory.getFactory().getInstance(UserService.class);
			
			user.setPassword(webUtils.md5(user.getPassword()));
			
			service.registUser(user);
			
			// 7、注册成功，跳转回首页
			 //注册成功3秒后跳转页面
			 response.getWriter().write("<h1 style='color:red;text-align:center' >注册成功，页面将在3秒后跳转回首页.....</h1>");
			 response.setHeader("refresh","3;url="+request.getContextPath()+"/index.jsp");
			
		}catch (MsgException e) {
			// 获取异常提示信息，存入request域中
			request.setAttribute("msg", e.getMessage());
			//转发到jsp,取出信息进行提示
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
