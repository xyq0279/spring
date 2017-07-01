package dao;

import org.springframework.stereotype.Repository;

import pojo.User;


@Repository
public class UserDaoImpl implements UserDao {

	@Override
	public void userAdd(User u) {
		System.out.println(u+"萌萌哒");

	}

}
