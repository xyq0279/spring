package cn.tedu.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import cn.tedu.domain.User;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.UserService;

public class AutoLoginFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//实现30天自动登录
		
		HttpServletRequest req = (HttpServletRequest) request;
		//用户是否是未登录状态
		if(req.getSession(false)==null || req.getSession().getAttribute("user")==null){//未登录状态
			//必须要有autologin cookie
			Cookie[] cs =req.getCookies();
			Cookie autoLoginC = null;
			if(cs!=null){
				for(Cookie c: cs){
					if("autologin".equals(c.getName())){
						autoLoginC = c;
						break;
					}
				}
			}
			if(autoLoginC!=null){
				
				String[] str = URLDecoder.decode(autoLoginC.getValue(), "utf-8").split(":");
				String username = str[0];
				String password = str[1];
				
				UserService service = BasicFactory.getFactory().getInstance(UserService.class);
				
				User user = service.loginUser(username,password);
				
				if(username !=null){//从cookie中的用户名和密码必须正确
					
					req.getSession().setAttribute("user", user);
				}
			}
		}
		
		//无论是否30天内自动登录，一定要放行过滤器
		chain.doFilter(request, response);
		
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
