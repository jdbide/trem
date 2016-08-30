package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.Iterator;
import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.service.comparePlanTransport.CompareAttributTranche;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

/**
 * Implémentation pour les comparaisons MODIFY et REGIMESPLIT au niveau d'une
 * tranche, c'est-à-dire la détection de paires d'attributs qui sont modifiés
 * (soit dans leur valeur, soit dans leur régime) entre deux listes d'attributs.
 * 
 * @author heloise.guillemaud
 *
 */
public abstract class ACompareTrancheModifyRegimesplit extends AChaineComparePlanTransport {

    /**
     * Indique s'il reste des attributs à tester dans les listes
     */
    protected boolean attributRestant = false;

    /**
     * Compare deux listes d'attributs de tranches, et retourne une liste de
     * {@link IComparaisonPlanTransport} correspondant aux comparaisons de type
     * MODIFY ou REGIMESPLIT.<br>
     * Pour chaque paire attributAncien-attributNouveau du type de comparaison
     * donné trouvée, les éléments correspondants sont retirés des listes (afin
     * qu'on ne les teste pas sur d'autres types de comparaison).
     * 
     * @param typeComparaisonPlanTransport
     *            Type de comparaison cherché (MODIFY ou REGIMESPLIT)
     * @param numeroTranche
     *            Numéro de la tranche à laquelle les attributs appartiennent
     * @param attributsFieldAncien
     *            Liste d'attributs dans la tranche la moins récente
     * @param attributsFieldNouveau
     *            Liste d'attributs dans la tranche la plus récente
     * @return Liste de {@link IComparaisonPlanTransport} du type
     *         typeComparaisonPlanTransport
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    protected MapComparaisonPlanTransport compareAttributLists(
            EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport, String numeroTranche,
            List<? extends IPlanTransport> attributsFieldAncien, List<? extends IPlanTransport> attributsFieldNouveau)
            throws Exception {
        MapComparaisonPlanTransport res = new MapComparaisonPlanTransport();

        ComparaisonPlanTransport<IPlanTransport> comparaisonPlanTransport;
        IComparePlanTransport comparePlanTransport = new CompareAttributTranche();

        /* Boucle sur les attributs de nouveau */
        for (Iterator<ASousRegimeTranche> itSousRegimeTrancheNouveau = (Iterator<ASousRegimeTranche>) attributsFieldNouveau
                .iterator(); itSousRegimeTrancheNouveau.hasNext();) {
            ASousRegimeTranche sousRegimeTrancheNouveau = itSousRegimeTrancheNouveau.next();

            /* Boucle sur les attributs de ancien */
            for (Iterator<ASousRegimeTranche> itSousRegimeTrancheAncien = (Iterator<ASousRegimeTranche>) attributsFieldAncien
                    .iterator(); itSousRegimeTrancheAncien.hasNext();) {
                ASousRegimeTranche sousRegimeTrancheAncien = itSousRegimeTrancheAncien.next();

                /* On compare les attributs de nouveau et ancien deux à deux */
                MapComparaisonPlanTransport resComparaison = comparePlanTransport.compare(sousRegimeTrancheAncien,
                        sousRegimeTrancheNouveau);

                /*
                 * Si on trouve un attribut modifié entre ancien et nouveau, on
                 * ajoute un objet dans le résultat, et on enlève les attributs
                 * des listes
                 */
                List<ComparaisonPlanTransport<IPlanTransport>> listComparaison = resComparaison
                        .getComparaison(typeComparaisonPlanTransport, sousRegimeTrancheAncien.getClass());
                if (listComparaison != null && listComparaison.size() > 0) {
                    comparaisonPlanTransport = listComparaison.get(0);
                    comparaisonPlanTransport.setNumeroTranche(numeroTranche);
                    res.putComparaison(comparaisonPlanTransport);

                    /*
                     * En RegimeSplit, l'attribut dans la trancheAncien peut
                     * correspondre à plusieurs attributs dans trancheNouveau,
                     * il faut donc garder le regimeComparableAncien dans la
                     * liste
                     */
                    if (!typeComparaisonPlanTransport.equals(EnumTypeComparaisonPlanTransport.REGIMESPLIT)) {
                        itSousRegimeTrancheAncien.remove();
                    }
                    itSousRegimeTrancheNouveau.remove();
                    break;
                }
                else {
                    this.attributRestant = true;
                }
            }
        }
        return res;
    }

}
