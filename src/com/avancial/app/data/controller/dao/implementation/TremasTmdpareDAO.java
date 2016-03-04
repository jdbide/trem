package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdpareDAO;
import com.avancial.app.data.databean.TremasTmdpare;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdpareDAO extends AGenericDAO<TremasTmdpare, Integer> implements ITremasTmdpareDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdpareDAO() {
		super(TremasTmdpare.class);
	}

}
