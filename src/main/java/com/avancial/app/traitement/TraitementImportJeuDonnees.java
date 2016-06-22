/**
 * 
 */
package com.avancial.app.traitement;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.dto.ImportJeuDonneesDto;
import com.avancial.app.persistence.EntityManagerFactoryProviderDb2;
import com.avancial.app.service.JeuDonneeService;
import com.avancial.app.utilitaire.SchemaMotrice;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;
import com.avancial.socle.traitement.ATraitementLogDetail;


/**
 * Traitement qui importe un jeu de données.
 *
 */
@RequestScoped
public class TraitementImportJeuDonnees extends ATraitementLogDetail {
   @Inject
   @Socle_PUSocle
   EntityManager entityManagerSocle;

   
   EntityManager entityManagerDb2;
   
   @Inject
   private JeuDonneeService jeuDonneeService;
   
   private boolean traitementOk;

   private ImportJeuDonneesDto importJeuDonneesDto;
   /**
    * 
    */
   public TraitementImportJeuDonnees() {
      super();
      this.traitementOk = false;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.socle.traitement.ATraitement#executeTraitement()
    */
   @Override
   protected void executeTraitement() {
      try {
         this.entityManagerDb2 = EntityManagerFactoryProviderDb2.getInstance(importJeuDonneesDto.getUsername(), importJeuDonneesDto.getPassword()).createEntityManager();
      } catch (Throwable ex) {
         this.logBean.setExceptionTraitement(ex.getMessage());
         this.logBean.setMessageTraitement("Echec de connexion avec la base de données externe Db2");
         throw ex;
      }
      
      JeuDonneeEntity jeuDonneeDataBean = this.initJeuDonnee();
      jeuDonneeDataBean.getIdJeuDonnees();
      

      //vider puis importer les tables
      TraitementImportDb2Motrice traitement = new TraitementImportDb2Motrice(this.entityManagerSocle, this.entityManagerDb2, SchemaMotrice.ES.getSchema());
      try {
         traitement.execute();
      } catch (SecurityException e) {
         this.log("Echec de l'import");
         e.printStackTrace();
      }
      
      
      jeuDonneeDataBean.setStatusJeuDonnees(true);
      jeuDonneeDataBean.setDateLastUpdateJeuDonnees(new Date());      
      this.jeuDonneeService.update(jeuDonneeDataBean);
      
      this.traitementOk = true;
   }

   /**
    * Initialise le jeu de données qui va être sauvegardé.
    * 
    * @return
    */
   public JeuDonneeEntity initJeuDonnee() {
      // création de l'entite
      JeuDonneeEntity jeuDonneeDataBean = new JeuDonneeEntity();
      jeuDonneeDataBean.setDateCreateJeuDonnees(new Date());
      jeuDonneeDataBean.setDateLastUpdateJeuDonnees(new Date());
      jeuDonneeDataBean.setIdUtilisateurCreateJeuDonnees(-1);
      jeuDonneeDataBean.setIdUtilisateurLastUpdateJeuDonnees(-1);
      jeuDonneeDataBean.setNomTechniqueJeuDonnees("");
      jeuDonneeDataBean.setLibelleJeuDonnees("");
      jeuDonneeDataBean.setActifJeuDonnees(true);
      jeuDonneeDataBean.setCommentaireJeuDonnees("");
      jeuDonneeDataBean.setOrdreJeuDonnees(0);
      jeuDonneeDataBean.setStatusJeuDonnees(false);

      return this.jeuDonneeService.save(jeuDonneeDataBean);
   }

   /**
    * @return the traitementOk
    */
   public boolean isTraitementOk() {
      return this.traitementOk;
   }

   /**
    * @param traitementOk the traitementOk to set
    */
   public void setTraitementOk(boolean traitementOk) {
      this.traitementOk = traitementOk;
   }

   /**
    * @return the importJeuDonneesDto
    */
   public ImportJeuDonneesDto getImportJeuDonneesDto() {
      return importJeuDonneesDto;
   }

   /**
    * @param importJeuDonneesDto the importJeuDonneesDto to set
    */
   public void setImportJeuDonneesDto(ImportJeuDonneesDto importJeuDonneesDto) {
      this.importJeuDonneesDto = importJeuDonneesDto;
   }

}
