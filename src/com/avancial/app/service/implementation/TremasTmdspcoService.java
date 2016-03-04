package com.avancial.app.service.implementation;


import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdspcoDAO;
import com.avancial.app.data.databean.TremasTmdspco;
/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdspcoService extends AGenericDAO<TremasTmdspco, Integer> implements ITremasTmdspcoDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdspcoService() {
		super(TremasTmdspco.class);
	}

}
