package cn.tedu.factory;

import cn.tedu.Utils.PropUtils;
import cn.tedu.dao.UserDao;

public class UserDaoFactory {
	private static UserDaoFactory factory = new UserDaoFactory();
	private UserDaoFactory(){}
	public static UserDaoFactory getFactory(){
		return factory;
	}
	public UserDao getInstance(){
		try {
			//读取配置文件中配置的信息（类的全限定名称）
			String className = PropUtils.getPro("UserDao");
			
			//根据类的全限定名称创建该类的实例并返回
			Class clz = Class.forName(className);
			return (UserDao) clz.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
