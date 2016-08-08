package com.avancial.app.service.comparePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.ComparePlanTransportDelete;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.ComparePlanTransportNew;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.ComparePlanTransportOther;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.IChaineComparePlanTransport;

/**
 * Création de la chaîne de responsabilité pour comparer deux
 * {@link PlanTransport}
 * 
 * @author heloise.guillemaud
 *
 */
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
