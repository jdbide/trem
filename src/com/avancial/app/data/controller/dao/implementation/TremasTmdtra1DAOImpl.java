package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.GenericDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasTmdtra1DAO;
import com.avancial.app.data.databean.TremasTmdtra1;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdtra1DAOImpl extends GenericDAOImpl<TremasTmdtra1, Long> implements TremasTmdtra1DAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdtra1DAOImpl() {
		super(TremasTmdtra1.class);
	}

}
