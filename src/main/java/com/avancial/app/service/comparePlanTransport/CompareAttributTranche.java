package com.avancial.app.service.comparePlanTransport;

import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.CompareAttributTrancheModify;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.CompareAttributTrancheRegimesplit;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.CompareAttributTrancheUnchanged;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.IChaineComparePlanTransport;

public class CompareAttributTranche extends AComparePlanTransport {

    @Override
    protected void initChaineComparePlanTransport() {
        IChaineComparePlanTransport chaineCompareAttributTrancheRegimesplit = new CompareAttributTrancheRegimesplit();
        IChaineComparePlanTransport chaineCompareAttributTrancheModify = new CompareAttributTrancheModify();
        IChaineComparePlanTransport chaineCompareAttributTrancheUnchanged = new CompareAttributTrancheUnchanged();

        chaineCompareAttributTrancheRegimesplit.setSuccesseur(chaineCompareAttributTrancheModify);
        chaineCompareAttributTrancheModify.setSuccesseur(chaineCompareAttributTrancheUnchanged);
        
        this.chaineComparePlanTransport = chaineCompareAttributTrancheRegimesplit;
    }

}
