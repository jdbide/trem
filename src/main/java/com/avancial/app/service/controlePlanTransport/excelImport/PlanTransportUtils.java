package com.avancial.app.service.controlePlanTransport.excelImport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

/**
 * classe utilitaire pour la manipulation des plans de transport en vue de leur contrôle.
 * @author raphael.cabaret
 */
public class PlanTransportUtils {
	
	/**
	 * fusionne deux plans de transports importés.
	 * aucun contrôle de cohérance des données n'est fait, les tranches sont justes regroupées par train.
	 * @param plan1 plan de transport à fusionner.
	 * @param plan2 plan de transport à fusionner.
	 * @return un plan de transport contenant les tranches des plans précédents regroupés par trains.
	 */
	public static PlanTransport merge(PlanTransport plan1, PlanTransport plan2) {
		EnumCompagnies compagnie = null;
		if(plan1.getCompagnie() != null && plan2.getCompagnie() != null) {
			// vérification de la concordance des compagnies.
			if(!plan1.getCompagnie().equals(plan2.getCompagnie())) {
				throw new IllegalArgumentException("la compagnie des deux plans de transport n'est pas la même");
			}
		// atribution de la compagnie
			compagnie = plan1.getCompagnie();
		} else {
			if(plan1.getCompagnie() != null) {
				compagnie = plan1.getCompagnie();
			} else {
				compagnie = plan2.getCompagnie();
			}
		}
		// regroupement des tranches
		PlanTransport plan = new PlanTransport(compagnie, new ArrayList<Train>());
		Map<String, Train> trains = new HashMap<String, Train>();
		PlanTransportUtils.putTranches(trains, plan1);
		PlanTransportUtils.putTranches(trains, plan2);
		// intégration au nouveau plan de transport
		plan.getTrains().addAll(trains.values());
		return plan;
	}
	
	/**
	 * ajoute les tranches des trains à ceux de la map.
	 * @param trains la map des trains où intégrer les tranches, indexés par numéros.
	 * @param plan le plan de transport d'où extraire les tranches.
	 */
	private static void putTranches(Map<String, Train> trains, PlanTransport plan) {
		for(Train train : plan.getTrains()) {
			if(!trains.containsKey(train.getNumeroTrain())) {
				trains.put(train.getNumeroTrain(), new Train(new ArrayList<Tranche>(), train.getNumeroTrain(), train.isValidForRR()));
			}
			trains.get(train.getNumeroTrain()).getTranches().addAll(train.getTranches());
		}
	}
	
}
