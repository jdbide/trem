package com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons;

import static com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileCodeSATExtractionStep.CODE_SAT;

import com.avancial.app.fileImport.excelImport.ExcelImportException;

/**
 * étape de validation des codes SAT.
 * @author raphael.cabaret
 */
public class DatafileCodeSATValidationStep extends ADatafileConditionalStep<DatafileContext> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void withoutParsingError(DatafileContext context) throws Exception {
		for(DatafileLineContext line : context.getLines()) {
			String sat = line.getParsed(CODE_SAT);
			if(!context.getRefCodeSat().contains(sat)) {
				context.addValidationError(new ExcelImportException(null, "le code SAT " + sat + " est invalide"));
			}
		}
	}

	@Override protected void withoutValidationAndParsingError(DatafileContext context) throws Exception {}

	@Override protected void always(DatafileContext context) throws Exception {}

}
