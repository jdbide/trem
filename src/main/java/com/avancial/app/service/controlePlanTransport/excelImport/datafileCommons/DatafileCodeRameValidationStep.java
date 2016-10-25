package com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons;

import static com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileCodeRameExtractionStep.CODE_RAME;

import com.avancial.app.fileImport.excelImport.ExcelImportException;

/**
 * étape de validation des codes rame.
 * @author raphael.cabaret
 */
public class DatafileCodeRameValidationStep extends ADatafileConditionalStep<DatafileContext> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void withoutParsingError(DatafileContext context) throws Exception {
		for(DatafileLineContext line : context.getLines()) {
			String rame = line.getParsed(CODE_RAME);
			if(!context.getRefCodeRame().contains(rame)) {
				context.addValidationError(new ExcelImportException(null, "le code rame " + rame + " est invalide"));
			}
		}
	}

	@Override protected void withoutValidationAndParsingError(DatafileContext context) throws Exception {}

	@Override protected void always(DatafileContext context) throws Exception {}

}
