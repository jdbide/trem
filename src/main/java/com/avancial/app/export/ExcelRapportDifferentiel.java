/**
 * 
 */
package com.avancial.app.export;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;
import com.avancial.app.utilitaire.MapPlansDeTransport;

/**
 * Génération du rapport différentiel
 * 
 * @author hamza.laterem
 *
 */
public class ExcelRapportDifferentiel extends ASocleExportExcelService {

   private static Logger                        logger                          = Logger.getLogger(ExcelRapportDifferentiel.class);
   /**
   * 
   */
   private static final long                    serialVersionUID                = 1L;

   /**
    * Nombre de feuilles (sheets) dans le fichier Excel
    */
   public static int                            NUMBER_SHEET                    = 5;

   /* Libellés */
   public static String                         REPORT_FOR                      = "Report for:";
   public static String                         DATE_IMPORT_DRAFT               = "Draft dataset loaded on:";
   public static String                         DATE_IMPORT_ACTIVE              = "Compared with dataset imported on:";
   public static String                         SHEET_NEW                       = "NEW";
   public static String                         SHEET_DELETE                    = "DELETE";
   public static String                         SHEET_REGIMESPLIT               = "REGIMESPLIT";
   public static String                         SHEET_MODIFY                    = "MODIFY";
   public static String                         SHEET_UNCHANGED                 = "UNCHANGED";

   /**
    * Map contenant toutes les données de comparaison à afficher dans le rapport
    */
   protected MapComparaisonPlanTransport        datas                           = new MapComparaisonPlanTransport();
   /**
    * Map contenant les plans de transport comparés pour le rapport
    */
   protected MapPlansDeTransport                mapPlansDeTransport;
   /**
    * Factory pour les générateurs des feuilles Excel du rapport
    */
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
         // Pas de lignes masquées
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
    * @see com.avancial.app.export.ASocleExportExcelService#generateEnteteBySheet()
    */
   @Override
   protected void generateEnteteBySheet() {
      this.ligne = this.firstLineEntete[this.numCurrentSheet];
      this.excelTools.createRow(this.ligne++);

      logger.info("Début génération entête onglet " + this.nameCurrentSheet);
      this.rapportDifferentielSheetFactory.get(this.nameCurrentSheet).generateEntete(this.excelTools, this.ligne);
      logger.info("Fin génération entête onglet " + this.nameCurrentSheet);
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.app.export.ASocleExportExcelService#generateContentBySheet()
    */
   @Override
   protected void generateContentBySheet() throws Exception {
      this.ligne = this.firstLineContent[this.numCurrentSheet];

      logger.info("Début génération contenu onglet " + this.nameCurrentSheet);
      this.rapportDifferentielSheetFactory.get(this.nameCurrentSheet).generateContent(this.excelTools, this.ligne, this.datas,
            this.mapPlansDeTransport);
      logger.info("Fin génération contenu onglet " + this.nameCurrentSheet);

   }

   @Override
   protected void chargeData() {
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.app.export.ASocleExportExcelService#generatePreEnteteBySheet ()
    */
   @Override
   protected void generatePreEnteteBySheet() {

      this.ligne = this.firstLinePreEntete[this.numCurrentSheet];
      this.excelTools.createRow(this.ligne++);
      this.excelTools.createCellTexteWithStyle(1, REPORT_FOR, this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(2, "", this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(3, "", this.excelTools.styleEnteteJaune);
      CompagnieEnvironnementEntity compagnieEnvironnement = this.mapPlansDeTransport.getJeuDonneesDraft().getCompagnieEnvironnement();
      this.excelTools.createCellTexteWithStyle(4, compagnieEnvironnement == null ? "" : compagnieEnvironnement.getLibelleCompagnie(),
            this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(5, compagnieEnvironnement == null ? "" : compagnieEnvironnement.getLibelleEnvironnement(),
            this.excelTools.styleEnteteJaune);

      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YY - HH:mm");
      this.excelTools.createRow(this.ligne++);
      this.excelTools.createCellTexteWithStyle(1, DATE_IMPORT_DRAFT, this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(2, "", this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(3, "", this.excelTools.styleEnteteJaune);
      Date dateImport = this.mapPlansDeTransport.getJeuDonneesDraft().getDateCreateJeuDonnees();
      this.excelTools.createCellTexteWithStyle(4,
            dateImport == null ? "" : formatter.format(this.mapPlansDeTransport.getJeuDonneesDraft().getDateCreateJeuDonnees()),
            this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(5, "", this.excelTools.styleEnteteJaune);

      this.excelTools.createRow(this.ligne++);
      this.excelTools.createCellTexteWithStyle(1, DATE_IMPORT_ACTIVE, this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(2, "", this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(3, "", this.excelTools.styleEnteteJaune);
      dateImport = this.mapPlansDeTransport.getJeuDonneesActive().getDateCreateJeuDonnees();
      this.excelTools.createCellTexteWithStyle(4,
            dateImport == null ? "" : formatter.format(this.mapPlansDeTransport.getJeuDonneesActive().getDateCreateJeuDonnees()),
            this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(5, "", this.excelTools.styleEnteteJaune);
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.app.export.ASocleExportExcelService#generateHideLineBySheet( )
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
    *           the datas to set
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
    *           the mapPlansDeTransport to set
    */
   public void setMapPlansDeTransport(MapPlansDeTransport mapPlansDeTransport) {
      this.mapPlansDeTransport = mapPlansDeTransport;
   }

}
