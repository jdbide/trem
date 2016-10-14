/**
 * 
 */
package com.avancial.app.serviceDto;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.app.data.databean.EStatus;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.dto.CompagnieEnvironnementWithJeuDonneesActiveDto;
import com.avancial.app.service.JeuDonneesService;

/**
 * @author hamza.laterem
 *
 */
public class CompagnieEnvironnementWithJeuDonneesActiveService {
   @Inject
   private JeuDonneesService jeuDonneesService;

   /**
    * 
    */
   public CompagnieEnvironnementWithJeuDonneesActiveService() {
      // TODO Auto-generated constructor stub
   }
   
   public List<CompagnieEnvironnementWithJeuDonneesActiveDto> getAllCompagnieEnvironnementWithJeuDonneesActive(List<CompagnieEnvironnementEntity> listCompagnieEnvironnement) {
      List<CompagnieEnvironnementWithJeuDonneesActiveDto> res = new ArrayList<>();
      JeuDonneeEntity jeuDonnees;
      
      for (CompagnieEnvironnementEntity compagnieEnvironnementEntity : listCompagnieEnvironnement) {
         jeuDonnees = this.jeuDonneesService.getJeuDonneeParIdCompagnieEtStatus(compagnieEnvironnementEntity, EStatus.ACTIVE);
         res.add(this.entityToDto(compagnieEnvironnementEntity, jeuDonnees));
      }
      
      return res;
   }

   public CompagnieEnvironnementWithJeuDonneesActiveDto entityToDto(CompagnieEnvironnementEntity compagnieEnvironnement) {
      CompagnieEnvironnementWithJeuDonneesActiveDto dto = new CompagnieEnvironnementWithJeuDonneesActiveDto();
      if (compagnieEnvironnement == null) {
         return null;
      }

      dto.setIdCompagnieEnvironnement(compagnieEnvironnement.getIdCompagnieEnvironnement());
      dto.setActifCompagnieEnvironnement(compagnieEnvironnement.getActifCompagnieEnvironnement());
      if (compagnieEnvironnement.getCompagnie() != null) {
         dto.setIdCompagnie(compagnieEnvironnement.getCompagnie().getIdCompagnie());
         dto.setLibelleCompagnie(compagnieEnvironnement.getCompagnie().getLibelleCompagnie());
         dto.setNomTechniqueCompagnie(compagnieEnvironnement.getCompagnie().getNomTechniqueCompagnie());
      }
      
      if (compagnieEnvironnement.getEnvironnement() != null) {
         dto.setIdEnvironnement(compagnieEnvironnement.getEnvironnement().getIdEnvironnement());
         dto.setLibelleEnvironnement(compagnieEnvironnement.getEnvironnement().getLibelleEnvironnement());
         dto.setNomTechniqueEnvironnement(compagnieEnvironnement.getEnvironnement().getNomTechniqueEnvironnement());
      }

      return dto;
   }

   public CompagnieEnvironnementWithJeuDonneesActiveDto entityToDto(CompagnieEnvironnementEntity compagnieEnvironnement, JeuDonneeEntity jeuDonnees) {
      CompagnieEnvironnementWithJeuDonneesActiveDto dto = new CompagnieEnvironnementWithJeuDonneesActiveDto();
      if (compagnieEnvironnement == null) {
         return null;
      }

      dto.setIdCompagnieEnvironnement(compagnieEnvironnement.getIdCompagnieEnvironnement());
      dto.setActifCompagnieEnvironnement(compagnieEnvironnement.getActifCompagnieEnvironnement());
      if (compagnieEnvironnement.getCompagnie() != null) {
         dto.setIdCompagnie(compagnieEnvironnement.getCompagnie().getIdCompagnie());
         dto.setLibelleCompagnie(compagnieEnvironnement.getCompagnie().getLibelleCompagnie());
         dto.setNomTechniqueCompagnie(compagnieEnvironnement.getCompagnie().getNomTechniqueCompagnie());
      }
      
      if (compagnieEnvironnement.getEnvironnement() != null) {
         dto.setIdEnvironnement(compagnieEnvironnement.getEnvironnement().getIdEnvironnement());
         dto.setLibelleEnvironnement(compagnieEnvironnement.getEnvironnement().getLibelleEnvironnement());
         dto.setNomTechniqueEnvironnement(compagnieEnvironnement.getEnvironnement().getNomTechniqueEnvironnement());
      }
      
      if (jeuDonnees != null) {
         dto.setIdJeuDonneesActive(jeuDonnees.getIdJeuDonnees());
         dto.setDateCreateJeuDonneesActive(jeuDonnees.getDateCreateJeuDonnees());
         dto.setDateLastUpdateJeuDonneesActive(jeuDonnees.getDateLastUpdateJeuDonnees());
         dto.setIdUtilisateurCreateJeuDonneesActive(jeuDonnees.getIdUtilisateurCreateJeuDonnees());
         dto.setIdUtilisateurLastUpdateJeuDonneesActive(jeuDonnees.getIdUtilisateurLastUpdateJeuDonnees());
         dto.setDateDebutPeriodeJeuDonneesActive(jeuDonnees.getDateDebutPeriode());
         dto.setStatusJeuDonneesActive(jeuDonnees.getStatusJeuDonnees());
      }

      return dto;
   }
}
