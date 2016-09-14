package com.avancial.app.utilitaire;

import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;

public class JeuDonneesPlanTransport {
    private JeuDonneeEntity jeuDonneeEntity;
    private PlanTransport planTransport;

    public JeuDonneesPlanTransport() {
        this.jeuDonneeEntity = new JeuDonneeEntity();
        this.planTransport = new PlanTransport();
    }

    public JeuDonneesPlanTransport(JeuDonneeEntity jeuDonneeEntity, PlanTransport planTransport) {
        super();
        this.jeuDonneeEntity = jeuDonneeEntity;
        this.planTransport = planTransport;
    }

    public JeuDonneeEntity getJeuDonneeEntity() {
        return this.jeuDonneeEntity;
    }

    public void setJeuDonneeEntity(JeuDonneeEntity jeuDonneeEntity) {
        this.jeuDonneeEntity = jeuDonneeEntity;
    }

    public PlanTransport getPlanTransport() {
        return this.planTransport;
    }

    public void setPlanTransport(PlanTransport planTransport) {
        this.planTransport = planTransport;
    }
}
