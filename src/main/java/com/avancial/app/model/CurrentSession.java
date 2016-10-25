package com.avancial.app.model;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.socle.model.managedbean.SocleMenuManagedBean;

/**
 * Managed Bean de gestion de la page liste utilisateurs public
 * 
 * @author guillaume.bouziou
 * 
 */

@SessionScoped
public class CurrentSession  {

	@Inject
	private PlanTransport planDeTransportTimeTable;

	@Inject
	private PlanTransport planDeTransportYield;

	@Inject
	private PlanTransport planDeTransportMerge;

	/**
	 * Constructeur
	 */

	public CurrentSession() {
		// TODO Auto-generated constructor stub
	}

	public PlanTransport getPlanDeTransportTimeTable() {
		return planDeTransportTimeTable;
	}

	public void setPlanDeTransportTimeTable(PlanTransport planDeTransportTimeTable) {
		this.planDeTransportTimeTable = planDeTransportTimeTable;
	}

	public PlanTransport getPlanDeTransportYield() {
		return planDeTransportYield;
	}

	public void setPlanDeTransportYield(PlanTransport planDeTransportYield) {
		this.planDeTransportYield = planDeTransportYield;
	}

	public PlanTransport getPlanDeTransportMerge() {
		return planDeTransportMerge;
	}

	public void setPlanDeTransportMerge(PlanTransport planDeTransportMerge) {
		this.planDeTransportMerge = planDeTransportMerge;
	}

}
