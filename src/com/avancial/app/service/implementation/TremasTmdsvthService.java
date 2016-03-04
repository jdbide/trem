package com.avancial.app.service.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdsvthDAO;
import com.avancial.app.data.databean.TremasTmdsvth;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdsvthService extends AGenericDAO<TremasTmdsvth, Integer> implements ITremasTmdsvthDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdsvthService() {
		super(TremasTmdsvth.class);
	}

}
