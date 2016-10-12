package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import org.apache.log4j.Logger;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonControlePlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

/**
 * Implémentation de la comparaison MODIFY entre deux attributs de Tranche (ils
 * héritent de {@link ASousRegimeTranche})
 * 
 * @author heloise.guillemaud
 *
 */
public class CompareAttributTrancheControl extends AChaineComparePlanTransport {

	private static Logger logger = Logger.getLogger(CompareAttributTrancheControl.class);

	@Override
	public MapComparaisonPlanTransport compare(IPlanTransport comparableBase, IPlanTransport comparableXls)
			throws Exception {
		MapComparaisonPlanTransport res = new MapComparaisonPlanTransport();
		ASousRegimeTranche attributBase = (ASousRegimeTranche) comparableBase;
		ASousRegimeTranche attributXls = (ASousRegimeTranche) comparableXls;

		if (!attributXls.getClass().equals(attributBase.getClass())) {
			logger.error("Ne peut pas comparer deux instances de IPlanTransport de classes différentes!");
			throw new Exception("Ne peut pas comparer deux instances de IPlanTransport de classes différentes!");
		}

		ComparaisonControlePlanTransport<IPlanTransport> comparaisonPlanTransport = new ComparaisonControlePlanTransport<IPlanTransport>();
		/*
		 * Deux attributs sont modifiés entre deux jeux de données s'ils ont des
		 * valeurs de champs différentes pour un même jour de circul
		 */
		if (!attributXls.equals(attributBase)) {
			/*
			 * Si les deux attributs sont effectivement une modification, on
			 * renvoie le résultat
			 */
			comparaisonPlanTransport.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.CONTROL);
			comparaisonPlanTransport.setAncienField(attributBase);
			comparaisonPlanTransport.setNouveauField(attributXls);

			res.putComparaison(comparaisonPlanTransport);
		}

		return res;
	}

}
