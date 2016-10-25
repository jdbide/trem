package com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons;

import com.avancial.app.data.objetsMetier.PlanTransport.TypeEquipement;

/**
 * étape d'extraction des types d'équipements.
 * @author raphael.cabaret
 */
public class DatafileRollingStockExtractionStep extends ADatafileConditionalStep<DatafileContext> {

	/** nom de la valeur parsée. */
	public static final String ROLLING_STOCK = "RollingStock";
	
	@Override protected void withoutParsingError(DatafileContext context) throws Exception {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void withoutValidationAndParsingError(DatafileContext context) throws Exception {
		for(DatafileLineContext line : context.getLines()) {
			String stock = line.getParsed(ROLLING_STOCK);
			TypeEquipement rollingStock = new TypeEquipement(stock, line.getRegime());
			line.addSousRegime(rollingStock);
		}
	}

	@Override protected void always(DatafileContext context) throws Exception {}

}
