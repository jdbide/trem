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
         // initialisation des styles pour les cellule

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

   private void generateContentForSheetModify() {
      for (IComparaisonPlanTransport comparaison : this.datas) {
         ComparaisonPlanTransport data = ((ComparaisonPlanTransport) comparaison);
         if (data.getTypeComparaisonPlanTransport().toString().equals(SHEET_MODIFY)) {
            // Creation d'une nouvelle ligne pour un train
            this.excelTools.createRow(this.ligne++);
            
            this.excelTools.createCellTexte(1, data.getNumeroTrain());
            this.excelTools.createCellTexte(2, data.getNumeroTranche());
            
            if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.FareProfile.toString())) {
               this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
               this.excelTools.createCellTexte(4, ((FareProfile) data.getNouveauField()).getRegime().getCodeRegime());
               this.excelTools.createCellTexte(5, ((FareProfile) data.getAncienField()).getFareProfileCode());
               this.excelTools.createCellTexte(6, ((FareProfile) data.getNouveauField()).getFareProfileCode());
            } 
            
            else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.CodeSat.toString())) {
               this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
               this.excelTools.createCellTexte(4, ((CodeSat) data.getNouveauField()).getRegime().getCodeRegime());
               this.excelTools.createCellTexte(5, ((CodeSat) data.getAncienField()).getCodeSat());
               this.excelTools.createCellTexte(6, ((CodeSat) data.getNouveauField()).getCodeSat());
            }
            
            else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.Desserte.toString())) {
               this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));               
               this.excelTools.createCellTexte(4, ((Desserte) data.getNouveauField()).getRegime().getCodeRegime());
               
               for (GareHoraire gareHoraire : ((Desserte) data.getAncienField()).getGareHoraires()) {
                  this.excelTools.createCellTexte(5, (gareHoraire.getGare().getCodeGare() + "(" + ((gareHoraire.getHoraire().getHoraireDebut() != null) ? gareHoraire.getHoraire().getHoraireDebut().toString() : "/") + ")\n"));
               }
               
               for (GareHoraire gareHoraire : ((Desserte) data.getNouveauField()).getGareHoraires()) {
                  this.excelTools.createCellTexte(6, (gareHoraire.getGare().getCodeGare() + "(" + ((gareHoraire.getHoraire().getHoraireDebut() != null) ? gareHoraire.getHoraire().getHoraireDebut().toString() : "/") + ")\n"));
               }
            } 

            else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.Distribution.toString())) {
               this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
               this.excelTools.createCellTexte(4, ((Distribution) data.getNouveauField()).getRegime().getCodeRegime());
               this.excelTools.createCellTexte(5, ((Distribution) data.getAncienField()).getIndiceDistribution());
               this.excelTools.createCellTexte(6, ((Distribution) data.getNouveauField()).getIndiceDistribution());
            } 

            else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.OrigineDestination.toString())) {
               
               this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
               this.excelTools.createCellTexte(4, ((OrigineDestination) data.getNouveauField()).getRegime().getCodeRegime());
               
               /**
                * TODO OrigineDestination
                */
               //this.excelTools.createCellTexte(5, ((OrigineDestination) (((ComparaisonPlanTransport) this.datas.get(j)).getNouveauField())).);
            
            } else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.Repas.toString())) {               
               this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));               
               this.excelTools.createCellTexte(4, ((Repas) data.getNouveauField()).getRegime().getCodeRegime());
               this.excelTools.createCellTexte(5, ((Repas) data.getAncienField()).getTypeRepas().toString());
               this.excelTools.createCellTexte(6, ((Repas) data.getNouveauField()).getTypeRepas().toString());
            } 
            else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.Restriction.toString())) {
               this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
               this.excelTools.createCellTexte(4, ((Restriction) data.getNouveauField()).getRegime().getCodeRegime());
               String ancienValue = ((Restriction) data.getAncienField()).getType().toString() + " from " + ((Restriction) data.getAncienField()).getOrigine().getCodeGare() + "to" + ((Restriction) data.getAncienField()).getDestination().toString();
               String nouvelleValue = ((Restriction) data.getNouveauField()).getType().toString() + " from " + ((Restriction) data.getNouveauField()).getOrigine().getCodeGare() + "to" + ((Restriction) data.getNouveauField()).getDestination().toString();
               this.excelTools.createCellTexte(5, ancienValue);
               this.excelTools.createCellTexte(5, nouvelleValue);
            }
            else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.ServiceABord.toString())) {
               this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
               this.excelTools.createCellTexte(4, ((ServiceABord) data.getNouveauField()).getRegime().getCodeRegime());

               String ancienValue = ((ServiceABord) data.getAncienField()).getCodeService() + " - " + ((ServiceABord) data.getAncienField()).getClasse().toString() + " - " + ((ServiceABord) data.getAncienField()).getOrigine().getCodeGare() + " to " + ((ServiceABord) data.getAncienField()).getDestination().getCodeGare();
               String nouvelleValue = ((ServiceABord) data.getNouveauField()).getCodeService() + " - " + ((ServiceABord) data.getNouveauField()).getClasse().toString() + " - " + ((ServiceABord) data.getNouveauField()).getOrigine().getCodeGare() + " to " + ((ServiceABord) data.getNouveauField()).getDestination().getCodeGare();

               this.excelTools.createCellTexte(5, ancienValue);
               this.excelTools.createCellTexte(6, nouvelleValue);
            } 
            
            
            else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.TypeEquipement.toString())) {
               this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));               
               this.excelTools.createCellTexte(4, ((TypeEquipement) data.getNouveauField()).getRegime().getCodeRegime());
               this.excelTools.createCellTexte(5, ((TypeEquipement) data.getAncienField()).getTypeEquipement());
               this.excelTools.createCellTexte(6, ((TypeEquipement) data.getNouveauField()).getTypeEquipement());
            } 
            
            else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.Specification.toString())) {
               this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
               this.excelTools.createCellTexte(4, ((Specification) data.getNouveauField()).getRegime().getCodeRegime());
               String ancienSpecification = "";
               for (Compartiment compartiment : ((Specification) data.getAncienField()).getVoiture().getCompartiments()) {
                  for (Siege siege : compartiment.getSieges()) {
                     ancienSpecification += "Coach " + ((Specification) data.getAncienField()).getVoiture().getNumeroVoiture() + " ,";
                     ancienSpecification +=" Seat " + siege.getNumeroSiege() + ", " + ((Specification) data.getAncienField()).getEtat().toString();
                     ancienSpecification +="\n";
                  } 

                  if (compartiment.getNumeroCompartiment() != null) {
                     ancienSpecification += "Coach " + ((Specification) data.getAncienField()).getVoiture().getNumeroVoiture() + " ,";
                     ancienSpecification +=" Compartment " + compartiment.getNumeroCompartiment() + ", " + ((Specification) data.getAncienField()).getEtat().toString();
                     ancienSpecification +="\n";
                  } 
               }

               this.excelTools.createCellTexte(5,ancienSpecification);
               String nouvelleSpecification = "";
               for (Compartiment compartiment : ((Specification) data.getNouveauField()).getVoiture().getCompartiments()) {
                  for (Siege siege : compartiment.getSieges()) {
                     nouvelleSpecification += "Coach " + ((Specification) data.getNouveauField()).getVoiture().getNumeroVoiture() + " ,";
                     nouvelleSpecification +=" Seat " + siege.getNumeroSiege() + ", " + ((Specification) data.getNouveauField()).getEtat().toString();
                     nouvelleSpecification +="\n";
                  } 
                  
                  if (compartiment.getNumeroCompartiment() != null) {
                     nouvelleSpecification += "Coach " + ((Specification) data.getNouveauField()).getVoiture().getNumeroVoiture() + " ,";
                     nouvelleSpecification +=" Compartment " + compartiment.getNumeroCompartiment() + ", " + ((Specification) data.getNouveauField()).getEtat().toString();
                     nouvelleSpecification +="\n";
                  } 
               }

               this.excelTools.createCellTexte(6,nouvelleSpecification);
            }  
            
            else if (data.getNouveauField().getClass().getSimpleName().equals(APP_Field.Composition.toString())) {
               this.excelTools.createCellTexte(3, this.getFieldName(data.getNouveauField().getClass().getSimpleName()));
               this.excelTools.createCellTexte(4, ((Composition) data.getNouveauField()).getRegime().getCodeRegime());
               String ancienComposition = "";
               for (int h = 0; h < (((Composition) data.getAncienField()).getVoitures()).size(); h++) {
                  ancienComposition += (((Composition) data.getAncienField()).getVoitures()).get(h).getNumeroVoiture(); 
                  if (h < (((Composition) data.getAncienField()).getVoitures()).size() - 1)
                     ancienComposition += ", ";
               }

               ancienComposition += " = ";
               ancienComposition += ((Composition) data.getAncienField()).getCodeRame() + ",";
               ancienComposition += ((Composition) data.getAncienField()).getCodeClasse() + ",";
               ancienComposition += ((Composition) data.getAncienField()).getCodeDiag();
               this.excelTools.createCellTexte(5, ancienComposition);
               String nouvelleComposition = "";
               for (int h = 0; h < (((Composition) data.getNouveauField()).getVoitures()).size(); h++) {
                  nouvelleComposition += (((Composition) data.getNouveauField()).getVoitures()).get(h).getNumeroVoiture(); 
                  if (h < (((Composition) data.getNouveauField()).getVoitures()).size() - 1)
                     nouvelleComposition += ", ";
               }

               nouvelleComposition += " = ";
               nouvelleComposition += ((Composition) data.getNouveauField()).getCodeRame() + ",";
               nouvelleComposition += ((Composition) data.getNouveauField()).getCodeClasse() + ",";
               nouvelleComposition += ((Composition) data.getNouveauField()).getCodeDiag();
               this.excelTools.createCellTexte(6, nouvelleComposition);
            }
         }
      }

   }

   private void generateContentForSheetNew() throws ClassNotFoundException {
      int debutRowTrain = 0;
      boolean isFirst = true;
      
      for (IComparaisonPlanTransport comparaison : this.datas) {
         isFirst = true;
         ComparaisonPlanTransport data = ((ComparaisonPlanTransport) comparaison);
         if (data.getTypeComparaisonPlanTransport().toString().equals(SHEET_NEW)) {
            Train currentTrain = ((PlanTransport) this.mapPlansDeTransport.get(2).get()).getTrainByNumeroTrain(data.getNumeroTrain());
            Tranche currentTranche = currentTrain.getTrancheByNumeroTranche(data.getNumeroTranche());
            
            this.excelTools.createRow(this.ligne++);
            for (int col = 1; col <= ENTETE_SHEET_NEW.length; col++) {
               this.excelTools.createCellVideWithStyle(col, this.excelTools.styleBorder);
            }
            
            debutRowTrain = this.ligne;
            
            this.writeTrainTrancheForSheetNew(data, currentTrain, currentTranche);
            // Dessertes
            List<Desserte> dessertes = (List<Desserte>) currentTranche.getByAttributsField(APP_Field.Desserte.toString());
            for (Desserte desserte : dessertes) {
               if (!isFirst)
                  this.excelTools.createRow(this.ligne++);

               this.writeTrainTrancheForSheetNew(data, currentTrain, currentTranche);
               this.excelTools.createCellTexte(7, desserte.getRegime().getCodeRegime());
               for (GareHoraire gareHoraire : desserte.getGareHoraires()) {
                  this.excelTools.createCellTexte(8, (gareHoraire.getGare().getCodeGare() + " (" + ((gareHoraire.getHoraire().getHoraireDebut() != null) ? 
                        gareHoraire.getHoraire().getHoraireDebut().getHours() + ":" + gareHoraire.getHoraire().getHoraireDebut().getMinutes() : "/") + ")\n"));
                  
//                  if (!desserte.getGareHoraires().get(desserte.getGareHoraires().size() - 1).equals(gareHoraire))
                     this.excelTools.createRow(this.ligne++);
               }
            }

            //OD
            List<OrigineDestination> ods = (List<OrigineDestination>) currentTranche.getByAttributsField(APP_Field.OrigineDestination.toString());
            for (OrigineDestination od : ods) {
               if (!isFirst)
                  this.excelTools.createRow(this.ligne++);
               
               this.excelTools.createCellTexte(9, od.getRegime().getCodeRegime());
               this.excelTools.createCellTexte(10, od.getOrigine().getCodeGare() + " - " + od.getDestination().getCodeGare());
            }
            
            //distri
            List<Distribution> distributions = (List<Distribution>) currentTranche.getByAttributsField(APP_Field.Distribution.toString());
            for (Distribution distribution : distributions) {
               if (!isFirst)
                  this.excelTools.createRow(this.ligne++);
               
               this.excelTools.createCellTexte(11, distribution.getRegime().getCodeRegime());
               this.excelTools.createCellTexte(12, distribution.getIndiceDistribution());
            }
            // compos
            List<Composition> compositions = (List<Composition>) currentTranche.getByAttributsField(APP_Field.Composition.toString());
            for (Composition composition : compositions) {
               if (!isFirst)
                  this.excelTools.createRow(this.ligne++);
               
               this.excelTools.createCellTexte(13, composition.getRegime().getCodeRegime());
               this.excelTools.createCellTexte(14, composition.getCodeClasse());

               String nouvelleComposition = "";
               for (int h = 0; h < (((Composition) data.getNouveauField()).getVoitures()).size(); h++) {
                  nouvelleComposition += (((Composition) data.getNouveauField()).getVoitures()).get(h).getNumeroVoiture(); 
                  if (h < (((Composition) data.getNouveauField()).getVoitures()).size() - 1)
                     nouvelleComposition += ", ";
               }

               nouvelleComposition += " = ";
               nouvelleComposition += ((Composition) data.getNouveauField()).getCodeRame() + ",";
               nouvelleComposition += ((Composition) data.getNouveauField()).getCodeClasse() + ",";
               nouvelleComposition += ((Composition) data.getNouveauField()).getCodeDiag();
               this.excelTools.createCellTexte(15, nouvelleComposition);
               this.excelTools.createCellTexte(16, composition.getCodeRame());
               this.excelTools.createCellTexte(17, composition.getCodeRm());
            }
            
         // CodeSat
            List<CodeSat> codeSats = (List<CodeSat>) currentTranche.getByAttributsField(APP_Field.CodeSat.toString());
            for (CodeSat codeSat : codeSats) {
               if (!isFirst)
                  this.excelTools.createRow(this.ligne++);
               
               this.excelTools.createCellTexte(18, codeSat.getRegime().getCodeRegime());
               this.excelTools.createCellTexte(19, codeSat.getCodeSat());
            }
         // FareProfile
            List<FareProfile> fareProfiles = (List<FareProfile>) currentTranche.getByAttributsField(APP_Field.FareProfile.toString());
            for (FareProfile fareProfile : fareProfiles) {
               if (!isFirst)
                  this.excelTools.createRow(this.ligne++);
               
               this.excelTools.createCellTexte(20, fareProfile.getRegime().getCodeRegime());
               this.excelTools.createCellTexte(21, fareProfile.getFareProfileCode());
            }
            
         // TypeEquipement
            List<TypeEquipement> typeEquipements = (List<TypeEquipement>) currentTranche.getByAttributsField(APP_Field.TypeEquipement.toString());
            for (TypeEquipement typeEquipement : typeEquipements) {
               if (!isFirst)
                  this.excelTools.createRow(this.ligne++);
               
               this.excelTools.createCellTexte(22, typeEquipement.getRegime().getCodeRegime());
               this.excelTools.createCellTexte(23, typeEquipement.getTypeEquipement());
            }
            
         // Service
            List<ServiceABord> services = (List<ServiceABord>) currentTranche.getByAttributsField(APP_Field.ServiceABord.toString());
            for (ServiceABord service : services) {
               if (!isFirst)
                  this.excelTools.createRow(this.ligne++);
               
               String value = service.getCodeService() + " - " + service.getClasse().toString() + " - " + service.getOrigine().getCodeGare() + " to " + service.getDestination().getCodeGare();

               this.excelTools.createCellTexte(24, service.getRegime().getCodeRegime());
               this.excelTools.createCellTexte(25, value);
               
            }
            
         // repas
            List<Repas> listRepas = (List<Repas>) currentTranche.getByAttributsField(APP_Field.Repas.toString());
            for (Repas repas : listRepas) {
               if (!isFirst)
                  this.excelTools.createRow(this.ligne++);
               
               this.excelTools.createCellTexte(26, repas.getRegime().getCodeRegime());
               this.excelTools.createCellTexte(27, repas.getTypeRepas().toString());
            }
            
            //Specif
            List<Specification> specifs = (List<Specification>) currentTranche.getByAttributsField(APP_Field.Specification.toString());
            for (Specification specif : specifs) {
               if (!isFirst)
                  this.excelTools.createRow(this.ligne++);
               
               this.excelTools.createCellTexte(28, specif.getRegime().getCodeRegime());
               
               String nouvelleSpecification = "";
               for (Compartiment compartiment : specif.getVoiture().getCompartiments()) {
                  for (Siege siege : compartiment.getSieges()) {
                     nouvelleSpecification += "Coach " + specif.getVoiture().getNumeroVoiture() + " ,";
                     nouvelleSpecification +=" Seat " + siege.getNumeroSiege() + ", " + specif.getEtat().toString();
                     nouvelleSpecification +="\n";
                  } 
                  
                  if (compartiment.getNumeroCompartiment() != null) {
                     nouvelleSpecification += "Coach " + specif.getVoiture().getNumeroVoiture() + " ,";
                     nouvelleSpecification +=" Compartment " + compartiment.getNumeroCompartiment() + ", " + specif.getEtat().toString();
                     nouvelleSpecification +="\n";
                  } 
               }
               
               this.excelTools.createCellTexte(29, nouvelleSpecification);
            }
            
            //Restriction
            List<Restriction> restrictions = (List<Restriction>) currentTranche.getByAttributsField(APP_Field.Restriction.toString());
            for (Restriction restriction : restrictions) {
               if (!isFirst)
                  this.excelTools.createRow(this.ligne++);
               
               this.excelTools.createCellTexte(30, restriction.getRegime().getCodeRegime());
               String nouvelleValue = restriction.getType().toString() + " from " + restriction.getOrigine().getCodeGare() + "to" + restriction.getDestination().toString();
               this.excelTools.createCellTexte(31, nouvelleValue);
            }
            
            isFirst = false;
         }
      }

   }
   
   public void writeTrainTrancheForSheetNew(ComparaisonPlanTransport data, Train currentTrain, Tranche currentTranche) {
      this.excelTools.createCellTexte(1, data.getNumeroTrain());
      this.excelTools.createCellTexte(2, data.getNumeroTranche());
      this.excelTools.createCellTexte(3, currentTranche.getRegime().getCodeRegime());
      this.excelTools.createCellTexte(4, ((PlanTransport) this.mapPlansDeTransport.get(1).get()).getCompagnie().toString());
      this.excelTools.createCellTexte(5, currentTranche.getTrancheStatut().toString());
      this.excelTools.createCellTexte(6, currentTrain.writeIsValidForRR());
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
