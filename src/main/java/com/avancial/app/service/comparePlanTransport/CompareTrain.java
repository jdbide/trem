package com.avancial.app.service.comparePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.CompareTrainDelete;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.CompareTrainModify;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.CompareTrainNew;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.CompareTrainOther;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.CompareTrainRegimesplit;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.IChaineComparePlanTransport;

/**
 * Création de la chaîne de responsabilité pour comparer deux {@link Train}
 * 
 * @author heloise.guillemaud
 *
 */
public class CompareTrain extends AComparePlanTransport {

    @Override
    protected void initChaineComparePlanTransport() {
        IChaineComparePlanTransport chaineCompareTrainRegimeSplit = new CompareTrainRegimesplit();
        IChaineComparePlanTransport chaineCompareTrainModify = new CompareTrainModify();
        IChaineComparePlanTransport chaineCompareTrainNew = new CompareTrainNew();
        IChaineComparePlanTransport chaineCompareTrainDelete = new CompareTrainDelete();
        IChaineComparePlanTransport chaineCompareTrainOther = new CompareTrainOther();

        chaineCompareTrainRegimeSplit.setSuccesseur(chaineCompareTrainModify);
        chaineCompareTrainModify.setSuccesseur(chaineCompareTrainNew);
        chaineCompareTrainNew.setSuccesseur(chaineCompareTrainDelete);
        chaineCompareTrainDelete.setSuccesseur(chaineCompareTrainOther);

        this.chaineComparePlanTransport = chaineCompareTrainRegimeSplit;
    }

}
