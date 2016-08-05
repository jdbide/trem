package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.ARegimeComparable;
import com.avancial.app.data.objetsMetier.PlanTransport.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.service.comparePlanTransport.CompareAttributTranche;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;

public abstract class ACompareTranche extends AChaineComparePlanTransport {
    
    protected boolean attributRestant = false;
    
    @SuppressWarnings("unchecked")
    protected List<IComparaisonPlanTransport> compareAttributLists(
            EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport,
            String numeroTranche,
            List<? extends IPlanTransport> attributsFieldAncien,
            List<? extends IPlanTransport> attributsFieldNouveau) throws Exception {
        List<IComparaisonPlanTransport> res = new ArrayList<>();

        ComparaisonPlanTransport<IPlanTransport> comparaisonPlanTransport;
        IComparePlanTransport comparePlanTransport = new CompareAttributTranche();

        /* Boucle sur les attributs de nouveau */
        for (Iterator<ARegimeComparable> itRegimeComparableNouveau = (Iterator<ARegimeComparable>) attributsFieldNouveau
                .iterator(); itRegimeComparableNouveau.hasNext();) {
            ARegimeComparable regimeComparableNouveau = itRegimeComparableNouveau.next();

            /* Boucle sur les attributs de ancien */
            for (Iterator<ARegimeComparable> itRegimeComparableAncien = (Iterator<ARegimeComparable>) attributsFieldAncien
                    .iterator(); itRegimeComparableAncien.hasNext();) {
                ARegimeComparable regimeComparableAncien = itRegimeComparableAncien.next();

                /* On compare les attributs de nouveau et ancien deux à deux */
                List<IComparaisonPlanTransport> resComparaison = comparePlanTransport.compare(regimeComparableAncien,
                        regimeComparableNouveau);

                /*
                 * Si on trouve un attribut modifié entre ancien et nouveau, on
                 * ajoute un objet dans le résultat, et on enlève les attributs
                 * des listes
                 */
                if (resComparaison.size() > 0
                        && ((ComparaisonPlanTransport<IPlanTransport>) resComparaison.get(0))
                                .getTypeComparaisonPlanTransport().equals(typeComparaisonPlanTransport)) {
                    comparaisonPlanTransport = (ComparaisonPlanTransport<IPlanTransport>) resComparaison.get(0);
                    comparaisonPlanTransport.setNumeroTranche(numeroTranche);
                    res.addAll(resComparaison);

                    itRegimeComparableAncien.remove();
                    itRegimeComparableNouveau.remove();
                }
                else {
                    this.attributRestant = true;
                }
            }
        }
        return res;
    }

}
