package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.AComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonControlePlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public class CompareTrainControl implements IComparePlanTransport {

	@Override
	public MapComparaisonPlanTransport compare(IPlanTransport comparableBase, IPlanTransport comparableXls)
			throws Exception {

		MapComparaisonPlanTransport res = new MapComparaisonPlanTransport();
		ComparaisonControlePlanTransport<IPlanTransport> comparaisonControlePlanTransport;

		Train trainBase = (Train) comparableBase;
		Train trainXls = (Train) comparableXls;
		for (Tranche trancheXls : trainXls.getTranches()) {
			for (Date date : trancheXls.getRegime().getListeJours()) {
				for (Tranche trancheBase : trainBase.getTranches()) {
					if (trancheBase.getRegime().getListeJours().contains(date)) {
						if (trancheXls.getTrancheStatut() == null || trancheBase.getTrancheStatut() != trancheXls.getTrancheStatut()) {
							comparaisonControlePlanTransport = new ComparaisonControlePlanTransport<>();
							comparaisonControlePlanTransport.setDateCircul(date);
							comparaisonControlePlanTransport.setNumeroTrain(trainBase.getNumeroTrain());
							comparaisonControlePlanTransport.setNumeroTranche(trancheBase.getNumeroTranche());
							comparaisonControlePlanTransport
									.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.CONTROL);
							comparaisonControlePlanTransport.setAncienField(trancheBase);
							comparaisonControlePlanTransport.setNouveauField(trancheXls);

							res.putComparaison(comparaisonControlePlanTransport);
						}

						for (Class<?> attribut : trancheXls.getAttributs().keySet()) {
							res.putAll(this.compareAttributLists(EnumTypeComparaisonPlanTransport.CONTROL,
									trancheBase.getNumeroTranche(), trainBase.getNumeroTrain(), date,
									trancheBase.getAttributsField(attribut), trancheXls.getAttributsField(attribut)));
						}
					}
				}
			}
		}

		return res;
	}

	private MapComparaisonPlanTransport compareAttributLists(
			EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport, String numeroTranche, String numeroTrain,
			Date dateCircul, List<? extends ASousRegimeTranche> attributsBase,
			List<? extends ASousRegimeTranche> attributsXls) throws Exception {
		MapComparaisonPlanTransport res = new MapComparaisonPlanTransport();

		if (attributsBase != null && attributsXls != null) {
			ComparaisonControlePlanTransport<IPlanTransport> comparaisonPlanTransport;
			CompareAttributTrancheControl comparePlanTransport = new CompareAttributTrancheControl();

			/* Boucle sur les attributs de nouveau */
			for (Iterator<ASousRegimeTranche> itSousRegimeTrancheXls = (Iterator<ASousRegimeTranche>) attributsXls
					.iterator(); itSousRegimeTrancheXls.hasNext();) {
				ASousRegimeTranche sousRegimeTrancheXls = itSousRegimeTrancheXls.next();

				/* Boucle sur les attributs de ancien */
				for (Iterator<ASousRegimeTranche> itSousRegimeTrancheBase = (Iterator<ASousRegimeTranche>) attributsBase
						.iterator(); itSousRegimeTrancheBase.hasNext();) {
					ASousRegimeTranche sousRegimeTrancheBase = itSousRegimeTrancheBase.next();

					/*
					 * On compare les attributs de nouveau et ancien deux à deux
					 */
					MapComparaisonPlanTransport resComparaison = comparePlanTransport.compare(sousRegimeTrancheBase,
							sousRegimeTrancheXls);

					/*
					 * Si on trouve un attribut modifié entre ancien et nouveau,
					 * on ajoute un objet dans le résultat, et on enlève les
					 * attributs des listes
					 */
					List<AComparaisonPlanTransport<IPlanTransport>> listComparaison = resComparaison
							.getComparaison(typeComparaisonPlanTransport, sousRegimeTrancheBase.getClass());
					if (listComparaison != null && listComparaison.size() > 0) {
						try {
							comparaisonPlanTransport = (ComparaisonControlePlanTransport<IPlanTransport>) listComparaison
									.get(0);
						} catch (ClassCastException e) {
							// logger.error("Rapport différentiel : comparaison
							// de type "
							// +
							// listComparaison.get(0).getClass().getSimpleName()
							// + " trouvée!");
							throw e;
						}
						comparaisonPlanTransport.setNumeroTranche(numeroTranche);
						comparaisonPlanTransport.setNumeroTrain(numeroTrain);
						comparaisonPlanTransport.setDateCircul(dateCircul);
						// logger.info("Attribut " +
						// typeComparaisonPlanTransport.toString());
						res.putComparaison(comparaisonPlanTransport);

						break;
					}
				}
			}
		}
		return res;
	}

}
