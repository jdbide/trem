package com.avancial.app.service.implementation;

import com.avancial.app.data.controller.dao.implementation.TremasJeuDonneeDAO;
import com.avancial.app.data.databean.TremasJeuDonnee;
import com.avancial.app.service.AGenericService;
import com.avancial.app.service.inter.ITremasJeuDonneeService;

/**
 * @author hamza.laterem
 */
public class TremasJeuDonneeService extends AGenericService<TremasJeuDonnee, Integer> implements ITremasJeuDonneeService {
	private static final long serialVersionUID = 1L;
	
	public TremasJeuDonneeService() {
		super(new TremasJeuDonneeDAO());
	}
	
	
}