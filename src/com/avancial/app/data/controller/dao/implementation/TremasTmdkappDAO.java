package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdkappDAO;
import com.avancial.app.data.databean.TremasTmdkapp;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdkappDAO extends AGenericDAO<TremasTmdkapp, Integer> implements ITremasTmdkappDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdkappDAO() {
		super(TremasTmdkapp.class);
	}

}
