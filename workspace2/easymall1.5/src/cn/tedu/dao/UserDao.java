package cn.tedu.dao;

import cn.tedu.domain.User;

public abstract interface UserDao extends Dao{
	/**
	 * 根据用户名查询用户信息是否存在
	 * @param username
	 * @return
	 */
	public User findUserByUsername(String username);
	/**
	 * 将用户信息添加到数据库
	 * @param user
	 */
	public void addUser(User user);
	/**
	 * 根据用户名和密码查询用户信息是否存在
	 * @param username
	 * @param password
	 * @return
	 */
	public User findUserByUsernameAndPassword(String username, String password) ;

}
