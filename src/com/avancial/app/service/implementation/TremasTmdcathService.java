package com.avancial.app.service.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdcathDAO;
import com.avancial.app.data.databean.TremasTmdcath;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdcathService extends AGenericDAO<TremasTmdcath, Integer> implements ITremasTmdcathDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdcathService() {
		super(TremasTmdcath.class);
	}

}