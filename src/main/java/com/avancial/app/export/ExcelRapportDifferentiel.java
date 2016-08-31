/**
 * 
 */
package com.avancial.app.export;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.streaming.SXSSFSheet;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.data.objetsMetier.PlanTransport.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Compartiment;
import com.avancial.app.data.objetsMetier.PlanTransport.Composition;
import com.avancial.app.data.objetsMetier.PlanTransport.Desserte;
import com.avancial.app.data.objetsMetier.PlanTransport.Distribution;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.FareProfile;
import com.avancial.app.data.objetsMetier.PlanTransport.GareHoraire;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.OrigineDestination;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Repas;
import com.avancial.app.data.objetsMetier.PlanTransport.Restriction;
import com.avancial.app.data.objetsMetier.PlanTransport.ServiceABord;
import com.avancial.app.data.objetsMetier.PlanTransport.Siege;
import com.avancial.app.data.objetsMetier.PlanTransport.Specification;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.TypeEquipement;
import com.avancial.app.export.generateColonneNew.GenerateExcelColonneNewFactory;
import com.avancial.app.export.generateColonneNew.IGenerateExcelColonneNew;
import com.avancial.app.resources.constants.APP_Field;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;
import com.avancial.app.utilitaire.MapPlansDeTransport;

/**
 * @author hamza.laterem
 *
 */
@SuppressWarnings("rawtypes")
public class ExcelRapportDifferentiel extends ASocleExportExcelService {
   /**
   * 
   */
   private static final long                serialVersionUID               = 1L;
   public static int                        NUMBER_SHEET                   = 5;
   public static String                     REPORT_FOR                     = "Report for:";
   public static String                     DATE_IMPORT_DRAFT              = "Draft dataset loaded on:";
   public static String                     DATE_IMPORT_ACTIVE             = "Compared with dataset imported on:";
   public static String                     SHEET_NEW                      = "NEW";
   public static String                     SHEET_DELETE                   = "DELETE";
   public static String                     SHEET_REGIMESPLIT              = "REGIMESPLIT";
   public static String                     SHEET_MODIFY                   = "MODIFY";
   public static String                     SHEET_UNCHANGED                = "UNCHANGED";
   public static String[]                   ENTETE_SHEET_NEW               = { "Train", "Tranche", "Régime Tranche", "Company", "Tranche Status", "Valid for RR", "Regime_Dessertes", "Dessertes", "Regime OD Tranche", "OD Tranche", "Regime Distrib", "IndicDistrib", "Regime Compo", "Classes", "Compo",
         "RameCodes", "RM Code", "Regime_CodeSAT", "CodeSAT", "Regime_FareProfileCode", "FareProfileCode", "Regime Eqp_Type", "Eqp_Type", "Regime Services", "Services by Class & OD", "Regime_Meal", "Meal Type", "Regime_Specif", "Specificities", "Regime_Restrictions", "Restrictions" };
   public static String[]                   ENTETE_SHEET_MODIFY            = { "Train", "Tranche", "Field", "Field Value Regime (if applicable)", "Previous Field Value", "New Field Value" };
   public static String[]                   ENTETE_SHEET_REGIMESPLIT       = { "Train", "Tranche", "Field", "Regime", "Value" };
   public static String[]                   ENTETE_SHEET_UNCHANGED_DELETE  = { "Train", "Tranche", "Régime Tranche" };

   protected MapComparaisonPlanTransport    datas;
   protected MapPlansDeTransport            mapPlansDeTransport;
   protected Map<Class<?>, Integer>         mapPremiereColonneNew;
   protected Map<Class<?>, Integer>         mapDerniereColonneNew;
   protected GenerateExcelColonneNewFactory generateExcelColonneNewFactory = new GenerateExcelColonneNewFactory();

   private void init() {
      this.datas = new MapComparaisonPlanTransport();
      this.initMapPremiereColonne();
      this.initMapDerniereColonne();
   }

   /**
   * 
   */
   public ExcelRapportDifferentiel() throws Exception {
      this.init();
   }

   /**
    * @param xlsx
    * @throws Exception
    */
   public ExcelRapportDifferentiel(boolean xlsx) throws Exception {
      super(xlsx);
      this.init();
   }

   /**
    * @param xlsx
    * @param fileName
    * @param filePath
    * @throws Exception
    */
   public ExcelRapportDifferentiel(boolean xlsx, String fileName, String filePath) throws Exception {
      super(xlsx, fileName, filePath);
      this.init();
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
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.app.export.ASocleExportExcelService#generatePreEnteteBySheet ()
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
      if (this.nameCurrentSheet.equals(SHEET_DELETE) || this.nameCurrentSheet.equals(SHEET_UNCHANGED))
         this.generateEnteteForSheetDeleteOrUnchanged(this.nameCurrentSheet);
   }

   private void generateEnteteForSheetDeleteOrUnchanged(String nameCurrentSheet) {
      // Gestion de la premiere ligne
      this.excelTools.createCellTexteWithStyle(1, nameCurrentSheet.equals(SHEET_UNCHANGED) ? "Identical Data" : "Removed Entries", this.excelTools.styleEnteteGris);
      this.excelTools.addMergedRegion(this.ligne - 1, this.ligne - 1, 1, ENTETE_SHEET_UNCHANGED_DELETE.length);
      this.excelTools.createRow(this.ligne++);
      // Gestion de la deuxieme ligne
      for (int i = 0; i < ENTETE_SHEET_UNCHANGED_DELETE.length; i++) {
         this.excelTools.createCellTexteWithStyle(i + 1, ENTETE_SHEET_UNCHANGED_DELETE[i], this.excelTools.styleEnteteGris);
      }

   }

   private void generateEnteteForSheetRegimeSplit() {
      // Gestion de la premiere ligne
      this.excelTools.createCellTexteWithStyle(1, "Modified Entries", this.excelTools.styleEnteteGris);
      this.excelTools.addMergedRegion(this.ligne - 1, this.ligne - 1, 1, ENTETE_SHEET_REGIMESPLIT.length);
      this.excelTools.createRow(this.ligne++);
      // Gestion de la deuxieme ligne
      for (int i = 0; i < ENTETE_SHEET_REGIMESPLIT.length; i++) {
         this.excelTools.createCellTexteWithStyle(i + 1, ENTETE_SHEET_REGIMESPLIT[i], this.excelTools.styleEnteteGris);
      }
   }

   private void generateEnteteForSheetModify() {
      // Gestion de la premiere ligne
      this.excelTools.createCellTexteWithStyle(1, "Modified Entries", this.excelTools.styleEnteteGris);
      this.excelTools.addMergedRegion(this.ligne - 1, this.ligne - 1, 1, ENTETE_SHEET_MODIFY.length);
      this.excelTools.createRow(this.ligne++);
      // Gestion de la deuxieme ligne
      for (int i = 0; i < ENTETE_SHEET_MODIFY.length; i++) {
         this.excelTools.createCellTexteWithStyle(i + 1, ENTETE_SHEET_MODIFY[i], this.excelTools.styleEnteteGris);
      }
   }

   private void generateEnteteForSheetNew() {
      // Gestion de la premiere ligne
      this.excelTools.createCellTexteWithStyle(1, "New Entries", this.excelTools.styleEnteteGris);
      this.excelTools.addMergedRegion(this.ligne - 1, this.ligne - 1, 1, ENTETE_SHEET_NEW.length);
      this.excelTools.createRow(this.ligne++);
      // Gestion de la deuxieme ligne
      for (int i = 0; i < ENTETE_SHEET_NEW.length; i++) {
         this.excelTools.createCellTexteWithStyle(i + 1, ENTETE_SHEET_NEW[i], this.excelTools.styleEnteteGris);
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.app.export.ASocleExportExcelService#generateHideLineBySheet( )
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
   protected void generateContentBySheet() throws Exception {
      try {
         this.ligne = this.firstLineContent[this.numCurrentSheet];

         if (this.nameCurrentSheet.equals(SHEET_NEW))
            this.generateContentForSheetNew();
         if (this.nameCurrentSheet.equals(SHEET_MODIFY))
            this.generateContentForSheetModify();
         if (this.nameCurrentSheet.equals(SHEET_REGIMESPLIT))
            this.generateContentForSheetRegimeSplit();
         if (this.nameCurrentSheet.equals(SHEET_UNCHANGED) || this.nameCurrentSheet.equals(SHEET_DELETE))
            this.generateContentForSheetUnchangedOrDelete(this.nameCurrentSheet);
      } catch (Exception e) {
         throw e;
      }

   }

   // "Train","Tranche","Régime Tranche"
   private void generateContentForSheetUnchangedOrDelete(String nameCurrentSheet) {
      // for (IComparaisonPlanTransport comparaison : this.datas) {
      // ComparaisonPlanTransport data = ((ComparaisonPlanTransport) comparaison);
      // if (data.getTypeComparaisonPlanTransport().toString().equals(nameCurrentSheet)) {
      // this.excelTools.createRow(this.ligne++);
      // this.excelTools.createCellTexte(1, data.getNumeroTrain());
      // this.excelTools.createCellTexte(2, data.getNumeroTranche());
      // this.excelTools.createCellTexte(3, mapPlansDeTransport.get(1).get().getTrainByNumeroTrain(data.getNumeroTrain()).getTrancheByNumeroTranche(data.getNumeroTranche()).getRegime().getCodeRegime());
      // }
      // }
   }

   private void generateContentForSheetRegimeSplit() {
      // int debutRowTrain = 0;
      //
      // // for (IComparaisonPlanTransport comparaison : this.datas) {
      // ComparaisonPlanTransport data = null;
      // ComparaisonPlanTransport dataCompare = null;
      //
      // for (int i = 0; i < this.datas.size(); i++) {
      // // initialisation des styles pour les cellule
      //
      // data = ((ComparaisonPlanTransport) this.datas.get(i));
      //
      // if (data.getTypeComparaisonPlanTransport().toString().equals(SHEET_REGIMESPLIT)) {
      //
      // debutRowTrain = this.ligne;
      // // Creation d'une nouvelle ligne pour un train
      // this.excelTools.createRow(this.ligne++);
      //
      // Boolean isfirst = true;
      // for (int j = i; j < this.datas.size(); j++) {
      // dataCompare = (ComparaisonPlanTransport) this.datas.get(j);
      // // Si le même Type && le meme n° train meme n° tranche et
      // // même class du nouveau field
      // if (dataCompare.getTypeComparaisonPlanTransport().toString().equals(SHEET_REGIMESPLIT) && dataCompare.getNumeroTrain().equals(data.getNumeroTrain()) && dataCompare.getNumeroTranche().equals(data.getNumeroTranche())
      // && dataCompare.getNouveauField().getClass().toString().equals(data.getNouveauField().getClass().toString())) {
      // if (!isfirst)
      // this.excelTools.createRow(this.ligne++);
      //
      // this.excelTools.createCellTexte(1, data.getNumeroTrain());
      // this.excelTools.createCellTexte(2, data.getNumeroTranche());
      //
      // if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.FareProfile.toString())) {
      // this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
      // this.excelTools.createCellTexte(4, ((FareProfile) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getRegime().getCodeRegime());
      // this.excelTools.createCellTexte(5, ((FareProfile) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getFareProfileCode());
      // } else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.CodeSat.toString())) {
      // this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
      // this.excelTools.createCellTexte(4, ((CodeSat) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getRegime().getCodeRegime());
      // this.excelTools.createCellTexte(5, ((CodeSat) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getCodeSat());
      // } else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.Desserte.toString())) {
      // this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
      // this.excelTools.createCellTexte(4, ((Desserte) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getRegime().getCodeRegime());
      // for (GareHoraire gareHoraire : ((Desserte) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getGareHoraires()) {
      // this.excelTools.createCellTexte(5, (gareHoraire.getGare().getCodeGare() + "(" + ((gareHoraire.getHoraire().getHoraireDebut() != null) ? gareHoraire.getHoraire().getHoraireDebut().toString() : "/") + ")\n"));
      // }
      // } else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.Distribution.toString())) {
      // this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
      // this.excelTools.createCellTexte(4, ((Distribution) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getRegime().getCodeRegime());
      // this.excelTools.createCellTexte(5, ((Distribution) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getIndiceDistribution());
      // } else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.OrigineDestination.toString())) {
      //
      // this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
      // this.excelTools.createCellTexte(4, ((OrigineDestination) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getRegime().getCodeRegime());
      // /**
      // * TODO OrigineDestination
      // */
      // // this.excelTools.createCellTexte(5,
      // // ((OrigineDestination)
      // // (((ComparaisonPlanTransport)
      // // this.datas.get(j)).getNouveauField())).);
      //
      // } else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.Repas.toString())) {
      //
      // this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
      // this.excelTools.createCellTexte(4, ((Repas) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getRegime().getCodeRegime());
      // this.excelTools.createCellTexte(5, ((Repas) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getTypeRepas().toString());
      //
      // } else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.Restriction.toString())) {
      //
      // this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
      // this.excelTools.createCellTexte(4, ((Restriction) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getRegime().getCodeRegime());
      // this.excelTools.createCellTexte(5, ((Restriction) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getType().toString() + " from " + ((Restriction) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getOrigine().getCodeGare() + "to"
      // + ((Restriction) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getDestination().toString());
      //
      // } else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.ServiceABord.toString())) {
      //
      // this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
      // this.excelTools.createCellTexte(4, ((ServiceABord) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getRegime().getCodeRegime());
      // this.excelTools.createCellTexte(5, ((ServiceABord) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getCodeService() + " - " + ((ServiceABord) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getClasse().toString() + " - "
      // + ((ServiceABord) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getOrigine().getCodeGare() + " to " + ((ServiceABord) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getDestination().getCodeGare());
      // } else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.TypeEquipement.toString())) {
      // this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
      // this.excelTools.createCellTexte(4, ((TypeEquipement) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getRegime().getCodeRegime());
      // this.excelTools.createCellTexte(5, ((TypeEquipement) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getTypeEquipement());
      // }
      //
      // else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.Specification.toString())) {
      //
      // this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
      // this.excelTools.createCellTexte(4, ((Specification) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getRegime().getCodeRegime());
      //
      // String valueSpecification = "";
      //
      // for (Compartiment compartiment : ((Specification) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getVoiture().getCompartiments()) {
      // for (Siege siege : compartiment.getSieges()) {
      // valueSpecification += "Coach " + ((Specification) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getVoiture().getNumeroVoiture() + " ,";
      // valueSpecification += " Seat " + siege.getNumeroSiege() + ", " + ((Specification) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getEtat().toString();
      // valueSpecification += "\n";
      // }
      //
      // if (compartiment.getNumeroCompartiment() != null) {
      // valueSpecification += "Coach " + ((Specification) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getVoiture().getNumeroVoiture() + " ,";
      // valueSpecification += " Compartment " + compartiment.getNumeroCompartiment() + ", " + ((Specification) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getEtat().toString();
      // valueSpecification += "\n";
      // }
      // }
      //
      // this.excelTools.createCellTexte(5, valueSpecification);
      // } else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.Composition.toString())) {
      // this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
      // this.excelTools.createCellTexte(4, ((Composition) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getRegime().getCodeRegime());
      //
      // String valueComposition = "";
      // for (int h = 0; h < (((Composition) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getVoitures()).size(); h++) {
      // valueComposition += (((Composition) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getVoitures()).get(h).getNumeroVoiture();
      // if (h < (((Composition) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getVoitures()).size() - 1)
      // valueComposition += ", ";
      // }
      //
      // valueComposition += " = ";
      // valueComposition += ((Composition) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getCodeRame() + ",";
      // valueComposition += valueComposition += ((Composition) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getCodeClasse() + ",";
      // valueComposition += valueComposition += ((Composition) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getCodeDiag();
      //
      // this.excelTools.createCellTexte(5, valueComposition);
      // }
      //
      // isfirst = false;
      // } else {
      // i = j - 1;
      // }
      // }
      //
      // // Merge la colonne train par nombre de ligne / Tranche / Field
      // // / Regine et Value
      // this.excelTools.addMergedRegion(debutRowTrain, this.ligne - 1, 1, 1, this.excelTools.styleBorder);
      // this.excelTools.addMergedRegion(debutRowTrain, this.ligne - 1, 2, 2, this.excelTools.styleBorder);
      // this.excelTools.addMergedRegion(debutRowTrain, this.ligne - 1, 3, 3, this.excelTools.styleBorder);
      // }
      // }
      //
   }

   private String getFieldName(String simpleName) {
      String res = "";

      if (simpleName.equals(APP_Field.CodeSat.toString()))
         res = "CodeSat";
      if (simpleName.equals(APP_Field.Desserte.toString()))
         res = "Stop";
      if (simpleName.equals(APP_Field.Distribution.toString()))
         res = "Distribution";
      if (simpleName.equals(APP_Field.FareProfile.toString()))
         res = "FareProfile";
      if (simpleName.equals(APP_Field.OrigineDestination.toString()))
         res = "OrigineDestination";
      if (simpleName.equals(APP_Field.Repas.toString()))
         res = "MealType";
      if (simpleName.equals(APP_Field.Restriction.toString()))
         res = "Restriction";
      if (simpleName.equals(APP_Field.ServiceABord.toString()))
         res = "Services";
      if (simpleName.equals(APP_Field.Specification.toString()))
         res = "Specification";
      if (simpleName.equals(APP_Field.TypeEquipement.toString()))
         res = "TypeEquipement";
      if (simpleName.equals(APP_Field.Composition.toString()))
         res = "Composition";

      return res;
   }

   private void generateContentForSheetModify() {
      // for (IComparaisonPlanTransport comparaison : this.datas) {
      // ComparaisonPlanTransport data = ((ComparaisonPlanTransport) comparaison);
      // if (data.getTypeComparaisonPlanTransport().toString().equals(SHEET_MODIFY)) {
      // // Creation d'une nouvelle ligne pour un train
      // this.excelTools.createRow(this.ligne++);
      //
      // this.excelTools.createCellTexte(1, data.getNumeroTrain());
      // this.excelTools.createCellTexte(2, data.getNumeroTranche());
      //
      // if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.FareProfile.toString())) {
      // this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
      // this.excelTools.createCellTexte(4, ((FareProfile) data.getNouveauField()).getRegime().getCodeRegime());
      // this.excelTools.createCellTexte(5, ((FareProfile) data.getAncienField()).getFareProfileCode());
      // this.excelTools.createCellTexte(6, ((FareProfile) data.getNouveauField()).getFareProfileCode());
      // }
      //
      // else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.CodeSat.toString())) {
      // this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
      // this.excelTools.createCellTexte(4, ((CodeSat) data.getNouveauField()).getRegime().getCodeRegime());
      // this.excelTools.createCellTexte(5, ((CodeSat) data.getAncienField()).getCodeSat());
      // this.excelTools.createCellTexte(6, ((CodeSat) data.getNouveauField()).getCodeSat());
      // }
      //
      // else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.Desserte.toString())) {
      // this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
      // this.excelTools.createCellTexte(4, ((Desserte) data.getNouveauField()).getRegime().getCodeRegime());
      //
      // for (GareHoraire gareHoraire : ((Desserte) data.getAncienField()).getGareHoraires()) {
      // this.excelTools.createCellTexte(5, (gareHoraire.getGare().getCodeGare() + "(" + ((gareHoraire.getHoraire().getHoraireDebut() != null) ? gareHoraire.getHoraire().getHoraireDebut().toString() : "/") + ")\n"));
      // }
      //
      // for (GareHoraire gareHoraire : ((Desserte) data.getNouveauField()).getGareHoraires()) {
      // this.excelTools.createCellTexte(6, (gareHoraire.getGare().getCodeGare() + "(" + ((gareHoraire.getHoraire().getHoraireDebut() != null) ? gareHoraire.getHoraire().getHoraireDebut().toString() : "/") + ")\n"));
      // }
      // }
      //
      // else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.Distribution.toString())) {
      // this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
      // this.excelTools.createCellTexte(4, ((Distribution) data.getNouveauField()).getRegime().getCodeRegime());
      // this.excelTools.createCellTexte(5, ((Distribution) data.getAncienField()).getIndiceDistribution());
      // this.excelTools.createCellTexte(6, ((Distribution) data.getNouveauField()).getIndiceDistribution());
      // }
      //
      // else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.OrigineDestination.toString())) {
      //
      // this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
      // this.excelTools.createCellTexte(4, ((OrigineDestination) data.getNouveauField()).getRegime().getCodeRegime());
      //
      // /**
      // * TODO OrigineDestination
      // */
      // // this.excelTools.createCellTexte(5, ((OrigineDestination)
      // // (((ComparaisonPlanTransport)
      // // this.datas.get(j)).getNouveauField())).);
      //
      // } else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.Repas.toString())) {
      // this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
      // this.excelTools.createCellTexte(4, ((Repas) data.getNouveauField()).getRegime().getCodeRegime());
      // this.excelTools.createCellTexte(5, ((Repas) data.getAncienField()).getTypeRepas().toString());
      // this.excelTools.createCellTexte(6, ((Repas) data.getNouveauField()).getTypeRepas().toString());
      // } else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.Restriction.toString())) {
      // this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
      // this.excelTools.createCellTexte(4, ((Restriction) data.getNouveauField()).getRegime().getCodeRegime());
      // String ancienValue = ((Restriction) data.getAncienField()).getType().toString() + " from " + ((Restriction) data.getAncienField()).getOrigine().getCodeGare() + "to" + ((Restriction) data.getAncienField()).getDestination().toString();
      // String nouvelleValue = ((Restriction) data.getNouveauField()).getType().toString() + " from " + ((Restriction) data.getNouveauField()).getOrigine().getCodeGare() + "to" + ((Restriction) data.getNouveauField()).getDestination().toString();
      // this.excelTools.createCellTexte(5, ancienValue);
      // this.excelTools.createCellTexte(5, nouvelleValue);
      // } else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.ServiceABord.toString())) {
      // this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
      // this.excelTools.createCellTexte(4, ((ServiceABord) data.getNouveauField()).getRegime().getCodeRegime());
      //
      // String ancienValue = ((ServiceABord) data.getAncienField()).getCodeService() + " - " + ((ServiceABord) data.getAncienField()).getClasse().toString() + " - " + ((ServiceABord) data.getAncienField()).getOrigine().getCodeGare() + " to "
      // + ((ServiceABord) data.getAncienField()).getDestination().getCodeGare();
      // String nouvelleValue = ((ServiceABord) data.getNouveauField()).getCodeService() + " - " + ((ServiceABord) data.getNouveauField()).getClasse().toString() + " - " + ((ServiceABord) data.getNouveauField()).getOrigine().getCodeGare() + " to "
      // + ((ServiceABord) data.getNouveauField()).getDestination().getCodeGare();
      //
      // this.excelTools.createCellTexte(5, ancienValue);
      // this.excelTools.createCellTexte(6, nouvelleValue);
      // }
      //
      // else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.TypeEquipement.toString())) {
      // this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
      // this.excelTools.createCellTexte(4, ((TypeEquipement) data.getNouveauField()).getRegime().getCodeRegime());
      // this.excelTools.createCellTexte(5, ((TypeEquipement) data.getAncienField()).getTypeEquipement());
      // this.excelTools.createCellTexte(6, ((TypeEquipement) data.getNouveauField()).getTypeEquipement());
      // }
      //
      // else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.Specification.toString())) {
      // this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
      // this.excelTools.createCellTexte(4, ((Specification) data.getNouveauField()).getRegime().getCodeRegime());
      // String ancienSpecification = "";
      // for (Compartiment compartiment : ((Specification) data.getAncienField()).getVoiture().getCompartiments()) {
      // for (Siege siege : compartiment.getSieges()) {
      // ancienSpecification += "Coach " + ((Specification) data.getAncienField()).getVoiture().getNumeroVoiture() + " ,";
      // ancienSpecification += " Seat " + siege.getNumeroSiege() + ", " + ((Specification) data.getAncienField()).getEtat().toString();
      // ancienSpecification += "\n";
      // }
      //
      // if (compartiment.getNumeroCompartiment() != null) {
      // ancienSpecification += "Coach " + ((Specification) data.getAncienField()).getVoiture().getNumeroVoiture() + " ,";
      // ancienSpecification += " Compartment " + compartiment.getNumeroCompartiment() + ", " + ((Specification) data.getAncienField()).getEtat().toString();
      // ancienSpecification += "\n";
      // }
      // }
      //
      // this.excelTools.createCellTexte(5, ancienSpecification);
      // String nouvelleSpecification = "";
      // for (Compartiment compartiment : ((Specification) data.getNouveauField()).getVoiture().getCompartiments()) {
      // for (Siege siege : compartiment.getSieges()) {
      // nouvelleSpecification += "Coach " + ((Specification) data.getNouveauField()).getVoiture().getNumeroVoiture() + " ,";
      // nouvelleSpecification += " Seat " + siege.getNumeroSiege() + ", " + ((Specification) data.getNouveauField()).getEtat().toString();
      // nouvelleSpecification += "\n";
      // }
      //
      // if (compartiment.getNumeroCompartiment() != null) {
      // nouvelleSpecification += "Coach " + ((Specification) data.getNouveauField()).getVoiture().getNumeroVoiture() + " ,";
      // nouvelleSpecification += " Compartment " + compartiment.getNumeroCompartiment() + ", " + ((Specification) data.getNouveauField()).getEtat().toString();
      // nouvelleSpecification += "\n";
      // }
      // }
      //
      // this.excelTools.createCellTexte(6, nouvelleSpecification);
      // }
      //
      // else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.Composition.toString())) {
      // this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
      // this.excelTools.createCellTexte(4, ((Composition) data.getNouveauField()).getRegime().getCodeRegime());
      // String ancienComposition = "";
      // for (int h = 0; h < (((Composition) data.getAncienField()).getVoitures()).size(); h++) {
      // ancienComposition += (((Composition) data.getAncienField()).getVoitures()).get(h).getNumeroVoiture();
      // if (h < (((Composition) data.getAncienField()).getVoitures()).size() - 1)
      // ancienComposition += ", ";
      // }
      //
      // ancienComposition += " = ";
      // ancienComposition += ((Composition) data.getAncienField()).getCodeRame() + ",";
      // ancienComposition += ((Composition) data.getAncienField()).getCodeClasse() + ",";
      // ancienComposition += ((Composition) data.getAncienField()).getCodeDiag();
      // this.excelTools.createCellTexte(5, ancienComposition);
      // String nouvelleComposition = "";
      // for (int h = 0; h < (((Composition) data.getNouveauField()).getVoitures()).size(); h++) {
      // nouvelleComposition += (((Composition) data.getNouveauField()).getVoitures()).get(h).getNumeroVoiture();
      // if (h < (((Composition) data.getNouveauField()).getVoitures()).size() - 1)
      // nouvelleComposition += ", ";
      // }
      //
      // nouvelleComposition += " = ";
      // nouvelleComposition += ((Composition) data.getNouveauField()).getCodeRame() + ",";
      // nouvelleComposition += ((Composition) data.getNouveauField()).getCodeClasse() + ",";
      // nouvelleComposition += ((Composition) data.getNouveauField()).getCodeDiag();
      // this.excelTools.createCellTexte(6, nouvelleComposition);
      // }
      // }
      // }

   }

   private void generateContentForSheetNew() throws ClassNotFoundException, IOException {
      int debutRowTrain = 0;
      boolean isFirst = true;

      this.excelTools.createRow(this.ligne++);

      int sizeAllCompa = this.datas.size();
      int cntTraiTranche = 1;

      for (IComparaisonPlanTransport comparaison : this.datas.getComparaison(EnumTypeComparaisonPlanTransport.NEW, null)) {
         isFirst = true;
         ComparaisonPlanTransport data = ((ComparaisonPlanTransport) comparaison);

         Train currentTrain = ((PlanTransport) this.mapPlansDeTransport.get(2).get()).getTrainByNumeroTrain(data.getNumeroTrain());
         Tranche currentTranche = currentTrain.getTrancheByNumeroTranche(data.getNumeroTranche());

         debutRowTrain = this.ligne - 1;
         this.writeTrainTrancheForSheetNew(data, currentTrain, currentTranche);

         for (Class<?> regime : currentTranche.getAttributs().keySet()) {
            IGenerateExcelColonneNew generateExcelColonneNew = this.generateExcelColonneNewFactory.get(regime);
            List<ASousRegimeTranche> liste = (List<ASousRegimeTranche>) currentTranche.getAttributsField(regime);
            this.excelTools.setRow(debutRowTrain);
            generateExcelColonneNew.generate(liste, this.mapPremiereColonneNew.get(regime), this.excelTools);
         }

         this.ligne = this.excelTools.getSheet().getLastRowNum() + 1;
         // this.writeTrainTrancheForSheetNewWithMerge(data, currentTrain, currentTranche, debutRowTrain);

         for (Class<?> regime : currentTranche.getAttributs().keySet()) {
            this.postTraitement(this.mapPremiereColonneNew.get(regime), this.mapDerniereColonneNew.get(regime), debutRowTrain, this.ligne - 1, this.excelTools);
         }
         this.excelTools.createRow(this.ligne++);
         ((SXSSFSheet) this.excelTools.getSheet()).flushRows(1);
      }

   }

   public void writeTrainTrancheForSheetNew(ComparaisonPlanTransport data, Train currentTrain, Tranche currentTranche) {
      for (int col = 7; col <= ENTETE_SHEET_NEW.length; col++) {
         if (col == 7)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotRight);
         else if (col == 8)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeft);
         else if (col == 9)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotRight);
         else if (col == 10)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeft);
         else if (col == 11)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotRight);
         else if (col == 12)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeft);
         else if (col == 13)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotRight);
         else if (col == 14)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeftNotRight);
         else if (col == 15)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeftNotRight);
         else if (col == 16)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeftNotRight);
         else if (col == 17)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeft);
         else if (col == 18)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotRight);
         else if (col == 19)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeft);
         else if (col == 20)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotRight);
         else if (col == 21)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeft);
         else if (col == 22)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotRight);
         else if (col == 23)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeft);
         else if (col == 24)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotRight);
         else if (col == 25)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeft);
         else if (col == 26)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotRight);
         else if (col == 27)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeft);
         else if (col == 28)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotRight);
         else if (col == 29)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeft);
         else if (col == 30)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotRight);
         else if (col == 31)
            this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeft);
         else
            this.excelTools.createCellVideWithStyle(col, this.excelTools.styleBorder);
      }

      this.excelTools.createCellTexte(1, data.getNumeroTrain());
      this.excelTools.createCellTexte(2, data.getNumeroTranche());
      this.excelTools.createCellTexte(3, currentTranche.getRegime().getCodeRegime());
      this.excelTools.createCellTexte(4, ((PlanTransport) this.mapPlansDeTransport.get(1).get()).getCompagnie().toString());
      this.excelTools.createCellTexte(5, currentTranche.getTrancheStatut().toString());
      this.excelTools.createCellTexte(6, currentTrain.writeIsValidForRR());
   }

   public void writeTrainTrancheForSheetNew(ComparaisonPlanTransport data, Train currentTrain, Tranche currentTranche, int line) {
      for (int col = 7; col <= ENTETE_SHEET_NEW.length; col++) {
         if (col == 7)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotRight);
         else if (col == 8)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeft);
         else if (col == 9)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotRight);
         else if (col == 10)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeft);
         else if (col == 11)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotRight);
         else if (col == 12)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeft);
         else if (col == 13)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotRight);
         else if (col == 14)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeftNotRight);
         else if (col == 15)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeftNotRight);
         else if (col == 16)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeftNotRight);
         else if (col == 17)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeft);
         else if (col == 18)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotRight);
         else if (col == 19)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeft);
         else if (col == 20)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotRight);
         else if (col == 21)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeft);
         else if (col == 22)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotRight);
         else if (col == 23)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeft);
         else if (col == 24)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotRight);
         else if (col == 25)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeft);
         else if (col == 26)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotRight);
         else if (col == 27)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeft);
         else if (col == 28)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotRight);
         else if (col == 29)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeft);
         else if (col == 30)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotRight);
         else if (col == 31)
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeft);
         else
            this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorder);
      }

      this.excelTools.createCellTexteByLigneAndStyle(1, data.getNumeroTrain(), line, this.excelTools.styleTexte);

      this.excelTools.createCellTexteByLigneAndStyle(2, data.getNumeroTranche(), line, this.excelTools.styleTexte);

      this.excelTools.createCellTexteByLigneAndStyle(3, currentTranche.getRegime().getCodeRegime(), line, this.excelTools.styleTexte);

      this.excelTools.createCellTexteByLigneAndStyle(4, ((PlanTransport) this.mapPlansDeTransport.get(1).get()).getCompagnie().toString(), line, this.excelTools.styleTexte);

      this.excelTools.createCellTexteByLigneAndStyle(5, currentTranche.getTrancheStatut().toString(), line, this.excelTools.styleTexte);

      this.excelTools.createCellTexteByLigneAndStyle(6, currentTrain.writeIsValidForRR(), line, this.excelTools.styleTexte);
   }

   public void writeTrainTrancheForSheetNewWithMerge(ComparaisonPlanTransport data, Train currentTrain, Tranche currentTranche, int firstRow) {
      for (int i = 1; i < 7; i++) {
         this.excelTools.addMergedRegion(firstRow, this.ligne - 1, i, i, this.excelTools.styleBorder);

         // this.excelTools.addMergedRegion(firstRow, this.ligne-1, i, i);
      }
   }

   private void initMapPremiereColonne() {
      this.mapPremiereColonneNew = new HashMap<>();
      this.mapPremiereColonneNew.put(Desserte.class, 7);
      this.mapPremiereColonneNew.put(OrigineDestination.class, 9);
      this.mapPremiereColonneNew.put(Distribution.class, 11);
      this.mapPremiereColonneNew.put(Composition.class, 13);
      this.mapPremiereColonneNew.put(CodeSat.class, 18);
      this.mapPremiereColonneNew.put(FareProfile.class, 20);
      this.mapPremiereColonneNew.put(TypeEquipement.class, 22);
      this.mapPremiereColonneNew.put(ServiceABord.class, 24);
      this.mapPremiereColonneNew.put(Repas.class, 26);
      this.mapPremiereColonneNew.put(Specification.class, 28);
      this.mapPremiereColonneNew.put(Restriction.class, 30);
   }

   private void initMapDerniereColonne() {
      this.mapDerniereColonneNew = new HashMap<>();
      this.mapDerniereColonneNew.put(Desserte.class, 8);
      this.mapDerniereColonneNew.put(OrigineDestination.class, 10);
      this.mapDerniereColonneNew.put(Distribution.class, 12);
      this.mapDerniereColonneNew.put(Composition.class, 17);
      this.mapDerniereColonneNew.put(CodeSat.class, 19);
      this.mapDerniereColonneNew.put(FareProfile.class, 21);
      this.mapDerniereColonneNew.put(TypeEquipement.class, 23);
      this.mapDerniereColonneNew.put(ServiceABord.class, 25);
      this.mapDerniereColonneNew.put(Repas.class, 27);
      this.mapDerniereColonneNew.put(Specification.class, 29);
      this.mapDerniereColonneNew.put(Restriction.class, 31);
   }

   /**
    * @return the datas
    */
   public MapComparaisonPlanTransport getDatas() {
      return datas;
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
      return mapPlansDeTransport;
   }

   /**
    * @param mapPlansDeTransport
    *           the mapPlansDeTransport to set
    */
   public void setMapPlansDeTransport(MapPlansDeTransport mapPlansDeTransport) {
      this.mapPlansDeTransport = mapPlansDeTransport;
   }

   public void postTraitement(int premiereColonne, int derniereColonne, int premiereLigne, int derniereLigne, ExcelTools excelTools) {

      boolean isEqual = true;
      for (int i = premiereColonne + 1; i <= derniereColonne && isEqual; i++) {
         excelTools.setRow(premiereLigne);
         // TODO : tester s'il y a bien une valeur
         if (excelTools.getCell(i) == null || excelTools.getCell(i).getStringCellValue().isEmpty() || excelTools.getCell(i).getStringCellValue().equals("")) {
            isEqual = false;
            break;
         }
         String valRef = excelTools.getCell(i).getStringCellValue();
         for (int j = premiereLigne + 1; j <= derniereLigne && isEqual; j++) {
            excelTools.setRow(j);
            if (excelTools.getCell(i) == null || excelTools.getCell(i).getCellType() == Cell.CELL_TYPE_BLANK) {
               /* Plus de cellules remplies */
               break;
            }
            isEqual = excelTools.getCell(i).getStringCellValue().equals(valRef);
         }
      }
      if (isEqual) {
         /* Faire le merge */
         /* Cas de la première colonne : Régime -> ALL */
         excelTools.addMergedRegion(premiereLigne, derniereLigne, premiereColonne, premiereColonne, "ALL", excelTools.styleBorderNotRight);

         /* Cas des autres colonnes */
         for (int i = premiereColonne + 1; i <= derniereColonne; i++) {
            excelTools.setRow(premiereLigne);
            String valRef = excelTools.getCell(i).getStringCellValue();
            if (i == derniereColonne) {
               excelTools.addMergedRegion(premiereLigne, derniereLigne, i, i, valRef, excelTools.styleBorderNotLeft);
            } else {
               excelTools.addMergedRegion(premiereLigne, derniereLigne, i, i, valRef, excelTools.styleBorderNotLeftNotRight);
            }
         }
      } else {
         /* Remplir les cellules vides */
         excelTools.setRow(derniereLigne);
         int currentLine = derniereLigne;
         while ((excelTools.getCell() == null || (excelTools.getCell(premiereColonne) != null && excelTools.getCell(premiereColonne).getCellType() == Cell.CELL_TYPE_BLANK)) && currentLine >= premiereLigne) {
            excelTools.createCellTexteWithStyle(premiereColonne, "", excelTools.styleBorderNotRight);
            for (int col = premiereColonne + 1; col < derniereColonne; col++) {
               excelTools.createCellTexteWithStyle(col, "", excelTools.styleBorderNotLeftNotRight);
            }
            excelTools.createCellTexteWithStyle(derniereColonne, "", excelTools.styleBorderNotLeft);
            excelTools.setRow(--currentLine);
         }
      }
   }

}
