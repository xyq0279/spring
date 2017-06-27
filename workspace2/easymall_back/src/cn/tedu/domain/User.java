package cn.tedu.domain;

import cn.tedu.exception.MsgException;
import cn.tedu.utils.webUtils;

public class User {
	
	private String username;
	private String password;
	private String password2;
	private String nickname;
	private String email;
	
	public User(){}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public void checkData() throws MsgException {
		// TODO Auto-generated method stub
		if(webUtils.isNull(username)){
			throw new MsgException("用户名不能为空");
		}
		if(webUtils.isNull(password)){
			throw new MsgException("密码不能为空");
		}
		if(webUtils.isNull(password2)){
			throw new MsgException("确认密码不能为空");
		}
		if(webUtils.isNull(nickname)){
			throw new MsgException("昵称不能为空");
		}
		if(webUtils.isNull(email)){
			throw new MsgException("邮箱不能为空");
		}
		if(!password.trim().equals(password2.trim())){
			throw new MsgException("密码不一致");
		}
		String str = "^\\w+@\\w+(\\.\\w+)+$";
		if(!email.matches(str)){
			throw new MsgException("邮箱格式不正确");
		}
		
	}
}
