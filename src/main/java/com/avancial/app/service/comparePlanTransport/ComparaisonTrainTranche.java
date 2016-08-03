package com.avancial.app.service.comparePlanTransport;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransportComparable;

/**
 * Objet comprenant pour un train-tranche présent dans deux plans de transport
 * (ancien et nouveau) deux listes de {@link IRegimeComparable} de même classe,
 * l'une avec les valeurs dans l'ancien plan de transport, et l'autre avec les
 * valeurs correspondantes dans le nouveau plan de transport.
 * 
 * @author heloise.guillemaud
 *
 */
public class ComparaisonTrainTranche<T extends IPlanTransportComparable> {

    private String numeroTrain;

    private String numeroTranche;

    private List<T> ancienFields;

    private List<T> nouveauFields;

    public ComparaisonTrainTranche() {
        // TODO Auto-generated constructor stub
    }

    public ComparaisonTrainTranche(String numeroTrain, String numeroTranche, List<T> ancienFields,
            List<T> nouveauFields) {
        super();
        this.numeroTrain = numeroTrain;
        this.numeroTranche = numeroTranche;
        this.ancienFields = ancienFields;
        this.nouveauFields = nouveauFields;
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

}
