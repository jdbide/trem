package com.avancial.app.service.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdavtrDAO;
import com.avancial.app.data.databean.TremasTmdavtr;
/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdavtrService extends AGenericDAO<TremasTmdavtr, Integer> implements ITremasTmdavtrDAO {	

	private static final long serialVersionUID = 1L;
	
	public TremasTmdavtrService() {
		super(TremasTmdavtr.class);
	}

}