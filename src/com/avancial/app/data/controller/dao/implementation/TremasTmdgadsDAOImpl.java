package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.GenericDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasTmdgadsDAO;
import com.avancial.app.data.databean.TremasTmdgads;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdgadsDAOImpl extends GenericDAOImpl<TremasTmdgads, Long> implements TremasTmdgadsDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdgadsDAOImpl() {
		super(TremasTmdgads.class);
	}

}
