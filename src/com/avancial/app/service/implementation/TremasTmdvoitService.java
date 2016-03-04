package com.avancial.app.service.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdvoitDAO;
import com.avancial.app.data.databean.TremasTmdvoit;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdvoitService extends AGenericDAO<TremasTmdvoit, Integer> implements ITremasTmdvoitDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdvoitService() {
		super(TremasTmdvoit.class);
	}

}
