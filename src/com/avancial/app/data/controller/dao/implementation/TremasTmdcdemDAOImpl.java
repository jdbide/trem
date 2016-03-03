package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.GenericDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasTmdcdemDAO;
import com.avancial.app.data.databean.TremasTmdcdem;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdcdemDAOImpl extends GenericDAOImpl<TremasTmdcdem, Long> implements TremasTmdcdemDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdcdemDAOImpl() {
		super(TremasTmdcdem.class);
	}

}
