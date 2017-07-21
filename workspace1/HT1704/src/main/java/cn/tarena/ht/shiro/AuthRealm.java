package cn.tarena.ht.shiro;

//import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.tarena.ht.pojo.User;
import cn.tarena.ht.service.UserService;


public class AuthRealm extends AuthorizingRealm{
	
	@Autowired
	private UserService userService;
	//权限控制
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		//通过查询模块信息
		List<String> pList = userService.findModileNameList(user.getUserId());
		//创建权限认证的对象 
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(pList);
		return info;
	}
	//登录认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//通过realm返回给安全中心真实的用户信息
		
		UsernamePasswordToken loginToken = (UsernamePasswordToken) token;
		String username = loginToken.getUsername();
		//表示真实信息
		User user = userService.findUserByUserName(username);
		/*
		 * principal:真实对象
		 * credentials；表示真是的密码
		 * realmName：当前的realm
		 */
		
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(),this.getName());
		return info;
	}


}
