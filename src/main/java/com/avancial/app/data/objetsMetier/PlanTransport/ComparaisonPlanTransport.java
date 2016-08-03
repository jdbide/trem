package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.ArrayList;
import java.util.List;
import com.avancial.socle.utils.ListUtils;

public class ComparaisonPlanTransport<T extends IPlanTransportComparable> implements IComparaisonPlanTransport {

    private String numeroTrain;

    private String numeroTranche;

    private List<T> ancienFields;

    private List<T> nouveauFields;

    private EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport;

    public ComparaisonPlanTransport() {
        this.numeroTrain = "";
        this.numeroTranche = "";
        this.ancienFields = new ArrayList<>();
        this.nouveauFields = new ArrayList<>();
        this.typeComparaisonPlanTransport = EnumTypeComparaisonPlanTransport.UNCHANGED;
    }

    @Override
    public boolean equals(Object obj) {
        ComparaisonPlanTransport<T> comparaison = (ComparaisonPlanTransport<T>) obj;
        return this.numeroTrain.equals(comparaison.getNumeroTrain())
                && this.numeroTranche.equals(comparaison.getNumeroTranche())
                && ListUtils.compareLists(this.ancienFields, comparaison.getAncienFields())
                && ListUtils.compareLists(this.nouveauFields, comparaison.getNouveauFields());
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
