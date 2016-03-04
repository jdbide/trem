package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdavthDAO;
import com.avancial.app.data.databean.TremasTmdavth;
/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdavthDAO extends AGenericDAO<TremasTmdavth, Integer> implements ITremasTmdavthDAO {	
	private static final long serialVersionUID = 1L;

	TremasTmdavthDAO() {
		super(TremasTmdavth.class);
	}

}
