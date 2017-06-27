package cn.tedu.service;

import cn.tedu.dao.UserDao;
import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;

public class UserService {
	private UserDao dao = new UserDao();
	/**
	 * 注册用户
	 * @param user
	 * @throws MsgException 
	 */
	public void registUser(User user) throws MsgException {
		//1、调用Dao层的方法检查用户名是否存在
		boolean b = (dao.findUserByUsername(user.getUsername()))!=null;
		if(b){
			//存在
			throw new MsgException("用户名已存在！");
			
		}
		//2、 调用Dao层的方法将用户数据保存进数据库
		dao.addUser(user);
	}
	/**
	 * 检查用户名是否存在
	 * @param username
	 * @return
	 */
	public boolean hasUser(String username) {
		return dao.findUserByUsername(username)!=null;	
	}
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public User loginUser(String username, String password) {
		
		return dao.findUserByUsernameAndPassword(username,password);
		
	}

}
