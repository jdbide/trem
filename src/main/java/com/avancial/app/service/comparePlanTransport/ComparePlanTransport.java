package com.avancial.app.service.comparePlanTransport;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
     * Liste des TrainTranche présents dans les deux plans de transport
     */
    private Set<TrainTranche> others;

    public ComparePlanTransport() {
        this.news = new HashSet<>();
        this.deletes = new HashSet<>();
        this.others = new HashSet<>();
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
        this.news.addAll(this.compare(ancien.getTrains(), nouveau.getTrains()));

        /*
         * Remplissage des listes de TrainTranche supprimés : présents dans le
         * plan de transport le moins récent, et pas dans le plus récent
         */
        this.deletes.addAll(this.compare(nouveau.getTrains(), ancien.getTrains()));
    }

    /**
     * Compare deux listes de trains, et retourne la liste des train-tranche qui
     * sont dans la seconde liste donnée et pas dans la première.
     * 
     * @param ancien
     *            Liste de trains correspondant à un jeu de données moins récent
     * @param nouveau
     *            Liste de trains correspondant à un jeu de données plus récent
     * @return Liste des {@link TrainTranche} qui sont dans "nouveau" et pas
     *         dans "ancien"
     */
    public List<TrainTranche> compare(List<Train> ancien, List<Train> nouveau) {
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
                    if (trainAncien.getTranches().contains(trancheNouveau)) {
                        /* TrainTranche présent dans nouveau et ancien */
                        this.others.add(new TrainTranche(trainNouveau.getNumeroTrain(), trancheNouveau));
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

    public Set<TrainTranche> getNews() {
        return this.news;
    }

    public Set<TrainTranche> getDeletes() {
        return this.deletes;
    }

    public Set<TrainTranche> getOthers() {
        return this.others;
    }

    @Override
    public void genereRapportDifferentiel() {
        // TODO Auto-generated method stub
        
    }

}
