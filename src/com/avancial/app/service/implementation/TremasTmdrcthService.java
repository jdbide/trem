package com.avancial.app.service.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdrcthDAO;
import com.avancial.app.data.databean.TremasTmdrcth;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdrcthService extends AGenericDAO<TremasTmdrcth, Integer> implements ITremasTmdrcthDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdrcthService() {
		super(TremasTmdrcth.class);
	}

}
