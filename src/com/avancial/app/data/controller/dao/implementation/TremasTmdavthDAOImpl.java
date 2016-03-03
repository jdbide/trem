package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.GenericDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasTmdavthDAO;
import com.avancial.app.data.databean.TremasTmdavth;
/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdavthDAOImpl extends GenericDAOImpl<TremasTmdavth, Long> implements TremasTmdavthDAO {	
	private static final long serialVersionUID = 1L;

	TremasTmdavthDAOImpl() {
		super(TremasTmdavth.class);
	}

}
