package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdtathDAO;
import com.avancial.app.data.databean.TremasTmdtath;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdtathDAO extends AGenericDAO<TremasTmdtath, Integer> implements ITremasTmdtathDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdtathDAO() {
		super(TremasTmdtath.class);
	}

}
