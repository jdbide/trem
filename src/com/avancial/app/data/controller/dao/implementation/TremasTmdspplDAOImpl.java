package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.GenericDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasTmdspplDAO;
import com.avancial.app.data.databean.TremasTmdsppl;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdspplDAOImpl extends GenericDAOImpl<TremasTmdsppl, Long> implements TremasTmdspplDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdspplDAOImpl() {
		super(TremasTmdsppl.class);
	}

}
