package cn.tedu.dao.impl;

import java.sql.SQLException;



import cn.tedu.Utils.BeanHandler;
import cn.tedu.Utils.DaoUtils;
import cn.tedu.dao.UserDao;
import cn.tedu.domain.User;

public class UserDaoImpl implements UserDao{
	/**
	 * 根据用户名查询用户信息是否存在
	 * @param username
	 * @return
	 */
	public User findUserByUsername(String username) {
		try {
			return DaoUtils.query("select * from user where username = ?", new BeanHandler<User>(User.class), username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * 将用户信息添加到数据库
	 * @param user
	 */
	public void addUser(User user) {
		try {
			DaoUtils.update("insert into user values(null,?,?,?,?)", user.getUsername(),user.getPassword(),user.getNickname(),user.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return User
	 */
	public User findUserByUsernameAndPassword(String username, String password) {
		try {
			return DaoUtils.query("select * from user where username=? and password=?", new BeanHandler<User>(User.class), username,password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
