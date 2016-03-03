package com.avancial.app.data.controller.dao.implementation;


import com.avancial.app.data.controller.dao.GenericDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasTmdspcoDAO;
import com.avancial.app.data.databean.TremasTmdspco;
/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasTmdspcoDAOImpl extends GenericDAOImpl<TremasTmdspco, Long> implements TremasTmdspcoDAO {
	
	private static final long serialVersionUID = 1L;
	
	public TremasTmdspcoDAOImpl() {
		super(TremasTmdspco.class);
	}

}
