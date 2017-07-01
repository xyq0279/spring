package cn.tedu.mapper;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.pojo.User;

public interface UserMapper {
	
	public List<User> findAll();
}
