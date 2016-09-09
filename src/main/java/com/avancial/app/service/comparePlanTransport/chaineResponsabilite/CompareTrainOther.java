package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.CompareTranche;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

/**
 * Appelle la chaîne de comparaison entre deux tranches que l'on retrouve dans
 * deux trains.
 * 
 * @author heloise.guillemaud
 *
 */
public class CompareTrainOther extends AChaineComparePlanTransport {

    private static Logger logger = Logger.getLogger(CompareTrainOther.class);

    @Override
    public MapComparaisonPlanTransport compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        MapComparaisonPlanTransport res = new MapComparaisonPlanTransport();
        Train trainAncien = (Train) comparableAncien;
        Train trainNouveau = (Train) comparableNouveau;
        MapComparaisonPlanTransport resTrain = new MapComparaisonPlanTransport();
        logger.info("Début comparaison Trains Other : " + trainAncien.getNumeroTrain() + " - "
                + trainNouveau.getNumeroTrain());

        IComparePlanTransport comparePlanTransport = new CompareTranche();
        /* Boucle sur les tranches de trainNouveau */
        for (Tranche trancheNouveau : trainNouveau.getTranches()) {
            /* Boucle sur les tranches de trainAncien */
            for (Iterator<Tranche> itTrancheAncien = (Iterator<Tranche>) trainAncien.getTranches()
                    .iterator(); itTrancheAncien.hasNext();) {
                Tranche trancheAncien = itTrancheAncien.next();
                /*
                 * Si les tranches ont le même numeroTranche, le même statut et
                 * le même régime, on les compare
                 */
                if (trancheNouveau.equals(trancheAncien) && trancheNouveau.getRegime().getCodeRegime()
                        .equals(trancheAncien.getRegime().getCodeRegime())) {

                    logger.info("Début comparaison Tranches : " + trancheAncien.getNumeroTranche() + " - "
                            + trancheNouveau.getNumeroTranche());
                    resTrain.clear();
                    resTrain.putAll(comparePlanTransport.compare(trancheAncien, trancheNouveau));
                    logger.info("Fin comparaison Tranches : " + trancheAncien.getNumeroTranche() + " - "
                            + trancheNouveau.getNumeroTranche());

                    for (List<ComparaisonPlanTransport<IPlanTransport>> listComparaison : resTrain.values()) {
                        for (ComparaisonPlanTransport<IPlanTransport> comparaisonPlanTransport : listComparaison) {
                            comparaisonPlanTransport.setNumeroTrain(trainAncien.getNumeroTrain());
                        }
                    }

                    res.putAll(resTrain);
                    /* La comparaison entre les tranches est terminée */
                    itTrancheAncien.remove();
                    break;
                }
            }
        }
        res.putAll(this.successeurCompare(comparableAncien, comparableNouveau));
        logger.info("Fin comparaison Trains Other : " + trainAncien.getNumeroTrain() + " - "
                + trainNouveau.getNumeroTrain());
        return res;
    }

}
