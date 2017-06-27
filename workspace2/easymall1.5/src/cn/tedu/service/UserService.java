package cn.tedu.service;

import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;

public interface UserService extends Service{

	/**
	 * 注册用户
	 * @param user
	 * @throws MsgException 
	 */
	public void registUser(User user) throws MsgException;
	/**
	 * 
	 * @param username
	 * @return
	 */
	public boolean hasUser(String username) ;
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public User loginUser(String username, String password);

}
