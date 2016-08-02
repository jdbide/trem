package com.avancial.app.data.objetsMetier.PlanTransport;

public class Gare {

    private String codeGare;

    @Override
    public boolean equals(Object obj) {
        Gare gare = (Gare) obj;
        if (gare.getCodeGare().equals(this.codeGare)) {
            return true;
        }
        return false;
    }

    public String getCodeGare() {
        return this.codeGare;
    }

    public void setCodeGare(String codeGare) {
        this.codeGare = codeGare;
    }
}
