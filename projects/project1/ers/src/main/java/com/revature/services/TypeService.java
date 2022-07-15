package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.TypeDAO;
import com.revature.daos.TypeHibernate;
import com.revature.exceptions.NotFoundException;
import com.revature.models.ReimbType;

public class TypeService {
	
	private TypeDAO td = new TypeHibernate();
	private static Logger log = LogManager.getLogger(TypeService.class);

	public ReimbType getType(String value) throws NotFoundException {
		ReimbType rt = td.retrieveTypeByValue(value);
		if (rt == null) {
			throw new NotFoundException();
		}
		log.info("Retrieved type: " + rt);
		return rt;
	}
	
	public ReimbType getType(int id) throws NotFoundException {
		ReimbType rt = td.retrieveTypeById(id);
		if (rt == null) {
			throw new NotFoundException();
		}
		log.info("Retrieved type: " + rt);
		return rt;
	}
}
