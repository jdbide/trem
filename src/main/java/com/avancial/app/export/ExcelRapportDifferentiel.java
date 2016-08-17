/**
 * 
 */
package com.avancial.app.export;

import java.util.ArrayList;
import java.util.List;

import com.avancial.app.data.objetsMetier.PlanTransport.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.utilitaire.MapPlansDeTransport;

/**
 * @author hamza.laterem
 *
 */
public class ExcelRapportDifferentiel extends ASocleExportExcelService {
   public static int    NUMBER_SHEET       = 5;
   public static String REPORT_FOR         = "Report for:";
   public static String DATE_IMPORT_DRAFT  = "Draft dataset loaded on:";
   public static String DATE_IMPORT_ACTIVE = "Compared with dataset imported on:";
   public static String SHEET_NEW          = "NEW";
   public static String SHEET_DELETE       = "DELETE";
   public static String SHEET_REGIMESPLIT  = "REGIMESPLIT";
   public static String SHEET_MODIFY       = "MODIFY";
   public static String SHEET_UNCHANGED    = "UNCHANGED";
   public static String [] ENTETE_SHEET_NEW={
         "Train","Tranche","Régime Tranche","Company","Tranche Status","Valid for RR",
         "Regime_Dessertes","Dessertes","Regime OD Tranche","OD Tranche","Regime Distrib",
         "IndicDistrib","Regime Compo","Classes","Compo","RameCodes","RM Code","Regime_CodeSAT","CodeSAT",
         "Regime_FareProfileCode","FareProfileCode","Regime Eqp_Type","Eqp_Type","Regime Services",
         "Services by Class & OD","Regime_Meal","Meal Type","Regime_Specif","Specificities","Regime_Restrictions","Restrictions"};
   public static String [] ENTETE_SHEET_MODIFY={"Train","Tranche","Field","Field Value Regime (if applicable)","Previous Field Value","New Field Value"};
   public static String [] ENTETE_SHEET_REGIMESPLIT={"Train","Tranche","Field","Regime","Value"};
   public static String [] ENTETE_SHEET_UNCHANGED_DELETE={"Train","Tranche","Régime Tranche"};
   
   protected List<IComparaisonPlanTransport> datas;
   protected MapPlansDeTransport   mapPlansDeTransport;

   /**
    * 
    */
   public ExcelRapportDifferentiel() throws Exception {
      this.datas = new ArrayList<>();
   }

   /**
    * @param xlsx
    * @throws Exception
    */
   public ExcelRapportDifferentiel(boolean xlsx) throws Exception {
      super(xlsx);
      this.datas = new ArrayList<>();
   }

   /**
    * @param xlsx
    * @param fileName
    * @param filePath
    * @throws Exception
    */
   public ExcelRapportDifferentiel(boolean xlsx, String fileName, String filePath) throws Exception {
      super(xlsx, fileName, filePath);
      this.datas = new ArrayList<>();
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
    * @see com.avancial.app.export.ASocleExportExcelService#chargeData()
    */
   @Override
   protected void chargeData() {
      List<IComparaisonPlanTransport> datasOrder = new ArrayList<>();
      
      for (int i = 0; i < nameSheet.length; i++) {
         datasOrder.addAll(this.orderByTypeComparaisonPlanTransport(nameSheet[i]));
      }
      
      this.datas.clear();
      this.datas.addAll(datasOrder);
   }

   

   private List<IComparaisonPlanTransport> orderByTypeComparaisonPlanTransport(String typeComparaison) {
      List<IComparaisonPlanTransport> datasOrder = new ArrayList<>();

      for (IComparaisonPlanTransport comparaison : this.datas) {
         if (((ComparaisonPlanTransport) comparaison).getTypeComparaisonPlanTransport().toString().equals(typeComparaison))
            datasOrder.add(comparaison);
      }
      
      return datasOrder;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.app.export.ASocleExportExcelService#generatePreEnteteBySheet()
    */
   @Override
   protected void generatePreEnteteBySheet() {

      this.ligne = firstLinePreEntete[this.numCurrentSheet];
      this.excelTools.createRow(this.ligne++);
      this.excelTools.createCellTexteWithStyle(1, REPORT_FOR, this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(2, "", this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(3, "", this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(4, "Eurostar", this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(5, "Production", this.excelTools.styleEnteteJaune);

      this.excelTools.createRow(this.ligne++);
      this.excelTools.createCellTexteWithStyle(1, DATE_IMPORT_DRAFT, this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(2, "", this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(3, "", this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(4, "20/05/2016 - 17:15", this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(5, "", this.excelTools.styleEnteteJaune);

      this.excelTools.createRow(this.ligne++);
      this.excelTools.createCellTexteWithStyle(1, DATE_IMPORT_ACTIVE, this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(2, "", this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(3, "", this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(4, "19/05/2016 - 17:18", this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(5, "", this.excelTools.styleEnteteJaune);
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
      
      if (this.nameCurrentSheet.equals(SHEET_NEW))
         this.generateEnteteForSheetNew();
      if (this.nameCurrentSheet.equals(SHEET_MODIFY))
         this.generateEnteteForSheetModify();
      if (this.nameCurrentSheet.equals(SHEET_REGIMESPLIT))
         this.generateEnteteForSheetRegimeSplit();
      if (this.nameCurrentSheet.equals(SHEET_DELETE))
         this.generateEnteteForSheetDelete();
      if (this.nameCurrentSheet.equals(SHEET_UNCHANGED))
         this.generateEnteteForSheetUnchanged();

   }

   private void generateEnteteForSheetUnchanged() {      
      // Gestion de la premiere ligne
      this.excelTools.createCellTexteWithStyle(1, "Identical Data", this.excelTools.styleEnteteGris);
      this.excelTools.addMergedRegion(this.ligne-1, this.ligne-1, 1, ENTETE_SHEET_UNCHANGED_DELETE.length);
      this.excelTools.createRow(this.ligne++);
      // Gestion de la deuxieme ligne
      for (int i = 0; i < ENTETE_SHEET_UNCHANGED_DELETE.length; i++) {
         this.excelTools.createCellTexteWithStyle(i+1, ENTETE_SHEET_UNCHANGED_DELETE[i], this.excelTools.styleEnteteGris);
      }
   }

   private void generateEnteteForSheetDelete() {
      // Gestion de la premiere ligne
      this.excelTools.createCellTexteWithStyle(1, "Removed Entries", this.excelTools.styleEnteteGris);
      this.excelTools.addMergedRegion(this.ligne-1, this.ligne-1, 1, ENTETE_SHEET_UNCHANGED_DELETE.length);
      this.excelTools.createRow(this.ligne++);
      // Gestion de la deuxieme ligne
      for (int i = 0; i < ENTETE_SHEET_UNCHANGED_DELETE.length; i++) {
         this.excelTools.createCellTexteWithStyle(i+1, ENTETE_SHEET_UNCHANGED_DELETE[i], this.excelTools.styleEnteteGris);
      }
   }

   private void generateEnteteForSheetRegimeSplit() {
      // Gestion de la premiere ligne
      this.excelTools.createCellTexteWithStyle(1, "Modified Entries", this.excelTools.styleEnteteGris);
      this.excelTools.addMergedRegion(this.ligne-1, this.ligne-1, 1, ENTETE_SHEET_REGIMESPLIT.length);
      this.excelTools.createRow(this.ligne++);
      // Gestion de la deuxieme ligne
      for (int i = 0; i < ENTETE_SHEET_REGIMESPLIT.length; i++) {
         this.excelTools.createCellTexteWithStyle(i+1, ENTETE_SHEET_REGIMESPLIT[i], this.excelTools.styleEnteteGris);
      }
   }

   private void generateEnteteForSheetModify() {
      // Gestion de la premiere ligne
      this.excelTools.createCellTexteWithStyle(1, "Modified Entries", this.excelTools.styleEnteteGris);
      this.excelTools.addMergedRegion(this.ligne-1, this.ligne-1, 1, ENTETE_SHEET_MODIFY.length);
      this.excelTools.createRow(this.ligne++);
      // Gestion de la deuxieme ligne
      for (int i = 0; i < ENTETE_SHEET_MODIFY.length; i++) {
         this.excelTools.createCellTexteWithStyle(i+1, ENTETE_SHEET_MODIFY[i], this.excelTools.styleEnteteGris);
      }
   }

   private void generateEnteteForSheetNew() {
      // Gestion de la premiere ligne
      this.excelTools.createCellTexteWithStyle(1, "New Entries", this.excelTools.styleEnteteGris);
      this.excelTools.addMergedRegion(this.ligne-1, this.ligne-1, 1, ENTETE_SHEET_NEW.length);
      this.excelTools.createRow(this.ligne++);
      // Gestion de la deuxieme ligne
      for (int i = 0; i < ENTETE_SHEET_NEW.length; i++) {
         this.excelTools.createCellTexteWithStyle(i+1, ENTETE_SHEET_NEW[i], this.excelTools.styleEnteteGris);
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.app.export.ASocleExportExcelService#generateHideLineBySheet()
    */
   @Override
   protected void generateHideLineBySheet() {
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.app.export.ASocleExportExcelService#generateContentBySheet()
    */
   @Override
   protected void generateContentBySheet() {
      this.ligne = this.firstLineContent[this.numCurrentSheet];
      
      if (this.nameCurrentSheet.equals(SHEET_NEW))
         this.generateContentForSheetNew();
      if (this.nameCurrentSheet.equals(SHEET_MODIFY))
         this.generateContentForSheetModify();
      if (this.nameCurrentSheet.equals(SHEET_REGIMESPLIT))
         this.generateContentForSheetRegimeSplit();
      if (this.nameCurrentSheet.equals(SHEET_DELETE))
         this.generateContentForSheetDelete();
      if (this.nameCurrentSheet.equals(SHEET_UNCHANGED))
         this.generateContentForSheetUnchanged();
   }

   private void generateContentForSheetUnchanged() {
      for (IComparaisonPlanTransport comparaison : this.datas) {
         ComparaisonPlanTransport data = ((ComparaisonPlanTransport) comparaison);
         if (data.getTypeComparaisonPlanTransport().toString().equals(SHEET_UNCHANGED)) {
            this.excelTools.createRow(this.ligne++);
            
            
            
         }
      }
   }

   private void generateContentForSheetDelete() {
      for (IComparaisonPlanTransport comparaison : this.datas) {
         ComparaisonPlanTransport data = ((ComparaisonPlanTransport) comparaison);
         if (data.getTypeComparaisonPlanTransport().toString().equals(SHEET_DELETE)) {
            
         }
      }
      
   }

   private void generateContentForSheetRegimeSplit() {
      for (IComparaisonPlanTransport comparaison : this.datas) {
         ComparaisonPlanTransport data = ((ComparaisonPlanTransport) comparaison);
         if (data.getTypeComparaisonPlanTransport().toString().equals(SHEET_REGIMESPLIT)) {
            
         }
      }
      
   }

   private void generateContentForSheetModify() {
      for (IComparaisonPlanTransport comparaison : this.datas) {
         ComparaisonPlanTransport data = ((ComparaisonPlanTransport) comparaison);
         if (data.getTypeComparaisonPlanTransport().toString().equals(SHEET_MODIFY)) {
            
         }
      }
      
   }

   private void generateContentForSheetNew() {
      for (IComparaisonPlanTransport comparaison : this.datas) {
         ComparaisonPlanTransport data = ((ComparaisonPlanTransport) comparaison);
         if (data.getTypeComparaisonPlanTransport().toString().equals(SHEET_NEW)) {
            
         }
      }
      
   }

   /**
    * @return the datas
    */
   public List<IComparaisonPlanTransport> getDatas() {
      return datas;
   }

   /**
    * @param datas the datas to set
    */
   public void setDatas(List<IComparaisonPlanTransport> datas) {
      this.datas = datas;
   }

   /**
    * @return the mapPlansDeTransport
    */
   public MapPlansDeTransport getMapPlansDeTransport() {
      return mapPlansDeTransport;
   }

   /**
    * @param mapPlansDeTransport the mapPlansDeTransport to set
    */
   public void setMapPlansDeTransport(MapPlansDeTransport mapPlansDeTransport) {
      this.mapPlansDeTransport = mapPlansDeTransport;
   }

}
