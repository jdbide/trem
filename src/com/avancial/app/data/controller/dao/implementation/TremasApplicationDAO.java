package com.avancial.app.data.controller.dao.implementation;

import com.avancial.app.data.controller.dao.AGenericDAO;
import com.avancial.app.data.controller.dao.inter.ITremasApplicationDAO;
import com.avancial.app.data.databean.TremasApplication;

public class TremasApplicationDAO extends AGenericDAO<TremasApplication, Integer> implements ITremasApplicationDAO {
		
	private static final long serialVersionUID = 1L;
	/**
     * Sets the class of the model that the DOA instance handles.
     * Note that this has been set up to use constructor injection
     * because it makes it easy to sub-class GenericDAOImpl in a robust
     * manner.
     */
	public TremasApplicationDAO() {
		super(TremasApplication.class);
	}
}
