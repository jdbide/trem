package com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons;

import static com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileCodeFareProfileExtractionStep.CODE_FARE_PROFILE;

import com.avancial.app.fileImport.excelImport.ExcelImportException;

/**
 * étape de validation des profils tarifaires.
 * @author raphael.cabaret
 */
public class DatafileCodeFareProfileValidationStep extends ADatafileConditionalStep<DatafileContext> {

	/** valeur indiquant l'absence de profil tarifaire. */
	private String nullValue;
	
	/**
	 * constructeur simple.
	 * @param nullValue valeur indiquant l'absence de profil tarifaire.
	 */
	public DatafileCodeFareProfileValidationStep(String nullValue) {
		this.nullValue = nullValue;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void withoutParsingError(DatafileContext context) throws Exception {
		for(DatafileLineContext line : context.getLines()) {
			String profile = line.getParsed(CODE_FARE_PROFILE);
			if(!profile.equals(this.nullValue) && !context.getRefCodeFareProfile().contains(profile)) {
				context.addValidationError(new ExcelImportException(null, "le profile tarifaire " + profile + " est invalide"));
			}
		}
	}

	@Override protected void withoutValidationAndParsingError(DatafileContext context) throws Exception {}

	@Override protected void always(DatafileContext context) throws Exception {}

}
