package com.ns.shiro.shiro;

import com.ns.shiro.entity.SysPermission;
import com.ns.shiro.entity.SysRole;
import com.ns.shiro.entity.SysUser;
import com.ns.shiro.service.LoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ns
 * @create 2020-07-12
 */

public class CustomRealm extends AuthorizingRealm {

	@Autowired
	private LoginService loginService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//获取登录用户名
		String name = (String) principalCollection.getPrimaryPrincipal();
		//根据用户名去数据库查询用户信息
		SysUser user = loginService.getUserByName(name);
		//添加角色和权限
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		for (SysRole role : user.getRoles()) {
			//添加角色
			simpleAuthorizationInfo.addRole(role.getName());
			//添加权限
			for (SysPermission permissions : role.getPermissions()) {
				simpleAuthorizationInfo.addStringPermission(permissions.getName());
			}
		}
		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		//加这一步的目的是在Post请求的时候会先进认证，然后在到请求
		if (authenticationToken.getPrincipal() == null) {
			return null;
		}
		//获取用户信息
		String name = authenticationToken.getPrincipal().toString();
		SysUser user = loginService.getUserByName(name);
		if (user == null) {
			//这里返回后会报出对应异常
			return null;
		} else {
			//这里验证authenticationToken和simpleAuthenticationInfo的信息
			SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPassword().toString(), getName());
			return simpleAuthenticationInfo;
		}
	}
}
