package com.avancial.app.service.traiteDeleteRegime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * List servant a delete les données dans chaque type de regime
 * @author gabriel.gagnier
 *
 */
public class TraiteDeleteDonneesRegimeFactory implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private List<ITraiteDeleteDonnees> donneesRegime = new ArrayList<>();

    /**
     * 
     */
    public TraiteDeleteDonneesRegimeFactory() {
        this.donneesRegime.add(new TraiteDeleteRegimeComposition());
        this.donneesRegime.add(new TraiteDeleteRegimeDistribution());
        this.donneesRegime.add(new TraiteDeleteRegimeEqpType());
        this.donneesRegime.add(new TraiteDeleteRegimeFareProfile());
        this.donneesRegime.add(new TraiteDeleteRegimeMealType());
        this.donneesRegime.add(new TraiteDeleteRegimeRestriction());
        this.donneesRegime.add(new TraiteDeleteRegimeSatcode());
        this.donneesRegime.add(new TraiteDeleteRegimeService());
        this.donneesRegime.add(new TraiteDeleteRegimeSpecificity());
        this.donneesRegime.add(new TraiteDeleteRegimeStop());
        this.donneesRegime.add(new TraiteDeleteRegimeOD());
        this.donneesRegime.add(new TraiteDeleteRegimeTrainTranche());
        this.donneesRegime.add(new TraiteDeleteRegimeTosp());
    }

    /**
     * @return the donneesRegime
     */
    public List<ITraiteDeleteDonnees> getDonneesRegime() {
        return this.donneesRegime;
    }

    /**
     * @param donneesRegime the donneesRegime to set
     */
    public void setDonneesRegime(List<ITraiteDeleteDonnees> donneesRegime) {
        this.donneesRegime = donneesRegime;
    }


}
