package com.avancial.app.service.comparePlanTransport;

import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.CompareTrainDelete;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.CompareTrainNew;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.CompareTrainOther;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.IChaineComparePlanTransport;

public class CompareTrain extends AComparePlanTransport {

    @Override
    protected void initChaineComparePlanTransport() {
        IChaineComparePlanTransport chaineCompareTrainNew = new CompareTrainNew();
        IChaineComparePlanTransport chaineCompareTrainDelete = new CompareTrainDelete();
        IChaineComparePlanTransport chaineCompareTrainOther = new CompareTrainOther();

        chaineCompareTrainNew.setSuccesseur(chaineCompareTrainDelete);
        chaineCompareTrainDelete.setSuccesseur(chaineCompareTrainOther);
        
        this.chaineComparePlanTransport = chaineCompareTrainNew;
    }

}
