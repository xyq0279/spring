package test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import damain.User;

public class Test1 {
	@Test
	public void test1() throws SQLException{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		
		Connection connection = dataSource.getConnection();
		
		//如果连接关闭 true 如果连接正常 为false
		boolean flag = connection.isClosed();
		System.out.println("测试数据库连接是否关闭："+flag);
	}
	
	//通过JDBC模板类操作数据库
	@Test
	public void test2() throws SQLException{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		
		String sql = "select * from user";
	
		
	}
}
