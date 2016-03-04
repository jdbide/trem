package com.avancial.app.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.avancial.app.data.controller.dao.IGenericDAO;

/**
 * 
 * @author hamza.laterem
 *
 */
public interface IGenericService<T, PK extends Serializable> extends Serializable {

	/**
	 * More information
	 * @see IGenericDAO.insert
	 */
	public T save(T newInstance);
	
	/**
	 * More information
	 * @see IGenericDAO.insert
	 */
	public Collection<T> save(Collection<T> newInstances);
	
	/**
	 * More information
	 * @see IGenericDAO.update
	 */
	public void update(T modifiedInstance);
	
	/**
	 * More information
	 * @see IGenericDAO.read
	 */
	public T getById(PK id);
	
	/**
	 * More information
	 * @see IGenericDAO.retrieveAll
	 */
	public List<T> getAll();
	
	/**
	 * More information
	 * @see IGenericDAO.delete
	 */
	public void delete(T persistentObject);
	
	/**
	 * More information
	 * @see IGenericDAO.deleteAll
	 */
	public int deleteAll();
	
	
}

