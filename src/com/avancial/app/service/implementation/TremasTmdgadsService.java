package com.avancial.app.service.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdgadsDAO;
import com.avancial.app.data.databean.TremasTmdgads;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdgadsService extends AGenericDAO<TremasTmdgads, Integer> implements ITremasTmdgadsDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdgadsService() {
		super(TremasTmdgads.class);
	}

}
