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

    /**
     * Compare deux listes d'attributs de tranches, et retourne une liste de
     * {@link IComparaisonPlanTransport} correspondant aux comparaisons d'un
     * type donné trouvées.<br>
     * Pour chaque paire attributAncien-attributNouveau du type de comparaison
     * donné trouvée, les éléments correspondants sont retirés des listes (afin
     * qu'on ne les teste pas sur d'autres types de comparaison).
     * 
     * @param typeComparaisonPlanTransport Type de comparaison cherché
     * @param numeroTranche Numéro de la tranche à laquelle les attributs appartiennent
     * @param attributsFieldAncien Liste d'attributs dans la tranche la moins récente
     * @param attributsFieldNouveau Liste d'attributs dans la tranche la plus récente
     * @return Liste de {@link IComparaisonPlanTransport} du type typeComparaisonPlanTransport
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    protected List<IComparaisonPlanTransport> compareAttributLists(
            EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport, String numeroTranche,
            List<? extends IPlanTransport> attributsFieldAncien, List<? extends IPlanTransport> attributsFieldNouveau)
            throws Exception {
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
                if (resComparaison.size() > 0 && ((ComparaisonPlanTransport<IPlanTransport>) resComparaison.get(0))
                        .getTypeComparaisonPlanTransport().equals(typeComparaisonPlanTransport)) {
                    comparaisonPlanTransport = (ComparaisonPlanTransport<IPlanTransport>) resComparaison.get(0);
                    comparaisonPlanTransport.setNumeroTranche(numeroTranche);
                    res.addAll(resComparaison);

                    /*
                     * En RegimeSplit, l'attribut dans la trancheAncien peut
                     * correspondre à plusieurs attributs dans trancheNouveau,
                     * il faut donc garder le regimeComparableAncien dans la
                     * liste
                     */
                    if (!typeComparaisonPlanTransport.equals(EnumTypeComparaisonPlanTransport.REGIMESPLIT)) {
                        itRegimeComparableAncien.remove();
                    }
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
