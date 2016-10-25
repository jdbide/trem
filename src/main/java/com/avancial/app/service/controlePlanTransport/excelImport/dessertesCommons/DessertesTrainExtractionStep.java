package com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

/**
 * étape d'extraction de la liste des trains.
 * @author raphael.cabaret
 */
public class DessertesTrainExtractionStep extends AConditionalLoopDessertesFinalStep<DessertesContext> {
	
	@Override protected void doFinally(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	@Override protected void doIfNoParsingError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doIfNoParsingAndValidationError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {
		DecimalFormat df = new DecimalFormat("000000");
		for(DessertesTrainContext train : subContext.getTrains()) {
			if(!context.getTranchesByTrain().containsKey(train.getIdTrain())) {
				// création de la tranche unique et du train si il n'existe pas déjà
				Tranche tranche = new Tranche();
				tranche.setRegime(null);
				List<Tranche> list = new ArrayList<Tranche>();
				list.add(tranche);
				context.getTranchesByTrain().put(train.getIdTrain(), tranche);
				context.getProduct().getTrains().add(new Train(list, df.format(train.getIdTrain()), false));
			}
		}
	}

}
