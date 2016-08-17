package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.List;

public class Tranche extends ASousRegimeTranche {
    private String numeroTranche;
    private EnumTrancheStatut trancheStatut;
    private Regime regime;

    /**
     * Map contenant tout les types de regime d'une tranche
     */
    private MapTranche attributs;

    public Tranche() {
        this.numeroTranche = "";
        this.trancheStatut = EnumTrancheStatut.Fermer;
        this.regime = new Regime();
        this.attributs = new MapTranche();
    }

    public Tranche clone() {
        Tranche tranche = new Tranche();
        tranche.setNumeroTranche(this.numeroTranche);
        tranche.setTrancheStatut(this.trancheStatut);
        if (this.attributs != null) {
           tranche.setAttributs(this.attributs.clone());
        } else {
           tranche.setAttributs(null);
        }     
        return tranche;
    }
    @Override
    public boolean equals(Object obj) {
       Tranche tranche = (Tranche) obj;
       return this.numeroTranche.equals(tranche.getNumeroTranche());
    }
    /**
     * 
     * @param key
     * @return getAttributs().get(key)
     */
    public List<? extends ASousRegimeTranche> getAttributsField(Class<?> key) {
        return this.attributs.get(key);
    }

    /**
     * attributs.put(value.class, List\<value\>)
     * 
     * @param value
     */
    public void addAttributsField(List<ASousRegimeTranche> value) {
        if (value.size() > 0) {
            this.attributs.put((Class<ASousRegimeTranche>) value.get(0).getClass(), value);
        }
    }

    /**
     * @return the numeroTranche
     */
    public String getNumeroTranche() {
        return this.numeroTranche;
    }

    /**
     * @param numeroTranche
     *            the numeroTranche to set
     */
    public void setNumeroTranche(String numeroTranche) {
        this.numeroTranche = numeroTranche;
    }

    /**
     * @return the trancheStatut
     */
    public EnumTrancheStatut getTrancheStatut() {
        return this.trancheStatut;
    }

    /**
     * @param trancheStatut
     *            the trancheStatut to set
     */
    public void setTrancheStatut(EnumTrancheStatut trancheStatut) {
        this.trancheStatut = trancheStatut;
    }

    /**
     * @return the values
     */
    public MapTranche getAttributs() {
        return this.attributs;
    }

    /**
     * @param values
     *            the values to set
     */
    public void setAttributs(MapTranche values) {
        this.attributs = values;
    }

    /**
     * @param regime
     *            the regime to set
     */
    public void setRegime(Regime regime) {
        this.regime = regime;
    }

    @Override
    public Regime getRegime() {
        return this.regime;
    }

}
