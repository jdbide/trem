package com.avancial.app.traitement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefEqpTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefGareEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefODEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefRameCodeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeTospEntity;
import com.avancial.app.data.dto.FiltrePlanTransportDto;
import com.avancial.app.data.objetsMetier.PlanTransport.Composition;
import com.avancial.app.data.objetsMetier.PlanTransport.Gare;
import com.avancial.app.data.objetsMetier.PlanTransport.OrigineDestination;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Tosp;
import com.avancial.app.data.objetsMetier.PlanTransport.TypeEquipement;
import com.avancial.app.service.filtrePlanTransport.FiltreEtPlanTransport;
import com.avancial.app.service.filtrePlanTransport.FiltreGareDesserteRegimePlanTransport;
import com.avancial.app.service.filtrePlanTransport.FiltreNumeroTrainPlanTransport;
import com.avancial.app.service.filtrePlanTransport.FiltreNumeroTranchePlanTransport;
import com.avancial.app.service.filtrePlanTransport.FiltreOuPlanTransport;
import com.avancial.app.service.filtrePlanTransport.FiltreSousRegimePlanTransport;
import com.avancial.app.service.filtrePlanTransport.FiltreStatutPlanTransport;
import com.avancial.app.service.filtrePlanTransport.IFiltre;
import com.avancial.app.utilitaire.MapPlansDeTransport;
import com.avancial.socle.traitement.ATraitementLogDetail;

/**
 * traitement permettant d'appliquer un filtre sur un plan de transport
 * 
 * @author gabriel.gagnier
 *
 */
public class TraitementFiltrePlanTransport extends ATraitementLogDetail implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private FiltrePlanTransportDto filtre;

    private PlanTransport planTransport;

    @Inject
    private TraitementObjetMetier traitementObjetMetier;

    @Override
    protected void executeTraitement() throws Exception {
        /**
         * Creation du plan de transport a filtrer
         */
        OrigineDestination od = new OrigineDestination();
        Gare gare = new Gare();
        String environnementCompagnie = this.getEntityManager()
                .createNamedQuery("CompagnieEnvironnementEntity.findById", CompagnieEnvironnementEntity.class)
                .setParameter("idCompagnieEnvironnement", this.filtre.getIdCompagnieEnvironnement()).getSingleResult()
                .getNomTechniqueCompagnieEnvironnement();
        MapPlansDeTransport mapPdt = new MapPlansDeTransport();
        this.traitementObjetMetier.setEnvironnementCompagnie(environnementCompagnie);
        this.traitementObjetMetier.setDatesFiltre(this.filtre.getDebutPeriode(), this.filtre.getFinPeriode());
        this.traitementObjetMetier.setIdTask(Thread.currentThread().getId());
        this.traitementObjetMetier.setMapPlansDeTransport(mapPdt);
        this.traitementObjetMetier.execute();
        PlanTransport planTransportCompar = mapPdt.getPlanTransportActive();
        
        /**
         * creation du filtre sur le numero de train
         */
        IFiltre<PlanTransport> filtreTrain = new FiltreNumeroTrainPlanTransport(this.filtre.getNumerosTrains());

        /**
         * creation du filtre sur le numero de tranche
         */
        IFiltre<PlanTransport> filtreTranche = new FiltreNumeroTranchePlanTransport(this.filtre.getNumerosTranches());

        /**
         * creation du filtre sur l'"origine/destination"
         */
        MotriceRefODEntity odEntity = this.getEntityManager()
                .createNamedQuery("MotriceRefODEntity.getById", MotriceRefODEntity.class)
                .setParameter("idMotriceRefOd", this.filtre.getIdOrigineDestination()).getSingleResult();
        gare.setCodeGare(odEntity.getCodeGareOrigineMotriceRefOd());
        od.setOrigine(gare);
        gare = new Gare();
        gare.setCodeGare(odEntity.getCodeGareDestinationMotriceRefOd());
        od.setDestination(gare);
        IFiltre<PlanTransport> filtreOD = new FiltreSousRegimePlanTransport(od);

        /**
         * creation du filtre sur les arret du train
         */
        List<Gare> garesStops = new ArrayList<>();
        MotriceRefGareEntity gareEntity;
        for (Integer idStop : this.filtre.getIdsStops()) {
            gare = new Gare();
            gareEntity = this.getEntityManager()
                    .createNamedQuery("MotriceRefGareEntity.getById", MotriceRefGareEntity.class)
                    .setParameter("idMotriceRefGare", idStop).getSingleResult();
            gare.setCodeGare(gareEntity.getCodeGareMotriceRefGare());
            garesStops.add(gare);
        }
        IFiltre<PlanTransport> filtreStop = new FiltreGareDesserteRegimePlanTransport(garesStops);

        /**
         * creation du filtre sur les Tosp
         */
        MotriceRegimeTospEntity tospEntity = this.getEntityManager()
                .createNamedQuery("MotriceRegimeTosp.getById", MotriceRegimeTospEntity.class)
                .setParameter("idMotriceRegimeTosp", this.filtre.getIdTosp()).getSingleResult();
        Tosp tosp = new Tosp();
        tosp.setOureCode(tospEntity.getMotriceRefTospEntity().getCodeMotriceRefTosp());
        IFiltre<PlanTransport> filtreTosp = new FiltreSousRegimePlanTransport(tosp);

        /**
         * creation du filtre sur le CodeRm
         */
        Composition compo = new Composition();
        MotriceRefRameCodeEntity codeRmEntity = this.getEntityManager()
                .createNamedQuery("MotriceRefRameCode.getById", MotriceRefRameCodeEntity.class)
                .setParameter("idMotriceRefRameCode", this.filtre.getIdCodeRM()).getSingleResult();
        compo.setCodeRm(codeRmEntity.getLabelRameCode());
        IFiltre<PlanTransport> filtreCodeRm = new FiltreSousRegimePlanTransport(compo);

        /**
         * creation du filtre sur le codeEquipement
         */
        TypeEquipement eqp = new TypeEquipement();
        MotriceRefEqpTypeEntity eqpEntity = this.getEntityManager()
                .createNamedQuery("MotriceRefEqpType.getById", MotriceRefEqpTypeEntity.class)
                .setParameter("idMotriceRefEqpType", this.filtre.getIdCodeEquipement()).getSingleResult();
        eqp.setTypeEquipement(eqpEntity.getLabelEqpType());
        IFiltre<PlanTransport> filtreCodeEqp = new FiltreSousRegimePlanTransport(eqp);

        /**
         * creation du filtre sur le statut de la tranche
         */
        IFiltre<PlanTransport> filtreStatus = new FiltreStatutPlanTransport(this.filtre.getStatus());

        /**
         * creation du filtre OU pour les trains "ou" les tranches
         */
        @SuppressWarnings("unchecked")
        IFiltre<PlanTransport> filtreOu = new FiltreOuPlanTransport(filtreTrain, filtreTranche);
        
        /**
         * creation du filtre global
         */
        @SuppressWarnings("unchecked")
        IFiltre<PlanTransport> filtreEt = new FiltreEtPlanTransport(filtreCodeEqp,filtreCodeRm,filtreOD, filtreStatus, filtreStop, filtreTosp, filtreOu);
        
        this.planTransport =  filtreEt.filtreParCritere(planTransportCompar);

    }

    /**
     * @return the filtre
     */
    public FiltrePlanTransportDto getFiltre() {
        return this.filtre;
    }

    /**
     * @param filtre
     *            the filtre to set
     */
    public void setFiltre(FiltrePlanTransportDto filtre) {
        this.filtre = filtre;
    }

    /**
     * @return the planTransport
     */
    public PlanTransport getPlanTransport() {
        return this.planTransport;
    }

    /**
     * @param planTransport
     *            the planTransport to set
     */
    public void setPlanTransport(PlanTransport planTransport) {
        this.planTransport = planTransport;
    }

}