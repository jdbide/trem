package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmddstrDAO;
import com.avancial.app.data.databean.TremasTmddstr;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmddstrDAO extends AGenericDAO<TremasTmddstr, Integer> implements ITremasTmddstrDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmddstrDAO() {
		super(TremasTmddstr.class);
	}

}
