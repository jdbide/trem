/**
 * 
 */
package com.avancial.socle.data.controller.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Session;

import com.avancial.socle.data.model.databean.AbstractDataBean;
import com.avancial.socle.exceptions.SocleExceptionManager;
import com.avancial.socle.exceptions.impl.ASocleException;
import com.avancial.socle.logging.ALogBean;
import com.avancial.socle.logging.ILogger;
import com.avancial.socle.logging.LogDaoBean;
import com.avancial.socle.logging.Logger;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;
import com.avancial.socle.resources.constants.SOCLE_logSortie;

/**
 * Classe abstraite servant de base Ã  tous les objets DAO
 * 
 * @author bruno.legloahec
 *
 */
public abstract class AbstractDao implements Serializable {
   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   @Inject
   @Socle_PUSocle
   EntityManager             entityManager;

   protected ILogger         logger;
   protected ALogBean        logBean;

   /**
    * Constructeur sans log
    * 
    * @throws Exception
    */
   public AbstractDao() {
      // this.setEntityManager(AbstractEntityManager.getInstance().getEntityManager());
   }

   /**
    * Constructeur definissant le log
    * 
    * @param libelleDoa
    * @throws Exception
    */
   public AbstractDao(String libelleDoaLog) throws Exception {

      // this.setEntityManager(AbstractEntityManager.getInstance().getEntityManager());
      this.logger = new Logger();
      this.logger.activerSortie(SOCLE_logSortie.CONSOLE);
      // TODO logger en fichier (mais pas encors implemanter)
      this.logBean = new LogDaoBean();
      ((LogDaoBean) this.logBean).setLibelleDao(libelleDoaLog);
   }

   /**
    * @return la liste contenant tous les enregistrements de l'entitÃ© concernÃ©e
    */
   public abstract List<?> getAll();

   protected EntityManager getEntityManager() {
      return this.entityManager;
   }

   /*
    * protected void setEntityManager(EntityManager entityManager) { this.entityManager = entityManager; }
    */

   public Session getSession() {
      // if(this.getEntityManager().unwrap(Session.class)==null)
      // System.out.println("erreur cration session");
      return this.getEntityManager().unwrap(Session.class);
   }

   /**
    * Enregistrement d'un DataBean en base
    * 
    * @param dataBean
    * @throws ASocleException
    */
   public void save(AbstractDataBean dataBean) throws ASocleException {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().persist(dataBean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         throw SocleExceptionManager.getException(e);
      }
   }

   public void delete(AbstractDataBean dataBean) throws ASocleException {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().remove(dataBean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         throw SocleExceptionManager.getException(e);
      }

   }

   public void update(AbstractDataBean dataBean) throws ASocleException {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().merge(dataBean);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         throw SocleExceptionManager.getException(e);
      }

   }

   /*
    * (non-Javadoc)
    * 
    * @see java.lang.Object#finalize()
    */
   @Override
   protected void finalize() throws Throwable {
      if (this.entityManager != null)
         if (this.entityManager.isOpen())
            this.entityManager.close();
      super.finalize();
   }
}
