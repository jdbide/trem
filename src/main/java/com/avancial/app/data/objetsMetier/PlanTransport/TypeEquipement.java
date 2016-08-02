package com.avancial.app.data.objetsMetier.PlanTransport;

public class TypeEquipement {

    private String typeEquipement;

    private Regime regime;

    @Override
    public boolean equals(Object obj) {
        TypeEquipement typeEquipement = (TypeEquipement) obj;
        return this.getTypeEquipement().equals(typeEquipement.getTypeEquipement());
    }

    public String getTypeEquipement() {
        return this.typeEquipement;
    }

    public void setTypeEquipement(String typeEquipement) {
        this.typeEquipement = typeEquipement;
    }

    public Regime getRegime() {
        return this.regime;
    }

    public void setRegime(Regime regime) {
        this.regime = regime;
    }

}
