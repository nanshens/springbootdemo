package com.ns.shiro.service;

import com.ns.shiro.entity.SysPermission;
import com.ns.shiro.entity.SysRole;
import com.ns.shiro.entity.SysUser;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ns
 * @create 2020-07-12
 */
@Service
public class LoginService {
	public SysUser getUserByName(String getMapByName) {
		return getMapByName(getMapByName);
	}
	private SysUser getMapByName(String userName){
		//共添加两个用户，两个用户都是admin一个角色，
		//wsl有query和add权限，zhangsan只有一个query权限
		SysPermission permissions1 = new SysPermission("1","query");
		SysPermission permissions2 = new SysPermission("2","add");
		Set<SysPermission> permissionsSet = new HashSet<>();
		permissionsSet.add(permissions1);
		permissionsSet.add(permissions2);
		SysRole role = new SysRole("1","admin",permissionsSet);
		Set<SysRole> roleSet = new HashSet<>();
		roleSet.add(role);
		SysUser user = new SysUser("1","admin","123456",roleSet);
		Map<String ,SysUser> map = new HashMap<>();
		map.put(user.getName(), user);

		SysPermission permissions3 = new SysPermission("3","query");
		Set<SysPermission> permissionsSet1 = new HashSet<>();
		permissionsSet1.add(permissions3);
		SysRole role1 = new SysRole("2","user",permissionsSet1);
		Set<SysRole> roleSet1 = new HashSet<>();
		roleSet1.add(role1);
		SysUser user1 = new SysUser("2","zhangsan","123456",roleSet1);
		map.put(user1.getName(), user1);
		return map.get(userName);
	}
}
