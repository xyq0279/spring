package cn.tedu.Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DaoUtils {
	
	private static ComboPooledDataSource pool= new ComboPooledDataSource();
	
	private DaoUtils(){}
	
	/**
	 * 获取连接池实例
	 * @return
	 */
	public static DataSource getPool() {
		return pool;
	}
	
	/**
	 * 从连接池中获得一个连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getconn() throws SQLException {
		// TODO Auto-generated method stub
		return pool.getConnection();
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
