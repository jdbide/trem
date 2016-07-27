package com.avancial.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.app.data.dto.ImportTmsDto;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

public class ImportTmsService {
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
