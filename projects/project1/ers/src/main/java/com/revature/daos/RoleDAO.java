package com.revature.daos;

import com.revature.models.Role;

public interface RoleDAO {
	
	Role retrieveRoleByValue(String value);

	Role retrieveRoleById(int id);
}
