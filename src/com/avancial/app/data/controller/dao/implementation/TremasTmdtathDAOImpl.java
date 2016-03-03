package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.GenericDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasTmdtathDAO;
import com.avancial.app.data.databean.TremasTmdtath;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdtathDAOImpl extends GenericDAOImpl<TremasTmdtath, Long> implements TremasTmdtathDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdtathDAOImpl() {
		super(TremasTmdtath.class);
	}

}
