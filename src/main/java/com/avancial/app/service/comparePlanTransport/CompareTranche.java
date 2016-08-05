package com.avancial.app.service.comparePlanTransport;

import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.CompareTrancheModify;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.CompareTrancheRegimesplit;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.CompareTrancheUnchanged;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.IChaineComparePlanTransport;

public class CompareTranche extends AComparePlanTransport {

    @Override
    protected void initChaineComparePlanTransport() {
        IChaineComparePlanTransport chaineCompareTrancheUnchanged = new CompareTrancheUnchanged();
        IChaineComparePlanTransport chaineCompareTrancheModify = new CompareTrancheModify();
        IChaineComparePlanTransport chaineCompareTrancheRegimesplit = new CompareTrancheRegimesplit();

        chaineCompareTrancheUnchanged.setSuccesseur(chaineCompareTrancheModify);
        chaineCompareTrancheModify.setSuccesseur(chaineCompareTrancheRegimesplit);
        
        this.chaineComparePlanTransport = chaineCompareTrancheModify;
    }

}
