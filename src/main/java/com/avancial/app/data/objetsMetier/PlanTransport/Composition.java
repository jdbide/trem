package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.List;

public class Composition {

    private String codeClasse;

    private String codeDiag;

    private String codeRame;

    private String codeRm;

    private List<Voiture> voitures;

    private Regime regime;

    public String getCodeClasse() {
        return this.codeClasse;
    }

    public void setCodeClasse(String codeClasse) {
        this.codeClasse = codeClasse;
    }

    public String getCodeDiag() {
        return this.codeDiag;
    }

    public void setCodeDiag(String codeDiag) {
        this.codeDiag = codeDiag;
    }

    public String getCodeRame() {
        return this.codeRame;
    }

    public void setCodeRame(String codeRame) {
        this.codeRame = codeRame;
    }

    public String getCodeRm() {
        return this.codeRm;
    }

    public void setCodeRm(String codeRm) {
        this.codeRm = codeRm;
    }

    public List<Voiture> getVoitures() {
        return this.voitures;
    }

    public void setVoitures(List<Voiture> voitures) {
        this.voitures = voitures;
    }

    public Regime getRegime() {
        return this.regime;
    }

    public void setRegime(Regime regime) {
        this.regime = regime;
    }

}
