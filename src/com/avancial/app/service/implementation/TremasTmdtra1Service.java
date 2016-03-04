package com.avancial.app.service.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdtra1DAO;
import com.avancial.app.data.databean.TremasTmdtra1;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdtra1Service extends AGenericDAO<TremasTmdtra1, Integer> implements ITremasTmdtra1DAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdtra1Service() {
		super(TremasTmdtra1.class);
	}

}
