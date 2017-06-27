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
			// user.setUsername(username);
			// user.setPassword(password);
			// user.setPassword2(password2);
			// user.setNickname(nickname);
			// user.setEmail(email);
			// 利用BeanUtils工具类封装数据到JavaBean
			BeanUtils.populate(user, request.getParameterMap());

			// 5、调用JavaBean中的方法校验数据
			user.checkData();
			// 6、 调用service层的方法进行注册
			UserService service = new UserService();
			service.registUser(user);
			// 7、注册成功，跳转回首页
			 //注册成功3秒后跳转页面
			 response.getWriter().write("<h1 style='color:red;text-align:center' >注册成功，页面将在3秒后跳转回首页.....</h1>");
			 response.setHeader("refresh","3;url="+request.getContextPath()+"/index.jsp");
			
		}catch (MsgException e) {
			// 获取异常提示信息，存入request域中
			request.setAttribute("msg", e.getMessage());
			//转发到jsp,取出
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// //设置乱码问题
		// //请求参数乱码问题
		// request.setCharacterEncoding("utf-8");
		// //正文乱码问题
		// response.setContentType("text/html;charset=utf-8");
		// //请求获取数据（用户数据）
		// String username = request.getParameter("username").trim();
		// String password = request.getParameter("password").trim();
		// String password2 = request.getParameter("password2").trim();
		// String nickname = request.getParameter("nickname").trim();
		// String email = request.getParameter("email").trim();
		// String valistr = request.getParameter("valistr").trim();
		// //非空校验
		// //用户名校验
		// if(webUtils.isNull(username)){
		// //校验不通过，将提示消息存入request域中
		// request.setAttribute("msg", "用户名不能为空");
		// //通过转发跳转至注册页面，并取出提示消息进行显示
		// request.getRequestDispatcher("/regist.jsp").forward(request,
		// response);
		// return;
		// }
		// //密码校验
		// if(webUtils.isNull(password)){
		// //校验不通过，将提示消息存入request域中
		// request.setAttribute("msg", "密码不能为空");
		// //通过转发跳转至注册页面，并取出提示消息进行显示
		// request.getRequestDispatcher("/regist.jsp").forward(request,
		// response);
		// return;
		// }
		// //确认密码校验
		// if(webUtils.isNull(password2)){
		// //校验不通过，将提示消息存入request域中
		// request.setAttribute("msg", "确认密码不能为空");
		// //通过转发跳转至注册页面，并取出提示消息进行显示
		// request.getRequestDispatcher("/regist.jsp").forward(request,
		// response);
		// return;
		// }
		// //昵称校验
		// if(webUtils.isNull(nickname)){
		// //校验不通过，将提示消息存入request域中
		// request.setAttribute("msg", "昵称不能为空");
		// //通过转发跳转至注册页面，并取出提示消息进行显示
		// request.getRequestDispatcher("/regist.jsp").forward(request,
		// response);
		// return;
		// }
		// // 邮箱校验
		// if (webUtils.isNull(email)) {
		// // 校验不通过，将提示消息存入request域中
		// request.setAttribute("msg", "邮箱不能为空");
		// // 通过转发跳转至注册页面，并取出提示消息进行显示
		// request.getRequestDispatcher("/regist.jsp").forward(request,response);
		// return;
		// }
		//
		// // 验证码校验
		// if (webUtils.isNull(valistr)) {
		// // 校验不通过，将提示消息存入request域中
		// request.setAttribute("msg", "验证码不能为空");
		// // 通过转发跳转至注册页面，并取出提示消息进行显示
		// request.getRequestDispatcher("/regist.jsp").forward(request,response);
		// return;
		// }
		// String code = (String) request.getSession().getAttribute("code");
		// if(!valistr.equals(code)){
		// // 校验不通过，将提示消息存入request域中
		// request.setAttribute("msg", "验证码错误");
		// // 通过转发跳转至注册页面，并取出提示消息进行显示
		// request.getRequestDispatcher("/regist.jsp").forward(request,response);
		// return;
		// }
		//
		// //密码不能重复
		// if(!password.equals(password2)){
		// // 校验不通过，将提示消息存入request域中
		// request.setAttribute("msg", "密码不一致");
		// // 通过转发跳转至注册页面，并取出提示消息进行显示
		// request.getRequestDispatcher("/regist.jsp").forward(request,response);
		// return;
		// }
		// String str = "^\\w+@+\\w+(.\\w+)+$";
		// //邮箱格式
		// if(!email.matches(str)){
		// // 校验不通过，将提示消息存入request域中
		// request.setAttribute("msg", "邮箱格式不正确");
		// // 通过转发跳转至注册页面，并取出提示消息进行显示
		// request.getRequestDispatcher("/regist.jsp").forward(request,response);
		// return;
		// }
		//
		//
		//
		//
		//
		// //注册用户
		//
		// Connection conn = null;
		// PreparedStatement ps= null;
		// // Statement stat=null;
		// ResultSet rs = null;
		// String sql = null;
		// //判断用户是否已存在
		// try {
		// //使用c3p0获得一个连接
		// conn = JDBCUtils.getconn();
		// sql = "select * from user where username=?";
		// ps = conn.prepareStatement(sql);
		// ps.setString(1, username);
		// rs = ps.executeQuery();
		// if(rs.next()){
		// request.setAttribute("msg", "该用户已存在");
		// request.getRequestDispatcher("/regist.jsp").forward(request,
		// response);
		// return;
		// }
		// //写入数据库的表中
		// sql = "insert into user value(null,?,?,?,?)";
		// ps = conn.prepareStatement(sql);
		// ps.setString(1, username);
		// ps.setString(2, password);
		// ps.setString(3, nickname);
		// ps.setString(4, email);
		// ps.executeUpdate();
		//
		// //注册成功3秒后跳转页面
		// response.getWriter().write("<h1 style='color:red;text-align:center' >注册成功，页面将在3秒后跳转回首页.....</h1>");
		// response.setHeader("refresh",
		// "3;url="+request.getContextPath()+"/index.jsp");
		//
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }finally{
		// JDBCUtils.close(conn, ps, rs);
		// }
		//
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
