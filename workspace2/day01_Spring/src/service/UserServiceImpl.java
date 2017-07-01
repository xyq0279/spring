package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;

import pojo.User;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public void userAdd(User u) {
		userDao.userAdd(u);
		System.out.println(u+"做咩嗟~~~");
		
	}

	
	
}
