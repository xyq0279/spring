package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.PersonService;
//import web.UserServlet;

public class SpringTest{
	@Test
	public void test2(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		PersonService person = (PersonService) context.getBean("person");
		System.out.println(person.getClass());
		person.save();
		
		
	}
	
}
