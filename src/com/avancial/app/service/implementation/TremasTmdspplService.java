package com.avancial.app.service.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdspplDAO;
import com.avancial.app.data.databean.TremasTmdsppl;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdspplService extends AGenericDAO<TremasTmdsppl, Integer> implements ITremasTmdspplDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdspplService() {
		super(TremasTmdsppl.class);
	}

}
