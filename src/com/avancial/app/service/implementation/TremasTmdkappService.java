package com.avancial.app.service.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdkappDAO;
import com.avancial.app.data.databean.TremasTmdkapp;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdkappService extends AGenericDAO<TremasTmdkapp, Integer> implements ITremasTmdkappDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdkappService() {
		super(TremasTmdkapp.class);
	}

}
