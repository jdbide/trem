package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.ARegimeComparable;
import com.avancial.app.data.objetsMetier.PlanTransport.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransportComparable;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public class CompareTrancheModify extends AChaineComparePlanTransport {

    /**
     * Indique si tous les attributs de la tranche sont modifiés
     */
    private boolean tousModify = true;

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransportComparable comparableAncien,
            IPlanTransportComparable comparableNouveau) throws Exception {
        System.out.println("CompareTrancheModify");
        List<IComparaisonPlanTransport> res = new ArrayList<>();
        Tranche trancheAncien = (Tranche) comparableAncien;
        Tranche trancheNouveau = (Tranche) comparableNouveau;

        /* Boucle sur les attributs de trancheNouveau */
        for (Class<?> attribut : trancheNouveau.getAttributs().keySet()) {
            res.addAll(this.compareAttributListsModify(trancheAncien.getAttributsField(attribut),
                    trancheNouveau.getAttributsField(attribut)));
        }

        /*
         * Si tous les tests de modify sont vrais, pas besoin de continuer la
         * chaîne
         */
        if (!this.tousModify) {
            res.addAll(this.successeurCompare(comparableAncien, comparableNouveau));
        }

        return res;
    }

    /**
     * 
     * @param attributsFieldAncien
     * @param attributsFieldNouveau
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private List<IComparaisonPlanTransport> compareAttributListsModify(
            List<? extends IPlanTransportComparable> attributsFieldAncien,
            List<? extends IPlanTransportComparable> attributsFieldNouveau) throws Exception {
        List<IComparaisonPlanTransport> res = new ArrayList<>();

        ComparaisonPlanTransport<IPlanTransportComparable> comparaisonPlanTransport;
        List<IPlanTransportComparable> ancien = new ArrayList<>();
        List<IPlanTransportComparable> nouveau = new ArrayList<>();

        /* Boucle sur les attributs de nouveau */
        for (Iterator<ARegimeComparable> itRegimeComparableNouveau = (Iterator<ARegimeComparable>) attributsFieldNouveau
                .iterator(); itRegimeComparableNouveau.hasNext();) {
            ARegimeComparable regimeComparableNouveau = itRegimeComparableNouveau.next();

            /* Boucle sur les attributs de ancien */
            for (Iterator<ARegimeComparable> itRegimeComparableAncien = (Iterator<ARegimeComparable>) attributsFieldAncien
                    .iterator(); itRegimeComparableAncien.hasNext();) {
                ARegimeComparable regimeComparableAncien = itRegimeComparableAncien.next();

                /* On compare les attributs de nouveau et ancien deux à deux */
                List<IComparaisonPlanTransport> resComparaison = regimeComparableNouveau
                        .compare(regimeComparableAncien);

                /*
                 * Si on trouve un attribut modifié entre ancien et nouveau, on
                 * ajoute un objet dans le résultat, et on enlève les attributs
                 * des listes
                 */
                if (resComparaison.size() > 0
                        && ((ComparaisonPlanTransport<IPlanTransportComparable>) resComparaison.get(0))
                                .getTypeComparaisonPlanTransport().equals(EnumTypeComparaisonPlanTransport.MODIFY)) {
                    ancien.clear();
                    ancien.add(regimeComparableAncien);
                    nouveau.clear();
                    nouveau.add(regimeComparableNouveau);
                    comparaisonPlanTransport = new ComparaisonPlanTransport<IPlanTransportComparable>();
                    comparaisonPlanTransport.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.MODIFY);
                    comparaisonPlanTransport.setAncienFields(ancien);
                    comparaisonPlanTransport.setNouveauFields(nouveau);
                    res.add(comparaisonPlanTransport);

                    itRegimeComparableAncien.remove();
                    itRegimeComparableNouveau.remove();
                }
                else {
                    this.tousModify = false;
                }
            }
        }
        return res;
    }

}
