package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.StatusDAO;
import com.revature.daos.StatusHibernate;
import com.revature.exceptions.NotFoundException;
import com.revature.models.ReimbStatus;

public class StatusService {

	private StatusDAO sd = new StatusHibernate();
	private static Logger log = LogManager.getLogger(StatusService.class);

	public ReimbStatus getStatus(String value) throws NotFoundException {
		ReimbStatus rs = sd.retrieveStatusByValue(value);
		if (rs == null) {
			throw new NotFoundException();
		}
		log.info("Retrieved status: " + rs);
		return rs;
	}
	
	public ReimbStatus getStatus(int id) throws NotFoundException {
		ReimbStatus rs = sd.retrieveStatusById(id);
		if (rs == null) {
			throw new NotFoundException();
		}
		log.info("Retrieved status: " + rs);
		return rs;
	}
}
