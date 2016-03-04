package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdspplDAO;
import com.avancial.app.data.databean.TremasTmdsppl;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdspplDAO extends AGenericDAO<TremasTmdsppl, Integer> implements ITremasTmdspplDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdspplDAO() {
		super(TremasTmdsppl.class);
	}

}
