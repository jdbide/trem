package com.avancial.app.service.controlePlanTransport.excelImport.commonSteps;

import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.fileImport.excelImport.ExcelImportException;

import static com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesEurostarMealParseStep.MEAL1;
import static com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesEurostarMealParseStep.MEAL2;

/**
 * étape de validation des repas.
 * @author raphael.cabaret
 */
public class DessertesEurostarMealValidationStep extends AConditionalLoopDessertesFinalStep<DessertesContext> {

	@Override protected void doFinally(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doIfNoParsingError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {
		for(DessertesTrainContext train : subContext.getTrains()) {
			boolean error = false;
			// validation du premier repas
			String meal1 = (String) train.getOthers().get(MEAL1);
			if(meal1 != null && !context.getRefMeal().containsKey(meal1)) {
				context.addValidationError(new ExcelImportException(null, "dans la feuille " + sheet.getSheetName() +
						" pour le train " + train.getIdTrain() + " le code repas " + meal1 + " est invalide"));
				error = true;
			}
			// validation du second repas
			String meal2 = (String) train.getOthers().get(MEAL2);
			if(meal2 != null && !context.getRefMeal().containsKey(meal2)) {
				context.addValidationError(new ExcelImportException(null, "dans la feuille " + sheet.getSheetName() +
						" pour le train " + train.getIdTrain() + " le code repas " + meal2 + " est invalide"));
				error = true;
			}
			// contrôle des doublons
			if(meal1 != null && meal2 != null && !error) {
				context.addValidationError(new ExcelImportException(null, "dans la feuille " + sheet.getSheetName() +
						" pour le train " + train.getIdTrain() + " les deux repas ne peuvent pas être identiques"));
			}
		}
	}

	@Override protected void doIfNoParsingAndValidationError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

}
