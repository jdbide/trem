package com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons;

import static com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileRollingStockExtractionStep.ROLLING_STOCK;

import com.avancial.app.fileImport.excelImport.ExcelImportException;

/**
 * étape de validation du type d'équipement.
 * @author raphael.cabaret
 */
public class DatafileRollingStockValidationStep extends ADatafileConditionalStep<DatafileContext> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void withoutParsingError(DatafileContext context) throws Exception {
		for(DatafileLineContext line : context.getLines()) {
			String stock = line.getParsed(ROLLING_STOCK);
			if(!context.getRefRollingStock().contains(stock)) {
				context.addValidationError(new ExcelImportException(null, "le code d'équipement " + stock + " est invalide"));
			}
		}
	}

	@Override protected void withoutValidationAndParsingError(DatafileContext context) throws Exception {}

	@Override protected void always(DatafileContext context) throws Exception {}

}
