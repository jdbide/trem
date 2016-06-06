package com.avancial.socle.authentification.service;

import javax.inject.Inject;

import com.avancial.socle.authentification.dto.InfoPageDto;
/**
 * Service pour la gestion des info de la page de login
 * TODO a completer
 * 
 * @author hamza.laterem
 *
 */
public class InfoPageService {
   @Inject
   private InfoPageDto infoPageDto;

   public InfoPageService() {
      this.initInfoPage();
      this.init();
   }

   /**
    * Methode qui récupère toutes les information a afficher sur la page d'acceuil (InfoPageDto)
    */
   private void init() {
      /*
       * TODO 
       */
   }
   /**
    * Initialisation d'InfoPageDto
    */
   private void initInfoPage() {
      /* TODO remplace by @inject */
      this.infoPageDto = new InfoPageDto();
      this.infoPageDto.setSocieteName("Avancial");
      this.infoPageDto.setAppName("Tremas");
      this.infoPageDto.setAppVersionEtEnv("Was ... - App ...");
      this.infoPageDto.setMsgHeaderLogin("");
      this.infoPageDto.setActifJob(false);
      this.infoPageDto.setActifServerBdd(false);      
   }

   /**
    * @return the infoPageDto
    */
   public InfoPageDto getInfoPageDataBean() {
      return infoPageDto;
   }

   /**
    * @param infoPageDto the infoPageDto to set
    */
   public void setInfoPageDataBean(InfoPageDto infoPageDto) {
      this.infoPageDto = infoPageDto;
   }
   
   

}
