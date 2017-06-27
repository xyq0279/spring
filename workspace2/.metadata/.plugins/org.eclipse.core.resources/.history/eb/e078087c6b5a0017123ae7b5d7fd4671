package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.PersonService;
import service.UserService;
import web.PersonServlet;
//import web.UserServlet;

public class SpringTest{
	@Test
	public void test1(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		UserService userService = (UserService) context.getBean("target");
		System.out.println(userService.getClass());
		userService.addUser();
		
//		UserServlet userServlet = (UserServlet) context.getBean("userServlet");
//		System.out.println(userServlet.getClass());
//		
//		userServlet.addUser();
//		
	}
	@Test
	public void test2(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		PersonService person = (PersonService) context.getBean("person");
		System.out.println(person.getClass());
		person.save();
		
		
	}
	@Test
	public void test3(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		PersonServlet person = (PersonServlet) context.getBean("personServlet");
		System.out.println(person.getClass());
		person.savePerson();
		System.out.println("ok");
		
	}
	
}
