package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.ArrayList;
import java.util.List;
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
            IPlanTransportComparable comparableNouveau) throws CloneNotSupportedException {
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
        if (this.successeur != null && !this.tousModify) {
            res.addAll(this.successeur.compare(comparableAncien, comparableNouveau));
        }
        
        return res;
    }

    /**
     * 
     * @param attributsFieldAncien
     * @param attributsFieldNouveau
     * @return
     */
    private List<? extends IComparaisonPlanTransport> compareAttributListsModify(
            List<? extends IPlanTransportComparable> attributsFieldAncien,
            List<? extends IPlanTransportComparable> attributsFieldNouveau) {
        List<? extends IComparaisonPlanTransport> res = new ArrayList<>();
        // TODO Auto-generated method stub
        return null;
    }

}
