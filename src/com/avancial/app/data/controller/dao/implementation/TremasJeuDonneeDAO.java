package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasJeuDonneeDAO;
import com.avancial.app.data.databean.TremasJeuDonnee;

/**
 * 
 * @author hamza.laterem
 *
 */
public class TremasJeuDonneeDAO extends AGenericDAO<TremasJeuDonnee, Integer> implements ITremasJeuDonneeDAO {
	private static final long serialVersionUID = 1L;

	public TremasJeuDonneeDAO() {
		super(TremasJeuDonnee.class);		
	}

}