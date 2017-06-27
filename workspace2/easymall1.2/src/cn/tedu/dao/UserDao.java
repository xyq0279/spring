package cn.tedu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.tedu.Utils.DaoUtils;
import cn.tedu.domain.User;

public class UserDao {
	/**
	 * 根据用户名查询用户信息是否存在
	 * @param username
	 * @return
	 */
	public User findUserByUsername(String username) {
		try {
			QueryRunner qr = new QueryRunner(DaoUtils.getPool());
			return qr.query("select * from user where username = ?", new BeanHandler<User>(User.class), username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			conn = DaoUtils.getconn();
//			String sql = "select * from user where username = ?";
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, username);
//			rs = ps.executeQuery();
////			return rs.next();
//			User user = null;
//			if(rs.next()){
//				user = new User();
//				user.setId(rs.getInt("id"));
//				user.setUsername(rs.getString("username"));
//				user.setPassword(rs.getString("password"));
//				user.setNickname(rs.getString("nickname"));
//				user.setEmail(rs.getString("email"));
//			}
//			return user;
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}finally{
//			DaoUtils.close(conn, ps, rs);
//		}
		
		
	}
	/**
	 * 将用户信息添加到数据库
	 * @param user
	 */
	public void addUser(User user) {
		Connection conn =null;
		PreparedStatement ps = null;
		try {
			conn = DaoUtils.getconn();
			String sql = "insert into user values(null,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNickname());
			ps.setString(4, user.getEmail());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public User findUserByUsernameAndPassword(String username, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DaoUtils.getconn();
			String sql = "select * from user where username = ? and password = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
//			return rs.next();
			User user = null;
			if(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				user.setEmail(rs.getString("email"));
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			DaoUtils.close(conn, ps, rs);
		}
	}

}
