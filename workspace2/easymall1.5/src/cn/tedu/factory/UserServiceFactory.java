package cn.tedu.factory;

import cn.tedu.Utils.PropUtils;
import cn.tedu.service.UserService;

public class UserServiceFactory {
	
	private static UserServiceFactory factory = new UserServiceFactory();
	
	private UserServiceFactory(){}
	
	public static UserServiceFactory getFactory(){
		return factory;
	}
	
	@SuppressWarnings("rawtypes")
	public UserService getInstance(){
		try {
			String className = PropUtils.getPro("UserService");
			
			Class clz = Class.forName(className);
			
			return (UserService) clz.newInstance();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
}
