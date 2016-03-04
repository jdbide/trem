package com.avancial.app.service.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdtathDAO;
import com.avancial.app.data.databean.TremasTmdtath;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdtathService extends AGenericDAO<TremasTmdtath, Integer> implements ITremasTmdtathDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdtathService() {
		super(TremasTmdtath.class);
	}

}
