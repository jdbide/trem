package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.GenericDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasTmdcatrDAO;
import com.avancial.app.data.databean.TremasTmdcatr;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdcatrDAOImpl extends GenericDAOImpl<TremasTmdcatr, Long> implements TremasTmdcatrDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdcatrDAOImpl() {
		super(TremasTmdcatr.class);
	}

}
