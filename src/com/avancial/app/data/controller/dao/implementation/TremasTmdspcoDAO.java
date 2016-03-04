package com.avancial.app.data.controller.dao.implementation;


import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasTmdspcoDAO;
import com.avancial.app.data.databean.TremasTmdspco;
/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdspcoDAO extends AGenericDAO<TremasTmdspco, Integer> implements ITremasTmdspcoDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdspcoDAO() {
		super(TremasTmdspco.class);
	}

}
