package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.GenericDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasTmdkappDAO;
import com.avancial.app.data.databean.TremasTmdkapp;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdkappDAOImpl extends GenericDAOImpl<TremasTmdkapp, Long> implements TremasTmdkappDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdkappDAOImpl() {
		super(TremasTmdkapp.class);
	}

}
