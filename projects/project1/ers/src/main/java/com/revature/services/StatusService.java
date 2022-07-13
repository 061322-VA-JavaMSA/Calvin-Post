package com.revature.services;

import com.revature.daos.StatusDAO;
import com.revature.daos.StatusHibernate;
import com.revature.exceptions.NotFoundException;
import com.revature.models.ReimbStatus;

public class StatusService {

	private StatusDAO sd = new StatusHibernate();

	public ReimbStatus getStatus(String value) throws NotFoundException {
		ReimbStatus rs = sd.retrieveStatusByValue(value);
		if (rs == null) {
			throw new NotFoundException();
		}
		return rs;
	}
	
	public ReimbStatus getStatus(int id) throws NotFoundException {
		ReimbStatus rs = sd.retrieveStatusById(id);
		if (rs == null) {
			throw new NotFoundException();
		}
		return rs;
	}
}
