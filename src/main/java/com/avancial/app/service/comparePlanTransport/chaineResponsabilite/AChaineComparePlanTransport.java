package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

public abstract class AChaineComparePlanTransport implements IChaineComparePlanTransport {

    protected IChaineComparePlanTransport successeur;

    @Override
    public void setSuccesseur(IChaineComparePlanTransport chaineComparePlanTransport) {
        this.successeur = chaineComparePlanTransport;
    }

}
