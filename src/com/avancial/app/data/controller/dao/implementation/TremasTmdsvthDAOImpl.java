package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.GenericDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasTmdsvthDAO;
import com.avancial.app.data.databean.TremasTmdsvth;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdsvthDAOImpl extends GenericDAOImpl<TremasTmdsvth, Long> implements TremasTmdsvthDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdsvthDAOImpl() {
		super(TremasTmdsvth.class);
	}

}
