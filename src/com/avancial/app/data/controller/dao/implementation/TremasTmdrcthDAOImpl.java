package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.GenericDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasTmdrcthDAO;
import com.avancial.app.data.databean.TremasTmdrcth;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdrcthDAOImpl extends GenericDAOImpl<TremasTmdrcth, Long> implements TremasTmdrcthDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdrcthDAOImpl() {
		super(TremasTmdrcth.class);
	}

}
