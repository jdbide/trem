package com.avancial.app.service.traiteMotriceRegime;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeSpecificityEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Compartiment;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumEtatSpecification;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Siege;
import com.avancial.app.data.objetsMetier.PlanTransport.Specification;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Voiture;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;

public class TraiteMotriceRegimeSpecificity implements ITraiteMotriceRegime {

	@Override
	public void traite(MotriceTrainTrancheEntity motriceTrainTrancheEntity,
			MapIdTablesMotriceRegime mapIdTablesMotriceRegime,
			MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager,
			AtomicReference<Tranche> atomicTranche) {
	   
	   Date debutPeriode = motriceTrainTrancheEntity.getJeuDonnee().getDateDebutPeriode();

		Query query = entityManager
				.createNativeQuery("SELECT voiture.VOIT_NUM_RESA AS coachNumberMotriceRegimeSpecificity, "
						+ "'' AS compartmentNumberMotriceRegimeSpecificity, "
						+ "LPAD( CONCAT( sppl.SPPL_PCDD_NUM_COMP, sppl.SPPL_PCDD_NUM_PLAC ), 3, '0' ) AS seatNumberMotriceRegimeSpecificity, "
						+ "sppl.SPPL_SPEC_COD AS stateCodeMotriceRegimeSpecificity, "
						+ "sppl.SPPL_REGI AS regime "
						+ "FROM tremas_import_tmdvoit AS voiture "
						+ "INNER JOIN tremas_import_tmdcath AS cara ON voiture.VOIT_TRCH_COD_CIE = cara.CATH_TRCH_COD_CIE "
						+ "AND voiture.VOIT_TRCH_NUM_TRA1 = cara.CATH_TRCH_NUM_TRA1 "
						+ "AND voiture.VOIT_TRCH_IND_FER = cara.CATH_TRCH_IND_FER "
						+ "AND voiture.VOIT_TRCH_NUM = cara.CATH_TRCH_NUM "
						+ "INNER JOIN tremas_import_tmdsppl AS sppl ON voiture.VOIT_NUM = sppl.SPPL_VOIT_NUM "
						+ "AND voiture.VOIT_TRCH_COD_CIE = sppl.SPPL_VOIT_COD_CIE "
						+ "AND voiture.VOIT_TRCH_NUM_TRA1 = sppl.SPPL_VOIT_NUM_TRA1 "
						+ "AND voiture.VOIT_TRCH_NUM = sppl.SPPL_VOIT_TRCH_NUM "
						+ "AND voiture.VOIT_TRCH_IND_FER = sppl.SPPL_VOIT_IND_FER " + "WHERE cara.CATH_SSIM = ? "
						+ "AND cara.CATH_TRCH_NUM_TRA1 = ? AND cara.CATH_ETAT_TRCH = ? "
						+ "AND sppl.SPPL_SPEC_COD = 'F' "
						+ "ORDER BY sppl.SPPL_REGI");
		query.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
		query.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());
		query.setParameter(3, motriceTrainTrancheEntity.getTrancheStatusMotriceTrainTranche());

		List<Object[]> listeSeat = query.getResultList();
		AtomicLong idRegime = mapIdTablesMotriceRegime.get(MotriceRegimeEntity.class);
		AtomicLong idRegimeSeat = mapIdTablesMotriceRegime.get(MotriceRegimeSpecificityEntity.class);

		List<ASousRegimeTranche> listeSpecifications = (List<ASousRegimeTranche>) atomicTranche.get()
				.getAttributsField(Specification.class);
		if (listeSpecifications == null) {
			listeSpecifications = new ArrayList<ASousRegimeTranche>();
		}

		List<Compartiment> listeCompartiments;
		List<Siege> sieges;

		String oldRegime = "";
		for (Object[] seat : listeSeat) {
			if (!oldRegime.equals(seat[4])) {// si le régime traité est
				// différent du précédent
				// on insère une nouvelle
				// entrée
				mapGeneratorTablesMotriceRegime.get(MotriceRegimeEntity.class).addValue(idRegime.incrementAndGet(),
						seat[4], 4, motriceTrainTrancheEntity.getIdMotriceTrainTranche());
			}
			// insertion du régime specificity lié au régime
			mapGeneratorTablesMotriceRegime.get(MotriceRegimeSpecificityEntity.class)
					.addValue(idRegimeSeat.getAndIncrement(), seat[0], seat[1], seat[2], seat[3], idRegime.get());

			listeCompartiments = new ArrayList<Compartiment>();
			sieges = new ArrayList<Siege>();
			sieges.add(new Siege((String) seat[2]));
			listeCompartiments.add(new Compartiment((String) seat[1], sieges));
			try {
            listeSpecifications.add(new Specification(new Voiture((String) seat[0], listeCompartiments),
            		EnumEtatSpecification.Blocked, new Regime((String) seat[4], debutPeriode)));
         } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }

			oldRegime = (String) seat[4];
		}

		query = entityManager.createNativeQuery("SELECT voiture.VOIT_NUM_RESA AS coachNumberMotriceRegimeSpecificity, "
				+ "LPAD(spco.SPCO_COMP_NUM, 3, '0') AS compartmentNumberMotriceRegimeSpecificity, "
				+ "'' AS seatNumberMotriceRegimeSpecificity, "
				+ "spco.SPCO_SPEC_COD AS stateCodeMotriceRegimeSpecificity, " + "spco.SPCO_REGI AS regime, "
				+ "tyvo.TYVO_DIAV_COD " + "FROM tremas_import_tmdvoit AS voiture "
				+ "INNER JOIN tremas_import_tmdcath AS cara ON voiture.VOIT_TRCH_COD_CIE = cara.CATH_TRCH_COD_CIE "
				+ "AND voiture.VOIT_TRCH_NUM_TRA1 = cara.CATH_TRCH_NUM_TRA1 "
				+ "AND voiture.VOIT_TRCH_IND_FER = cara.CATH_TRCH_IND_FER"
				+ " AND voiture.VOIT_TRCH_NUM = cara.CATH_TRCH_NUM "
				+ "INNER JOIN tremas_import_tmdspco AS spco ON voiture.VOIT_NUM = spco.SPCO_VOIT_NUM "
				+ "AND voiture.VOIT_TRCH_COD_CIE = spco.SPCO_VOIT_COD_CIE "
				+ "AND voiture.VOIT_TRCH_NUM_TRA1 = spco.SPCO_VOIT_NUM_TRA1 "
				+ "AND voiture.VOIT_TRCH_NUM = spco.SPCO_VOIT_TRCH_NUM "
				+ "AND voiture.VOIT_TRCH_IND_FER = spco.SPCO_VOIT_IND_FER "
				+ "INNER JOIN tremas_import_tmdtyvo AS tyvo ON voiture.VOIT_TYVO_NUM_TYP = tyvo.TYVO_NUM_TYP "
				+ "WHERE cara.CATH_SSIM = ? AND cara.CATH_TRCH_NUM_TRA1 = ? AND spco.SPCO_SPEC_COD = 'F'");
		query.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
		query.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());

		List<Object[]> listeComp = query.getResultList();
		idRegime = mapIdTablesMotriceRegime.get(MotriceRegimeEntity.class);
		AtomicLong idRegimeComp = mapIdTablesMotriceRegime.get(MotriceRegimeSpecificityEntity.class);

		query = entityManager.createNativeQuery(
				"SELECT pcdd.PCDD_COMP_COD_DIAG AS codeDiag, COUNT( DISTINCT pcdd.PCDD_COMP_NUM_COMP ) AS nombreCompartiments "
						+ "FROM tremas_import_tmdpcdd AS pcdd " + "GROUP BY codeDiag " + "ORDER BY codeDiag");
		List<Object[]> nbComp = query.getResultList();

		Map<KeyMotriceRegimeSpecificity, Collection<String>> mapRegimeCodeDiag = new HashMap<>();

		for (Object[] comp : listeComp) {
			KeyMotriceRegimeSpecificity key = new KeyMotriceRegimeSpecificity((String) comp[0], (String) comp[4],
					(String) comp[5], (String) comp[3]);
			Collection<String> compartiments = mapRegimeCodeDiag.get(key);
			if (compartiments == null) {
				compartiments = new ArrayList<String>();
				mapRegimeCodeDiag.put(key, compartiments);
			}
			compartiments.add((String) comp[1]);
		}

		for (KeyMotriceRegimeSpecificity mapKey : mapRegimeCodeDiag.keySet()) {
			int i = 0;
			while (!mapKey.getCodeDiagramme().equals((String) nbComp.get(i)[0])) {
				i++;
			}
			mapGeneratorTablesMotriceRegime.get(MotriceRegimeEntity.class).addValue(idRegime.incrementAndGet(),
					mapKey.getRegime(), 4, motriceTrainTrancheEntity.getIdMotriceTrainTranche());

			if (Integer.parseInt(nbComp.get(i)[1].toString()) == mapRegimeCodeDiag.get(mapKey).size()) {
				// Cas où tous les compartiments d'une voiture sont fermés : la
				// voiture est fermée
				mapGeneratorTablesMotriceRegime.get(MotriceRegimeSpecificityEntity.class).addValue(
						idRegimeComp.getAndIncrement(), mapKey.getVoiture(), "", "", mapKey.getStateCode(),
						idRegime.get());

				listeCompartiments = new ArrayList<Compartiment>();
				sieges = new ArrayList<Siege>();
				sieges.add(new Siege(""));
				listeCompartiments.add(new Compartiment("", sieges));
				try {
               listeSpecifications.add(new Specification(new Voiture(mapKey.getVoiture(), listeCompartiments),
               		EnumEtatSpecification.Blocked, new Regime(mapKey.getRegime(), debutPeriode)));
            } catch (ParseException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
			} else {
				// Cas où seuls certains compartiments d'une voiture sont fermés
				for (String compart : mapRegimeCodeDiag.get(mapKey)) {
					mapGeneratorTablesMotriceRegime.get(MotriceRegimeSpecificityEntity.class).addValue(
							idRegimeComp.getAndIncrement(), mapKey.getVoiture(), compart, "", mapKey.getStateCode(),
							idRegime.get());
					
					listeCompartiments = new ArrayList<Compartiment>();
					sieges = new ArrayList<Siege>();
					sieges.add(new Siege(""));
					listeCompartiments.add(new Compartiment(compart, sieges));
					try {
                  listeSpecifications.add(new Specification(new Voiture(mapKey.getVoiture(), listeCompartiments),
                  		EnumEtatSpecification.Blocked, new Regime(mapKey.getRegime(), debutPeriode)));
               } catch (ParseException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
				}
			}
		}
		atomicTranche.get().addAttributsField(listeSpecifications);
	}

}
