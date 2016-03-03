package com.avancial.app.data.controller.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

/**
 * 
 * @author hamza.laterem
 *
 */
public interface GenericDAO<T, PK extends Serializable> extends Serializable{
	/**
    * Hibernate will create in clauses of any size if left to
    * it's own devices, so need to consider this when building
    * potentially unrestricted in-clauses.
    */
   int MAXIMUM_IN_CLAUSE_SIZE = 100;
   
   /**
    * Insert a new Model instance.
    *
    * @param newInstance being a new instance to persist.
    * @return the inserted Instance.  This MAY NOT be the same object as
    *         has been passed in, for sub-classes that check for the pre-existence of the object
    *         in the database.
    */
   @Transactional
   T insert(T newInstance);
   
   /**
    * Insert a Set of new Model instances.
    *
    * @param newInstances being a Set of instances to persist.
    * @return the Set of persisted instances.
    *         This MAY NOT contain the same objects as
    *         have been passed in, for sub-classes that check for the pre-existence of the object
    *         in the database.
    */
   @Transactional
   Collection<T> insert(Collection<T> newInstances);
   
   /**
    * Update the instance into the database
    *
    * @param modifiedInstance being an attached or unattached, persisted object that has been modified.
    */
   @Transactional
   void update(T modifiedInstance);
   
   /**
    * Retrieve an object that was previously persisted to the database using
    * the indicated id as primary key
    *
    * @param id being the primary key value of the required object.
    * @return a single instance of the object with the specified primary key,
    *         or null if it does not exist.
    */
   @Transactional
   T read(PK id);
   
   /**
    * Retrieve an object that was previously persisted to the database using
    * the indicated id as primary key and go deep on the fields listed.
    *
    * @param id         being the primary key value of the required object.
    * @param deepFields being the names of the fields to retrieve with the main object.
    * @return a single instance of the object with the specified primary key,
    *         or null if it does not exist, with the lazy objects initialised.
    */
   @Transactional
   T readDeep(PK id, String... deepFields);
   
   /**
    * Remove an object from persistent storage in the database
    *
    * @param persistentObject being the (attached or unattached) object to be deleted.
    */
   @Transactional
   void delete(T persistentObject);
   
   /**
    * Returns a count of all instances of the type.  Note that select count(object) JSQL
    * returns a Long object.
    *
    * @return a count of all instances of the type.
    */
   @Transactional
   Long count();
   
   /**
    * Returns a List of all the instances of T in the database.
    *
    * @return a List of all the instances of T in the database.
    */
   @Transactional
   List<T> retrieveAll();
   
   /**
    * Deletes all instances of class T in the database.
    *
    * @return the number of rows affected by this operation.
    */   
   @Transactional
   int deleteAll();

   /**
    * Returns the highest primary key value for the Model class.
    *
    * @return the highest primary key value for the Model class.
    */
   @Transactional
   Long getMaximumPrimaryKey();

   /**
    * Experimental - included to allow explicit flush following DAO transaction.
    */
   @Transactional
   void flush();
}
