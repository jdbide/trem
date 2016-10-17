package com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.data.objetsMetier.PlanTransport.EnumTrancheStatut;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.fileImport.excelImport.AExcelImportContext;
import com.avancial.app.fileImport.excelImport.SocleExcelReadFile;

/**
 * contexte pour l'importation des fichiers de données de contrôle.
 * @author raphael.cabaret
 */
public class DatafileContext extends AExcelImportContext<PlanTransport> {

	private final List<DatafileLineContext> lines = new ArrayList<DatafileLineContext>();
	
	private List<String> refRollingStock;
	
	private Sheet sheet;
	
	private int firstDataLineIndex;
	
	/**
	 * construction simple.
	 * @param source
	 */
	public DatafileContext(SocleExcelReadFile source, int firstDataLineIndex) {
		super(source);
		this.firstDataLineIndex = firstDataLineIndex;
	}

	public void addSubContext(int index, int trainId, Date date, EnumTrancheStatut statut) {
		DatafileLineContext subContext = new DatafileLineContext(this, trainId, date, statut, index);
		this.lines.add(subContext);
	}
	
	/**
	 * @return the lines
	 */
	public List<DatafileLineContext> getLines() {
		return lines;
	}

	/**
	 * @return the sheet
	 */
	public Sheet getSheet() {
		return sheet;
	}

	/**
	 * @param sheet the sheet to set
	 */
	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	/**
	 * @return the refRollingStock
	 */
	public List<String> getRefRollingStock() {
		return refRollingStock;
	}

	/**
	 * @param refRollingStock the refRollingStock to set
	 */
	public void setRefRollingStock(List<String> refRollingStock) {
		this.refRollingStock = refRollingStock;
	}

	/**
	 * @return the firstDataLineIndex
	 */
	public int getFirstDataLineIndex() {
		return firstDataLineIndex;
	}

	/**
	 * @param firstDataLineIndex the firstDataLineIndex to set
	 */
	public void setFirstDataLineIndex(int firstDataLineIndex) {
		this.firstDataLineIndex = firstDataLineIndex;
	}
	
	
}
