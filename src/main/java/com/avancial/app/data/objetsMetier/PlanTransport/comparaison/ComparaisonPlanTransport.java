package com.avancial.app.data.objetsMetier.PlanTransport.comparaison;

import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;

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
                && this.fieldEquals(this.ancienField, comparaison.getAncienField())
                && this.fieldEquals(this.nouveauField, comparaison.getNouveauField());
    }

    /**
     * Retourne le résultat de la comparaison de deux attributs de comparaison:
     * <ul>
     * <li>soit les deux sont nuls, et donc égaux</li>
     * <li>soit les deux sont non nuls, alors on vérifie qu'ils sont de la même classe et qu'ils sont égaux</li>
     * <li>soit seulement un des deux est nul, alors ils ne sont pas égaux</li>
     * </ul>
     * @param field1
     * @param field2
     * @return
     */
    private boolean fieldEquals(T field1, T field2) {
        return (field1 == null && field2 == null) || (!(field1 == null || field2 == null)
                && field1.getClass().equals(field2.getClass()) && field1.equals(field2));
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
