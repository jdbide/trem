package com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons;

import com.avancial.app.fileImport.excelImport.ExcelImportException;

import static com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileMealExtractionStep.CODE_MEAL;

/**
 * étape de validation de repas pour les fichiers de données.
 * @author raphael.cabaret
 */
public class DatafileMealValidationStep extends ADatafileConditionalStep<DatafileContext> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void withoutParsingError(DatafileContext context) throws Exception {
		for(DatafileLineContext line : context.getLines()) {
			String label = line.getParsed(CODE_MEAL);
			if(label != null && !label.isEmpty()) {
				if(!context.getRefMeal().containsKey(label)) {
					context.addValidationError(new ExcelImportException(null, "le code repas " + label + " est invalide"));
				}
			}
		}
	}

	@Override protected void withoutValidationAndParsingError(DatafileContext context) throws Exception {}

	@Override protected void always(DatafileContext context) throws Exception {}

}
