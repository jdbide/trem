package com.avancial.app.data.databean.importMotriceBrut;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_import_TMDCATR")
@NamedQuery(name = "ImportTMDCATR.getAll", query = "SELECT t FROM ImportTMDCATREntity t")
public class ImportTMDCATREntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long   idTMDCATR;
   private String CATR_TRA1_COD_CIE;
   private String CATR_TRA1_NUM_TRA1;
   private String CATR_TRA1_IND_FER;
   private String CATR_NUM;
   private String CATR_READ_COD;
   private String CATR_TYEQ_COD;
   private String CATR_LIBS_QLCO_COD;
   private String CATR_LIBS_OURE_COD;
   private String CATR_IND_TRA1_RESA;
   private String CATR_REGI;
   private String CATR_PARITE;
   private String CATR_USER;
   private String CATR_DHDO;

   public Long getIdTMDCATR() {
      return this.idTMDCATR;
   }

   public void setIdTMDCATR(Long idTMDCATR) {
      this.idTMDCATR = idTMDCATR;
   }

   public String getCATR_TRA1_COD_CIE() {
      return this.CATR_TRA1_COD_CIE;
   }

   public void setCATR_TRA1_COD_CIE(String cATR_TRA1_COD_CIE) {
      this.CATR_TRA1_COD_CIE = cATR_TRA1_COD_CIE;
   }

   public String getCATR_TRA1_NUM_TRA1() {
      return this.CATR_TRA1_NUM_TRA1;
   }

   public void setCATR_TRA1_NUM_TRA1(String cATR_TRA1_NUM_TRA1) {
      this.CATR_TRA1_NUM_TRA1 = cATR_TRA1_NUM_TRA1;
   }

   public String getCATR_TRA1_IND_FER() {
      return this.CATR_TRA1_IND_FER;
   }

   public void setCATR_TRA1_IND_FER(String cATR_TRA1_IND_FER) {
      this.CATR_TRA1_IND_FER = cATR_TRA1_IND_FER;
   }

   public String getCATR_NUM() {
      return this.CATR_NUM;
   }

   public void setCATR_NUM(String cATR_NUM) {
      this.CATR_NUM = cATR_NUM;
   }

   public String getCATR_READ_COD() {
      return this.CATR_READ_COD;
   }

   public void setCATR_READ_COD(String cATR_READ_COD) {
      this.CATR_READ_COD = cATR_READ_COD;
   }

   public String getCATR_TYEQ_COD() {
      return this.CATR_TYEQ_COD;
   }

   public void setCATR_TYEQ_COD(String cATR_TYEQ_COD) {
      this.CATR_TYEQ_COD = cATR_TYEQ_COD;
   }

   public String getCATR_LIBS_QLCO_COD() {
      return this.CATR_LIBS_QLCO_COD;
   }

   public void setCATR_LIBS_QLCO_COD(String cATR_LIBS_QLCO_COD) {
      this.CATR_LIBS_QLCO_COD = cATR_LIBS_QLCO_COD;
   }

   public String getCATR_LIBS_OURE_COD() {
      return this.CATR_LIBS_OURE_COD;
   }

   public void setCATR_LIBS_OURE_COD(String cATR_LIBS_OURE_COD) {
      this.CATR_LIBS_OURE_COD = cATR_LIBS_OURE_COD;
   }

   public String getCATR_IND_TRA1_RESA() {
      return this.CATR_IND_TRA1_RESA;
   }

   public void setCATR_IND_TRA1_RESA(String cATR_IND_TRA1_RESA) {
      this.CATR_IND_TRA1_RESA = cATR_IND_TRA1_RESA;
   }

   public String getCATR_REGI() {
      return this.CATR_REGI;
   }

   public void setCATR_REGI(String cATR_REGI) {
      this.CATR_REGI = cATR_REGI;
   }

   public String getCATR_PARITE() {
      return this.CATR_PARITE;
   }

   public void setCATR_PARITE(String cATR_PARITE) {
      this.CATR_PARITE = cATR_PARITE;
   }

   public String getCATR_USER() {
      return this.CATR_USER;
   }

   public void setCATR_USER(String cATR_USER) {
      this.CATR_USER = cATR_USER;
   }

   public String getCATR_DHDO() {
      return this.CATR_DHDO;
   }

   public void setCATR_DHDO(String cATR_DHDO) {
      this.CATR_DHDO = cATR_DHDO;
   }
}
