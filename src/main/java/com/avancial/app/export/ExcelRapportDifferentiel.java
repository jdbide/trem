/**
 * 
 */
package com.avancial.app.export;

import java.text.SimpleDateFormat;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;
import com.avancial.app.utilitaire.MapPlansDeTransport;

/**
 * @author hamza.laterem
 *
 */
public class ExcelRapportDifferentiel extends ASocleExportExcelService {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;
    public static int NUMBER_SHEET = 5;
    public static String REPORT_FOR = "Report for:";
    public static String DATE_IMPORT_DRAFT = "Draft dataset loaded on:";
    public static String DATE_IMPORT_ACTIVE = "Compared with dataset imported on:";
    public static String SHEET_NEW = "NEW";
    public static String SHEET_DELETE = "DELETE";
    public static String SHEET_REGIMESPLIT = "REGIMESPLIT";
    public static String SHEET_MODIFY = "MODIFY";
    public static String SHEET_UNCHANGED = "UNCHANGED";

    protected MapComparaisonPlanTransport datas = new MapComparaisonPlanTransport();
    protected MapPlansDeTransport mapPlansDeTransport;
    private ExcelRapportDifferentielSheetFactory rapportDifferentielSheetFactory = new ExcelRapportDifferentielSheetFactory();

    /**
    * 
    */
    public ExcelRapportDifferentiel() throws Exception {
    }

    /**
     * @param xlsx
     * @throws Exception
     */
    public ExcelRapportDifferentiel(boolean xlsx) throws Exception {
        super(xlsx);
    }

    /**
     * @param xlsx
     * @param fileName
     * @param filePath
     * @throws Exception
     */
    public ExcelRapportDifferentiel(boolean xlsx, String fileName, String filePath) throws Exception {
        super(xlsx, fileName, filePath);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.avancial.app.export.ASocleExportExcelService#initVarStatic()
     */
    @Override
    protected void initVarStatic() {
        this.firstLinePreEntete = new int[NUMBER_SHEET];
        this.firstLineEntete = new int[NUMBER_SHEET];
        this.firstLineHide = new int[NUMBER_SHEET];

        this.firstLineContent = new int[NUMBER_SHEET];
        this.numberColumnMax = new int[NUMBER_SHEET];
        this.nameSheet = new String[NUMBER_SHEET];
        this.lockSheet = new boolean[NUMBER_SHEET];

        for (int i = 0; i < NUMBER_SHEET; i++) {
            this.firstLinePreEntete[i] = 1;

            this.firstLineEntete[i] = 5;

            this.firstLineContent[i] = 7;
            // Pas de lignes masuqés
            this.firstLineHide[i] = -1;

            this.lockSheet[i] = false;
        }

        this.numberColumnMax[0] = 32;
        this.nameSheet[0] = SHEET_NEW;

        this.numberColumnMax[1] = 6;
        this.nameSheet[1] = SHEET_MODIFY;

        this.numberColumnMax[2] = 5;
        this.nameSheet[2] = SHEET_REGIMESPLIT;

        this.numberColumnMax[3] = 3;
        this.nameSheet[3] = SHEET_DELETE;

        this.numberColumnMax[4] = 3;
        this.nameSheet[4] = SHEET_UNCHANGED;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.avancial.app.export.ASocleExportExcelService#generateEnteteBySheet()
     */
    @Override
    protected void generateEnteteBySheet() {
        this.ligne = this.firstLineEntete[this.numCurrentSheet];
        this.excelTools.createRow(this.ligne++);

        this.rapportDifferentielSheetFactory.get(this.nameCurrentSheet).generateEntete(this.excelTools, this.ligne);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.avancial.app.export.ASocleExportExcelService#generateContentBySheet()
     */
    @Override
    protected void generateContentBySheet() throws Exception {
        try {
            this.ligne = this.firstLineContent[this.numCurrentSheet];

            this.rapportDifferentielSheetFactory.get(this.nameCurrentSheet).generateContent(this.excelTools, this.ligne,
                    this.datas, this.mapPlansDeTransport);
        }
        catch (Exception e) {
            throw e;
        }

    }

    @Override
    protected void chargeData() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.avancial.app.export.ASocleExportExcelService#generatePreEnteteBySheet
     * ()
     */
    @Override
    protected void generatePreEnteteBySheet() {

        this.ligne = this.firstLinePreEntete[this.numCurrentSheet];
        this.excelTools.createRow(this.ligne++);
        this.excelTools.createCellTexteWithStyle(1, REPORT_FOR, this.excelTools.styleEnteteJaune);
        this.excelTools.createCellTexteWithStyle(2, "", this.excelTools.styleEnteteJaune);
        this.excelTools.createCellTexteWithStyle(3, "", this.excelTools.styleEnteteJaune);
        this.excelTools.createCellTexteWithStyle(4,
                this.mapPlansDeTransport.getJeuDonneesDraft().getCompagnieEnvironnement().getLibelleCompagnie(),
                this.excelTools.styleEnteteJaune);
        this.excelTools.createCellTexteWithStyle(5,
                this.mapPlansDeTransport.getJeuDonneesDraft().getCompagnieEnvironnement().getLibelleEnvironnement(),
                this.excelTools.styleEnteteJaune);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YY - HH:mm");
        this.excelTools.createRow(this.ligne++);
        this.excelTools.createCellTexteWithStyle(1, DATE_IMPORT_DRAFT, this.excelTools.styleEnteteJaune);
        this.excelTools.createCellTexteWithStyle(2, "", this.excelTools.styleEnteteJaune);
        this.excelTools.createCellTexteWithStyle(3, "", this.excelTools.styleEnteteJaune);
        this.excelTools.createCellTexteWithStyle(4,
                formatter.format(this.mapPlansDeTransport.getJeuDonneesDraft().getDateCreateJeuDonnees()),
                this.excelTools.styleEnteteJaune);
        this.excelTools.createCellTexteWithStyle(5, "", this.excelTools.styleEnteteJaune);

        this.excelTools.createRow(this.ligne++);
        this.excelTools.createCellTexteWithStyle(1, DATE_IMPORT_ACTIVE, this.excelTools.styleEnteteJaune);
        this.excelTools.createCellTexteWithStyle(2, "", this.excelTools.styleEnteteJaune);
        this.excelTools.createCellTexteWithStyle(3, "", this.excelTools.styleEnteteJaune);
        this.excelTools.createCellTexteWithStyle(4,
                formatter.format(this.mapPlansDeTransport.getJeuDonneesActive().getDateCreateJeuDonnees()),
                this.excelTools.styleEnteteJaune);
        this.excelTools.createCellTexteWithStyle(5, "", this.excelTools.styleEnteteJaune);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.avancial.app.export.ASocleExportExcelService#generateHideLineBySheet(
     * )
     */
    @Override
    protected void generateHideLineBySheet() {
    }

    /**
     * @return the datas
     */
    public MapComparaisonPlanTransport getDatas() {
        return this.datas;
    }

    /**
     * @param datas
     *            the datas to set
     */
    public void setDatas(MapComparaisonPlanTransport datas) {
        this.datas = datas;
    }

    /**
     * @return the mapPlansDeTransport
     */
    public MapPlansDeTransport getMapPlansDeTransport() {
        return this.mapPlansDeTransport;
    }

    /**
     * @param mapPlansDeTransport
     *            the mapPlansDeTransport to set
     */
    public void setMapPlansDeTransport(MapPlansDeTransport mapPlansDeTransport) {
        this.mapPlansDeTransport = mapPlansDeTransport;
    }

}
