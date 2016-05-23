package com.avancial.app.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.modelmapper.ModelMapper;

import com.avancial.app.data.databean.JeuDonneeDataBean;
import com.avancial.app.data.databean.TablesMotriceDataBean;
import com.avancial.app.data.databean.importMotrice.ImportTMDAVTRDataBean;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

import data.model.databean.Socle_PUExterne;

public class JeuDonneeService {
   @Inject
   @Socle_PUSocle
   EntityManager       em;  
   
   @Inject
   @Socle_PUExterne
   private EntityManager ext;

   public JeuDonneeDataBean save(JeuDonneeDataBean jeuDonneeDataBean) {
      // FIXME a cenlever lors de la mise en place de l'@Inject
      // this.em = Persistence.createEntityManagerFactory("PU_app").createEntityManager();
      this.em.getTransaction().begin();
      this.em.persist(jeuDonneeDataBean);
      this.em.flush();
      this.em.getTransaction().commit();
      return jeuDonneeDataBean;
   }

   public static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
      try {
         return clazz.cast(o);
      } catch (ClassCastException e) {
         return null;
      }
   }

   public void readTable(TablesMotriceDataBean tableMotrice) {
//      this.ext = Persistence.createEntityManagerFactory("pu-externe").createEntityManager();
      Query query = this.ext.createQuery("FROM TMDAVTRDataBean");
      
      ModelMapper modelMapper = new ModelMapper();
      ImportTMDAVTRDataBean importTMDAVTRDataBean;

      List<?> tmdavtrDataBeans = query.getResultList();
      for(int i=0; i<tmdavtrDataBeans.size(); i++) {
         importTMDAVTRDataBean = modelMapper.map(tmdavtrDataBeans.get(i), ImportTMDAVTRDataBean.class);
         this.ext.getTransaction().begin();
         this.ext.persist(importTMDAVTRDataBean);
         this.ext.flush();
         this.ext.getTransaction().commit();
      }

   }

}
