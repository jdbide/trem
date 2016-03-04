package com.avancial.app.data.controller.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import com.avancial.socle.data.controller.dao.AbstractDao;
/**
 * @todo  Gestion des Exception
 * 
 * @author hamza.laterem
 *
 * @param <T>
 * @param <PK>
 */
public abstract class AGenericDAO<T, PK extends Serializable> extends AbstractDao implements IGenericDAO<T, PK> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	/**
     * The Class of the concrete object.
     */
    protected Class<T> modelClass;
    
    /**
     * The unqualified class name of T, used to build JSQL queries.
     */
    protected String unqualifiedModelClassName;
    
    /**
     * Required
     */
    @SuppressWarnings("unused")
	private AGenericDAO() {
    }
    
    /**
     * Sets the class of the model that the DOA instance handles.
     * Note that this has been set up to use constructor injection
     * because it makes it easy to sub-class GenericDAOImpl in a robust
     * manner.
     * <p/>
     * Model class specific sub-classes should define a no-argument constructor
     * that calls this constructor with the appropriate class.
     *
     * @param modelClass the model that the DOA instance handles.
     */
    public AGenericDAO(Class<T> modelClass) {
    	super();
        this.modelClass = modelClass;
        this.unqualifiedModelClassName = modelClass.getSimpleName();
    }    
    
    
	
    /**
     * Insert a new Model instance.
     *
     * @param newInstance being a new instance to persist.
     * @return the inserted Instance.  This MAY NOT be the same object as
     *         has been passed in, for sub-classes that check for the pre-existence of the object
     *         in the database.
     */
    @Transactional
	public T insert(T newInstance) {
    	if (this.getEntityManager().contains(newInstance)) {            
            return newInstance;
        }
    	
    	this.getEntityManager().getTransaction().begin();    	
    	this.getEntityManager().persist(newInstance);
    	this.getEntityManager().flush();
    	this.getEntityManager().getTransaction().commit();
    	
        return newInstance;
	}

    /**
     * Insert a List of new Model instances.
     *
     * @param newInstances being a List of instances to persist.
     * @return the Collection of persisted instances.
     *         This MAY NOT contain the same objects as
     *         have been passed in, for sub-classes that check for the pre-existence of the object
     *         in the database.
     */
    @Transactional
	public Collection<T> insert(Collection<T> newInstances) {
    	for (T newInstance : newInstances) {
            if (this.getEntityManager().contains(newInstance)) {               
                continue;
            }
            
            this.getEntityManager().getTransaction().begin();            
            this.getEntityManager().persist(newInstance);
            this.getEntityManager().flush();
        	this.getEntityManager().getTransaction().commit();
        }
        return newInstances;
	}

    /**
     * Update the instance into the database
     *
     * @param modifiedInstance being an attached or unattached, persisted object that has been modified.
     */
    @Transactional
	public void update(T modifiedInstance) {
    	this.getEntityManager().getTransaction().begin();  
    	this.getEntityManager().merge(modifiedInstance);	
    	this.getEntityManager().flush();
    	this.getEntityManager().getTransaction().commit();
	}

    /**
     * Retrieve an object that was previously persisted to the database using
     * the indicated id as primary key
     *
     * @param id being the primary key value of the required object.
     * @return a single instance of the object with the specified primary key,
     *         or null if it does not exist.
     */
    @Transactional
	public T read(PK id) {
    	String queryString = String.format("select o from %s o where o.id = :id", unqualifiedModelClassName);
        Query query = this.getEntityManager().createQuery(queryString);
        query.setParameter("id", id);

        // Originally this made use of query.getSingleResult
        // however this method throws an Exception if there is no
        // matching object, which seems like overkill.  Modified to return
        // null if there is no matching object.

        @SuppressWarnings("unchecked") List<T> results = query.getResultList();
        if (results.size() == 0) {
            return null;
        } else return results.get(0);
	}

    @Transactional
    public T readSpecific(String id) {
        String queryString = String.format("select o from %s o where o.modelId = :modelId", unqualifiedModelClassName);
        Query query = this.getEntityManager().createQuery(queryString);
        query.setParameter("modelId", id);

        // Originally this made use of query.getSingleResult
        // however this method throws an Exception if there is no
        // matching object, which seems like overkill.  Modified to return
        // null if there is no matching object.

        @SuppressWarnings("unchecked") List<T> results = query.getResultList();
        if (results.size() == 0) {
            return null;
        } else return results.get(0);
    }

    /**
     * Retrieve an object that was previously persisted to the database using
     * the indicated id as primary key and go deep on the fields listed.
     * <p/>
     * TODO - Could make use of reflection to determine if the field name passed in is accessible via a public getter.
     *
     * @param id         being the primary key value of the required object.
     * @param deepFields being the names of the fields to retrieve with the main object.
     * @return a single instance of the object with the specified primary key,
     *         or null if it does not exist, with the lazy objects initialised.
     */
    @Transactional
    public T readDeep(PK id, String... deepFields) {
        StringBuffer queryString = new StringBuffer("select o from ");
        queryString.append(unqualifiedModelClassName)
                .append(" o ");

        for (String field : deepFields) {
            queryString.append(" left outer join fetch o.")
                    .append(field);
        }

        queryString.append(" where o.id = :id");

        Query query = this.getEntityManager().createQuery(queryString.toString());
        query.setParameter("id", id);

        // Originally this made use of query.getSingleResult
        // however this method throws an Exception if there is no
        // matching object, which seems like overkill.  Modified to return
        // null if there is no matching object.

        @SuppressWarnings("unchecked") List<T> results = query.getResultList();
        if (results.size() == 0) {
            return null;
        } else return results.get(0);
    }

    /**
     * Remove an object from persistent storage in the database
     *
     * @param persistentObject being the (attached or unattached) object to be deleted.
     */
    @Transactional
    public void delete(T persistentObject) {
        if (!this.getEntityManager().contains(persistentObject)) {
            persistentObject = this.getEntityManager().merge(persistentObject);
        }
        
        this.getEntityManager().getTransaction().begin();
        this.getEntityManager().remove(persistentObject);
        this.getEntityManager().flush();
    	this.getEntityManager().getTransaction().commit();
    }

    /**
     * Returns a count of all instances of the type.  Note that select count(object) JSQL
     * returns a Long object.
     *
     * @return a count of all instances of the type.
     */
    @Transactional
    public Long count() {
        String queryString = String.format("select count(o) from %s o", unqualifiedModelClassName);
        Query query = this.getEntityManager().createQuery(queryString);
        return (Long) query.getSingleResult();
    }

    /**
     * Returns a List of all the instances of T in the database.
     *
     * @return a List of all the instances of T in the database.
     */
    @Transactional
    public List<T> retrieveAll() {
        String queryString = String.format("select o from %s o", unqualifiedModelClassName);
        Query query = this.getEntityManager().createQuery(queryString);
        @SuppressWarnings("unchecked") List<T> results = query.getResultList();
        return results;
    }

    /**
     * Deletes all instances of class T in the database.
     *
     * @return the number of rows affected by this operation.
     */
    @Transactional
    public int deleteAll() {
//        String queryString = String.format("delete from %s", unqualifiedModelClassName);
//        Query query = this.this.getEntityManager().createQuery(queryString);
//        return query.executeUpdate();
        List<T> allEntities = retrieveAll();
        for (T entity : allEntities) {
            delete(entity);
        }
        return allEntities.size();
    }

    /**
     * Returns the highest primary key value for the Model class.
     *
     * @return the highest primary key value for the Model class.
     */
    @Transactional
    public Long getMaximumPrimaryKey() {
        String queryString = String.format("select max(id) from %s", unqualifiedModelClassName);
        Query query = this.getEntityManager().createQuery(queryString);
        return (Long) query.getSingleResult();
    }

    /**
     * Experimental - included to allow explicit flush following DAO transaction.
     */
    @Transactional
    public void flush() {
        this.getEntityManager().flush();
    }

	@Override
	public List<?> getAll() {
		return this.retrieveAll();
	}

}
