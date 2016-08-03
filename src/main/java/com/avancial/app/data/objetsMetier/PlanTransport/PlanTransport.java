package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.ArrayList;
import java.util.List;
import com.avancial.socle.utils.ListUtils;

public class PlanTransport implements IPlanTransportComparable {

    private EnumCompagnies compagnie;
    private List<Train> trains;

    @Override
    public boolean equals(Object obj) {
        PlanTransport plan = (PlanTransport) obj;
        if (plan.getCompagnie().equals(this.compagnie) && ListUtils.compareLists(plan.getTrains(), this.trains)) {
            return true;
        }
        return false;
    }

    /**
     * @param compagnie
     * @param trains
     */
    public PlanTransport(EnumCompagnies compagnie, List<Train> trains) {
        super();
        this.compagnie = compagnie;
        this.trains = trains;
    }

    public PlanTransport() {
        this.compagnie = EnumCompagnies.ES;
        this.trains = new ArrayList<>();
    }

    /**
     * @return the compagnie
     */
    public EnumCompagnies getCompagnie() {
        return this.compagnie;
    }

    /**
     * @param compagnie
     *            the compagnie to set
     */
    public void setCompagnie(EnumCompagnies compagnie) {
        this.compagnie = compagnie;
    }

    /**
     * @return the trains
     */
    public List<Train> getTrains() {
        return this.trains;
    }

    /**
     * @param trains
     *            the trains to set
     */
    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransportComparable autre) throws Exception {
        List<IComparaisonPlanTransport> res = new ArrayList<>();
        PlanTransport autrePlanTransport = (PlanTransport) autre;

        res.addAll(this.compareNew(this.trains, autrePlanTransport.getTrains()));

        res.addAll(this.compareDelete(this.trains, autrePlanTransport.getTrains()));

        return res;
    }

    /**
     * Récupère les trains nouveaux ainsi que toutes les modifications entre
     * deux listes de trains.
     * 
     * @param ancien
     *            Liste de trains d'un jeu de données moins récent
     * @param nouveau
     *            Liste de trains d'un jeu de données plus récent
     * @return
     * @throws Exception
     */
    private List<IComparaisonPlanTransport> compareNew(List<Train> ancien, List<Train> nouveau) throws Exception {
        return this.compare(ancien, nouveau, true);
    }

    /**
     * Récupère les trains supprimés entre deux listes de trains.
     * 
     * @param ancien
     *            Liste de trains d'un jeu de données moins récent
     * @param nouveau
     *            Liste de trains d'un jeu de données plus récent
     * @return
     * @throws Exception
     */
    private List<IComparaisonPlanTransport> compareDelete(List<Train> ancien, List<Train> nouveau) throws Exception {
        return this.compare(nouveau, ancien, false);
    }

    /**
     * Compare deux listes de trains, et retourne la liste des différences entre
     * les deux.
     * 
     * @param ancien
     *            Liste de trains correspondant à un jeu de données moins récent
     * @param nouveau
     *            Liste de trains correspondant à un jeu de données plus récent
     * @param chercheAjout
     *            Indique si l'on cherche des trains ajoutés ou supprimés
     * @return Liste des {@link IComparaisonPlanTransport} entre "nouveau" et
     *         "ancien"
     * @throws Exception
     */
    private List<IComparaisonPlanTransport> compare(List<Train> ancien, List<Train> nouveau, boolean chercheAjout)
            throws Exception {
        List<IComparaisonPlanTransport> res = new ArrayList<>();
        EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport = chercheAjout
                ? EnumTypeComparaisonPlanTransport.NEW : EnumTypeComparaisonPlanTransport.DELETE;
        Train trainAncien;
        ComparaisonPlanTransport<IPlanTransportComparable> comparaisonPlanTransport = null;

        /* Boucle sur les trains de nouveau */
        for (Train trainNouveau : nouveau) {
            /* Vérifie si trainNouveau existe dans ancien */
            int index = ancien.indexOf(trainNouveau);
            /* Si trainNouveau n'est pas dans ancien */
            if (index < 0) {
                /* C'est un nouveau train */
                comparaisonPlanTransport = new ComparaisonPlanTransport<>();
                comparaisonPlanTransport.setNumeroTrain(trainNouveau.getNumeroTrain());
                comparaisonPlanTransport.setTypeComparaisonPlanTransport(typeComparaisonPlanTransport);
                res.add(comparaisonPlanTransport);
            }
            /* TrainNouveau est dans ancien */
            else if (chercheAjout) {
                /* On ajoute les résultats du compare des trains */
                trainAncien = ancien.get(index);
                res.addAll(trainNouveau.compare(trainAncien));
            }
        }
        return res;
    }

}
