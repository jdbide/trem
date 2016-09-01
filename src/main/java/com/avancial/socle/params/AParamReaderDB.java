/**
 * 
 */
package com.avancial.socle.params;

import java.util.ArrayList;

import com.avancial.socle.data.controller.dao.AbstractDao;

/**
 * Classe abstraite servant de point de d�part aux classes voulant charger des param�tres � partir des bases de donn�es.
 * 
 * @author bruno.legloahec
 *
 */
public abstract class AParamReaderDB extends AParamReader {
   private AbstractDao dao;

   /**
    * Constructeur
    */
   public AParamReaderDB(AbstractDao dao) {
      this.setDao(dao);
      this.colIParamBeans = new ArrayList<>();
   }

   /**
    * @return the dao
    */
   public AbstractDao getDao() {
      return this.dao;
   }

   /**
    * @param dao
    *           the dao to set
    */
   public void setDao(AbstractDao dao) {
      this.dao = dao;
   }

}
