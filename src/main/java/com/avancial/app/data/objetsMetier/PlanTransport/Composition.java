package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.ArrayList;
import java.util.List;
import com.avancial.socle.utils.ListUtils;

public class Composition extends ARegimeComparable {

    private String codeClasse;

    private String codeDiag;

    private String codeRame;

    private String codeRm;

    private List<Voiture> voitures;

    private Regime regime;

    public Composition() {
        this.codeClasse = "";
        this.codeDiag = "";
        this.codeRame = "";
        this.codeRm = "";
    }

    public Composition clone() {
        Composition res = new Composition();
        List<Voiture> resVoitures = new ArrayList<>();
        res.setCodeClasse(this.codeClasse);
        res.setCodeDiag(this.codeDiag);
        res.setCodeRame(this.codeRame);
        res.setCodeRm(this.codeRm);
        for (Voiture voiture : this.voitures) {
            resVoitures.add(voiture.clone());
        }
        res.setVoitures(resVoitures);
        return res;

    }

    public Composition(String codeClasse, String codeDiag, String codeRame, String codeRm, List<Voiture> voitures,
            Regime regime) {
        super();
        this.codeClasse = codeClasse;
        this.codeDiag = codeDiag;
        this.codeRame = codeRame;
        this.codeRm = codeRm;
        this.voitures = voitures;
        this.regime = regime;
    }

    @Override
    public boolean equals(Object obj) {
        Composition composition = (Composition) obj;
        if (composition.getCodeClasse().equals(this.codeClasse) && composition.getCodeDiag().equals(this.codeDiag)
                && composition.getCodeRame().equals(this.codeRame) && composition.getCodeRm().equals(this.codeRm)
                && ListUtils.compareLists(composition.getVoitures(), this.voitures)) {
            return true;
        }
        return false;
    }

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
