package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTrancheStatut;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonDifferentielPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.CompareTranche;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public class CompareTrainModify extends AChaineComparePlanTransport {

    private static Logger logger = Logger.getLogger(CompareTrainModify.class);

    @Override
    public MapComparaisonPlanTransport compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {

        Train trainAncien = (Train) comparableAncien;
        Train trainNouveau = (Train) comparableNouveau;
        logger.info("Début comparaison Trains Modify : " + trainAncien.getNumeroTrain() + " - "
                + trainNouveau.getNumeroTrain());

        MapComparaisonPlanTransport res = new MapComparaisonPlanTransport();

        MapComparaisonPlanTransport resModify;

        ComparaisonDifferentielPlanTransport<IPlanTransport> comparaisonPlanTransport;

        Tranche trancheAncien, trancheNouveau;

        for (Iterator<Tranche> itTrancheAncien = (Iterator<Tranche>) trainAncien.getTranches()
                .iterator(); itTrancheAncien.hasNext();) {
            trancheAncien = itTrancheAncien.next();
            for (Iterator<Tranche> itTrancheNouveau = (Iterator<Tranche>) trainNouveau.getTranches()
                    .iterator(); itTrancheNouveau.hasNext();) {
                trancheNouveau = itTrancheNouveau.next();
                if (this.trancheModify(trancheNouveau, trancheAncien)) {
                    comparaisonPlanTransport = new ComparaisonDifferentielPlanTransport<>();
                    comparaisonPlanTransport.setNumeroTrain(trainNouveau.getNumeroTrain());
                    comparaisonPlanTransport.setNumeroTranche(trancheNouveau.getNumeroTranche());
                    comparaisonPlanTransport.setRegimeTranche(trancheNouveau.getRegime());
                    comparaisonPlanTransport.setTrancheStatut(EnumTrancheStatut.Ouvert);//aucun sens mais permet de normer pour les testes
                    comparaisonPlanTransport.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.MODIFY);
                    comparaisonPlanTransport.setAncienField(trancheAncien);
                    comparaisonPlanTransport.setNouveauField(trancheNouveau);
                    logger.info("Train-Tranche Modify : " + comparaisonPlanTransport.getNumeroTrain() + "-"
                            + comparaisonPlanTransport.getNumeroTranche());
                    res.putComparaison(comparaisonPlanTransport);
                    resModify = this.compareOther(trancheAncien, trancheNouveau, trainAncien);
                    res.putAll(resModify);
                    itTrancheNouveau.remove();
                    itTrancheAncien.remove();
                    break;
                }
            }
        }
        res.putAll(this.successeurCompare(comparableAncien, comparableNouveau));
        return res;
    }

    private boolean trancheModify(Tranche trancheNouveau, Tranche trancheAncien) {
        return (trancheNouveau.getNumeroTranche() == trancheAncien.getNumeroTranche()
                && trancheNouveau.getRegime().equals(trancheAncien.getRegime())
                && trancheNouveau.getTrancheStatut() != trancheAncien.getTrancheStatut());
    }

    private MapComparaisonPlanTransport compareOther(Tranche trancheAncien, Tranche trancheNouveau, Train train)
            throws Exception {
        MapComparaisonPlanTransport resTrain = new MapComparaisonPlanTransport();
        logger.info("Début comparaison Trains Modify : " + train.getNumeroTrain());
        IComparePlanTransport comparePlanTransport = new CompareTranche();
        logger.info("Début comparaison Tranches : " + trancheAncien.getNumeroTranche());
        resTrain.putAll(comparePlanTransport.compare(trancheAncien, trancheNouveau));
        logger.info("Fin comparaison Tranches : " + trancheAncien.getNumeroTranche());

        for (List<ComparaisonDifferentielPlanTransport<IPlanTransport>> listComparaison : resTrain.values()) {
            for (ComparaisonDifferentielPlanTransport<IPlanTransport> comparaisonPlanTransport : listComparaison) {
                comparaisonPlanTransport.setNumeroTrain(train.getNumeroTrain());
            }
        }
        return resTrain;
    }

}
