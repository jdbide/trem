package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdcdclDAO;
import com.avancial.app.data.databean.TremasTmdcdcl;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdcdclDAO extends AGenericDAO<TremasTmdcdcl, Integer> implements ITremasTmdcdclDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdcdclDAO() {
		super(TremasTmdcdcl.class);
	}

}
