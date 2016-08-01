package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.List;

public class Train {

    private List<Tranche> tranches;
    private String numeroTrain;
    private boolean validePourRR;
    /**
     * @param tranches
     * @param numeroTrain
     * @param validForRR
     */
    public Train(List<Tranche> tranches, String numeroTrain, boolean validForRR) {
        super();
        this.tranches = tranches;
        this.numeroTrain = numeroTrain;
        this.validePourRR = validForRR;
    }
    /**
     * @return the tranches
     */
    public List<Tranche> getTranches() {
        return this.tranches;
    }
    /**
     * @param tranches the tranches to set
     */
    public void setTranches(List<Tranche> tranches) {
        this.tranches = tranches;
    }
    /**
     * @return the validForRR
     */
    public boolean isValidForRR() {
        return this.validePourRR;
    }
    /**
     * @param validForRR the validForRR to set
     */
    public void setValidForRR(boolean validForRR) {
        this.validePourRR = validForRR;
    }
    /**
     * @return the numeroTrain
     */
    public String getNumeroTrain() {
        return this.numeroTrain;
    }
    /**
     * @param numeroTrain the numeroTrain to set
     */
    public void setNumeroTrain(String numeroTrain) {
        this.numeroTrain = numeroTrain;
    }
    
    
    
}
