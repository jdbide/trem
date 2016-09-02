package com.avancial.app.export;

import java.util.Iterator;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SocleExcelReadFile extends ASocleReadFile {

    private XSSFWorkbook workbook;
    
    private XSSFSheet currentSheet;
    
    private Iterator<Row> rowIterator;
    private Row currentRow;
    private Integer currentRowNumber = -1;
    
    private Iterator<Cell> cellIterator;
    private Cell currentCell;
    private Integer currentCellNumber = -1;
    
    public SocleExcelReadFile(String filePath) throws FileTypeNotExpectedException {
        super(filePath);
        
        if (!FilenameUtils.getExtension(filePath).equals("xls") && !FilenameUtils.getExtension(filePath).equals("xlsx")) {
            throw new FileTypeNotExpectedException(filePath, "xls, xlsx");
        }
    }
    
    @Override
    public void start() throws Exception {
        super.start();
        this.workbook = new XSSFWorkbook(getFileInput());
    }
    
    public void setSheet(int sheetIndex) {
        this.currentSheet = this.workbook.getSheetAt(sheetIndex);
        this.rowIterator = this.currentSheet.iterator();
        this.currentRowNumber = -1;
        this.currentCellNumber = -1;
    }
    
    public Row getNextRow() {
        this.currentRow = this.rowIterator.next();
        this.cellIterator = this.currentRow.cellIterator();
        this.currentRowNumber++;
        this.currentCellNumber = -1;
        return this.currentRow;
    }
    
    public boolean hasNextRow() {
        return this.rowIterator.hasNext();
    }
    
    public Cell getNextCell() {
        this.currentCell = this.cellIterator.next();
        this.currentCellNumber++;
        return this.currentCell;
    }
    
    public boolean hasNextCell() {
        return this.cellIterator.hasNext();
    }

    public XSSFSheet getCurrentSheet() {
        return this.currentSheet;
    }

    public void setCurrentSheet(XSSFSheet currentSheet) {
        this.currentSheet = currentSheet;
    }

    public Row getCurrentRow() {
        return this.currentRow;
    }

    public void setCurrentRow(Row currentRow) {
        this.currentRow = currentRow;
    }

    public Integer getCurrentRowNumber() {
        return this.currentRowNumber;
    }

    public void setCurrentRowNumber(Integer currentRowNumber) {
        this.currentRowNumber = currentRowNumber;
    }

    public Integer getCurrentCellNumber() {
        return this.currentCellNumber;
    }

    public void setCurrentCellNumber(Integer currentCellNumber) {
        this.currentCellNumber = currentCellNumber;
    }

}
