package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.GenericDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasTmdpareDAO;
import com.avancial.app.data.databean.TremasTmdpare;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdpareDAOImpl extends GenericDAOImpl<TremasTmdpare, Long> implements TremasTmdpareDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdpareDAOImpl() {
		super(TremasTmdpare.class);
	}

}
