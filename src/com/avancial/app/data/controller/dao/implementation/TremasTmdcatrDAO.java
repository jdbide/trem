package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdcatrDAO;
import com.avancial.app.data.databean.TremasTmdcatr;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdcatrDAO extends AGenericDAO<TremasTmdcatr, Integer> implements ITremasTmdcatrDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdcatrDAO() {
		super(TremasTmdcatr.class);
	}

}
