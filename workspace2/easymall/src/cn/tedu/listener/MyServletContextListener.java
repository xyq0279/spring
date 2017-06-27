package cn.tedu.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		// 在servletContext对象创建前将web应用的URL存入该域中
		//获取ServletContext域
		ServletContext context = sce.getServletContext();
		//获取web应用的虚拟路径
		String contextPath = context.getContextPath();
		//将路径存入ServletContext域
		context.setAttribute("app", contextPath);
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
