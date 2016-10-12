package com.avancial.app.service.controlePlanTransport.excelImport.commonSteps;

import static com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesEurostarRollingStockParseStep.ROLLING_STOCK;

import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.fileImport.excelImport.ExcelImportException;

/**
 * étape de validation du matériel roulant.
 * @author raphael.cabaret
 */
public class DessertesEurostarRollingStockValidationStep extends AConditionalLoopDessertesFinalStep<DessertesContext> {

	@Override protected void doFinally(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doIfNoParsingError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {
		for(DessertesTrainContext train : subContext.getTrains()) {
			String rollingStock = (String) train.getOthers().get(ROLLING_STOCK);
			if(rollingStock == null) {
				context.addValidationError(new ExcelImportException(null, "dans la feuille " + sheet.getSheetName() + " le 'Rolling Stock' est obligatoire"));
				break;
			}
			if(!context.getRefRollingStock().contains(rollingStock)) {
				context.addValidationError(new ExcelImportException(null, "dans la feuille " + sheet.getSheetName() +
						" pour le train " + train.getIdTrain() + " le 'Rolling Stock' " + rollingStock + " est invalide"));
			}
		}
	}

	@Override protected void doIfNoParsingAndValidationError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

}
