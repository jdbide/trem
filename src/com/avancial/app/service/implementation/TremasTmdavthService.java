package com.avancial.app.service.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdavthDAO;
import com.avancial.app.data.databean.TremasTmdavth;
/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdavthService extends AGenericDAO<TremasTmdavth, Integer> implements ITremasTmdavthDAO {	
	private static final long serialVersionUID = 1L;

	TremasTmdavthService() {
		super(TremasTmdavth.class);
	}

}
