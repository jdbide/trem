package com.avancial.app.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.app.data.dto.ImportTmsDto;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;
@SessionScoped
public class ImportTmsService implements Serializable {
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   @Inject
   @Socle_PUSocle
   private EntityManager       em;
   
   public ImportTmsService() {
      // TODO Auto-generated constructor stub
   }

   public List<ImportTmsDto> getAllImportTmsActif() {
      Query query = this.em.createNamedQuery("CompagnieEnvironnementEntity.findAllActif", CompagnieEnvironnementEntity.class);
      List<CompagnieEnvironnementEntity> listCompagnieEnvironnementEntity = ((List<CompagnieEnvironnementEntity>) query.getResultList());
      
      List<ImportTmsDto> listImportTmsDto = new ArrayList<ImportTmsDto>();
      
      for (CompagnieEnvironnementEntity compagnieEnvironnementEntity : listCompagnieEnvironnementEntity) {
         ImportTmsDto newImportTmsDto = new ImportTmsDto();
         
         newImportTmsDto.mergeByCompagnieEnvironnement(compagnieEnvironnementEntity);
         
         listImportTmsDto.add(newImportTmsDto);
      }
      
      return listImportTmsDto;
   }

}
