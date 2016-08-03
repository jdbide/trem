package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.List;

public class ComparaisonPlanTransport<T extends IPlanTransportComparable> implements IComparaisonPlanTransport {

    private String numeroTrain;

    private String numeroTranche;

    private List<T> ancienFields;

    private List<T> nouveauFields;

    private EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport;

    public String getNumeroTrain() {
        return this.numeroTrain;
    }

    public void setNumeroTrain(String numeroTrain) {
        this.numeroTrain = numeroTrain;
    }

    public String getNumeroTranche() {
        return this.numeroTranche;
    }

    public void setNumeroTranche(String numeroTranche) {
        this.numeroTranche = numeroTranche;
    }

    public List<T> getAncienFields() {
        return this.ancienFields;
    }

    public void setAncienFields(List<T> ancienFields) {
        this.ancienFields = ancienFields;
    }

    public List<T> getNouveauFields() {
        return this.nouveauFields;
    }

    public void setNouveauFields(List<T> nouveauFields) {
        this.nouveauFields = nouveauFields;
    }

    public EnumTypeComparaisonPlanTransport getTypeComparaisonPlanTransport() {
        return this.typeComparaisonPlanTransport;
    }

    public void setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport) {
        this.typeComparaisonPlanTransport = typeComparaisonPlanTransport;
    }

}
