package com.avancial.app.service.filtrePlanTransport;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Desserte;
import com.avancial.app.data.objetsMetier.PlanTransport.Gare;
import com.avancial.app.data.objetsMetier.PlanTransport.GareHoraire;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

/**
 * Filtre des dessertes d'une tranche en fonction de l'inclusion ou non de gares
 * dans celles-ci.
 * 
 * @author heloise.guillemaud
 *
 */
public abstract class AFiltreGareDesserteTranche implements IFiltre<Tranche> {

    private List<Gare> gares;

    /**
     * 
     * @param gares
     *            Liste de gares pour filtrer les dessertes
     */
    public AFiltreGareDesserteTranche(List<Gare> gares) {
        super();
        this.gares = gares;
    }

    @Override
    public Tranche filtreParCritere(Tranche object) {
        if (this.gares == null)
            return object;
        Tranche tranche = object.clone();

        /* Indique si l'on trouve une gare de la liste dans un desserte */
        boolean garePresente = false;
        if (object.getAttributsField(Desserte.class) != null) {
            /* Boucle sur les dessertes à filtrer */
            for (ASousRegimeTranche aSousRegimeTranche : object.getAttributsField(Desserte.class)) {
                garePresente = false;
                Desserte desserte = (Desserte) aSousRegimeTranche;

                /*
                 * On cherche dans les gares de la desserte une gare présente
                 * dans la liste du filtre
                 */
                for (GareHoraire gareHoraire : desserte.getGareHoraires()) {
                    if (this.gares.contains(gareHoraire.getGare())) {
                        garePresente = true;
                        break;
                    }
                }

                this.traiteDesserte(garePresente, desserte);
            }
            this.traiteTranche(tranche);
        }
        return tranche;
    }

    /**
     * Traitement d'une desserte, en fonction de la présence ou absence d'une
     * gare de la liste dans celle-ci
     * 
     * @param garePresente
     *            Booléen indiquant si une gare de la liste est présente
     *            (<tt>true</tt>) ou absente ({@code false})
     * @param desserte
     *            Desserte dans laquelle la présence d'une gare de la liste a
     *            été testée
     */
    protected abstract void traiteDesserte(boolean garePresente, Desserte desserte);

    /**
     * Traitement de la tranche résultat une fois que toutes ses dessertes ont
     * été testées
     * 
     * @param tranche
     *            Tranche résultat, qui sera le résultat du filtre
     */
    protected abstract void traiteTranche(Tranche tranche);
}
