package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;


@Service("target")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userdao;
	
	@Override
	public void addUser() {
		
		System.out.println("这是琴女");
		userdao.addUser();	
	}

}
