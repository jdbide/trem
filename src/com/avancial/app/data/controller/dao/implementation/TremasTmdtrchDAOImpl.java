package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.GenericDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasTmdtrchDAO;
import com.avancial.app.data.databean.TremasTmdtrch;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdtrchDAOImpl extends GenericDAOImpl<TremasTmdtrch, Long> implements TremasTmdtrchDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdtrchDAOImpl() {
		super(TremasTmdtrch.class);
	}

}
