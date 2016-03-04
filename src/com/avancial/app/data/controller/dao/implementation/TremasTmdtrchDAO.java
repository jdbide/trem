package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdtrchDAO;
import com.avancial.app.data.databean.TremasTmdtrch;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdtrchDAO extends AGenericDAO<TremasTmdtrch, Integer> implements ITremasTmdtrchDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdtrchDAO() {
		super(TremasTmdtrch.class);
	}

}
