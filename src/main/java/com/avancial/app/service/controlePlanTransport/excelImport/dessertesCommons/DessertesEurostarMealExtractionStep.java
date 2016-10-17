package com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons;

import static com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesEurostarMealParseStep.MEAL1;
import static com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesEurostarMealParseStep.MEAL2;

import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeRepas;
import com.avancial.app.data.objetsMetier.PlanTransport.Repas;

/**
 * étape d'extraction des repas.
 * @author raphael.cabaret
 */
public class DessertesEurostarMealExtractionStep extends AConditionalLoopDessertesFinalStep<DessertesContext> {

	@Override protected void doFinally(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	@Override protected void doIfNoParsingError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doIfNoParsingAndValidationError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {
		for(DessertesTrainContext train : subContext.getTrains()) {
			// extraction du premier repas
			if(train.getOthers().get(MEAL1) != null) {
				EnumTypeRepas type1 = context.getRefMeal().get(train.getOthers().get(MEAL1));
				Repas meal1 = new Repas(type1, null, train.getRegime());
				train.getSousRegimes().add(meal1);
			}
			// extraction du second repas
			if(train.getOthers().get(MEAL2) != null) {
				EnumTypeRepas type2 = context.getRefMeal().get(train.getOthers().get(MEAL2));
				Repas meal2 = new Repas(type2, null, train.getRegime());
				train.getSousRegimes().add(meal2);
			}
		}
	}

}
