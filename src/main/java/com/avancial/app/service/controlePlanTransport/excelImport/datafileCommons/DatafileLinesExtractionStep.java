package com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons;

import java.util.Collections;

import com.avancial.app.data.objetsMetier.PlanTransport.Regime;

/**
 * étape d'extraction des lignes.
 * @author raphael.cabaret
 */
public class DatafileLinesExtractionStep implements IDatafileFinalStep<DatafileContext> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void executeStep(DatafileContext context) throws Exception {
		for(DatafileLineContext line : context.getLines()) {
			// construit le régime
			line.setRegime(new Regime("", line.getDate(), line.getDate(), Collections.singletonList(line.getDate())));
		}
	}

}
