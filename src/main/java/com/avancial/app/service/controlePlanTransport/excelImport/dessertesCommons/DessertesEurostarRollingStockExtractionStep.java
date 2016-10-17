package com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons;

import static com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesEurostarRollingStockParseStep.ROLLING_STOCK;

import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.data.objetsMetier.PlanTransport.TypeEquipement;

/**
 * étape d'extraction du matériel roulant.
 * @author raphael.cabaret
 */
public class DessertesEurostarRollingStockExtractionStep extends AConditionalLoopDessertesFinalStep<DessertesContext> {

	@Override protected void doFinally(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	@Override protected void doIfNoParsingError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doIfNoParsingAndValidationError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {
		for(DessertesTrainContext train : subContext.getTrains()) {
			String stockName = (String) train.getOthers().get(ROLLING_STOCK);
			TypeEquipement rollingStock = new TypeEquipement(stockName, train.getRegime());
			train.getSousRegimes().add(rollingStock);
		}
	}

}
