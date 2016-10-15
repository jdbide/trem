package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.AComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonControlePlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;
import com.avancial.socle.utils.DateUtils;

public class CompareTrainControl implements IComparePlanTransport {

	private static Logger logger = Logger.getLogger(CompareTrainControl.class);

	@Override
	public MapComparaisonPlanTransport compare(IPlanTransport comparableBase, IPlanTransport comparableXls)
			throws Exception {
		MapComparaisonPlanTransport res = new MapComparaisonPlanTransport();
		ComparaisonControlePlanTransport<IPlanTransport> comparaisonControlePlanTransport;

		Train trainBase = (Train) comparableBase;
		Train trainXls = (Train) comparableXls;

		// on boucle sur les tranches créées pour le fichier Excel
		// 3 types de tranches possible : tranches à statut différent, à statut
		// null ou à attributs différents
		for (Tranche trancheXls : trainXls.getTranches()) {
			// liste des jours de circulation du train
			for (Date date : trancheXls.getRegime().getListeJours()) {
				for (Tranche trancheBase : trainBase.getTranches()) {
					// détermine à quel régime tranche appartient la date de
					// circul
					if (trancheBase.getRegime().getListeJours().contains(date)) {
						if (trancheXls.getTrancheStatut() == null
								|| trancheBase.getTrancheStatut() != trancheXls.getTrancheStatut()) {
							// on ajoute ici les différences de statut pour une
							// tranche
							comparaisonControlePlanTransport = new ComparaisonControlePlanTransport<>();
							comparaisonControlePlanTransport.setDateCircul(date);
							comparaisonControlePlanTransport.setNumeroTrain(trainBase.getNumeroTrain());
							comparaisonControlePlanTransport.setNumeroTranche(trancheBase.getNumeroTranche());
							comparaisonControlePlanTransport
									.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.CONTROL);
							comparaisonControlePlanTransport.setAncienField(trancheBase);
							comparaisonControlePlanTransport.setNouveauField(trancheXls);
							logger.info(new StringBuilder("Statut différent pour le ")
									.append(DateUtils.dateToStringShortFormat(date)).append(" : ")
									.append(trancheBase.getTrancheStatut()).append("/")
									.append(trancheXls.getTrancheStatut()));
							res.putComparaison(comparaisonControlePlanTransport);
						}

						// on compare les attributs de la tranche
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

	/**
	 * Compare les attributs d'une tranche pour une date de circulation.
	 * 
	 * @param typeComparaisonPlanTransport
	 *            CONTROL
	 * @param numeroTranche
	 * @param numeroTrain
	 * @param dateCircul
	 * @param attributsBase
	 *            pdt en base
	 * @param attributsXls
	 *            pdt du fichier Excel
	 * @return map contenant les attributs différents
	 * @throws Exception
	 */
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
					 * Si on trouve un attribut modifié entre la base et le
					 * Excel, on ajoute un objet dans le résultat, et on enlève
					 * les attributs des listes
					 */
					List<AComparaisonPlanTransport<IPlanTransport>> listComparaison = resComparaison
							.getComparaison(typeComparaisonPlanTransport, sousRegimeTrancheBase.getClass());
					if (listComparaison != null && listComparaison.size() > 0) {
						try {
							comparaisonPlanTransport = (ComparaisonControlePlanTransport<IPlanTransport>) listComparaison
									.get(0);
						} catch (ClassCastException e) {
							throw e;
						}
						comparaisonPlanTransport.setNumeroTranche(numeroTranche);
						comparaisonPlanTransport.setNumeroTrain(numeroTrain);
						comparaisonPlanTransport.setDateCircul(dateCircul);
						res.putComparaison(comparaisonPlanTransport);
						logger.info(
								new StringBuilder(comparaisonPlanTransport.getAncienField().getClass().getSimpleName())
										.append(" différent pour le ")
										.append(DateUtils.dateToStringShortFormat(dateCircul)));
						break;
					}
				}
			}
		}
		return res;
	}

}
