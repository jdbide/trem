/**
 * 
 */
package com.avancial.app.traitement;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.TablesMotriceEntity;
import com.avancial.app.service.GetEntiteService;
import com.avancial.app.service.TremasJeuDonneesService;
import com.avancial.app.service.MotriceService;
import com.avancial.app.service.TremasImportService;
import com.avancial.app.service.TremasTablesMotriceService;
import com.avancial.socle.traitement.ATraitementLogDetail;


/**
 * Traitement qui importe un jeu de données.
 *
 */
@RequestScoped
public class TraitementImportJeuDonnees extends ATraitementLogDetail {

   @Inject
   private TremasJeuDonneesService jeuDonneeService;
   @Inject
   private TremasTablesMotriceService tablesMotriceService;
   @Inject
   private GetEntiteService getEntiteService;
   @Inject
   private MotriceService motriceService;
   @Inject
   private TremasImportService tablesImportService;

   /**
    * 
    */
   public TraitementImportJeuDonnees() {
      super();
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
      for(int i=0; i<listTables.size(); i++) {
         libelleTableMotrice = listTables.get(i).getLibelleTablesMotrice();
         this.tablesImportService.deleteTable(this.getEntiteService.getNomEntiteFromNomEntiteImportMotrice(libelleTableMotrice));
         this.log("Truncate de la table d'import " + libelleTableMotrice);
         
         // lecture des enregistrements dans motrice
         List<?> entityList = this.motriceService.readAll(this.getEntiteService.getNomEntiteFromTableMotrice(libelleTableMotrice));
         
         try {// import des données en local
            this.log("Import de la table " + libelleTableMotrice);
            this.tablesImportService.insertAll(entityList, libelleTableMotrice);
            
         } catch (ClassNotFoundException e) {
            this.log("Impossible d'instancier la classe associée à la table " + libelleTableMotrice);
            e.printStackTrace();
         }
      }
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

      return this.jeuDonneeService.save(jeuDonneeDataBean);
   }

}
