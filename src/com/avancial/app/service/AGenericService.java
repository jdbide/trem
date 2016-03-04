/**
 * 
 */
package com.avancial.app.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.avancial.app.data.controller.dao.IGenericDAO;

/**
 * @author hamza.laterem
 * TODO Gestion des exception
 * TODO Gestion du log
 */
public abstract class AGenericService<T, PK extends Serializable> implements IGenericService<T, PK> {
	private static final long serialVersionUID = 1L;
	
	protected IGenericDAO<T, PK> dao;
	
	public AGenericService() {
	}
	
	public AGenericService(IGenericDAO<T, PK> genericDao) {
		this.dao = genericDao;
	}
	
	@Override
	public T save(T newInstance) {
		try {
			/*
			 * TODO Gestion du logger
			 */
			return dao.insert(newInstance);
		} catch (Exception e) {
			/*
			 * TODO Gestion des exception
			 */
			return null;
		}
	}
	
	@Override
	public Collection<T> save(Collection<T> newInstances) {
		try {
			/*
			 * TODO Gestion du logger
			 */
			return dao.insert(newInstances);
		} catch (Exception e) {
			/*
			 * TODO Gestion des exception
			 */
			return null;
		}
	}
	
	
	public void update(T modifiedInstance) {
		try {
			/*
			 * TODO Gestion du logger
			 */
			dao.update(modifiedInstance);
		} catch (Exception e) {
			/*
			 * TODO Gestion des exception
			 */			
		}
	}
	
	public T getById(PK id) {
		try {
			/*
			 * TODO Gestion du logger
			 */
			return dao.read(id);
		} catch (Exception e) {
			/*
			 * TODO Gestion des exception
			 */
			return null;
		}
	}
	
	public List<T> getAll() {
		try {
			/*
			 * TODO Gestion du logger
			 */
			return dao.retrieveAll();
		} catch (Exception e) {
			/*
			 * TODO Gestion des exception
			 */
			return null;
		}
	}
	
	
	public void delete(T persistentObject) {
		try {
			/*
			 * TODO Gestion du logger
			 */
			dao.delete(persistentObject);
		} catch (Exception e) {
			/*
			 * TODO Gestion des exception
			 */
		}
	}
	
	public int deleteAll() {
		try {
			/*
			 * TODO Gestion du logger
			 */
			return dao.deleteAll();
		} catch (Exception e) {
			/*
			 * TODO Gestion des exception
			 */
			return -1;
		}
	}
}
