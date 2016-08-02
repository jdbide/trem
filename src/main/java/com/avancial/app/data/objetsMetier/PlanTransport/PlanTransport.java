package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.List;

public class PlanTransport {

    private EnumCompagnies compagnie;
    private List<Train> trains;

    /**
     * @param compagnie
     * @param trains
     */
    public PlanTransport(EnumCompagnies compagnie, List<Train> trains) {
        super();
        this.compagnie = compagnie;
        this.trains = trains;
    }

    public PlanTransport() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the compagnie
     */
    public EnumCompagnies getCompagnie() {
        return this.compagnie;
    }

    /**
     * @param compagnie
     *            the compagnie to set
     */
    public void setCompagnie(EnumCompagnies compagnie) {
        this.compagnie = compagnie;
    }

    /**
     * @return the trains
     */
    public List<Train> getTrains() {
        return this.trains;
    }

    /**
     * @param trains
     *            the trains to set
     */
    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

}
