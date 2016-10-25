package com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons;

import com.avancial.app.data.objetsMetier.PlanTransport.Repas;

/**
 * étape d'extraction de repas d'un fichier de données.
 * @author raphael.cabaret
 */
public class DatafileMealExtractionStep extends ADatafileConditionalStep<DatafileContext> {

	/** nom de la valeur parsée. */
	public static final String CODE_MEAL = "Meal";
	
	@Override protected void withoutParsingError(DatafileContext context) throws Exception {}
	
	@Override protected void always(DatafileContext context) throws Exception {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void withoutValidationAndParsingError(DatafileContext context) throws Exception {
		for(DatafileLineContext line : context.getLines()) {
			String label = line.getParsed(CODE_MEAL);
			if(label != null && !label.isEmpty()) {
				Repas meal = new Repas();
				meal.setRegime(line.getRegime());
				meal.setTypeRepas(context.getRefMeal().get(label));
				line.addSousRegime(meal);
			}
		}
	}

}
