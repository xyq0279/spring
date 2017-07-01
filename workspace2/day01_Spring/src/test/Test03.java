package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pojo.People;

public class Test03 {
	@Test
	public void test(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext4.xml");
		People p = (People) context.getBean("people");
		System.out.println(p);
	}
}
