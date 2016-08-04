package com.avancial.app.service.comparePlanTransport;

import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.ComparePlanTransportDelete;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.ComparePlanTransportNew;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.ComparePlanTransportOther;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.IChaineComparePlanTransport;

public class ComparePlanTransport extends AComparePlanTransport {

    @Override
    protected void initChaineComparePlanTransport() {
        IChaineComparePlanTransport chaineComparePlanTransportNew = new ComparePlanTransportNew();
        IChaineComparePlanTransport chaineComparePlanTransportDelete = new ComparePlanTransportDelete();
        IChaineComparePlanTransport chaineComparePlanTransportOther = new ComparePlanTransportOther();

        chaineComparePlanTransportNew.setSuccesseur(chaineComparePlanTransportDelete);
        chaineComparePlanTransportDelete.setSuccesseur(chaineComparePlanTransportOther);
        
        this.chaineComparePlanTransport = chaineComparePlanTransportNew;
    }

}
