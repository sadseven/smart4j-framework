package org.smart4j.plugin.security;

import java.util.Set;

/**
 * Smart Security接口
 * @author Administrator
 *
 */
public interface SmartSecurity {

	/**
	 * 根据用户名获取密码
	 */
	String getPassword(String username);
	
	/**
	 * 根据用户名获取角色名集合
	 */
	Set<String> getRoleNameSet(String username);
	
	/**
	 * 根据角色名获取权限合集
	 */
	Set<String> getPermissionNmaeSet(String roleName);
	
}
