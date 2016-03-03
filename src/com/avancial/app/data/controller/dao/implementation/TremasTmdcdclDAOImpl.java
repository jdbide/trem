package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.GenericDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasTmdcdclDAO;
import com.avancial.app.data.databean.TremasTmdcdcl;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdcdclDAOImpl extends GenericDAOImpl<TremasTmdcdcl, Long> implements TremasTmdcdclDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdcdclDAOImpl() {
		super(TremasTmdcdcl.class);
	}

}
