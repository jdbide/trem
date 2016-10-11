package com.avancial.app.service.controlePlanTransport.excelImport.commonSteps;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.fileImport.excelImport.ExcelImportException;

/**
 * étape de parsing de la liste des gares.
 * @author raphael.cabaret
 */
public class DessertesStationsParseStep extends AConditionalLoopDessertesFinalStep<DessertesContext>{
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doFinally(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {
		int i = context.getFirstStationRow();
		DessertesStationContext station = null;
		do {
			String content = "";
			Cell cell = null;
			// lecture du lébelé de la gare
			try {
				cell = sheet.getRow(i).getCell(context.getStationsColumn());
				content = cell.getStringCellValue();
			} catch (Exception e) {
				context.setFatalException(new ExcelImportException(cell, "impossible de lire un libellé de gare dans cette cellule", e));
				this.breakStepExecution();
			}
			// si un libelle est présent
			if(!content.equals("")) {
				station = new DessertesStationContext(content, subContext);
				subContext.getStations().add(station);
				// si il n'y à qu'un horaire de départ
				if(sheet.getRow(i).getCell(context.getFirstTrainColumn() - 1).getStringCellValue().trim().toLowerCase().matches("dep|depart")) {
					station.setArrivalRow(cell.getRow());
				// si la première ligne est un horaire d'arrivé
				} else if (sheet.getRow(i).getCell(context.getFirstTrainColumn() - 1).getStringCellValue().trim().toLowerCase().matches("arr|arrive")) {
					station.setDepartureRow(cell.getRow());
					Cell labelCell = null;
					String label = "";
					// lecture de la ligne suivante
					try {
						cell = sheet.getRow(i + 1).getCell(context.getFirstTrainColumn() - 1);
						content = cell.getStringCellValue();
						labelCell = sheet.getRow(i + 1).getCell(context.getStationsColumn());
						label = labelCell.getStringCellValue();
					} catch (Exception e) {
						context.setFatalException(new ExcelImportException(cell, "impossible de lire cette cellule", e));
						this.breakStepExecution();
					}
					// si la ligne est celle du départ de la même gare
					if(content.trim().toLowerCase().matches("dep|depart") && (label.equals("") || label.equals(station.getName()))){
						i++;
						station.setArrivalRow(cell.getRow());
					}
				} else {
					context.setFatalException(new ExcelImportException(cell, "les cellules 'arrivé' et 'depart' de la gare ne sont pas correctes"));
					this.breakStepExecution();
				}
				i++;
			} else {
				station = null;
			}
		} while(station != null);
	}

	@Override protected void doIfNoParsingError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	@Override protected void doIfNoParsingAndValidationError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

}
