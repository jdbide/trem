package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.GenericDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasTmdavtrDAO;
import com.avancial.app.data.databean.TremasTmdavtr;
/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdavtrDAOImp extends GenericDAOImpl<TremasTmdavtr, Long> implements TremasTmdavtrDAO {	

	private static final long serialVersionUID = 1L;
	
	public TremasTmdavtrDAOImp() {
		super(TremasTmdavtr.class);
	}

}