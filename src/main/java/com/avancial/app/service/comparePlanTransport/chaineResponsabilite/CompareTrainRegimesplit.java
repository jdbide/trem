package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonDifferentielPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.CompareTranche;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public class CompareTrainRegimesplit extends AChaineComparePlanTransport {

    private static Logger logger = Logger.getLogger(CompareTrainRegimesplit.class);

    @Override
    public MapComparaisonPlanTransport compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {

        Train trainAncien = (Train) comparableAncien;
        Train trainNouveau = (Train) comparableNouveau;
        logger.info("Début comparaison Trains RegimeSplit : " + trainAncien.getNumeroTrain() + " - "
                + trainNouveau.getNumeroTrain());

        MapComparaisonPlanTransport res = new MapComparaisonPlanTransport();

        ComparaisonDifferentielPlanTransport<IPlanTransport> comparaisonPlanTransport;

        MapComparaisonPlanTransport resSplit;

        Tranche trancheAncien;
        List<Tranche> tranchesNouveau;
        /* Boucle sur les tranchesAncien */
        for (Iterator<Tranche> itTrancheAncien = (Iterator<Tranche>) trainAncien.getTranches()
                .iterator(); itTrancheAncien.hasNext();) {
            trancheAncien = itTrancheAncien.next();

            /*
             * Vérifie si tranche nouveau est inclus dans un element de
             * trancheAncien et que la date de debut ou de fin est egale
             */
            tranchesNouveau = this.findTrancheSplit(trancheAncien, (List<Tranche>) trainNouveau.getTranches());
            for (Tranche trancheNouveau : tranchesNouveau) {
                /* traincheNouveau est un des regimeSplit de trancheAncien */
                comparaisonPlanTransport = new ComparaisonDifferentielPlanTransport<>();
                comparaisonPlanTransport.setNumeroTrain(trainNouveau.getNumeroTrain());
                comparaisonPlanTransport.setNumeroTranche(trancheNouveau.getNumeroTranche());
                comparaisonPlanTransport.setTrancheStatut(trancheNouveau.getTrancheStatut());
                comparaisonPlanTransport.setRegimeTranche(trancheAncien.getRegime());
                comparaisonPlanTransport.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.REGIMESPLIT);
                comparaisonPlanTransport.setAncienField(trancheAncien);
                comparaisonPlanTransport.setNouveauField(trancheNouveau);
                logger.info("Train-Tranche RegimeSplit : " + comparaisonPlanTransport.getNumeroTrain() + "-"
                        + comparaisonPlanTransport.getNumeroTranche());
                res.putComparaison(comparaisonPlanTransport);
                resSplit = this.compareOther(trancheAncien, trancheNouveau, trainAncien);
                res.putAll(resSplit);
                trainNouveau.getTranches().remove(trancheNouveau);
            }
            if (!tranchesNouveau.isEmpty())
                itTrancheAncien.remove();
        }

        res.putAll(this.successeurCompare(comparableAncien, comparableNouveau));
        return res;
    }

    private MapComparaisonPlanTransport compareOther(Tranche trancheAncien, Tranche trancheNouveau, Train train)
            throws Exception {
        MapComparaisonPlanTransport resTrain = new MapComparaisonPlanTransport();
        logger.info("Début comparaison Trains Split : " + train.getNumeroTrain());
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

    private List<Tranche> findTrancheSplit(Tranche trancheNouveau, List<Tranche> tranches) {
        List<Tranche> res = new ArrayList<>();
        for (Tranche tranche : tranches) {
            if (
            // verifie si les tranches on le meme n° et statut
            tranche.equals(trancheNouveau)
                    // verifie si les tranches ont la meme dfate debut
                    // mais pas la meme fin
                    && (((tranche.getRegime().getDateDebut().equals(trancheNouveau.getRegime().getDateDebut())
                            && !tranche.getRegime().getDateFin().equals(trancheNouveau.getRegime().getDateFin()))
                            // verifie si les tranches ont la meme date fin mais
                            // pas le meme
                            // debut
                            || (tranche.getRegime().getDateFin().equals(trancheNouveau.getRegime().getDateFin())
                                    && !tranche.getRegime().getDateDebut()
                                            .equals(trancheNouveau.getRegime().getDateDebut()))))) {
                res.add(tranche);
            }
        }
        return res;
    }

}
