package com.avancial.app.service.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdtrchDAO;
import com.avancial.app.data.databean.TremasTmdtrch;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdtrchService extends AGenericDAO<TremasTmdtrch, Integer> implements ITremasTmdtrchDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdtrchService() {
		super(TremasTmdtrch.class);
	}

}
