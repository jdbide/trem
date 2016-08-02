package com.avancial.app.service.comparePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public class TrainTranche {

    private String numeroTrain;

    private Tranche tranche;

    public TrainTranche() {
        // TODO Auto-generated constructor stub
    }

    public TrainTranche(String numeroTrain, Tranche tranche) {
        super();
        this.numeroTrain = numeroTrain;
        this.tranche = tranche;
    }

    @Override
    public boolean equals(Object obj) {
        TrainTranche trainTranche = (TrainTranche) obj;
        return this.getNumeroTrain().equals(trainTranche.getNumeroTrain())
                && this.getTranche().equals(trainTranche.getTranche());
    }

    public String getNumeroTrain() {
        return this.numeroTrain;
    }

    public void setNumeroTrain(String numeroTrain) {
        this.numeroTrain = numeroTrain;
    }

    public Tranche getTranche() {
        return this.tranche;
    }

    public void setTranche(Tranche tranche) {
        this.tranche = tranche;
    }
}
