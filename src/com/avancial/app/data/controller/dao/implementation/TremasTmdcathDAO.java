package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdcathDAO;
import com.avancial.app.data.databean.TremasTmdcath;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdcathDAO extends AGenericDAO<TremasTmdcath, Integer> implements ITremasTmdcathDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdcathDAO() {
		super(TremasTmdcath.class);
	}

}