package com.avancial.app.service.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdcatrDAO;
import com.avancial.app.data.databean.TremasTmdcatr;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdcatrService extends AGenericDAO<TremasTmdcatr, Integer> implements ITremasTmdcatrDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdcatrService() {
		super(TremasTmdcatr.class);
	}

}
