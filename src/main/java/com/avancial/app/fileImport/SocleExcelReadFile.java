package com.avancial.app.fileImport;

import java.util.Iterator;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SocleExcelReadFile extends ASocleReadFile {

    /**
     * Fichier Excel en cours de lecture
     */
    private Workbook workbook;

    /**
     * Feuille Excel en cours de lecture
     */
    private Sheet currentSheet;

    /**
     * Itérateur sur les lignes dans la feuille Excel courante
     */
    private Iterator<Row> rowIterator;
    /**
     * Ligne en cours de lecture
     */
    private Row currentRow;
    /**
     * Numéro de la ligne en cours dans le parcours des lignes
     */
    private Integer currentRowNumber = -1;

    /**
     * Itérateur sur les cellules dans la ligne courante
     */
    private Iterator<Cell> cellIterator;
    /**
     * Cellule en cours de lecture
     */
    private Cell currentCell;
    /**
     * Numéro de la cellule en cours dans le parcours de la ligne
     */
    private Integer currentCellNumber = -1;

    public SocleExcelReadFile(String filePath) throws FileTypeNotExpectedException {
        super(filePath);

        if (!FilenameUtils.getExtension(filePath).equals("xls")
                && !FilenameUtils.getExtension(filePath).equals("xlsx")) {
            throw new FileTypeNotExpectedException(filePath, "xls, xlsx");
        }
    }

    @Override
    public void start() throws Exception {
        super.start();
        this.workbook = new XSSFWorkbook(getFileInput());
    }

    /**
     * Set la feuille Excel à lire, et initialise l'itérateur sur les lignes
     * 
     * @param sheetIndex
     *            Indice de la feuille (à partir de 0)
     */
    public void setSheet(int sheetIndex) {
        this.currentSheet = this.workbook.getSheetAt(sheetIndex);
        this.rowIterator = this.currentSheet.iterator();
        this.currentRowNumber = -1;
        this.currentCellNumber = -1;
    }

    /**
     * Prochaine itération sur les lignes de la feuille en cours, initialise
     * l'itérateur sur les cellules de cette nouvelle ligne
     * 
     * @return La ligne suivante dans le fichier, qui devient la nouvelle ligne
     *         courante
     */
    public Row getNextRow() {
        this.currentRow = this.rowIterator.next();
        this.cellIterator = this.currentRow.cellIterator();
        this.currentRowNumber++;
        this.currentCellNumber = -1;
        return this.currentRow;
    }

    /**
     * Test sur la fin du fichier
     * 
     * @return {@code True} si et seulement si il reste des lignes à lire dans
     *         la feuille en cours
     */
    public boolean hasNextRow() {
        return this.rowIterator.hasNext();
    }

    /**
     * Prochaine itération sur les cellules de la ligne en cours.
     * 
     * @return La cellule suivante dans la ligne, qui devient la nouvelles
     *         cellule courante
     */
    public Cell getNextCell() {
        this.currentCell = this.cellIterator.next();
        this.currentCellNumber++;
        return this.currentCell;
    }

    /**
     * Test sur la fin de la ligne
     * 
     * @return {@code True} si et seulement si il reste des cellules à lire dans
     *         la ligne en cours
     */
    public boolean hasNextCell() {
        return this.cellIterator.hasNext();
    }

    /**
     * 
     * @return La feuille en cours de lecture
     */
    public Sheet getCurrentSheet() {
        return this.currentSheet;
    }

    public void setCurrentSheet(Sheet currentSheet) {
        this.currentSheet = currentSheet;
    }

    /**
     * 
     * @return La ligne en cours de lecture
     */
    public Row getCurrentRow() {
        return this.currentRow;
    }

    public void setCurrentRow(Row currentRow) {
        this.currentRow = currentRow;
    }

    /**
     * 
     * @return Le numéro de la ligne en cours dans le parcours
     */
    public Integer getCurrentRowNumber() {
        return this.currentRowNumber;
    }

    /**
     * Sette la ligne à lire, et initialise l'itérateur sur les cellules de
     * cette ligne. L'itérateur sur les lignes est passé à {@code null}.
     * 
     * @param currentRowNumber
     *            Indice de la ligne
     * @return La nouvelle ligne courante
     */
    public Row setCurrentRowNumber(Integer currentRowNumber) {
        this.currentRow = this.currentSheet.getRow(currentRowNumber);
        this.currentRowNumber = currentRowNumber;
        this.rowIterator = null;
        this.cellIterator = this.currentRow.cellIterator();
        this.currentCellNumber = -1;
        return this.currentRow;
    }

    /**
     * 
     * @return Le numéro de la cellule en cours dans le parcours
     */
    public Integer getCurrentCellNumber() {
        return this.currentCellNumber;
    }

    /**
     * Sette la cellule à lire dans la ligne en cours. L'itérateur sur les
     * cellules est passé à {@code null}.
     * 
     * @param currentCellNumber
     *            Indice de la colonne
     * @return La nouvelle cellule courante
     */
    public Cell setCurrentCellNumber(Integer currentCellNumber) {
        this.currentCell = this.currentRow.getCell(currentCellNumber);
        this.currentCellNumber = currentCellNumber;
        this.cellIterator = null;
        return this.currentCell;
    }

}
