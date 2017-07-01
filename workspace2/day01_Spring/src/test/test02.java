package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import web.UserServlet;

public class test02 {
	
	
	@Test
	public void test(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");
		UserServlet s = (UserServlet) context.getBean("userServlet");
		s.userAdd();
	}
}
