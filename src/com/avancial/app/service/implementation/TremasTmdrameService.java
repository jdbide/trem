package com.avancial.app.service.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdrameDAO;
import com.avancial.app.data.databean.TremasTmdrame;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdrameService extends AGenericDAO<TremasTmdrame, Integer> implements ITremasTmdrameDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdrameService() {
		super(TremasTmdrame.class);
	}

}
