/**
 * 
 */
package com.avancial.app.serviceDto;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.app.data.databean.JeuDonneesControlEntity;
import com.avancial.app.data.databean.EStatusControl;
import com.avancial.app.data.dto.JeuDonneesControlDto;
import com.avancial.app.service.JeuDonneesControlService;

/**
 * @author hamza.laterem
 *
 */
public class JeuDonneesControlServiceDto {
   @Inject
   private JeuDonneesControlService jeuDonneesControlService;

   /**
    * 
    */
   public JeuDonneesControlServiceDto() {
   }

   /**
    * Recupération des jeudDonneesControl par idCompagnieEnvironnement
    * 
    * @param idCompagnieEnvironnement
    * @return List<JeuDonneesControlDto>
    */
   public List<JeuDonneesControlDto> getAllJeuDonneesControlDtoParIdCompagnieEnvironnement(int idCompagnieEnvironnement) {
      List<JeuDonneesControlDto> res = new ArrayList<>();
      try {
         List<JeuDonneesControlEntity> listJeuDonneesControl = this.jeuDonneesControlService.getAllJeuDonneesControlParIdCompagnieEnvironnement(idCompagnieEnvironnement);
         for (JeuDonneesControlEntity jeuDonneesControl : listJeuDonneesControl) {
            res.add(this.entityToDtoControl(jeuDonneesControl));
         }
      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      }

      return res;
   }

   /**
    * Creation d'un jeuDonneesControl a partir jeuDonneesControlDto
    * 
    * @param compagnieEnvironnementEntity
    * @return
    */
   public JeuDonneesControlDto createJeuDonneesControl(CompagnieEnvironnementEntity compagnieEnvironnementEntity, int idUser) {
      try {
         JeuDonneesControlDto jeuDonneesControlDto = new JeuDonneesControlDto();
         jeuDonneesControlDto.setIdUtilisateurCreateJeuDonneesControl(idUser);
         jeuDonneesControlDto.setIdUtilisateurLastUpdateJeuDonneesControl(idUser);
         
         JeuDonneesControlEntity entity = this.dtoToEntityControl(jeuDonneesControlDto, compagnieEnvironnementEntity);

         jeuDonneesControlService.save(entity);

         return this.entityToDtoControl(entity);
      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      }
   }

   /**
    * Merge JeuDonneesControlEntity to JeuDonneesControlDto
    * 
    * @param jeuDonneesControl
    * @return JeuDonneesControlDto
    */
   public JeuDonneesControlDto entityToDtoControl(JeuDonneesControlEntity jeuDonneesControl) {
      JeuDonneesControlDto jeuDonneesControlDto = new JeuDonneesControlDto();

      jeuDonneesControlDto.setIdJeuDonneesControl(jeuDonneesControl.getIdJeuDonneesControl());
      jeuDonneesControlDto.setDateCreateJeuDonneesControl(jeuDonneesControl.getDateCreateJeuDonneesControl());
      jeuDonneesControlDto.setDateLastUpdateJeuDonneesControl(jeuDonneesControl.getDateLastUpdateJeuDonneesControl());
      jeuDonneesControlDto.setIdCompagnieEnvironnement(jeuDonneesControl.getCompagnieEnvironnement().getIdCompagnieEnvironnement());
      jeuDonneesControlDto.setIdJeuDonnees(jeuDonneesControl.getIdJeuDonnees());
      jeuDonneesControlDto.setIdUtilisateurCreateJeuDonneesControl(jeuDonneesControl.getIdUtilisateurCreateJeuDonneesControl());
      jeuDonneesControlDto.setIdUtilisateurLastUpdateJeuDonneesControl(jeuDonneesControl.getIdUtilisateurLastUpdateJeuDonneesControl());
      jeuDonneesControlDto.setPathFileImportJeuDonneesControlTimeTable(jeuDonneesControl.getPathFileImportJeuDonneesControlTimeTable());
      jeuDonneesControlDto.setPathFileImportJeuDonneesControlYield(jeuDonneesControl.getPathFileImportJeuDonneesControlYield());
      jeuDonneesControlDto.setPathFileReportJeuDonneesControl(jeuDonneesControl.getPathFileReportJeuDonneesControl());
      jeuDonneesControlDto.setStatusJeuDonnees(jeuDonneesControl.getStatusJeuDonnees());
      jeuDonneesControlDto.setStatusJeuDonneesControl(jeuDonneesControl.getStatusJeuDonneesControl().toString());
      jeuDonneesControlDto.setTitleJeuDonneesControl(jeuDonneesControl.getTitleJeuDonneesControl());

      return jeuDonneesControlDto;
   }

   /**
    * Merge JeuDonneesControlDto to JeuDonneesControlEntity
    * 
    * @param jeuDonneesControlDto
    * @param compagnieEnvironnement
    * @return JeuDonneesControlEntity
    */
   public JeuDonneesControlEntity dtoToEntityControl(JeuDonneesControlDto jeuDonneesControlDto, CompagnieEnvironnementEntity compagnieEnvironnement) {
      JeuDonneesControlEntity jeuDonneesControlEntity = new JeuDonneesControlEntity();

      jeuDonneesControlEntity.setCompagnieEnvironnement(compagnieEnvironnement);
      jeuDonneesControlEntity.setDateCreateJeuDonneesControl(jeuDonneesControlDto.getDateCreateJeuDonneesControl());
      jeuDonneesControlEntity.setDateLastUpdateJeuDonneesControl(jeuDonneesControlDto.getDateLastUpdateJeuDonneesControl());
      jeuDonneesControlEntity.setIdJeuDonnees(jeuDonneesControlDto.getIdJeuDonnees());
      jeuDonneesControlEntity.setIdUtilisateurCreateJeuDonneesControl(jeuDonneesControlDto.getIdUtilisateurCreateJeuDonneesControl());
      jeuDonneesControlEntity.setIdUtilisateurLastUpdateJeuDonneesControl(jeuDonneesControlDto.getIdUtilisateurLastUpdateJeuDonneesControl());
      jeuDonneesControlEntity.setPathFileImportJeuDonneesControlTimeTable(jeuDonneesControlDto.getPathFileImportJeuDonneesControlTimeTable());
      jeuDonneesControlEntity.setPathFileImportJeuDonneesControlYield(jeuDonneesControlDto.getPathFileImportJeuDonneesControlYield());
      jeuDonneesControlEntity.setPathFileReportJeuDonneesControl(jeuDonneesControlDto.getPathFileReportJeuDonneesControl());
      jeuDonneesControlEntity.setStatusJeuDonnees(jeuDonneesControlDto.getStatusJeuDonnees());
      jeuDonneesControlEntity.setStatusJeuDonneesControl(jeuDonneesControlDto.getStatusJeuDonneesControl().equals(EStatusControl.FINISHED.toString()) ? EStatusControl.FINISHED : EStatusControl.LOADING);
      jeuDonneesControlEntity.setTitleJeuDonneesControl(jeuDonneesControlDto.getTitleJeuDonneesControl());
      if (jeuDonneesControlDto.getIdJeuDonnees()>0)
         jeuDonneesControlEntity.setIdJeuDonneesControl(jeuDonneesControlDto.getIdJeuDonneesControl());

      return jeuDonneesControlEntity;
   }

}
