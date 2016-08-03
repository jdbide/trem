package com.avancial.app.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.app.data.dto.ImportTmsDto;
@SessionScoped
public class ImportTmsService implements Serializable {
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   
   @Inject
   private CompagnieEnvironnementService compagnieEnvironnementService;

   @Inject
   private JeuDonneeService jeuDonneeService;

   public ImportTmsService() {
      // TODO Auto-generated constructor stub
   }

   /**
    * TODO Recuperation des jeu de données
    * @return
    */
   public List<ImportTmsDto> getAllImportTmsActif() {            
      List<ImportTmsDto> listImportTmsDto = new ArrayList<ImportTmsDto>();
      
      for (CompagnieEnvironnementEntity compagnieEnvironnementEntity : this.compagnieEnvironnementService.getAllCompagnieEnvironnementActif()) {
         ImportTmsDto newImportTmsDto = new ImportTmsDto();
         
         newImportTmsDto.mergeByCompagnieEnvironnement(compagnieEnvironnementEntity);
         
         listImportTmsDto.add(newImportTmsDto);
      }
      
      return listImportTmsDto;
   }
   /**
    * Suppression d'un jeu de données en brouillon
    * @param importTmsDto
    * @return
    */
   public Boolean deleteDraft(ImportTmsDto importTmsDto) {
      try {
         return (this.jeuDonneeService.deleteById(importTmsDto.getIdJeuDonneeBrouillon()) > 0) ? true : false;
      } catch (Exception e) {
         throw e;
      }
   }

}
