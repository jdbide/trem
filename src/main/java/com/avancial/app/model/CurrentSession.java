package com.avancial.app.model;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;

/**
 * Utilisé pour stocker des objets pendant la session
 * 
 * @author jeandaniel.bide
 * 
 */

@SessionScoped
public class CurrentSession implements Serializable   {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
