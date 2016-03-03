package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.GenericDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasTmddstrDAO;
import com.avancial.app.data.databean.TremasTmddstr;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmddstrDAOImpl extends GenericDAOImpl<TremasTmddstr, Long> implements TremasTmddstrDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmddstrDAOImpl() {
		super(TremasTmddstr.class);
	}

}
