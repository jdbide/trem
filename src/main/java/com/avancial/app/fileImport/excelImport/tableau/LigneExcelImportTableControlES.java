package com.avancial.app.fileImport.excelImport.tableau;

import java.util.Date;

public class LigneExcelImportTableControlES implements ILigneExcelImportTable {

   private Double numeroTrain;

   private String codeSat;

   private Date   dateCodeSat;

   private String codeRm;

   private String equipmentCode;

   @Override
   public void remplirLigne(Object... valeursLigne) {
      this.numeroTrain = (Double) valeursLigne[0];
      this.codeSat = (String) valeursLigne[1];
      this.dateCodeSat = (Date) valeursLigne[2];
      this.codeRm = (String) valeursLigne[3];
      this.equipmentCode = (String) valeursLigne[4];
   }
   
   public void printLigne() {
      System.out.println("LigneExcelImportTableControlES : ");
      System.out.println("  Numéro Train = " + this.numeroTrain);
      System.out.println("  Code Sat = " + this.codeSat);
      System.out.println("  Date code sat = " + this.dateCodeSat);
      System.out.println("  Code Rm = " + this.codeRm);
      System.out.println("  Equipement code = " + this.equipmentCode);
      System.out.println();
   }

   public Double getNumeroTrain() {
      return this.numeroTrain;
   }

   public String getCodeSat() {
      return this.codeSat;
   }

   public Date getDateCodeSat() {
      return this.dateCodeSat;
   }

   public String getCodeRm() {
      return this.codeRm;
   }

   public String getEquipmentCode() {
      return this.equipmentCode;
   }

}
