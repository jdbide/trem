package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.GenericDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasApplicationDAO;
import com.avancial.app.data.databean.TremasApplication;

public class TremasApplicationDAOImpl extends GenericDAOImpl<TremasApplication, Long> implements TremasApplicationDAO {
		
	private static final long serialVersionUID = 1L;
	/**
     * Sets the class of the model that the DOA instance handles.
     * Note that this has been set up to use constructor injection
     * because it makes it easy to sub-class GenericDAOImpl in a robust
     * manner.
     */
	public TremasApplicationDAOImpl() {
		super(TremasApplication.class);
	}
}
