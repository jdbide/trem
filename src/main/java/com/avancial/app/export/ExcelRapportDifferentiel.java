/**
 * 
 */
package com.avancial.app.export;

import java.util.ArrayList;
import java.util.List;

import com.avancial.app.data.objetsMetier.PlanTransport.*;
import com.avancial.app.resources.constants.APP_Field;
import com.avancial.app.utilitaire.MapPlansDeTransport;

/**
 * @author hamza.laterem
 *
 */
public class ExcelRapportDifferentiel extends ASocleExportExcelService {
   public static int                         NUMBER_SHEET                  = 5;
   public static String                      REPORT_FOR                    = "Report for:";
   public static String                      DATE_IMPORT_DRAFT             = "Draft dataset loaded on:";
   public static String                      DATE_IMPORT_ACTIVE            = "Compared with dataset imported on:";
   public static String                      SHEET_NEW                     = "NEW";
   public static String                      SHEET_DELETE                  = "DELETE";
   public static String                      SHEET_REGIMESPLIT             = "REGIMESPLIT";
   public static String                      SHEET_MODIFY                  = "MODIFY";
   public static String                      SHEET_UNCHANGED               = "UNCHANGED";
   public static String[]                    ENTETE_SHEET_NEW              = { "Train", "Tranche", "Régime Tranche", "Company", "Tranche Status", "Valid for RR", "Regime_Dessertes", "Dessertes", "Regime OD Tranche", "OD Tranche", "Regime Distrib", "IndicDistrib", "Regime Compo", "Classes", "Compo",
         "RameCodes", "RM Code", "Regime_CodeSAT", "CodeSAT", "Regime_FareProfileCode", "FareProfileCode", "Regime Eqp_Type", "Eqp_Type", "Regime Services", "Services by Class & OD", "Regime_Meal", "Meal Type", "Regime_Specif", "Specificities", "Regime_Restrictions", "Restrictions" };
   public static String[]                    ENTETE_SHEET_MODIFY           = { "Train", "Tranche", "Field", "Field Value Regime (if applicable)", "Previous Field Value", "New Field Value" };
   public static String[]                    ENTETE_SHEET_REGIMESPLIT      = { "Train", "Tranche", "Field", "Regime", "Value" };
   public static String[]                    ENTETE_SHEET_UNCHANGED_DELETE = { "Train", "Tranche", "Régime Tranche" };

   protected List<IComparaisonPlanTransport> datas;
   protected MapPlansDeTransport             mapPlansDeTransport;

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
      if (this.nameCurrentSheet.equals(SHEET_UNCHANGED) || this.nameCurrentSheet.equals(SHEET_DELETE))
         this.generateContentForSheetUnchangedOrDelete(this.nameCurrentSheet);
   }

   // "Train","Tranche","Régime Tranche"
   private void generateContentForSheetUnchangedOrDelete(String nameCurrentSheet) {
      for (IComparaisonPlanTransport comparaison : this.datas) {
         ComparaisonPlanTransport data = ((ComparaisonPlanTransport) comparaison);
         if (data.getTypeComparaisonPlanTransport().toString().equals(nameCurrentSheet)) {
            this.excelTools.createRow(this.ligne++);
            this.excelTools.createCellTexte(1, data.getNumeroTrain());
            this.excelTools.createCellTexte(2, data.getNumeroTranche());
            this.excelTools.createCellTexte(3, mapPlansDeTransport.get(1).get().getTrainByNumeroTrain(data.getNumeroTrain()).getTrancheByNumeroTranche(data.getNumeroTranche()).getRegime().getCodeRegime());
         }
      }
   }

   private void generateContentForSheetRegimeSplit() {
      int debutRowTrain = 0;

      // for (IComparaisonPlanTransport comparaison : this.datas) {
      ComparaisonPlanTransport data = null;
      int idSousRegimeTranche = -1;

      for (int i = 0; i < this.datas.size(); i++) {
         data = ((ComparaisonPlanTransport) this.datas.get(i));

         if (data.getTypeComparaisonPlanTransport().toString().equals(SHEET_REGIMESPLIT)) {

            debutRowTrain = this.ligne;
            // Creation d'une nouvelle ligne pour un train
            this.excelTools.createRow(this.ligne++);

            Boolean isfirst = true;
            for (int j = i; j < this.datas.size(); j++) {
               // Si le même Type && le meme n° train meme n° tranche et même class du nouveau field
               if (((ComparaisonPlanTransport) this.datas.get(j)).getTypeComparaisonPlanTransport().toString().equals(SHEET_REGIMESPLIT) && ((ComparaisonPlanTransport) this.datas.get(j)).getNumeroTrain().equals(data.getNumeroTrain())
                     && ((ComparaisonPlanTransport) this.datas.get(j)).getNumeroTranche().equals(data.getNumeroTranche()) && ((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField().getClass().toString().equals(data.getNouveauField().getClass().toString())) {
                  if (!isfirst)
                     this.excelTools.createRow(this.ligne++);
                  
                  this.excelTools.createCellTexte(1, data.getNumeroTrain());
                  this.excelTools.createCellTexte(2, data.getNumeroTranche());

                  if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.FareProfile.toString())) {
                     this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
                     this.excelTools.createCellTexte(4, ((FareProfile) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getRegime().getCodeRegime());
                     this.excelTools.createCellTexte(5, ((FareProfile) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getFareProfileCode());
                  } else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.CodeSat.toString())) {
                     this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
                     this.excelTools.createCellTexte(4, ((CodeSat) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getRegime().getCodeRegime());
                     this.excelTools.createCellTexte(5, ((CodeSat) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getCodeSat());
                  } else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.Desserte.toString())) {
                     this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
                     this.excelTools.createCellTexte(4, ((Desserte) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getRegime().getCodeRegime());
                     for (GareHoraire gareHoraire : ((Desserte) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getGareHoraires()) {
                        this.excelTools.createCellTexte(5, (gareHoraire.getGare().getCodeGare() + "(" + ((gareHoraire.getHoraire().getHoraireDebut() != null) ? gareHoraire.getHoraire().getHoraireDebut().toString() : "/") + ")\n"));
                     }
                  } else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.Distribution.toString())) {
                     this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
                     this.excelTools.createCellTexte(4, ((Distribution) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getRegime().getCodeRegime());
                     this.excelTools.createCellTexte(5, ((Distribution) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getIndiceDistribution());
                  } else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.OrigineDestination.toString())) {
                     
                     this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
                     this.excelTools.createCellTexte(4, ((OrigineDestination) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getRegime().getCodeRegime());
                     /**
                      * TODO OrigineDestination
                      */
                     //this.excelTools.createCellTexte(5, ((OrigineDestination) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).);
                  
                  } else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.Repas.toString())) {
                     
                     this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
                     this.excelTools.createCellTexte(4, ((Repas) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getRegime().getCodeRegime());
                     this.excelTools.createCellTexte(5, ((Repas) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getTypeRepas().toString());
                  
                  } else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.Restriction.toString())) {
                     
                     this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
                     this.excelTools.createCellTexte(4, ((Restriction) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getRegime().getCodeRegime());
                     this.excelTools.createCellTexte(5, ((Restriction) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getType().toString() + 
                           " from " + 
                           ((Restriction) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getOrigine().getCodeGare() + 
                           "to" +
                           ((Restriction) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getDestination().toString());
                  
                  } else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.ServiceABord.toString())) {
                     
                     this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
                     this.excelTools.createCellTexte(4, ((ServiceABord) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getRegime().getCodeRegime());
                     this.excelTools.createCellTexte(5, ((ServiceABord) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getCodeService() + " - " +
                           ((ServiceABord) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getClasse().toString() + " - " +
                           ((ServiceABord) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getOrigine().getCodeGare() + " to " +
                           ((ServiceABord) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getDestination().getCodeGare());
                  } else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.TypeEquipement.toString())) {
                     this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
                     this.excelTools.createCellTexte(4, ((TypeEquipement) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getRegime().getCodeRegime());
                     this.excelTools.createCellTexte(5, ((TypeEquipement) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getTypeEquipement());
                  } 
                  
                  else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.Specification.toString())) {
                     
                     this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
                     this.excelTools.createCellTexte(4, ((Specification) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getRegime().getCodeRegime());
                     
                     String valueSpecification = "";
                     
                     for (Compartiment compartiment : ((Specification) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getVoiture().getCompartiments()) {
                        for (Siege siege : compartiment.getSieges()) {
                           valueSpecification += "Coach " + ((Specification) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getVoiture().getNumeroVoiture() + " ,";
                           valueSpecification +=" Seat " + siege.getNumeroSiege() + ", " + ((Specification) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getEtat().toString();
                           valueSpecification +="\n";
                        } 
                        
                        if (compartiment.getNumeroCompartiment() != null) {
                           valueSpecification += "Coach " + ((Specification) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getVoiture().getNumeroVoiture() + " ,";
                           valueSpecification +=" Compartment " + compartiment.getNumeroCompartiment() + ", " + ((Specification) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getEtat().toString();
                           valueSpecification +="\n";
                        } 
                     }

                     this.excelTools.createCellTexte(5,valueSpecification);
                  }  else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.Composition.toString())) {
                     this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
                     this.excelTools.createCellTexte(4, ((Composition) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getRegime().getCodeRegime());
                     
                     String valueComposition = "";
                     for (int h = 0; h < (((Composition) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getVoitures()).size(); h++) {
                        valueComposition += (((Composition) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getVoitures()).get(h).getNumeroVoiture(); 
                        if (h < (((Composition) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getVoitures()).size() - 1)
                           valueComposition += ", ";
                     }
                     
                     valueComposition += " = ";
                     valueComposition += ((Composition) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getCodeRame() + ",";
                     valueComposition += valueComposition += ((Composition) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getCodeClasse() + ",";
                     valueComposition += valueComposition += ((Composition) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).getCodeDiag();
                     
                     this.excelTools.createCellTexte(5, valueComposition);
                  }

                  isfirst = false;
               } else {
                  i = j - 1;
               }
            }

            // Merge la colonne train par nombre de ligne / Tranche / Field / Regine et Value
            this.excelTools.addMergedRegion(debutRowTrain, this.ligne - 1, 1, 1, this.excelTools.styleBorder);
            this.excelTools.addMergedRegion(debutRowTrain, this.ligne - 1, 2, 2, this.excelTools.styleBorder);
            this.excelTools.addMergedRegion(debutRowTrain, this.ligne - 1, 3, 3, this.excelTools.styleBorder);
         }
      }

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

   private void generateContentSheetRegimeSplitByField(ComparaisonPlanTransport data) {
      Tranche tranche = mapPlansDeTransport.get(1).get().getTrainByNumeroTrain(data.getNumeroTrain()).getTrancheByNumeroTranche(data.getNumeroTranche());
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
    * @param datas
    *           the datas to set
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
    * @param mapPlansDeTransport
    *           the mapPlansDeTransport to set
    */
   public void setMapPlansDeTransport(MapPlansDeTransport mapPlansDeTransport) {
      this.mapPlansDeTransport = mapPlansDeTransport;
   }

}
