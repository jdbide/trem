package com.avancial.app.serviceDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.avancial.app.service.JeuDonneesService;
import com.avancial.app.data.databean.JeuDonneesControlEntity;
import com.avancial.app.data.dto.JeuDonneesControlDto;
import com.avancial.app.service.JeuDonneesControlService;

/**
 * @author hamza.laterem
 *
 */
public class JeuDonneesControlServiceDto implements Serializable{
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   
   @Inject
   private JeuDonneesService jeuDonneesService;
   
   @Inject
   private JeuDonneesControlService jeuDonneesControlService;

   public JeuDonneesControlServiceDto() {
   }
   
   public List<JeuDonneesControlDto> getAllJeuDonneesControlByIdCompagnieEnvironnement(int idCompagnieEnvironnement) {
      try {
         List<JeuDonneesControlDto> res = new ArrayList<>();
         List<JeuDonneesControlEntity> listJeuDonneesControl = this.jeuDonneesControlService.getAllJeuDonneesControlParIdCompagnieEnvironnement(idCompagnieEnvironnement);
         
         for (JeuDonneesControlEntity jeuDonneesControl : listJeuDonneesControl) {
            JeuDonneesControlDto jeuDonneesControlDto = new JeuDonneesControlDto();
            
            jeuDonneesControlDto.entityToDto(jeuDonneesControl);
            /**
             * TODO Ajout des infos sur l'utilisateur
             */
            res.add(jeuDonneesControlDto);
         }
         
         return res;
      } catch (Exception ex) {
         ex.printStackTrace();
         throw ex;
      }
   }

}
