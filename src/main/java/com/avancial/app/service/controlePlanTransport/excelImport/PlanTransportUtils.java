package com.avancial.app.service.controlePlanTransport.excelImport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Desserte;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;
import com.avancial.app.data.objetsMetier.PlanTransport.OrigineDestination;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

/**
 * classe utilitaire pour la manipulation des plans de transport en vue de leur contrôle.
 * @author raphael.cabaret
 */
public class PlanTransportUtils {
	
	/**
	 * constructeur bloqué.
	 */
	private PlanTransportUtils() {
		
	}
	
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
	
	/**
	 * récupère la liste des origines-destination distinctes contenues dans le plan de transport.
	 * @param plan le plan de transport.
	 * @return les couples origine-destination.
	 */
	public static List<OrigineDestination> extractOriginesDestinations(PlanTransport plan) {
		List<OrigineDestination> odList = new ArrayList<OrigineDestination>();
		//parcour du plan de transport
		for(Train train : plan.getTrains()) {
			for(Tranche tranche : train.getTranches()) {
				@SuppressWarnings("unchecked")
				List<Desserte> dessertes = (List<Desserte>) tranche.getAttributs().get(Desserte.class);
				if(dessertes != null) {
					for(Desserte desserte : dessertes) {
						//ajout de l'OD il n'y est pas déjà
						if(desserte.getGareHoraires() != null && desserte.getGareHoraires().size() > 1) {
							OrigineDestination od = new OrigineDestination(
									desserte.getGareHoraires().get(0).getGare(),
									desserte.getGareHoraires().get(desserte.getGareHoraires().size() - 1).getGare(),
									null);
							if(!odList.contains(od))
								odList.add(od);
						}
					}
				}
			}
		}
		return odList;
	}
	
	/**
	 * extrai l'interval de dates dans lequel se trouve inclus tout le plan de transport.
	 * @param plan le plan de transport.
	 * @return un régime sans liste de dates mais avec un interval.
	 */
	public static Regime extractDateInterval(PlanTransport plan) {
		Regime interval = new Regime("", null, null, new ArrayList<Date>());
		// sur tout les sous régimes du plan de transport
		for(Train train : plan.getTrains()) {
			for(Tranche tranche : train.getTranches()) {
				for(List<? extends ASousRegimeTranche> sousRegimes : tranche.getAttributs().values()) {
					for(ASousRegimeTranche sousRegime : sousRegimes) {
						//récupère les dates la première fois
						if(interval.getDateDebut() == null) {
							interval.setDateDebut(sousRegime.getRegime().getDateDebut());
							interval.setDateFin(sousRegime.getRegime().getDateFin());
						} else {
							//récupères les bornes si besoin
							if(isDayBefore(sousRegime.getRegime().getDateDebut(), interval.getDateDebut())) {
								interval.setDateDebut(sousRegime.getRegime().getDateDebut());
							}
							if(isDayBefore(interval.getDateFin(), sousRegime.getRegime().getDateFin())) {
								interval.setDateFin(sousRegime.getRegime().getDateFin());
							}
						}
					}
				}
			}
		}
		return interval;
	}
	
	/**
	 * indique si le jour représenté par la première date est situé avant ou est le même que le second jour.
	 * @param first le premier jour.
	 * @param second le second jour.
	 * @return true si le premier jour est avant ou le même que le second, false si il est après.
	 */
	public static boolean isDayBeforeOrEquals(Calendar first, Calendar second) {
		if(first.get(Calendar.YEAR) < second.get(Calendar.YEAR)) {
			return true;
		} else if(first.get(Calendar.YEAR) > second.get(Calendar.YEAR)) {
			return false;
		} else {
			if(first.get(Calendar.DAY_OF_YEAR) > second.get(Calendar.DAY_OF_YEAR)) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	/**
	 * indique si le jour représenté par la première date est situé avant ou est le même que le second jour.
	 * @param first le premier jour.
	 * @param second le second jour.
	 * @return true si le premier jour est avant ou le même que le second, false si il est après.
	 */
	public static boolean isDayBeforeOrEquals(Date first, Date second) {
		Calendar firstCal = new GregorianCalendar();
		firstCal.setTime(first);
		Calendar secondCal = new GregorianCalendar();
		secondCal.setTime(second);
		return isDayBeforeOrEquals(firstCal, secondCal);
	}
	
	/**
	 * indique si le jour représenté par la première date est situé avant le second jour.
	 * @param first le premier jour.
	 * @param second le second jour.
	 * @return true si le premier jour est avant le second, false si il est après ou égale.
	 */
	public static boolean isDayBefore(Calendar first, Calendar second) {
		if(first.get(Calendar.YEAR) < second.get(Calendar.YEAR)) {
			return true;
		} else if(first.get(Calendar.YEAR) > second.get(Calendar.YEAR)) {
			return false;
		} else {
			if(first.get(Calendar.DAY_OF_YEAR) >= second.get(Calendar.DAY_OF_YEAR)) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	/**
	 * indique si le jour représenté par la première date est situé avant le second jour.
	 * @param first le premier jour.
	 * @param second le second jour.
	 * @return true si le premier jour est avant le second, false si il est après ou égal.
	 */
	public static boolean isDayBefore(Date first, Date second) {
		Calendar firstCal = new GregorianCalendar();
		firstCal.setTime(first);
		Calendar secondCal = new GregorianCalendar();
		secondCal.setTime(second);
		return isDayBeforeOrEquals(firstCal, secondCal);
	}
}
