package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdrcthDAO;
import com.avancial.app.data.databean.TremasTmdrcth;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdrcthDAO extends AGenericDAO<TremasTmdrcth, Integer> implements ITremasTmdrcthDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdrcthDAO() {
		super(TremasTmdrcth.class);
	}

}
