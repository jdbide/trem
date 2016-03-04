package com.avancial.app.service.implementation;

import com.avancial.app.data.controller.dao.implementation.TremasApplicationDAO;
import com.avancial.app.data.databean.TremasApplication;
import com.avancial.app.service.AGenericService;
import com.avancial.app.service.inter.ITremasApplicationService;

public class TremasApplicationService extends AGenericService<TremasApplication, Integer> implements ITremasApplicationService {
	private static final long serialVersionUID = 1L;
	
	public TremasApplicationService() {
		super(new TremasApplicationDAO());		
	}
	
}
