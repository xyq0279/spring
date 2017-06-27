package cn.tedu.jdbc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
	private static Properties prop = new Properties();
	private JDBCUtils(){}
	
	static{
		try {
			//该路径不要写死, 最好动态去获取
			//String path = "bin/config.properties";
			
			//类加载器
			ClassLoader classLoader = JDBCUtils.class.getClassLoader();
			//D:\softspace\Workspaces\BIG_1704\day07\bin\config.properties
			String path = classLoader.getResource("config.properties").getPath();
			
			prop.load(new FileInputStream(new File(path)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConn(){
		try {
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * 关闭资源
	 * @param conn 数据库连接
	 * @param stat 传输器
	 * @param rs 结果集
	 */
	public static void close(Connection conn, Statement stat, ResultSet rs){
		if(rs != null ){
			try {
				rs.close();//关闭rs
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				rs = null;
			}
		}
		if(stat != null ){
			try {
				stat.close();//关闭stat
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				stat = null;
			}
		}
		if(conn != null ){
			try {
				conn.close();//关闭conn
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				conn = null;
			}
		}
	}
}
