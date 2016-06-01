/**
 * 
 */
package com.avancial.app.traitement;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.TablesMotriceEntity;
import com.avancial.app.service.ImportMotriceService;
import com.avancial.app.service.JeuDonneeService;
import com.avancial.app.service.TablesMotriceService;
import com.avancial.app.utilitaire.GetEntiteService;
import com.avancial.app.utilitaire.SchemaMotrice;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;
import com.avancial.socle.traitement.ATraitementLogDetail;

import data.model.databean.Socle_PUExterne;


/**
 * Traitement qui importe un jeu de données.
 *
 */
@RequestScoped
public class TraitementImportJeuDonnees extends ATraitementLogDetail {
   @Inject
   @Socle_PUSocle
   EntityManager entityManagerSocle;
   
   @Inject
   @Socle_PUExterne
   EntityManager entityManagerExterne;
   
   @Inject
   private JeuDonneeService jeuDonneeService;
   @Inject
   private TablesMotriceService tablesMotriceService;
   @Inject
   private GetEntiteService getEntiteService;
   @Inject
   private ImportMotriceService importMotriceService;
   
   private boolean traitementOk;

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
      JeuDonneeEntity jeuDonneeDataBean = this.initJeuDonnee();
      jeuDonneeDataBean.getIdJeuDonnees();
      
      // on appelle le service qui récupère la liste des tables à importer
      List<TablesMotriceEntity> listTables = this.tablesMotriceService.getAllTablesMotrice();
      String libelleTableMotrice;
      //vide les tables d'import
      for(int i=0; i<listTables.size(); i++) {
         libelleTableMotrice = listTables.get(i).getLibelleTablesMotrice();
         this.importMotriceService.deleteTable(this.getEntiteService.getNomEntiteImportFromTableMotrice(libelleTableMotrice));
         this.log("Delete de la table d'import " + libelleTableMotrice);
      }
      //importer les tables
      TraitementImportDb2Motrice traitement = new TraitementImportDb2Motrice(this.entityManagerSocle, this.entityManagerExterne, SchemaMotrice.ES.getSchema());
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

}
