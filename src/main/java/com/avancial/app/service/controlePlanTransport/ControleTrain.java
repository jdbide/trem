package com.avancial.app.service.controlePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.service.comparePlanTransport.AComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.IChaineComparePlanTransport;
import com.avancial.app.service.controlePlanTransport.chaineResponsabilite.CompareTrainControl;

/**
 * Création de la chaîne de responsabilité pour comparer deux {@link Train}
 * 
 * @author heloise.guillemaud
 *
 */
public class ControleTrain extends AComparePlanTransport {

    @Override
    protected void initChaineComparePlanTransport() {
        IChaineComparePlanTransport chaineCompareTrainOther = new CompareTrainControl();

        this.chaineComparePlanTransport = chaineCompareTrainOther;
    }

}
