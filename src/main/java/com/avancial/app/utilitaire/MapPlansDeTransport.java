/**
 * 
 */
package com.avancial.app.utilitaire;

import java.util.HashMap;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.Status;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;

/**
 * @author mikael.muller
 *
 */
public class MapPlansDeTransport extends HashMap<Status, JeuDonneesPlanTransport> {

    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    public MapPlansDeTransport() {
        this.put(Status.ACTIVE, new JeuDonneesPlanTransport());
        this.put(Status.DRAFT, new JeuDonneesPlanTransport());
    }

    public void setPlanTransportActive(JeuDonneeEntity jdActive, PlanTransport ptActive) {
        this.put(Status.ACTIVE, new JeuDonneesPlanTransport(jdActive, ptActive));
    }

    public void setPlanTransportDraft(JeuDonneeEntity jdDraft, PlanTransport ptDraft) {
        this.put(Status.DRAFT, new JeuDonneesPlanTransport(jdDraft, ptDraft));
    }

    public void setPlanTransportActive(JeuDonneesPlanTransport jdptActive) {
        this.put(Status.ACTIVE, jdptActive);
    }
    
    public void setPlanTransportDraft(JeuDonneesPlanTransport jdptDraft) {
        this.put(Status.DRAFT, jdptDraft);
    }
    
    public void setMapPlansDeTransport(JeuDonneeEntity jdDraft, PlanTransport ptDraft, JeuDonneeEntity jdActive,
            PlanTransport ptActive) {
        this.setPlanTransportActive(jdActive, ptActive);
        this.setPlanTransportDraft(jdDraft, ptDraft);
    }

    public PlanTransport getPlanTransportActive() {
        return this.get(Status.ACTIVE).getPlanTransport();
    }

    public PlanTransport getPlanTransportDraft() {
        return this.get(Status.DRAFT).getPlanTransport();
    }

    public JeuDonneeEntity getJeuDonneesActive() {
        return this.get(Status.ACTIVE).getJeuDonneeEntity();
    }

    public JeuDonneeEntity getJeuDonneesDraft() {
        return this.get(Status.DRAFT).getJeuDonneeEntity();
    }
}
