package cn.tedu.domain;

import java.io.Serializable;

import cn.tedu.Utils.webUtils;
import cn.tedu.exception.MsgException;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// dbutils框架将自动将数据库中检索出的数据自动封装到javaBean中
	public User() {
	}

	private int id;
	private String username;
	private String password;
	private String password2;
	private String nickname;
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
	/**
	 * 提示校验信息
	 * @throws MsgException
	 */
	public void checkData() throws MsgException {
		// 非空校验
		// 用户名校验
		if (webUtils.isNull(username)) {
			throw new MsgException("用户名不能为空");
		}
		// 密码校验
		if (webUtils.isNull(password)) {
			throw new MsgException("密码不能为空");
		}
		// 确认密码校验
		if (webUtils.isNull(password2)) {
			throw new MsgException("确认密码不能为空");
		}
		// 昵称校验
		if (webUtils.isNull(nickname)) {
			throw new MsgException("昵称不能为空");
		}
		// 邮箱校验
		if (webUtils.isNull(email)) {
			throw new MsgException("邮箱不能为空");
		}
		// 密码不能重复
		if (!password.equals(password2)) {
			throw new MsgException("密码不一致");
		}
		String str = "^\\w+@+\\w+(.\\w+)+$";
		// 邮箱格式
		if (!email.matches(str)) {
			throw new MsgException("邮箱格式不正确");
		}
		
	}
}
