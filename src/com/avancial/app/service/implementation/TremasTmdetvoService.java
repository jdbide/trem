package com.avancial.app.service.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdetvoDAO;
import com.avancial.app.data.databean.TremasTmdetvo;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdetvoService extends AGenericDAO<TremasTmdetvo, Integer> implements ITremasTmdetvoDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdetvoService() {
		super(TremasTmdetvo.class);
	}

}
