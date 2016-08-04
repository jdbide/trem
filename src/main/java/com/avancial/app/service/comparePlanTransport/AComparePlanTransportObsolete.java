package com.avancial.app.service.comparePlanTransport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransportComparable;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;

@Deprecated
public abstract class AComparePlanTransportObsolete implements IComparePlanTransportObsolete {

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

    public AComparePlanTransportObsolete() {
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
    }

    public <T extends IPlanTransportComparable> List<IComparaisonPlanTransport> compare(List<T> ancien, List<T> nouveau,
            boolean chercheAjout) throws Exception {
        List<IComparaisonPlanTransport> res = new ArrayList<>();
        EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport = chercheAjout
                ? EnumTypeComparaisonPlanTransport.NEW : EnumTypeComparaisonPlanTransport.DELETE;
        T trainAncien;
        IComparaisonPlanTransport comparaisonPlanTransport = null;

        /* Boucle sur les trains de nouveau */
        for (T trainNouveau : nouveau) {
            /* Vérifie si trainNouveau existe dans ancien */
            int index = ancien.indexOf(trainNouveau);
            /* Si trainNouveau n'est pas dans ancien */
            if (index < 0) {
                /* C'est un nouveau train */
                comparaisonPlanTransport = this.generateComparaisonPlanTransportNewDelete(typeComparaisonPlanTransport);
                res.add(comparaisonPlanTransport);
            }
            /* TrainNouveau est dans ancien */
            else if (chercheAjout) {
                /* On ajoute les résultats du chaineComparePlanTransport des trains */
                trainAncien = ancien.get(index);
                res.addAll(this.updateComparaisonPlanTransport(trainNouveau.compare(trainAncien)));
            }
        }
        return res;
    }

    /**
     * Génère un objet de comparaison d'un type donné
     * 
     * @param typeComparaisonPlanTransport
     * @return
     */
    protected abstract IComparaisonPlanTransport generateComparaisonPlanTransportNewDelete(
            EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport);

    /**
     * Met à jour des comparaisons (issues de comparaisons d'objets enfants)
     * 
     * @param comparaisonPlanTransports
     * @return
     */
    protected abstract List<IComparaisonPlanTransport> updateComparaisonPlanTransport(
            List<IComparaisonPlanTransport> comparaisonPlanTransports);

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

}
