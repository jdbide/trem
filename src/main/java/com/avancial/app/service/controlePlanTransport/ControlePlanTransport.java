package com.avancial.app.service.controlePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.service.comparePlanTransport.AComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.ComparePlanTransportControl;

/**
 * Création de la chaîne de responsabilité pour comparer deux
 * {@link PlanTransport}
 * 
 * @author heloise.guillemaud
 *
 */
public class ControlePlanTransport extends AComparePlanTransport {

    @Override
    protected void initChaineComparePlanTransport() {
       ComparePlanTransportControl chaineComparePlanTransportControl = new ComparePlanTransportControl();

       this.chaineComparePlanTransport = chaineComparePlanTransportControl;
    }

}
