package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.GenericDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasTmdrameDAO;
import com.avancial.app.data.databean.TremasTmdrame;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdrameDAOImpl extends GenericDAOImpl<TremasTmdrame, Long> implements TremasTmdrameDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdrameDAOImpl() {
		super(TremasTmdrame.class);
	}

}
