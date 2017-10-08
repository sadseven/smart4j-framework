package org.smart4j.chapter5;

import java.util.Set;

import org.smart4j.framework.helper.DatabaseHelper;
import org.smart4j.plugin.security.SmartSecurity;

public class AppSecurity implements SmartSecurity{

	@Override
	public String getPassword(String username) {
		String sql = "SELECT password FROM user WHERE username = ?";
		return null;
	}

	@Override
	public Set<String> getRoleNameSet(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getPermissionNmaeSet(String roleName) {
		// TODO Auto-generated method stub
		return null;
	}

		
	
}
