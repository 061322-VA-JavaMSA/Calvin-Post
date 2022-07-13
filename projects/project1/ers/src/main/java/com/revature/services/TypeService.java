package com.revature.services;

import com.revature.daos.TypeDAO;
import com.revature.daos.TypeHibernate;
import com.revature.exceptions.NotFoundException;
import com.revature.models.ReimbType;

public class TypeService {
	
	private TypeDAO td = new TypeHibernate();

	public ReimbType getType(String value) throws NotFoundException {
		ReimbType rt = td.retrieveTypeByValue(value);
		if (rt == null) {
			throw new NotFoundException();
		}
		return rt;
	}
	
	public ReimbType getType(int id) throws NotFoundException {
		ReimbType rt = td.retrieveTypeById(id);
		if (rt == null) {
			throw new NotFoundException();
		}
		return rt;
	}
}
