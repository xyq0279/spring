package com.jt.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.util.CookieUtils;
import com.jt.web.controller.UserController;
import com.jt.web.pojo.User;
import com.jt.web.threadlocal.UserThreadLocal;

public class OrderInterceptor implements HandlerInterceptor{
	@Autowired
	private HttpClientService httpClientService;
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//获取userId
				//1、读取cookie
				String cookieName = UserController.JT_TICKET;
				String ticket = CookieUtils.getCookieValue(request, cookieName);
				//2、调用sso业务去获取redis中的值
				if(!StringUtils.isEmpty(ticket)){
					String url = "http://sso.jt.com/user/query/"+ticket;
					String jsonData = httpClientService.doGet(url);
					if(!StringUtils.isEmpty(jsonData)){
						JsonNode jsonNode = MAPPER.readTree(jsonData);
						String data = jsonNode.get("data").asText();
						User user = MAPPER.readValue(data, User.class);
						//3、userTreadLocal.user
						UserThreadLocal.set(user);
						return true;
					}else{
						UserThreadLocal.set(null);
					}
				}else{
					UserThreadLocal.set(null);
				}
				//4、判断如果cookie、redis中不存在用户信息转向登录页面
				response.sendRedirect("/user/login.html");
				
				return false;//不放行，true才是放行
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
