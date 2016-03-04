package com.avancial.app.service.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdcdclDAO;
import com.avancial.app.data.databean.TremasTmdcdcl;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdcdclService extends AGenericDAO<TremasTmdcdcl, Integer> implements ITremasTmdcdclDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdcdclService() {
		super(TremasTmdcdcl.class);
	}

}
