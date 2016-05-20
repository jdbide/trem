/**
 * 
 */
package com.avancial.app.traitement;

import java.util.Date;

import com.avancial.app.data.databean.JeuDonneeDataBean;
import com.avancial.app.service.JeuDonneeService;
import com.avancial.socle.traitement.ATraitementLogDetail;

/**
 * Traitement qui importe un jeu de données.
 *
 */
public class TraitementImportJeuDonnees extends ATraitementLogDetail {

   private JeuDonneeService jeuDonneeService;
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
      this.initJeuDonnee();
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
