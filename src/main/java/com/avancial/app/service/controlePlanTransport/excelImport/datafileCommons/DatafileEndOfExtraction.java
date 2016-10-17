package com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTrancheStatut;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

/**
 * étape de finalisation de l'extraction.
 * @author raphael.cabaret
 */
public class DatafileEndOfExtraction extends ADatafileConditionalStep<DatafileContext> {

	@Override protected void withoutParsingError(DatafileContext context) {}

	@Override protected void always(DatafileContext context) {}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void withoutValidationAndParsingError(DatafileContext context) {
		Map<Integer, Tranche> opens = new HashMap<Integer, Tranche>();
		Map<Integer, Tranche> closes = new HashMap<Integer, Tranche>();
		// construction des tranches
		for(DatafileLineContext line : context.getLines()) {
			Tranche tranche = getTrancheForLine(closes, opens, line);
			// ajout des sous régimes
			for(ASousRegimeTranche sousRegime : line.getSousRegimes()) {
				@SuppressWarnings("unchecked")
				List<ASousRegimeTranche> list = (List<ASousRegimeTranche>) tranche.getAttributs().get(sousRegime.getClass());
				if(list == null) {
					list = new ArrayList<ASousRegimeTranche>();
					list.add(sousRegime);
					tranche.addAttributsField(list);
				} else {
					list.add(sousRegime);
				}
			}
		}
		// insertion des tranches dans les trains
		@SuppressWarnings("unchecked")
		Collection<Train> trains = this.buildTrainList(opens, closes);
		// ajouts des trains
		context.getProduct().getTrains().addAll(trains);
	}

	/**
	 * construit une liste de trains contenant toutes les tranches qui leur sont associées.
	 * @param trancheMaps les maps des tranches associées à leurs numéros de train.
	 * @return la liste des trains.
	 */
	@SuppressWarnings("unchecked")
	private Collection<Train> buildTrainList(Map<Integer, Tranche>... trancheMaps) {
		DecimalFormat df = new DecimalFormat("000000");
		Map<Integer, Train> trains = new HashMap<Integer, Train>();
		for(Map<Integer, Tranche> tranches : trancheMaps) {
			for(Entry<Integer, Tranche> tranche : tranches.entrySet()) {
				// pour chaque tranche
				Train train = trains.get(tranche.getKey());
				if(train == null) {
					// création du train si il n'existe pas
					train = new Train(new ArrayList<Tranche>(), df.format(tranche.getKey()), true);
					trains.put(tranche.getKey(), train);
				}
				train.getTranches().add(tranche.getValue());
			}
		}
		return trains.values();
	}
	
	/**
	 * récupère une tranche pour y mettre les sous régimes de la ligne.
	 * @param opens la map contenant les tranches ouvertes.
	 * @param closes la map contenant les tranches fermées.
	 * @param line la ligne.
	 * @return la tranche.
	 */
	private Tranche getTrancheForLine(Map<Integer, Tranche> opens, Map<Integer, Tranche> closes, DatafileLineContext line) {
		// identifie la map
		Map<Integer, Tranche> map;
		if(line.getStatut().equals(EnumTrancheStatut.Ouvert)) {
			map = opens;
		} else {
			map = closes;
		}
		// récupère le tranche
		Tranche tranche;
		tranche = map.get(line.getTrainId());
		// crée une nouvelle tranche au besoin
		if(tranche == null) {
			tranche = new Tranche();
			tranche.setTrancheStatut(line.getStatut());
			map.put(line.getTrainId(), tranche);
		}
		return tranche;
	}

}
