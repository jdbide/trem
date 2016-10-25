package com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons;

import static com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileCodeRameExtractionStep.CODE_RM;

import com.avancial.app.fileImport.excelImport.ExcelImportException;

/**
 * étape de validation des codes RM.
 * @author raphael.cabaret
 */
public class DatafileCodeRmValidationStep extends ADatafileConditionalStep<DatafileContext> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void withoutParsingError(DatafileContext context) throws Exception {
		for(DatafileLineContext line : context.getLines()) {
			String rm = line.getParsed(CODE_RM);
			if(!context.getRefCodeRm().containsKey(rm)) {
				context.addValidationError(new ExcelImportException(null, "le code RM " + rm + " est invalide"));
			}
		}
	}

	@Override protected void withoutValidationAndParsingError(DatafileContext context) throws Exception {}

	@Override protected void always(DatafileContext context) throws Exception {}

}
