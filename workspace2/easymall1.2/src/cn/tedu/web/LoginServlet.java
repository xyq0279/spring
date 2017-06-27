package cn.tedu.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.Utils.DaoUtils;
import cn.tedu.domain.User;
import cn.tedu.service.UserService;


/**
 * 处理登录信息
 * 
 * @author Administrator
 * 
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 1、乱码问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 2、获取用户数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String remname = request.getParameter("remname");
//		String autologin = request.getParameter("autologin");

		///3、实现记住用户名

		if ("true".equals(remname)) {// 如果勾选了记住用户名

			// Cookie cookie = new Cookie("rename", username);
			Cookie cookie = new Cookie("remname", URLEncoder.encode(username,
					"utf-8"));
			// 设置cookie的最大生存时间
			cookie.setMaxAge(3600 * 24 * 7);
			// 设置cookie的path
			cookie.setPath(request.getContextPath() + "/");
			// 将cookie添加到response中，在浏览器中保存cookie信息
			response.addCookie(cookie);
		} else {// 如果取消了记住用户名（删除cookie）
			Cookie cookie = new Cookie("remname", "");
			// 设置最大生存时间
			cookie.setMaxAge(0);
			// 设置cookie的path
			cookie.setPath(request.getContextPath() + "/");
			// 将cookie添加到response中，在浏览器中保存cookie信息
			response.addCookie(cookie);
		}
		//4、 登录用户
		UserService service = new UserService();
		User user = service.loginUser(username,password);
		if(user ==null){
			// 如果用户名密码错误
			request.setAttribute("msg", "用户名或密码输入错误，请重新输入！！！");
			// 请求转发回登录页面
			request.getRequestDispatcher(request.getContextPath() + "/login.jsp").forward(request, response);
		}else{
			// 如果用户名密码正确
			// 将User对象写入session，作为用户登录标识
			request.getSession().setAttribute("user", user);
			// 登录成功后跳转回首页
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
		
		/*// 登录用户
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DaoUtils.getconn();
			String sql = "select * from user where username=? and password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				// 如果用户名密码正确
				// 将用户名写入session，作为用户登录标识
				request.getSession().setAttribute("user", username);
				// 登录成功后跳转回首页
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			} else {
				// 如果用户名密码错误
				request.setAttribute("msg", "用户名或密码输入错误，请重新输入！！！");
				// 请求转发回登录页面
				request.getRequestDispatcher(
						request.getContextPath() + "/login.jsp").forward(
						request, response);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DaoUtils.close(conn, ps, rs);
		}
*/
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
