package com.avancial.app.service.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdcdemDAO;
import com.avancial.app.data.databean.TremasTmdcdem;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdcdemService extends AGenericDAO<TremasTmdcdem, Integer> implements ITremasTmdcdemDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdcdemService() {
		super(TremasTmdcdem.class);
	}

}
