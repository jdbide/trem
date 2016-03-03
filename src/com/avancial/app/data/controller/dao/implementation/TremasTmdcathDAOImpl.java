package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.GenericDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasTmdcathDAO;
import com.avancial.app.data.databean.TremasTmdcath;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdcathDAOImpl extends GenericDAOImpl<TremasTmdcath, Long> implements TremasTmdcathDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdcathDAOImpl() {
		super(TremasTmdcath.class);
	}

}