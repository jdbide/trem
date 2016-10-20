package com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons;

import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;

/**
 * étape d'extraction des codes SAT.
 * @author raphael.cabaret
 */
public class DatafileCodeSATExtractionStep extends ADatafileConditionalStep<DatafileContext> {
	
	/** nom de la valeur parsée. */
	public static final String CODE_SAT = "CodeSat";

	@Override protected void withoutParsingError(DatafileContext context) throws Exception {}
	
	@Override protected void always(DatafileContext context) throws Exception {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void withoutValidationAndParsingError(DatafileContext context) throws Exception {
		for(DatafileLineContext line : context.getLines()) {
			String code = line.getParsed(CODE_SAT);
			CodeSat codeSat = new CodeSat(code, line.getRegime());
			line.addSousRegime(codeSat);
		}
	}

}
