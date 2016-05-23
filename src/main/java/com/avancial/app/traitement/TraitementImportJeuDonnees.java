/**
 * 
 */
package com.avancial.app.traitement;

import java.util.Date;
import java.util.List;

import com.avancial.app.data.databean.JeuDonneeDataBean;
import com.avancial.app.data.databean.TablesMotriceDataBean;
import com.avancial.app.service.JeuDonneeService;
import com.avancial.app.service.TablesMotriceService;
import com.avancial.socle.traitement.ATraitementLogDetail;


/**
 * Traitement qui importe un jeu de données.
 *
 */
public class TraitementImportJeuDonnees extends ATraitementLogDetail {

   private JeuDonneeService jeuDonneeService;
   private TablesMotriceService tablesMotriceService;

   /**
    * 
    */
   public TraitementImportJeuDonnees() {
      super();
   }
   //
   // /**
   // *
   // */
   // public TraitementImportJeuDonnees() {
   // super();
   //
   // this.logBean = new LogTraitementDataBean();
   // }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.socle.traitement.ATraitement#executeTraitement()
    */
   @Override
   protected void executeTraitement() {
      this.jeuDonneeService = new JeuDonneeService();
      
      JeuDonneeDataBean jeuDonneeDataBean = this.initJeuDonnee();
      jeuDonneeDataBean.getIdJeuDonnees();
      
      // on appelle le service qui récupère la liste des tables à importer
      this.tablesMotriceService = new TablesMotriceService();
      List<TablesMotriceDataBean> listTables = this.tablesMotriceService.getAllTablesMotrice();
      
      for(int i=0; i<listTables.size(); i++) {
         System.out.println("Lecture table : " + listTables.get(i).getLibelleTablesMotrice());
         this.jeuDonneeService.readTable(listTables.get(i));
      }
   }

   /**
    * Initialise le jeu de données qui va être sauvegardé.
    * 
    * @return
    */
   public JeuDonneeDataBean initJeuDonnee() {
      // création de l'entite
      JeuDonneeDataBean jeuDonneeDataBean = new JeuDonneeDataBean();
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
