package com.avancial.app.data.objetsMetier.PlanTransport;

public class TypeEquipement extends ARegimeComparable {

    private String typeEquipement;

    private Regime regime;

    public TypeEquipement() {
        // TODO Auto-generated constructor stub
    }

    public TypeEquipement(String typeEquipement, Regime regime) {
        super();
        this.typeEquipement = typeEquipement;
        this.regime = regime;
    }

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
