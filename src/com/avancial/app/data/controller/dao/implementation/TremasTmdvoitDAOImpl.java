package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.GenericDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasTmdvoitDAO;
import com.avancial.app.data.databean.TremasTmdvoit;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdvoitDAOImpl extends GenericDAOImpl<TremasTmdvoit, Long> implements TremasTmdvoitDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdvoitDAOImpl() {
		super(TremasTmdvoit.class);
	}

}
