package com.avancial.app.service.controlePlanTransport;

import com.avancial.app.service.comparePlanTransport.AComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.IChaineComparePlanTransport;
import com.avancial.app.service.controlePlanTransport.chaineResponsabilite.CompareTrancheControl;

public class ControleTranche extends AComparePlanTransport {

    @Override
    protected void initChaineComparePlanTransport() {
        IChaineComparePlanTransport chaineCompareTrancheModify = new CompareTrancheControl();
        
        this.chaineComparePlanTransport = chaineCompareTrancheModify;
    }

}
