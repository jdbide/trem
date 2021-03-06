package com.avancial.app.data.databean.importMotriceBrut;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_import_TMDCDDS")
@NamedQuery(name = "ImportTMDCDDS.getAll", query = "SELECT t FROM ImportTMDCDDSEntity t")
public class ImportTMDCDDSEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long   idTMDCDDS;
   private String CDDS_CDEM_COD_CIE;
   private String CDDS_CDEM_NUM_TRA1;
   private String CDDS_CDEM_IND_FER;
   private String CDDS_CDEM_NUM_COND;
   private String CDDS_INPT_RR_MONT;
   private String CDDS_INPT_RR_DESC;
   private String CDDS_USER;
   private String CDDS_DHDO;

   public Long getIdTMDCDDS() {
      return this.idTMDCDDS;
   }

   public void setIdTMDCDDS(Long idTMDCDDS) {
      this.idTMDCDDS = idTMDCDDS;
   }

   public String getCDDS_CDEM_COD_CIE() {
      return this.CDDS_CDEM_COD_CIE;
   }

   public void setCDDS_CDEM_COD_CIE(String cDDS_CDEM_COD_CIE) {
      this.CDDS_CDEM_COD_CIE = cDDS_CDEM_COD_CIE;
   }

   public String getCDDS_CDEM_NUM_TRA1() {
      return this.CDDS_CDEM_NUM_TRA1;
   }

   public void setCDDS_CDEM_NUM_TRA1(String cDDS_CDEM_NUM_TRA1) {
      this.CDDS_CDEM_NUM_TRA1 = cDDS_CDEM_NUM_TRA1;
   }

   public String getCDDS_CDEM_IND_FER() {
      return this.CDDS_CDEM_IND_FER;
   }

   public void setCDDS_CDEM_IND_FER(String cDDS_CDEM_IND_FER) {
      this.CDDS_CDEM_IND_FER = cDDS_CDEM_IND_FER;
   }

   public String getCDDS_CDEM_NUM_COND() {
      return this.CDDS_CDEM_NUM_COND;
   }

   public void setCDDS_CDEM_NUM_COND(String cDDS_CDEM_NUM_COND) {
      this.CDDS_CDEM_NUM_COND = cDDS_CDEM_NUM_COND;
   }

   public String getCDDS_INPT_RR_MONT() {
      return this.CDDS_INPT_RR_MONT;
   }

   public void setCDDS_INPT_RR_MONT(String cDDS_INPT_RR_MONT) {
      this.CDDS_INPT_RR_MONT = cDDS_INPT_RR_MONT;
   }

   public String getCDDS_INPT_RR_DESC() {
      return this.CDDS_INPT_RR_DESC;
   }

   public void setCDDS_INPT_RR_DESC(String cDDS_INPT_RR_DESC) {
      this.CDDS_INPT_RR_DESC = cDDS_INPT_RR_DESC;
   }

   public String getCDDS_USER() {
      return this.CDDS_USER;
   }

   public void setCDDS_USER(String cDDS_USER) {
      this.CDDS_USER = cDDS_USER;
   }

   public String getCDDS_DHDO() {
      return this.CDDS_DHDO;
   }

   public void setCDDS_DHDO(String cDDS_DHDO) {
      this.CDDS_DHDO = cDDS_DHDO;
   }
}
