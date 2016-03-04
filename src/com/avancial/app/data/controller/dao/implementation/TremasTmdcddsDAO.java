package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdcddsDAO;
import com.avancial.app.data.databean.TremasTmdcdds;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdcddsDAO extends AGenericDAO<TremasTmdcdds, Integer> implements ITremasTmdcddsDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdcddsDAO() {
		super(TremasTmdcdds.class);
	}

}
