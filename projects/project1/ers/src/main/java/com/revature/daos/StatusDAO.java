package com.revature.daos;

import com.revature.models.ReimbStatus;

public interface StatusDAO {

	ReimbStatus retrieveStatusByValue(String value);
	ReimbStatus retrieveStatusById(int id);
}
