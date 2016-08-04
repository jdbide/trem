package com.avancial.app.service.comparePlanTransport;

import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.CompareTrancheModify;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.CompareTrancheRegimesplit;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.CompareTrancheUnchanged;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.IChaineComparePlanTransport;

public class CompareTranche extends AComparePlanTransport {

    @Override
    protected void initChaineComparePlanTransport() {
        IChaineComparePlanTransport chaineCompareTrancheModify = new CompareTrancheModify();
        IChaineComparePlanTransport chaineCompareTrancheRegimesplit = new CompareTrancheRegimesplit();
        IChaineComparePlanTransport chaineCompareTrancheUnchanged = new CompareTrancheUnchanged();

        chaineCompareTrancheModify.setSuccesseur(chaineCompareTrancheRegimesplit);
        chaineCompareTrancheRegimesplit.setSuccesseur(chaineCompareTrancheUnchanged);
        
        this.chaineComparePlanTransport = chaineCompareTrancheModify;
    }

}
