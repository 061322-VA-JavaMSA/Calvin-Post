package com.revature.daos;

import com.revature.models.ReimbType;

public interface TypeDAO {

	ReimbType retrieveTypeByValue(String value);
	ReimbType retrieveTypeById(int id);
}
