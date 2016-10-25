package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.Iterator;
import org.apache.log4j.Logger;

import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

/**
 * Compare deux plans de transport. L'un issue d'un jeu de données, l'autre des
 * fichiers Excel de contrôle des clients.
 * 
 * @author sebastien.benede
 *
 */
public class ComparePlanTransportControl implements IChaineComparePlanTransport {

	private static Logger logger = Logger.getLogger(ComparePlanTransportControl.class);
	
	public void setSuccesseur(IChaineComparePlanTransport chaineComparePlanTransport){
	   return;
	}

	/**
	 * Compare deux listes de trains issus des plans de transport comparés.
	 * 
	 * @param comparableBase
	 * @param comparableXls
	 * @return
	 * @throws Exception
	 */
	public MapComparaisonPlanTransport compare(IPlanTransport comparableBase, IPlanTransport comparableXls)
			throws Exception {
		MapComparaisonPlanTransport res = new MapComparaisonPlanTransport();
		PlanTransport pdtBase = (PlanTransport) comparableBase;
		PlanTransport pdtXls = (PlanTransport) comparableXls;
		logger.info("Début comparaison Rapport de contrôle : " + pdtBase.getCompagnie());

		IComparePlanTransport comparePlanTransport = new CompareTrainControl();
		/* Boucle sur les trains des fichiers Excel */
		for (Train trainXls : pdtXls.getTrains()) {
			/* Boucle sur les trains du pdt en base */
			for (Iterator<Train> itTrainBase = (Iterator<Train>) pdtBase.getTrains().iterator(); itTrainBase
					.hasNext();) {
				Train trainBase = itTrainBase.next();
				/* Si les trains ont le même numeroTrain, on les compare */
				if (trainXls.equals(trainBase)) {
					logger.info(new StringBuilder("Comparaison des trains : ").append(trainBase.getNumeroTrain())
							.append("/").append(trainXls.getNumeroTrain()));
					res.putAll(comparePlanTransport.compare(trainBase, trainXls));

					/* Comparaison entre les trains terminée */
					itTrainBase.remove();
					break;
				}
			}
		}
		logger.info("Fin comparaison Rapport de contrôle : " + pdtBase.getCompagnie());

		return res;
	}
}
