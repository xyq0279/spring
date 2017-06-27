package cn.tedu.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	 * 查询
	 * @param sql
	 * @param rsh
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	public static <T> T query(String sql,ResultSetHandler<T> rsh,Object... params) throws SQLException{
		Connection conn  =null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = TranManage.getconn();
			ps = conn.prepareStatement(sql);
			if(params!=null&&params.length>0){
				for(int i = 0;i<params.length;i++){
					ps.setObject(i+1, params[i]);
				}
			}
			rs = ps.executeQuery();
			//处理结果集
			return rsh.handle(rs);
		} catch (Exception e) {
			throw new RuntimeException(e);
			
		}finally{
			close(null, ps, rs);
		}
	}
	/**
	 * 增删改
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static int update(String sql,Object... params) throws SQLException{
		Connection conn  =null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = TranManage.getconn();
			ps = conn.prepareStatement(sql);
			if(params!=null && params.length>0){
				for(int i=0;i<params.length;i++){
					ps.setObject(i+1,params[i]);
				}
			}
			return ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			close(null, ps, rs);
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
