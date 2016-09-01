/**
 * 
 */
package com.avancial.app.export;

import java.awt.Color;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

/**
 * @author hamza.laterem
 *
 */
public class ExcelTools {
    private Workbook classeur = null;
    private Sheet sheet;
    private Row ligneSheet;
    private Cell cell;

    public XSSFCellStyle styleNombre = null;
    public XSSFCellStyle styleNombreUnLock = null;
    public XSSFCellStyle styleNombreHide = null;
    public XSSFCellStyle styleNombreStandard = null;
    public XSSFCellStyle styleNombreStandardUnLock = null;
    public XSSFCellStyle styleEnteteGris = null;
    public XSSFCellStyle styleEnteteJaune = null;
    public XSSFCellStyle styleTexte = null;
    public XSSFCellStyle stylePourcent = null;
    public XSSFCellStyle stylePourcentOk = null;
    public XSSFCellStyle stylePourcentKo = null;
    public XSSFCellStyle styleTexteLock = null;
    public XSSFCellStyle styleTexteUnLock = null;
    public XSSFCellStyle styleCellVideWithMergin = null;
    public XSSFCellStyle styleNombreStandardLock = null;
    public XSSFCellStyle styleTitre = null;
    public XSSFCellStyle styleSousTitre = null;
    public XSSFCellStyle styleHide = null;
    public XSSFCellStyle styleWarning = null;
    public XSSFCellStyle styleBorder = null;
    public XSSFCellStyle styleBorderNotRight = null;
    public XSSFCellStyle styleBorderNotLeft = null;
    public XSSFCellStyle styleBorderNotLeftNotRight = null;

    public Font fontCellVideWithMergin = null;
    public Font fontEntete = null;
    public Font fontNombre = null;
    public Font fontNombreOk = null;
    public Font fontNombreKo = null;
    public Font fontTexte = null;
    public Font fontTitre = null;
    public Font fontSousTitre = null;
    public Font fontHide = null;
    public Font fontWarning = null;

    public Color couleurVert = new Color(216, 228, 188);
    public Color couleurRosePale = new Color(242, 220, 219);
    public Color couleurRoseFonce = new Color(255, 204, 204);
    public Color couleurVioletPale = new Color(228, 223, 236);
    public Color couleurVioletFonce = new Color(217, 210, 228);
    public Color couleurGris = new Color(217, 217, 217);
    public Color couleurBleu = new Color(220, 230, 241);
    public Color couleurRouge = new Color(242, 220, 219);
    public Color couleurMarron = new Color(238, 236, 225);
    public Color couleurJaune = new Color(255, 255, 204);

    /**
     * Constructeur sans arguments
     */
    public ExcelTools() {
        this.init();
    }

    /**
     * Constructeur avec le Workbook en argument
     * 
     * @param classeur
     */
    public ExcelTools(Workbook classeur) {
        this.classeur = classeur;
        this.init();
    }

    /**
     * Constructeur avec le Workbook et Sheet en argument
     * 
     * @param classeur
     * @param sheet
     */
    public ExcelTools(Workbook classeur, Sheet sheet) {
        this.classeur = classeur;
        this.sheet = sheet;
        this.init();
    }

    /**
     * Constructeur avec le Workbook et String : le nom de l'onglet pour la
     * creation du sheet
     * 
     * @param classeur
     * @param nameSheet
     */
    public ExcelTools(Workbook classeur, String nameSheet) {
        this.classeur = classeur;
        this.sheet = this.classeur.createSheet(nameSheet);
        this.init();
    }

    private void init() {
        this.initStyle();
    }

    /**
     * Création d'un nouveau sheet
     * 
     * @param nameSheet
     */
    public void createSheet(String nameSheet) {
        this.sheet = this.classeur.createSheet(nameSheet);
    }

    /**
     * Récuperation du sheet par Nom
     * 
     * @param nameSheet
     * @return
     */
    public void setSheetByName(String nameSheet) {
        this.sheet = this.classeur.getSheet(nameSheet);
    }

    /**
     * Récuperation du sheet par id
     * 
     * @param nameSheet
     * @return
     */
    public void setSheetByIndex(int indexSheet) {
        this.sheet = this.classeur.getSheetAt(indexSheet);
    }

    /**
     * Récuperation du libelle sheet en cours
     * 
     * @param nameSheet
     * @return
     */
    public String getLibelleSheet() {
        return this.sheet.getSheetName();
    }

    /**
     * Nombre d'onglet d'un classeur
     * 
     * @return
     */
    public int getNbrSheet() {
        return this.classeur.getNumberOfSheets();
    }

    /**
     * Return le nombre de ligne
     * 
     * @return
     */
    public int getNumberRow() {
        return this.sheet.getLastRowNum();
    }

    /**
     * Création d'une nouvelle ligne
     * 
     * @param row
     */
    public void createRow(int row) {
        this.ligneSheet = this.sheet.createRow(row);
    }

    /**
     * Modification du possitionnement du row
     * 
     * @param row
     */
    public void setRow(int row) {
        this.ligneSheet = this.sheet.getRow(row);
        if (this.ligneSheet == null) {
            this.createRow(row);
        }
    }

    public int getRowNum() {
        return this.ligneSheet.getRowNum();
    }

    /**
     * Fusionner les cellules (FirstRow, LastRow, FirtCol, LastCol)
     * 
     * @param firstRow
     * @param lastRow
     * @param firstCol
     * @param lastCol
     */
    public void addMergedRegion(int firstRow, int lastRow, int firstCol, int lastCol) {
        this.sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
    }

    /**
     * Fusionner les cellules (FirstRow, LastRow, FirtCol, LastCol, Style)
     * 
     * @param firstRow
     * @param lastRow
     * @param firstCol
     * @param lastCol
     * @param style
     */
    public void addMergedRegion(int firstRow, int lastRow, int firstCol, int lastCol, CellStyle style) {
        for (int i = firstRow; i <= lastRow; i++) {
            Row r = this.sheet.getRow(i);
            for (int j = firstCol; j <= lastCol; j++) {
                Cell c = r.getCell(j);
                c.setCellStyle(style);

            }
        }

        this.sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
    }

    /**
     * Fusionner les cellules (FirstRow, LastRow, FirtCol, LastCol, Value)
     * 
     * @param firstRow
     * @param lastRow
     * @param firstCol
     * @param lastCol
     * @param value
     */
    public void addMergedRegion(int firstRow, int lastRow, int firstCol, int lastCol, String value) {
        this.setRow(firstRow);
        for (int ligne = firstRow; ligne <= lastRow; ligne++) {
            this.setRow(ligne);
            for (int colonne = firstCol; colonne <= lastCol; colonne++) {
                this.createCellTexte(colonne, value);
            }
        }
        this.addMergedRegion(firstRow, lastRow, firstCol, lastCol);
    }

    /**
     * Fusionner les cellules (FirstRow, LastRow, FirtCol, LastCol, Value)
     * 
     * @param firstRow
     * @param lastRow
     * @param firstCol
     * @param lastCol
     * @param value
     * @param style
     */
    public void addMergedRegion(int firstRow, int lastRow, int firstCol, int lastCol, String value, CellStyle style) {
        this.setRow(firstRow);
        for (int ligne = firstRow; ligne <= lastRow; ligne++) {
            this.setRow(ligne);
            for (int colonne = firstCol; colonne <= lastCol; colonne++) {
                this.createCellTexteWithStyle(colonne, value, style);
            }
        }
        this.addMergedRegion(firstRow, lastRow, firstCol, lastCol);
    }

    /**
     * Création d'une cellule avec une liste DropDown
     * 
     * @param firstRow
     * @param lastRow
     * @param firstCol
     * @param lastCol
     * @param explicitListValues
     * @param isSuppressDropDownArrow
     * @param valueSelected
     */
    /*
     * public void createCellDropDown(int firstRow, int lastRow, int firstCol,
     * int lastCol, String[] explicitListValues) { CellRangeAddressList
     * addressList = new CellRangeAddressList(firstRow, lastRow, firstCol,
     * lastCol); DVConstraint dvConstraint =
     * DVConstraint.createExplicitListConstraint(explicitListValues);
     * DataValidation dataValidation = new DataValidation(addressList,
     * dvConstraint); dataValidation.setSuppressDropDownArrow(false);
     * this.sheet.addValidationData(dataValidation); }
     */

    /**
     * <p>
     * Limiter la taille d'une cellule.
     * </p>
     * <p>
     * Affichage d'une pop-up dans le cas ou on dépasse le nombre de caractère
     * </p>
     * Il ne fonctionne pas avec copie-coller
     * 
     * @param colonne
     * @param sizeMin
     * @param sizeMax
     * @param nameCol
     */
    /*
     * public void createCellTextelimitSize(final int colonne, final String
     * sizeMin, final String sizeMax, String nameCol) { CellRangeAddressList
     * addressList = new CellRangeAddressList(this.ligneSheet.getRowNum(),
     * this.ligneSheet.getRowNum(), colonne, colonne); DVConstraint dvConstraint
     * = DVConstraint.createNumericConstraint(DataValidationConstraint.
     * ValidationType.TEXT_LENGTH,
     * DataValidationConstraint.OperatorType.BETWEEN, sizeMin, sizeMax);
     * DataValidation dataValidation = new DataValidation(addressList,
     * dvConstraint); dataValidation.setShowErrorBox(true);
     * dataValidation.createErrorBox("Impec : " + nameCol,
     * "Le nombre de caractères de la cellule est compris entre " + sizeMin +
     * " et " + sizeMax); this.sheet.addValidationData(dataValidation); }
     */

    /**
     * Masquer la ligne en cours
     */
    public void hideLine() {
        this.ligneSheet.setHeight((short) 0);
    }

    /**
     * Masquer la ligne avec le numero de la ligne
     */
    public void hideLine(int row) {
        this.sheet.getRow(row).setHeight((short) 0);
    }

    /**
     * recupération du type de la colonne
     * 
     * @param colonne
     * @return
     */
    public int getTypeCell(final int colonne) {
        return this.ligneSheet.getCell(colonne).getCellType();
    }

    /**
     * Compare le type d'une cell
     * 
     * @param colonne
     * @param type
     * @return
     */
    public Boolean compareTypeCell(final int colonne, int type) {
        return (this.ligneSheet.getCell(colonne).getCellType() == type);
    }

    /**
     * Récupération de la valeur d'une cellule String
     * 
     * @param colonne
     * @return
     */
    public String getCellTexte(final int colonne) {
        if (this.ligneSheet.getCell(colonne).getCellType() == Cell.CELL_TYPE_STRING) {
            return this.ligneSheet.getCell(colonne).getStringCellValue();
        }

        return null;
    }

    /**
     * Récupération de la valeur d'une cellule Double
     * 
     * @param colonne
     * @return
     */
    public Double getCellDouble(final int colonne) {
        if (this.ligneSheet.getCell(colonne).getCellType() == Cell.CELL_TYPE_NUMERIC) {
            Cell cellTmp = this.ligneSheet.getCell(colonne);
            cellTmp.setCellStyle(this.styleNombre);
            return (Double) this.ligneSheet.getCell(colonne).getNumericCellValue();
        }

        return null;
    }

    /**
     * Récupération de la valeur d'une cellule Avec son style
     * 
     * @param colonne
     * @return
     */
    public Integer getCellHide(final int colonne) {
        if (this.ligneSheet.getCell(colonne).getCellType() == Cell.CELL_TYPE_NUMERIC) {
            Cell cellTmp = this.ligneSheet.getCell(colonne);
            cellTmp.setCellStyle(this.styleHide);
            if ((Double) this.ligneSheet.getCell(colonne).getNumericCellValue() != null)
                return (((Double) this.ligneSheet.getCell(colonne).getNumericCellValue()).intValue());
        }

        return null;
    }

    /**
     * Récupération de la valeur d'une cellule Double
     * 
     * @param colonne
     * @return
     */
    public Double getCellPourcentage(final int colonne) {
        if (this.ligneSheet.getCell(colonne).getCellType() == Cell.CELL_TYPE_NUMERIC) {
            Cell cellTmp = this.ligneSheet.getCell(colonne);
            cellTmp.setCellStyle(this.stylePourcent);
            return (Double) this.ligneSheet.getCell(colonne).getNumericCellValue();
        }

        return null;
    }

    /**
     * bloqué la modification d'une celulle, !!!il faut verrouillé le sheet
     * avant
     * 
     * @param i
     */
    public void lockCell(int i) {
        CellStyle styleTmp = this.ligneSheet.getCell(i).getCellStyle();
        styleTmp.setLocked(true);

        this.ligneSheet.getCell(i).setCellStyle(styleTmp);
    }

    public void unLockCell(int i) {
        CellStyle styleTmp = this.ligneSheet.getCell(i).getCellStyle();
        styleTmp.setLocked(false);

        this.ligneSheet.getCell(i).setCellStyle(styleTmp);
    }

    /**
     * débloqué la modification d'une celulle de type Nombre, !!!il faut
     * verrouillé le sheet avant
     * 
     * @param i
     */
    public void unLockCellTexte(int i) {
        if (this.ligneSheet.getCell(i).getCellType() == 1) {
            this.ligneSheet.getCell(i).setCellStyle(this.styleTexteUnLock);
        }
        else {
            CellStyle styleTmp = this.ligneSheet.getCell(i).getCellStyle();
            styleTmp.setLocked(false);
            this.ligneSheet.getCell(i).setCellStyle(styleTmp);
        }
    }

    /**
     * bloqué la modification d'une celulle, !!!il faut verrouillé le sheet
     * avant
     * 
     * @param i
     */
    public void lockCellTexte(int i) {
        this.ligneSheet.getCell(i).setCellStyle(this.styleTexteLock);
    }

    /**
     * bloqué la modification d'une celulle, !!!il faut verrouillé le sheet
     * avant
     * 
     * @param i
     */
    public void lockCellNombreStandard(int i) {
        this.ligneSheet.getCell(i).setCellStyle(this.styleNombreStandardLock);
    }

    /**
     * débloqué la modification d'une celulle de type Nombre, !!!il faut
     * verrouillé le sheet avant
     * 
     * @param i
     */
    public void unLockCellNombreStandard(int i) {
        if (this.ligneSheet.getCell(i).getCellType() == 0) {
            this.ligneSheet.getCell(i).setCellStyle(this.styleNombreStandardUnLock);
        }
        else {
            CellStyle styleTmp = this.ligneSheet.getCell(i).getCellStyle();
            styleTmp.setLocked(false);
            this.ligneSheet.getCell(i).setCellStyle(styleTmp);
        }
    }

    /**
     * débloqué la modification d'une celulle de type Nombre, !!!il faut
     * verrouillé le sheet avant
     * 
     * @param i
     */
    public void unLockCellNombre(int i) {
        if (this.ligneSheet.getCell(i).getCellType() == 0) {
            this.ligneSheet.getCell(i).setCellStyle(this.styleNombreUnLock);
        }
        else {
            CellStyle styleTmp = this.ligneSheet.getCell(i).getCellStyle();
            styleTmp.setLocked(false);
            this.ligneSheet.getCell(i).setCellStyle(styleTmp);
        }
    }

    /**
     * débloqué la modification d'une celulle de type Nombre, !!!il faut
     * verrouillé le sheet avant
     * 
     * @param i
     */
    public void unLockCellNombre(int i, CellStyle style) {
        style.setLocked(false);
        // style.cloneStyleFrom(this.ligneSheet.getCell(i).getCellStyle());
        this.ligneSheet.getCell(i).setCellStyle(style);
    }

    /**
     * débloqué la modification d'une celulle de type Nombre, !!!il faut
     * verrouillé le sheet avant
     * 
     * @param i
     */
    public void unLockCellPourcent(int colonne) {
        CellStyle styleTmp = this.ligneSheet.getCell(colonne).getCellStyle();
        styleTmp.setLocked(false);
        this.ligneSheet.getCell(colonne).setCellStyle(styleTmp);
    }

    /**
     * Vérrouillé le sheet
     * 
     * @param b
     */
    public void lockSheet(boolean isLock) {
        if (isLock)
            this.sheet.protectSheet("");
    }

    /**
     * Création d'une cellule vide avec le style
     * 
     * @param i
     * @param styleCellVideWithMergin2
     */
    public void createCellVideWithStyle(final int colonne, CellStyle style) {
        this.cell = this.ligneSheet.createCell(colonne);
        style.setLocked(false);
        this.cell.setCellStyle(style);
    }

    /**
     * Création d'une cellule texte
     * 
     * @param colonne
     * @param value
     */
    public void createCellTexte(final int colonne, final String value) {
        this.cell = this.ligneSheet.createCell(colonne);
        this.cell.setCellStyle(this.styleTexte);
        this.cell.setCellValue(value);
    }

    /**
     * Création d'une cellule texte avec un style en parametre
     * 
     * @param colonne
     * @param value
     */
    public void createCellTexteWithStyle(final int colonne, final String value, CellStyle styleTexte) {
        this.cell = this.ligneSheet.createCell(colonne);
        this.cell.setCellStyle(styleTexte);
        this.cell.setCellValue(value);
    }

    /**
     * Création d'une cellule texte avec un style en parametre
     * 
     * @param colonne
     * @param value
     */
    public void createCellTexteWithStyle(final int colonne, final String value, Object styleTexte) {
        this.cell = this.ligneSheet.createCell(colonne);
        this.cell.setCellStyle((CellStyle) styleTexte);
        this.cell.setCellValue(value);
    }

    /**
     * Création d'une cellule texte en entete
     * 
     * @param colonne
     * @param value
     */
    public void createCellTexteEntete(final int colonne, final String value) {
        this.cell = this.ligneSheet.createCell(colonne);
        this.cell.setCellStyle(this.styleEnteteGris);
        this.cell.setCellValue(value);
    }

    /**
     * Création d'une cellule texte en entete avec un style en parametre
     * 
     * @param colonne
     * @param value
     */
    public void createCellTexteEnteteWithStyle(final int colonne, final String value, CellStyle styleTexte) {
        this.cell = this.ligneSheet.createCell(colonne);
        this.cell.setCellStyle(styleTexte);
        this.cell.setCellValue(value);
    }

    /**
     * Création d'une cellule pourcentage
     * 
     * @param colonne
     * @param value
     * @param style
     */
    public void createCellPourcentage(final int colonne, final Double value) {
        this.cell = this.ligneSheet.createCell(colonne);
        this.cell.setCellStyle(this.stylePourcent);
        this.cell.setCellValue(value);
    }

    /**
     * Création d'une cellule pourcentageOk
     * 
     * @param colonne
     * @param value
     * @param style
     */
    public void createCellPourcentageOk(final int colonne, final Double value) {
        this.cell = this.ligneSheet.createCell(colonne);
        this.cell.setCellStyle(this.stylePourcentOk);
        this.cell.setCellValue(value);
    }

    /**
     * Création d'une cellule pourcentageKo
     * 
     * @param colonne
     * @param value
     * @param style
     */
    public void createCellPourcentageKo(final int colonne, final Double value) {
        this.cell = this.ligneSheet.createCell(colonne);
        this.cell.setCellStyle(this.stylePourcentKo);
        this.cell.setCellValue(value);
    }

    /**
     * Création d'une cellule pourcentage avec un style en parametre
     * 
     * @param colonne
     * @param value
     * @param style
     */
    public void createCellPourcentageWithStyle(final int colonne, final Double value, CellStyle styleTexte) {
        this.cell = this.ligneSheet.createCell(colonne);
        this.cell.setCellStyle(styleTexte);
        this.cell.setCellValue(value);
    }

    /**
     * Création d'une cellule double
     * 
     * @param colonne
     * @param value
     * @param style
     */
    public void createCellNombre(final int colonne, final Double value) {
        this.cell = this.ligneSheet.createCell(colonne);
        this.cell.setCellStyle(this.styleNombre);
        this.cell.getCellStyle().setLocked(false);
        this.cell.setCellValue(value);
    }

    /**
     * @param i
     * @param idQuadri
     */
    public void createCellNombreHide(final int colonne, Integer value) {
        this.cell = this.ligneSheet.createCell(colonne);
        this.cell.setCellStyle(this.styleNombreHide);
        this.cell.getCellStyle().setLocked(true);
        this.cell.getCellStyle().setHidden(true);
        ;
        this.cell.setCellValue(value);
    }

    /**
     * Création d'une cellule double
     * 
     * @param colonne
     * @param value
     * @param style
     */
    public void createCellNombreWithStyle(final int colonne, final Double value, CellStyle styleTexte) {
        this.cell = this.ligneSheet.createCell(colonne);
        this.cell.setCellStyle(styleTexte);
        this.cell.setCellValue(value);
    }

    /**
     * Création d'une cellule double
     * 
     * @param colonne
     * @param value
     * @param style
     */
    public void createCellNombreWithStyle(final int colonne, final Integer value, CellStyle styleTexte) {
        this.cell = this.ligneSheet.createCell(colonne);
        this.cell.setCellStyle(styleTexte);
        this.cell.setCellValue(value);
    }

    /**
     * Création d'une cellule contenant une formule
     * 
     * @param colonne
     * @param formule
     */
    public void createCellFormula(final int colonne, String formule) {
        this.cell = this.ligneSheet.createCell(colonne);
        this.cell.setCellFormula(formule);
    }

    public void createCellFormulaWithStyle(final int colonne, String formule, CellStyle style) {
        this.cell = this.ligneSheet.createCell(colonne);
        this.cell.setCellStyle(style);
        this.cell.setCellFormula(formule);
    }

    /**
     * Modification de la valeur d'une cellule en double
     * 
     * @param i
     * @param doubleValue
     */
    public void setCellValueNombre(final int colonne, double value) {
        this.cell = this.ligneSheet.getCell(colonne);
        this.cell.setCellValue(value);
    }

    /**
     * Modification de la valeur d'une cellule en texte
     * 
     * @param i
     * @param doubleValue
     */
    public void setCellValueTexte(final int colonne, String value) {
        this.cell = this.ligneSheet.getCell(colonne);
        this.cell.setCellValue(value);
    }

    /**
     * Règler la largeur de la colonne pour adapter le contenu.<\br> Ce
     * processus peut être relativement lente sur de grandes feuilles , donc
     * cela ne devrait normalement être appelé une fois par colonne , à la fin
     * de votre traitement.<\br>
     * 
     * @param column
     */
    public void ajutementCell(int column) {
        for (int i = 0; i < column; i++) {
            this.sheet.autoSizeColumn(i);
        }
    }

    /**
     * Retourne la localisation de la cellule en String (exemple: E11)
     * 
     * @param cellReference
     * @return
     */
    public String getCellStringReference(CellReference cellReference) {
        StringBuilder sb = new StringBuilder(cellReference.getCellRefParts()[2]);
        sb.append(cellReference.getCellRefParts()[1]);

        return sb.toString();
    }

    public void test(int l, int c, String s) {
        Cell cl = this.sheet.getRow(l).getCell(c);

        cl.setCellValue(s);
    }

    /**
     * Getter classeur
     * 
     * @return the classeur
     */
    public Workbook getclasseur() {
        return this.classeur;
    }

    /**
     * Setter classeur
     * 
     * @param classeur
     *            the classeur to set
     */
    public void setclasseur(Workbook classeur) {
        this.classeur = classeur;
    }

    /**
     * Getter sheet
     * 
     * @return the sheet
     */
    public Sheet getSheet() {
        return this.sheet;
    }

    /**
     * Setter sheet
     * 
     * @param sheet
     *            the sheet to set
     */
    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    /**
     * Getter cell
     * 
     * @return the cell
     */
    public Cell getCell() {
        return this.cell;
    }

    /**
     * Setter cell
     * 
     * @param cell
     *            the cell to set
     */
    public void setCell(Cell cell) {
        this.cell = cell;
    }

    /**
     * Getter ligneSheet
     * 
     * @return the ligneSheet
     */
    public Row getLigneSheet() {
        return this.ligneSheet;
    }

    /**
     * Setter ligneSheet
     * 
     * @param ligneSheet
     *            the ligneSheet to set
     */
    public void setLigneSheet(Row ligneSheet) {
        this.ligneSheet = ligneSheet;
    }

    public Cell getCell(int colonne) {
        return this.getLigneSheet().getCell(colonne);
    }

    private void initStyle() {
        /*
         * this.fontCellVideWithMergin = this.classeur.createFont();
         * this.fontCellVideWithMergin.setFontHeightInPoints((short)(8));
         * 
         * this.styleCellVideWithMergin = this.classeur.createCellStyle();
         * this.styleCellVideWithMergin.setAlignment(CellStyle.ALIGN_RIGHT);
         * this.styleCellVideWithMergin.setFont(this.fontCellVideWithMergin);
         * 
         * this.styleNombreStandard.setBorderBottom(CellStyle.BORDER_THIN);
         * this.styleNombreStandard.setBorderLeft(CellStyle.BORDER_THIN);
         * this.styleNombreStandard.setBorderRight(CellStyle.BORDER_THIN);
         * this.styleNombreStandard.setBorderTop(CellStyle.BORDER_THIN);
         * this.styleNombreStandard.setLocked(true);
         */

        this.fontNombre = this.classeur.createFont();
        this.fontNombre.setFontHeightInPoints((short) (8));

        this.fontNombreOk = this.classeur.createFont();
        this.fontNombreOk.setFontHeightInPoints((short) (8));
        this.fontNombreOk.setColor(HSSFColor.GREEN.index);

        this.fontNombreKo = this.classeur.createFont();
        this.fontNombreKo.setFontHeightInPoints((short) (8));
        this.fontNombreKo.setColor(HSSFColor.RED.index);

        this.fontTitre = this.classeur.createFont();
        this.fontTitre.setBoldweight(Font.BOLDWEIGHT_BOLD);
        this.fontTitre.setFontHeightInPoints((short) (12));

        this.fontSousTitre = this.classeur.createFont();
        this.fontSousTitre.setColor(HSSFColor.TEAL.index);

        this.fontEntete = this.classeur.createFont();
        this.fontEntete.setBoldweight(Font.BOLDWEIGHT_BOLD);
        this.fontEntete.setItalic(true);
        this.fontEntete.setFontHeightInPoints((short) (9));

        this.fontTexte = this.classeur.createFont();
        this.fontTexte.setFontHeightInPoints((short) (8));

        this.fontHide = this.classeur.createFont();
        this.fontHide.setColor(HSSFColor.WHITE.index);

        this.fontWarning = this.classeur.createFont();
        this.fontWarning.setBoldweight(Font.BOLDWEIGHT_BOLD);
        this.fontWarning.setFontHeightInPoints((short) (9));
        this.fontWarning.setColor(HSSFColor.RED.index);

        this.styleNombreStandard = (XSSFCellStyle) this.classeur.createCellStyle();
        this.styleNombreStandard.setAlignment(CellStyle.ALIGN_CENTER);
        this.styleNombreStandard.setFont(this.fontNombre);
        this.styleNombreStandard.setDataFormat(this.classeur.createDataFormat().getFormat("# ##0"));

        this.styleNombreStandard.setBorderBottom(CellStyle.BORDER_THIN);
        this.styleNombreStandard.setBorderLeft(CellStyle.BORDER_THIN);
        this.styleNombreStandard.setBorderRight(CellStyle.BORDER_THIN);
        this.styleNombreStandard.setBorderTop(CellStyle.BORDER_THIN);

        this.styleNombreStandardUnLock = (XSSFCellStyle) this.classeur.createCellStyle();
        this.styleNombreStandardUnLock.setAlignment(CellStyle.ALIGN_CENTER);
        this.styleNombreStandardUnLock.setFont(this.fontNombre);
        this.styleNombreStandardUnLock.setDataFormat(this.classeur.createDataFormat().getFormat("# ##0"));

        this.styleNombreStandardUnLock.setBorderBottom(CellStyle.BORDER_THIN);
        this.styleNombreStandardUnLock.setBorderLeft(CellStyle.BORDER_THIN);
        this.styleNombreStandardUnLock.setBorderRight(CellStyle.BORDER_THIN);
        this.styleNombreStandardUnLock.setBorderTop(CellStyle.BORDER_THIN);
        this.styleNombreStandardUnLock.setLocked(false);

        this.styleNombreStandardLock = (XSSFCellStyle) this.classeur.createCellStyle();
        this.styleNombreStandardLock.setAlignment(CellStyle.ALIGN_CENTER);
        this.styleNombreStandardLock.setFont(this.fontNombre);
        this.styleNombreStandardLock.setDataFormat(this.classeur.createDataFormat().getFormat("# ##0"));

        this.styleNombreStandardLock.setBorderBottom(CellStyle.BORDER_THIN);
        this.styleNombreStandardLock.setBorderLeft(CellStyle.BORDER_THIN);
        this.styleNombreStandardLock.setBorderRight(CellStyle.BORDER_THIN);
        this.styleNombreStandardLock.setBorderTop(CellStyle.BORDER_THIN);
        this.styleNombreStandardLock.setLocked(true);

        this.styleNombre = (XSSFCellStyle) this.classeur.createCellStyle();
        this.styleNombre.setAlignment(CellStyle.ALIGN_RIGHT);
        this.styleNombre.setFont(this.fontNombre);
        this.styleNombre.setDataFormat(this.classeur.createDataFormat().getFormat("### ### ##0.00"));

        this.styleNombre.setBorderBottom(CellStyle.BORDER_THIN);
        this.styleNombre.setBorderLeft(CellStyle.BORDER_THIN);
        this.styleNombre.setBorderRight(CellStyle.BORDER_THIN);
        this.styleNombre.setBorderTop(CellStyle.BORDER_THIN);

        this.styleNombreUnLock = (XSSFCellStyle) this.classeur.createCellStyle();
        this.styleNombreUnLock.setAlignment(CellStyle.ALIGN_RIGHT);
        this.styleNombreUnLock.setFont(this.fontNombre);
        this.styleNombreUnLock.setDataFormat(this.classeur.createDataFormat().getFormat("### ### ##0.00"));

        this.styleNombreUnLock.setBorderBottom(CellStyle.BORDER_THIN);
        this.styleNombreUnLock.setBorderLeft(CellStyle.BORDER_THIN);
        this.styleNombreUnLock.setBorderRight(CellStyle.BORDER_THIN);
        this.styleNombreUnLock.setBorderTop(CellStyle.BORDER_THIN);
        this.styleNombreUnLock.setLocked(false);

        this.styleNombreHide = (XSSFCellStyle) this.classeur.createCellStyle();
        this.styleNombreHide.setDataFormat(this.classeur.createDataFormat().getFormat("### ### ##0.00"));
        this.styleNombreHide.setLocked(true);
        this.styleNombreHide.setHidden(true);

        this.styleEnteteGris = (XSSFCellStyle) this.classeur.createCellStyle();
        this.styleEnteteGris.setFont(this.fontEntete);
        this.styleEnteteGris.setFillForegroundColor(new XSSFColor(this.couleurGris));
        this.styleEnteteGris.setFillPattern(PatternFormatting.SOLID_FOREGROUND);

        this.styleEnteteJaune = (XSSFCellStyle) this.classeur.createCellStyle();
        this.styleEnteteJaune.setFont(this.fontEntete);
        this.styleEnteteJaune.setFillForegroundColor(new XSSFColor(this.couleurJaune));
        this.styleEnteteJaune.setFillPattern(PatternFormatting.SOLID_FOREGROUND);

        this.styleTexteLock = (XSSFCellStyle) this.classeur.createCellStyle();
        this.styleTexteLock.setAlignment(CellStyle.ALIGN_LEFT);
        this.styleTexteLock.setFont(this.fontTexte);
        this.styleTexteLock.setBorderBottom(CellStyle.BORDER_THIN);
        this.styleTexteLock.setBorderLeft(CellStyle.BORDER_THIN);
        this.styleTexteLock.setBorderRight(CellStyle.BORDER_THIN);
        this.styleTexteLock.setBorderTop(CellStyle.BORDER_THIN);
        this.styleTexteLock.setFillBackgroundColor(HSSFColor.RED.index);
        this.styleTexteLock.setLocked(true);

        this.styleTexteUnLock = (XSSFCellStyle) this.classeur.createCellStyle();
        this.styleTexteUnLock.setAlignment(CellStyle.ALIGN_LEFT);
        this.styleTexteUnLock.setFont(this.fontTexte);
        this.styleTexteUnLock.setBorderBottom(CellStyle.BORDER_THIN);
        this.styleTexteUnLock.setBorderLeft(CellStyle.BORDER_THIN);
        this.styleTexteUnLock.setBorderRight(CellStyle.BORDER_THIN);
        this.styleTexteUnLock.setBorderTop(CellStyle.BORDER_THIN);
        this.styleTexteUnLock.setFillBackgroundColor(HSSFColor.RED.index);
        this.styleTexteUnLock.setLocked(false);

        this.styleTexte = (XSSFCellStyle) this.classeur.createCellStyle();
        this.styleTexte.setAlignment(CellStyle.ALIGN_LEFT);
        this.styleTexte.setFont(this.fontTexte);
        this.styleTexte.setBorderBottom(CellStyle.BORDER_THIN);
        this.styleTexte.setBorderLeft(CellStyle.BORDER_THIN);
        this.styleTexte.setBorderRight(CellStyle.BORDER_THIN);
        this.styleTexte.setBorderTop(CellStyle.BORDER_THIN);
        this.styleTexte.setFillBackgroundColor(HSSFColor.RED.index);

        this.styleBorder = (XSSFCellStyle) this.classeur.createCellStyle();
        this.styleBorder.setBorderBottom(CellStyle.BORDER_THIN);
        this.styleBorder.setBorderLeft(CellStyle.BORDER_THIN);
        this.styleBorder.setBorderRight(CellStyle.BORDER_THIN);
        this.styleBorder.setBorderTop(CellStyle.BORDER_THIN);
        this.styleBorder.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        this.styleBorder.setAlignment(CellStyle.ALIGN_LEFT);

        this.styleBorderNotRight = (XSSFCellStyle) this.classeur.createCellStyle();
        this.styleBorderNotRight.setBorderBottom(CellStyle.BORDER_THIN);
        this.styleBorderNotRight.setBorderLeft(CellStyle.BORDER_THIN);
        // this.styleBorderNotRight.setBorderRight(CellStyle.BORDER_THIN);
        this.styleBorderNotRight.setBorderTop(CellStyle.BORDER_THIN);
        this.styleBorderNotRight.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        this.styleBorderNotRight.setAlignment(CellStyle.ALIGN_LEFT);

        this.styleBorderNotLeft = (XSSFCellStyle) this.classeur.createCellStyle();
        this.styleBorderNotLeft.setBorderBottom(CellStyle.BORDER_THIN);
        // this.styleBorderNotLeft.setBorderLeft(CellStyle.BORDER_THIN);
        this.styleBorderNotLeft.setBorderRight(CellStyle.BORDER_THIN);
        this.styleBorderNotLeft.setBorderTop(CellStyle.BORDER_THIN);
        this.styleBorderNotLeft.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        this.styleBorderNotLeft.setAlignment(CellStyle.ALIGN_LEFT);

        this.styleBorderNotLeftNotRight = (XSSFCellStyle) this.classeur.createCellStyle();
        this.styleBorderNotLeftNotRight.setBorderBottom(CellStyle.BORDER_THIN);
        // this.styleBorderNotLeftNotRight.setBorderLeft(CellStyle.BORDER_THIN);
        // this.styleBorderNotLeftNotRight.setBorderRight(CellStyle.BORDER_THIN);
        this.styleBorderNotLeftNotRight.setBorderTop(CellStyle.BORDER_THIN);
        this.styleBorderNotLeftNotRight.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        this.styleBorderNotLeftNotRight.setAlignment(CellStyle.ALIGN_LEFT);

        this.stylePourcent = (XSSFCellStyle) this.classeur.createCellStyle();
        this.stylePourcent.setAlignment(CellStyle.ALIGN_RIGHT);
        this.stylePourcent.setBorderBottom(CellStyle.BORDER_THIN);
        this.stylePourcent.setBorderLeft(CellStyle.BORDER_THIN);
        this.stylePourcent.setBorderRight(CellStyle.BORDER_THIN);
        this.stylePourcent.setBorderTop(CellStyle.BORDER_THIN);
        this.stylePourcent.setFont(this.fontNombre);
        this.stylePourcent.setDataFormat(this.classeur.createDataFormat().getFormat("##0.00%"));

        this.stylePourcentOk = (XSSFCellStyle) this.classeur.createCellStyle();
        this.stylePourcentOk.setAlignment(CellStyle.ALIGN_RIGHT);
        this.stylePourcentOk.setBorderBottom(CellStyle.BORDER_THIN);
        this.stylePourcentOk.setBorderLeft(CellStyle.BORDER_THIN);
        this.stylePourcentOk.setBorderRight(CellStyle.BORDER_THIN);
        this.stylePourcentOk.setBorderTop(CellStyle.BORDER_THIN);
        this.stylePourcentOk.setFont(this.fontNombreOk);
        this.stylePourcentOk.setDataFormat(this.classeur.createDataFormat().getFormat("##0.00%"));

        this.stylePourcentKo = (XSSFCellStyle) this.classeur.createCellStyle();
        this.stylePourcentKo.setAlignment(CellStyle.ALIGN_RIGHT);
        this.stylePourcentKo.setBorderBottom(CellStyle.BORDER_THIN);
        this.stylePourcentKo.setBorderLeft(CellStyle.BORDER_THIN);
        this.stylePourcentKo.setBorderRight(CellStyle.BORDER_THIN);
        this.stylePourcentKo.setBorderTop(CellStyle.BORDER_THIN);
        this.stylePourcentKo.setFont(this.fontNombreKo);
        this.stylePourcentKo.setDataFormat(this.classeur.createDataFormat().getFormat("##0.00%"));

        this.styleTitre = (XSSFCellStyle) this.classeur.createCellStyle();
        this.styleTitre.setFont(this.fontTitre);

        this.styleSousTitre = (XSSFCellStyle) this.classeur.createCellStyle();
        this.styleSousTitre.setFont(this.fontSousTitre);

        this.styleHide = (XSSFCellStyle) this.classeur.createCellStyle();
        this.styleHide.setFont(this.fontHide);

        this.styleWarning = (XSSFCellStyle) this.classeur.createCellStyle();
        this.styleWarning.setFont(this.fontWarning);
    }

    public void createCellTexteByLigne(int colonne, String value, int line) {
        this.cell = this.sheet.getRow(line).createCell(colonne);
        this.cell.setCellStyle(this.styleTexte);
        this.cell.setCellValue(value);
    }

    public void createCellTexteByLigneAndStyle(int colonne, String value, int line, CellStyle style) {
        this.cell = this.sheet.getRow(line).createCell(colonne);
        this.cell.setCellStyle(style);
        this.cell.setCellValue(value);
    }

    public XSSFCellStyle addColor(CellStyle sourceStyle, Color c) {
        XSSFCellStyle res = (XSSFCellStyle) this.classeur.createCellStyle();
        res.cloneStyleFrom(sourceStyle);
        res.setFillForegroundColor(new XSSFColor(c));
        res.setFillPattern(PatternFormatting.SOLID_FOREGROUND);
        return res;
    }
}
