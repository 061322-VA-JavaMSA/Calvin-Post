package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.RoleDAO;
import com.revature.daos.RoleHibernate;
import com.revature.exceptions.NotFoundException;
import com.revature.models.Role;

public class RoleService {

	private RoleDAO rd = new RoleHibernate();
	private static Logger log = LogManager.getLogger(RoleService.class);

	public Role getRole(String value) throws NotFoundException {
		Role r = rd.retrieveRoleByValue(value);
		if (r == null) {
			throw new NotFoundException();
		}
		log.info("Retrieved role: " + r);
		return r;
	}
	
	public Role getRole(int id) throws NotFoundException {
		Role r = rd.retrieveRoleById(id);
		if (r == null) {
			throw new NotFoundException();
		}
		log.info("Retrieved role: " + r);
		return r;
	}
}
