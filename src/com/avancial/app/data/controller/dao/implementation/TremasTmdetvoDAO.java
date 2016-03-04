package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdetvoDAO;
import com.avancial.app.data.databean.TremasTmdetvo;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdetvoDAO extends AGenericDAO<TremasTmdetvo, Integer> implements ITremasTmdetvoDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdetvoDAO() {
		super(TremasTmdetvo.class);
	}

}
