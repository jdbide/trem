package com.avancial.app.data.objetsMetier.PlanTransport;

public class ComparaisonPlanTransport<T extends IPlanTransport> implements IComparaisonPlanTransport {

    private String numeroTrain;

    private String numeroTranche;

    private T ancienField;

    private T nouveauField;

    private EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport;

    public ComparaisonPlanTransport() {
        this.numeroTrain = "";
        this.numeroTranche = "";
        this.ancienField = null;
        this.nouveauField = null;
        this.typeComparaisonPlanTransport = EnumTypeComparaisonPlanTransport.UNCHANGED;
    }

    @Override
    public boolean equals(Object obj) {
        ComparaisonPlanTransport<T> comparaison = (ComparaisonPlanTransport<T>) obj;
        return this.numeroTrain.equals(comparaison.getNumeroTrain())
                && this.numeroTranche.equals(comparaison.getNumeroTranche())
                && this.ancienField.getClass().equals(comparaison.getAncienField().getClass())
                && this.ancienField.equals(comparaison.getAncienField())
                && this.nouveauField.getClass().equals(comparaison.getNouveauField().getClass())
                && this.nouveauField.equals(comparaison.getNouveauField());
    }

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

    public T getAncienField() {
        return this.ancienField;
    }

    public void setAncienField(T ancienField) {
        this.ancienField = ancienField;
    }

    public T getNouveauField() {
        return this.nouveauField;
    }

    public void setNouveauField(T nouveauField) {
        this.nouveauField = nouveauField;
    }

    public EnumTypeComparaisonPlanTransport getTypeComparaisonPlanTransport() {
        return this.typeComparaisonPlanTransport;
    }

    public void setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport) {
        this.typeComparaisonPlanTransport = typeComparaisonPlanTransport;
    }

}
