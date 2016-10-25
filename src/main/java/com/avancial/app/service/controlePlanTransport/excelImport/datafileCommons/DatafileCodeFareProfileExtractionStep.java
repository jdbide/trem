package com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons;

import com.avancial.app.data.objetsMetier.PlanTransport.FareProfile;

/**
 * étape d'extraction des profils tarifaires.
 * @author raphael.cabaret
 */
public class DatafileCodeFareProfileExtractionStep extends ADatafileConditionalStep<DatafileContext> {

	/** nom de la valeur parsée. */
	public static final String CODE_FARE_PROFILE = "CodeFareProfile";
	
	/** valeur indiquant l'absence de profil tarifaire. */
	private String nullValue;
	
	/**
	 * constructeur simple.
	 * @param nullValue valeur indiquant l'absence de profil tarifaire.
	 */
	public DatafileCodeFareProfileExtractionStep(String nullValue) {
		this.nullValue = nullValue;
	}
	
	@Override protected void withoutParsingError(DatafileContext context) throws Exception {}
	
	@Override protected void always(DatafileContext context) throws Exception {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void withoutValidationAndParsingError(DatafileContext context) throws Exception {
		for(DatafileLineContext line : context.getLines()) {
			String fareCode = line.getParsed(CODE_FARE_PROFILE);
			FareProfile fare = new FareProfile(fareCode, line.getRegime());
			if(fareCode.equals(this.nullValue)) {
				fare.setFareProfileCode(null);
			}
			line.addSousRegime(fare);
		}
	}

}
