/**
 * 
 */
package com.avancial.app.traitement;

import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.Status;
import com.avancial.app.data.dto.ImportTmsDto;
import com.avancial.app.persistence.EntityManagerFactoryProviderDb2;
import com.avancial.app.service.CompagnieEnvironnementService;
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
    private CompagnieEnvironnementService compagnieEnvironnementService;
    @Inject
    private JeuDonneeService jeuDonneeService;

    private boolean traitementOk;

    private ImportTmsDto importTmsDto;

    @Inject
    private TraitementMotrice traitementMotrice;

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
       CompagnieEnvironnementEntity compagnieEnvironnementEntity = null;
       JeuDonneeEntity jeuDonneeDataBean = null;
       try {
          try {
             // Récupération de l'environnement sélectionné
             compagnieEnvironnementEntity = this.compagnieEnvironnementService.getCompagnieEnvironnementById(this.importTmsDto.getIdCompagnieEnvironnement());
          }
          catch (Throwable ex) {
             this.logBean.setExceptionTraitement(ex.getMessage());
             this.logBean.setMessageTraitement("L'environnement sélectionné n'existe pas");
             throw ex;
          }

           try {
              // Instanciation EntityManagerFactory avec les bonnes données de la dataSource de l'environnement
               this.entityManagerDb2 = EntityManagerFactoryProviderDb2
                       .getInstance(compagnieEnvironnementEntity, this.importTmsDto.getUsername(), this.importTmsDto.getPassword()).createEntityManager();
           }
           catch (Throwable ex) {
               this.logBean.setExceptionTraitement(ex.getMessage());
               this.logBean.setMessageTraitement("Echec de connexion avec la base de données externe Db2");
               throw ex;
           }
           
           // Instanciation et sauvegarde du nouveau jeu de données
           jeuDonneeDataBean = this.jeuDonneeService.initJeuDonnee(compagnieEnvironnementEntity);
           this.jeuDonneeService.save(jeuDonneeDataBean);

           // vider puis importer les tables
           TraitementImportDb2Motrice traitement = new TraitementImportDb2Motrice(this.entityManagerSocle,
                   this.entityManagerDb2, SchemaMotrice.ES.getSchema());
           try {
               traitement.execute();
           } catch (SecurityException e) {
              this.log("Echec de l'import");
              e.printStackTrace();
           }

           /* Insertion dans les tables du modèle motrice */
           this.traitementMotrice.setJeuDonneeEntity(jeuDonneeDataBean);
           try {
               this.traitementMotrice.execute();
           }
           catch (Exception e) {              
               this.log("Echec du traitement motrice.");
               e.printStackTrace();
               throw e;
           }

           this.traitementOk = true;
           jeuDonneeDataBean.setDateLastUpdateJeuDonnees(new Date());
           jeuDonneeDataBean.setStatusJeuDonnees(Status.DRAFT);
       }  catch (Throwable ex) {
          if (jeuDonneeDataBean != null) {
             jeuDonneeDataBean.setDateLastUpdateJeuDonnees(new Date());
             jeuDonneeDataBean.setActifJeuDonnees(false);
          }
       } finally {
          if (jeuDonneeDataBean != null) {
             this.jeuDonneeService.update(jeuDonneeDataBean);
          }
       }
    }

    /**
     * @return the traitementOk
     */
    public boolean isTraitementOk() {
        return this.traitementOk;
    }

    /**
     * @param traitementOk
     *            the traitementOk to set
     */
    public void setTraitementOk(boolean traitementOk) {
        this.traitementOk = traitementOk;
    }

    /**
     * @return the importJeuDonneesDto
     */
    public ImportTmsDto getImportJeuDonneesDto() {
        return this.importTmsDto;
    }

    /**
     * @param importJeuDonneesDto
     *            the importJeuDonneesDto to set
     */
    public void setImportJeuDonneesDto(ImportTmsDto importTmsDto) {
        this.importTmsDto = importTmsDto;
    }

}
