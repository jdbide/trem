package com.avancial.app.data.databean.importMotrice;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_import_TMDCDCL")
@NamedQuery(name = "ImportTMDCDCL.getAll", query= "SELECT t FROM ImportTMDCDCLEntity t")
public class ImportTMDCDCLEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Double   idTMDCDCL;
   private String CDCL_CDEM_COD_CIE;
   private String CDCL_CDEM_NUM_TRA1;
   private String CDCL_CDEM_IND_FER;
   private Double   CDCL_CDEM_NUM_COND;
   private String CDCL_CLBA_COD;
   private String CDCL_USER;
   private Date   CDCL_DHDO;

   public Double getIdTMDCDCL() {
      return this.idTMDCDCL;
   }

   public void setIdTMDCDCL(Double idTMDCDCL) {
      this.idTMDCDCL = idTMDCDCL;
   }

   public String getCDCL_CDEM_COD_CIE() {
      return this.CDCL_CDEM_COD_CIE;
   }

   public void setCDCL_CDEM_COD_CIE(String cDCL_CDEM_COD_CIE) {
      this.CDCL_CDEM_COD_CIE = cDCL_CDEM_COD_CIE;
   }

   public String getCDCL_CDEM_NUM_TRA1() {
      return this.CDCL_CDEM_NUM_TRA1;
   }

   public void setCDCL_CDEM_NUM_TRA1(String cDCL_CDEM_NUM_TRA1) {
      this.CDCL_CDEM_NUM_TRA1 = cDCL_CDEM_NUM_TRA1;
   }

   public String getCDCL_CDEM_IND_FER() {
      return this.CDCL_CDEM_IND_FER;
   }

   public void setCDCL_CDEM_IND_FER(String cDCL_CDEM_IND_FER) {
      this.CDCL_CDEM_IND_FER = cDCL_CDEM_IND_FER;
   }

   public Double getCDCL_CDEM_NUM_COND() {
      return this.CDCL_CDEM_NUM_COND;
   }

   public void setCDCL_CDEM_NUM_COND(Double cDCL_CDEM_NUM_COND) {
      this.CDCL_CDEM_NUM_COND = cDCL_CDEM_NUM_COND;
   }

   public String getCDCL_CLBA_COD() {
      return this.CDCL_CLBA_COD;
   }

   public void setCDCL_CLBA_COD(String cDCL_CLBA_COD) {
      this.CDCL_CLBA_COD = cDCL_CLBA_COD;
   }

   public String getCDCL_USER() {
      return this.CDCL_USER;
   }

   public void setCDCL_USER(String cDCL_USER) {
      this.CDCL_USER = cDCL_USER;
   }

   public Date getCDCL_DHDO() {
      return this.CDCL_DHDO;
   }

   public void setCDCL_DHDO(Date cDCL_DHDO) {
      this.CDCL_DHDO = cDCL_DHDO;
   }
}
