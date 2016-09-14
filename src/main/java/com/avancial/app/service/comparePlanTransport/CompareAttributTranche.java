package com.avancial.app.service.comparePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.CompareAttributTrancheModify;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.CompareAttributTrancheRegimesplit;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.CompareAttributTrancheUnchanged;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.IChaineComparePlanTransport;

/**
 * Création de la chaîne de responsabilité pour comparer deux
 * {@link ASousRegimeTranche}.
 * 
 * @author heloise.guillemaud
 *
 */
public class CompareAttributTranche extends AComparePlanTransport {

    @Override
    protected void initChaineComparePlanTransport() {
        IChaineComparePlanTransport chaineCompareAttributTrancheModify = new CompareAttributTrancheModify();
        IChaineComparePlanTransport chaineCompareAttributTrancheRegimesplit = new CompareAttributTrancheRegimesplit();
        IChaineComparePlanTransport chaineCompareAttributTrancheUnchanged = new CompareAttributTrancheUnchanged();

        chaineCompareAttributTrancheModify.setSuccesseur(chaineCompareAttributTrancheRegimesplit);
        chaineCompareAttributTrancheRegimesplit.setSuccesseur(chaineCompareAttributTrancheUnchanged);

        this.chaineComparePlanTransport = chaineCompareAttributTrancheModify;
    }

}
