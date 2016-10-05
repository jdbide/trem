/**
 * 
 */
package com.avancial.app.utilitaire;

import java.util.HashMap;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.EStatus;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;

/**
 * @author mikael.muller
 *
 */
public class MapPlansDeTransport extends HashMap<EStatus, JeuDonneesPlanTransport> {

    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    public MapPlansDeTransport() {
        this.put(EStatus.ACTIVE, new JeuDonneesPlanTransport());
        this.put(EStatus.DRAFT, new JeuDonneesPlanTransport());
    }

    public void setPlanTransportActive(JeuDonneeEntity jdActive, PlanTransport ptActive) {
        this.put(EStatus.ACTIVE, new JeuDonneesPlanTransport(jdActive, ptActive));
    }

    public void setPlanTransportDraft(JeuDonneeEntity jdDraft, PlanTransport ptDraft) {
        this.put(EStatus.DRAFT, new JeuDonneesPlanTransport(jdDraft, ptDraft));
    }

    public void setPlanTransportActive(JeuDonneesPlanTransport jdptActive) {
        this.put(EStatus.ACTIVE, jdptActive);
    }
    
    public void setPlanTransportDraft(JeuDonneesPlanTransport jdptDraft) {
        this.put(EStatus.DRAFT, jdptDraft);
    }
    
    public void setMapPlansDeTransport(JeuDonneeEntity jdDraft, PlanTransport ptDraft, JeuDonneeEntity jdActive,
            PlanTransport ptActive) {
        this.setPlanTransportActive(jdActive, ptActive);
        this.setPlanTransportDraft(jdDraft, ptDraft);
    }

    public PlanTransport getPlanTransportActive() {
        return this.get(EStatus.ACTIVE).getPlanTransport();
    }

    public PlanTransport getPlanTransportDraft() {
        return this.get(EStatus.DRAFT).getPlanTransport();
    }

    public JeuDonneeEntity getJeuDonneesActive() {
        return this.get(EStatus.ACTIVE).getJeuDonneeEntity();
    }

    public JeuDonneeEntity getJeuDonneesDraft() {
        return this.get(EStatus.DRAFT).getJeuDonneeEntity();
    }
}
