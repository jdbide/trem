package com.avancial.app.service.comparePlanTransport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransportComparable;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public class ComparePlanTransport implements IComparePlanTransport {

    /**
     * Liste des TrainTranche présents dans le plan de transport le plus récent,
     * et absents du moins récent
     */
    private Set<TrainTranche> news;
    /**
     * Liste des TrainTranche présents dans le plan de transport le moins
     * récent, et absents du plus récent
     */
    private Set<TrainTranche> deletes;
    /**
     * Map des TrainTranche présents dans les deux plans de transport :<br>
     * la clé correspond au TrainTranche du plan de transport le moins récent,
     * <br>
     * et la valeur au TrainTranche du plus récent.
     */
    private Map<TrainTranche, TrainTranche> others;

    private Set<ComparaisonTrainTranche> modifies;
    private Set<ComparaisonTrainTranche> unchangeds;
    private Set<ComparaisonTrainTranche> regimeSplits;

    public ComparePlanTransport() {
        this.news = new HashSet<>();
        this.deletes = new HashSet<>();
        this.others = new HashMap<>();
    }

    /**
     * Vidage des listes présentant les différences entre deux plans de
     * transport.
     */
    private void initLists() {
        this.news.clear();
        this.deletes.clear();
        this.others.clear();
    }

    @Override
    public void compare(PlanTransport ancien, PlanTransport nouveau) {
        /* On vide toutes les listes */
        this.initLists();

        /*
         * Remplissage des listes de TrainTranche nouveaux : présents dans le
         * plan de transport le plus récent, et pas dans le moins récent
         */
        this.compareNew(ancien.getTrains(), nouveau.getTrains());

        /*
         * Remplissage des listes de TrainTranche supprimés : présents dans le
         * plan de transport le moins récent, et pas dans le plus récent
         */
        this.compareDelete(ancien.getTrains(), nouveau.getTrains());

        this.compareTrainTranche();
    }

    private void compareNew(List<Train> ancien, List<Train> nouveau) {
        this.news.addAll(this.compare(ancien, nouveau, false));
    }

    private void compareDelete(List<Train> ancien, List<Train> nouveau) {
        this.deletes.addAll(this.compare(nouveau, ancien, true));
    }

    /**
     * Compare deux listes de trains, et retourne la liste des train-tranche qui
     * sont dans la seconde liste donnée et pas dans la première.
     * 
     * @param ancien
     *            Liste de trains correspondant à un jeu de données moins récent
     * @param nouveau
     *            Liste de trains correspondant à un jeu de données plus récent
     * @param ajoutOthers
     *            Indique si l'on ajoute les TrainTranche qui sont dans les deux
     *            jeux de données dans la map others
     * @return Liste des {@link TrainTranche} qui sont dans "nouveau" et pas
     *         dans "ancien"
     */
    private List<TrainTranche> compare(List<Train> ancien, List<Train> nouveau, boolean ajoutOthers) {
        List<TrainTranche> res = new ArrayList<>();
        Train trainAncien;

        /* Boucle sur les trains de nouveau */
        for (Train trainNouveau : nouveau) {
            /* Vérifie que trainNouveau existe dans ancien */
            if (ancien.contains(trainNouveau)) {
                /* On récupère le trainNouveau dans ancien */
                trainAncien = ancien.get(ancien.indexOf(trainNouveau));
                /* Boucle sur les tranches de trainNouveau */
                for (Tranche trancheNouveau : trainNouveau.getTranches()) {
                    /* Vérifie que trancheNouveau est dans trainAncien */
                    int index = trainAncien.getTranches().indexOf(trancheNouveau);
                    if (index >= 0) {
                        /* TrainTranche présent dans nouveau et ancien */
                        if (ajoutOthers) {
                            this.others.put(new TrainTranche(trainNouveau.getNumeroTrain(), trancheNouveau),
                                    new TrainTranche(trainAncien.getNumeroTrain(),
                                            trainAncien.getTranches().get(index)));
                        }
                    }
                    /* TrancheNouveau n'est pas dans trainAncien */
                    else {
                        /*
                         * TrainTranche présent dans nouveau et pas dans ancien
                         */
                        res.add(new TrainTranche(trainNouveau.getNumeroTrain(), trancheNouveau));
                    }
                }
            }
            /* TrainNouveau n'est pas dans ancien */
            else {
                /*
                 * Boucle sur les tranches de trainNouveau pour les ajouter dans
                 * news
                 */
                for (Tranche trancheNouveau : trainNouveau.getTranches()) {
                    res.add(new TrainTranche(trainNouveau.getNumeroTrain(), trancheNouveau));
                }
            }
        }
        return res;
    }

    private void compareTrainTranche() {
        for (TrainTranche tt : this.others.keySet()) {
            this.compareTrainTranche(tt, this.others.get(tt));
        }
    }

    private void compareTrainTranche(TrainTranche ttAncien, TrainTranche ttNouveau) {
        // TODO Auto-generated method stub

    }

    private <T extends IPlanTransportComparable> void compare(List<T> regimeComparables1, List<T> regimeComparables2) {
        // TODO Auto-generated method stub

    }

    public Set<TrainTranche> getNews() {
        return this.news;
    }

    public Set<TrainTranche> getDeletes() {
        return this.deletes;
    }

    public Map<TrainTranche, TrainTranche> getOthers() {
        return this.others;
    }

    @Override
    public void genereRapportDifferentiel() {
        // TODO Auto-generated method stub

    }

    public Set<ComparaisonTrainTranche> getModifies() {
        return this.modifies;
    }

    public Set<ComparaisonTrainTranche> getUnchangeds() {
        return this.unchangeds;
    }

    public Set<ComparaisonTrainTranche> getRegimeSplits() {
        return this.regimeSplits;
    }

}
