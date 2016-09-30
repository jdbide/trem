/**
 * 
 */
package com.avancial.app.serviceDto;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.Status;
import com.avancial.app.data.dto.JeuDonneesForControlDto;
import com.avancial.app.service.JeuDonneesService;
import com.avancial.socle.data.controller.dao.UserDao;

/**
 * @author hamza.laterem
 *
 */
public class JeuDonneesServiceDto {
   public static String      IMPORT = "Import";

   @Inject
   private JeuDonneesService jeuDonneesService;

   @Inject
   private UserDao           userDao;

   /**
    * 
    */
   public JeuDonneesServiceDto() {
   }

//   public List<JeuDonneesForControlDto> getAllJeuDonneesForControlDtoParIdCompagnieEnvironnementEtListStatus(int idCompagnieEnvironnement, List<Status> status) {
//      List<JeuDonneesForControlDto> res = new ArrayList<>();
//      try {
//         List<JeuDonneeEntity> listJeuDonneeEntity = this.jeuDonneesService.getJeuDonneeParIdCompagnieEtStatus(idCompagnieEnvironnement, status);
//         for (JeuDonneeEntity jeuDonneeEntity : listJeuDonneeEntity) {
//            res.add(this.jeuDonneeToJeuDonneesForControlDto(jeuDonneeEntity));
//         }
//      } catch (Exception e) {
//         e.printStackTrace();
//         throw e;
//      }
//
//      return res;
//   }

   /**
    * @param jeuDonneeEntity
    * @return
    */
   private JeuDonneesForControlDto jeuDonneeToJeuDonneesForControlDto(JeuDonneeEntity jeuDonneeEntity) {
      JeuDonneesForControlDto jeuDonneesForControlDto = new JeuDonneesForControlDto();

      jeuDonneesForControlDto.setIdJeuDonnees(jeuDonneeEntity.getIdJeuDonnees());
      jeuDonneesForControlDto.setStatusJeuDonnees(jeuDonneeEntity.getStatusJeuDonnees());
      jeuDonneesForControlDto.setTitleJeuDonnees(JeuDonneesServiceDto.IMPORT + jeuDonneeEntity.getDateLastUpdateJeuDonnees() + " ( " + userDao.getUserById(new Long(jeuDonneeEntity.getIdUtilisateurCreateJeuDonnees())).getNomComplet() + " )");

      return jeuDonneesForControlDto;
   }

}
