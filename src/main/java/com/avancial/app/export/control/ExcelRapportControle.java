package com.avancial.app.export.control;

import com.avancial.app.export.AExcelRapportComparaisonPlanTransport;

public class ExcelRapportControle extends AExcelRapportComparaisonPlanTransport {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /* Données onglets */
   public static String      ERR_STATUT       = "Err_Statut";
   public static String      ERR_CONFIG       = "Err_Config";
   public static String      ERR_SPECIF       = "Err_Specif";
   public static String      ERR_HORAIRES     = "Err_Horaires";
   public static String      ERR_PROFTARIF    = "Err_ProfTarif";
   public static String      ERR_SAT          = "Err_SAT";
   public static String      ERR_TRANCHE      = "Err_Tranche";
   public static String      ERR_GDS_AVN      = "Err_GDS_AVN";
   public static String      ERR_TOSP         = "Err_TOSP";
   public static String      ERR_REPAS        = "Err_Repas";
   public static String      ERR_RESTRICTIONS = "Err_Restrictions";

   public ExcelRapportControle() throws Exception {
      super();
   }

   public ExcelRapportControle(boolean xlsx, String fileName, String filePath) throws Exception {
      super(xlsx, fileName, filePath);
   }

   public ExcelRapportControle(boolean xlsx) throws Exception {
      super(xlsx);
   }

   @Override
   protected void initVarSheets() {
      this.setNombreSheet(11);

      this.getNomSheets().add(ERR_STATUT);
      this.getNombreColonneSheets().add(7);

      this.getNomSheets().add(ERR_CONFIG);
      this.getNombreColonneSheets().add(9);

      this.getNomSheets().add(ERR_SPECIF);
      this.getNombreColonneSheets().add(0);

      this.getNomSheets().add(ERR_HORAIRES);
      this.getNombreColonneSheets().add(10);

      this.getNomSheets().add(ERR_PROFTARIF);
      this.getNombreColonneSheets().add(7);

      this.getNomSheets().add(ERR_SAT);
      this.getNombreColonneSheets().add(7);

      this.getNomSheets().add(ERR_TRANCHE);
      this.getNombreColonneSheets().add(0);

      this.getNomSheets().add(ERR_GDS_AVN);
      this.getNombreColonneSheets().add(0);

      this.getNomSheets().add(ERR_TOSP);
      this.getNombreColonneSheets().add(7);

      this.getNomSheets().add(ERR_REPAS);
      this.getNombreColonneSheets().add(9);
      
      this.getNomSheets().add(ERR_RESTRICTIONS);
      this.getNombreColonneSheets().add(11);

      this.setPremiereLigneEntetes(1);
      this.setPremiereLigneContents(2);
   }

   @Override
   protected void generatePreEnteteBySheet() {
      return;
   }

}
