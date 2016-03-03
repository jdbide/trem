package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.GenericDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasTmdcddsDAO;
import com.avancial.app.data.databean.TremasTmdcdds;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdcddsDAOImpl extends GenericDAOImpl<TremasTmdcdds, Long> implements TremasTmdcddsDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdcddsDAOImpl() {
		super(TremasTmdcdds.class);
	}

}
