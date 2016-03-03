package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.GenericDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasTmdetvoDAO;
import com.avancial.app.data.databean.TremasTmdetvo;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdetvoDAOImpl extends GenericDAOImpl<TremasTmdetvo, Long> implements TremasTmdetvoDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdetvoDAOImpl() {
		super(TremasTmdetvo.class);
	}

}
