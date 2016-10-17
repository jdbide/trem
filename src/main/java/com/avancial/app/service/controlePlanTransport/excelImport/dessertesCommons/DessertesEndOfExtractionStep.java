package com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public class DessertesEndOfExtractionStep extends AConditionalLoopDessertesFinalStep<DessertesContext> {

	@Override protected void doFinally(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	@Override protected void doIfNoParsingError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doIfNoParsingAndValidationError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {
		for(DessertesTrainContext train : subContext.getTrains()) {
			Tranche tranche = context.getTranchesByTrain().get(train.getIdTrain());
			this.putAttributs(tranche, train.getDesserte());
			for(ASousRegimeTranche att : train.getSousRegimes()) {
				this.putAttributs(tranche, att);
			}
		}
	}

	/**
	 * inssère le sous régime dans la tranche.
	 * @param tranche la tranche.
	 * @param att le sous-régime.
	 */
	private void putAttributs(Tranche tranche, ASousRegimeTranche att) {
		@SuppressWarnings("unchecked")
		List<ASousRegimeTranche> atts = (List<ASousRegimeTranche>) tranche.getAttributsField(att.getClass());
		if(atts == null) {
			atts = new ArrayList<ASousRegimeTranche>();
			atts.add(att);
			tranche.addAttributsField(atts);
		} else {
			atts.add(att);
		}
	}
}
