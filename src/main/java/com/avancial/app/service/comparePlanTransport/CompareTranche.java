package com.avancial.app.service.comparePlanTransport;

import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.CompareTrancheModify;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.CompareTrancheRegimesplit;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.CompareTrancheUnchanged;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.IChaineComparePlanTransport;

/**
 * Création de la chaîne de responsabilité pour comparer deux tranches.<br>
 * On commence par la comparaison UNCHANGED, qui va recevoir le résultat de
 * comparaison du maillon suivant de la chaîne.<br>
 * Les maillons suivants sont MODIFY et REGIMESPLIT; ainsi, si UNCHANGED ne
 * reçoit aucun résultat, cela veut dire que dans tous les tests de tous les
 * attributs de deux tranches, il n'y a eu aucun positif pour MODIFY ou
 * REGIMESPLIT. Cela veut bien dire qu'aucun attribut n'a été changé entre les
 * deux tranches.
 * 
 * @author heloise.guillemaud
 *
 */
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
