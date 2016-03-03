package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.GenericDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasJeuDonneeDAO;
import com.avancial.app.data.databean.TremasJeuDonnee;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasJeuDonneeDAOImpl extends GenericDAOImpl<TremasJeuDonnee, Long> implements TremasJeuDonneeDAO {
	
	private static final long serialVersionUID = 1L;

	public TremasJeuDonneeDAOImpl() {
		super(TremasJeuDonnee.class);		
	}

}