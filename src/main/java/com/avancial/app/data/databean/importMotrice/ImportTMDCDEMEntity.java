package com.avancial.app.data.databean.importMotrice;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_import_TMDCDEM")
@NamedQuery(name = "ImportTMDCDEM.getAll", query= "SELECT t FROM ImportTMDCDEMEntity t")
public class ImportTMDCDEMEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long idTMDCDEM;
   private String  CDEM_TRA1_COD_CIE;
   private String  CDEM_TRA1_NUM_TRA1;
   private String  CDEM_TRA1_IND_FER;
   private Double    CDEM_NUM_CONDITION;
   private String  CDEM_LIBS_TYCO_COD;
   private String CDEM_REGI;
   private String  CDEM_USER;
   private Date    CDEM_DHDO;
   
   
   /**
    * @return the idTMDCDEM
    */
   public Long getIdTMDCDEM() {
      return this.idTMDCDEM;
   }
   /**
    * @param idTMDCDEM the idTMDCDEM to set
    */
   public void setIdTMDCDEM(Long idTMDCDEM) {
      this.idTMDCDEM = idTMDCDEM;
   }
   /**
    * @return the cDEM_TRA1_COD_CIE
    */
   public String getCDEM_TRA1_COD_CIE() {
      return this.CDEM_TRA1_COD_CIE;
   }
   /**
    * @param cDEM_TRA1_COD_CIE the cDEM_TRA1_COD_CIE to set
    */
   public void setCDEM_TRA1_COD_CIE(String cDEM_TRA1_COD_CIE) {
      this.CDEM_TRA1_COD_CIE = cDEM_TRA1_COD_CIE;
   }
   /**
    * @return the cDEM_TRA1_NUM_TRA1
    */
   public String getCDEM_TRA1_NUM_TRA1() {
      return this.CDEM_TRA1_NUM_TRA1;
   }
   /**
    * @param cDEM_TRA1_NUM_TRA1 the cDEM_TRA1_NUM_TRA1 to set
    */
   public void setCDEM_TRA1_NUM_TRA1(String cDEM_TRA1_NUM_TRA1) {
      this.CDEM_TRA1_NUM_TRA1 = cDEM_TRA1_NUM_TRA1;
   }
   /**
    * @return the cDEM_TRA1_IND_FER
    */
   public String getCDEM_TRA1_IND_FER() {
      return this.CDEM_TRA1_IND_FER;
   }
   /**
    * @param cDEM_TRA1_IND_FER the cDEM_TRA1_IND_FER to set
    */
   public void setCDEM_TRA1_IND_FER(String cDEM_TRA1_IND_FER) {
      this.CDEM_TRA1_IND_FER = cDEM_TRA1_IND_FER;
   }
   /**
    * @return the cDEM_NUM_CONDITION
    */
   public Double getCDEM_NUM_CONDITION() {
      return this.CDEM_NUM_CONDITION;
   }
   /**
    * @param cDEM_NUM_CONDITION the cDEM_NUM_CONDITION to set
    */
   public void setCDEM_NUM_CONDITION(Double cDEM_NUM_CONDITION) {
      this.CDEM_NUM_CONDITION = cDEM_NUM_CONDITION;
   }
   /**
    * @return the cDEM_LIBS_TYCO_COD
    */
   public String getCDEM_LIBS_TYCO_COD() {
      return this.CDEM_LIBS_TYCO_COD;
   }
   /**
    * @param cDEM_LIBS_TYCO_COD the cDEM_LIBS_TYCO_COD to set
    */
   public void setCDEM_LIBS_TYCO_COD(String cDEM_LIBS_TYCO_COD) {
      this.CDEM_LIBS_TYCO_COD = cDEM_LIBS_TYCO_COD;
   }
   /**
    * @return the cDEM_REGI
    */
   public String getCDEM_REGI() {
      return this.CDEM_REGI;
   }
   /**
    * @param cDEM_REGI the cDEM_REGI to set
    */
   public void setCDEM_REGI(String cDEM_REGI) {
      this.CDEM_REGI = cDEM_REGI;
   }
   /**
    * @return the cDEM_USER
    */
   public String getCDEM_USER() {
      return this.CDEM_USER;
   }
   /**
    * @param cDEM_USER the cDEM_USER to set
    */
   public void setCDEM_USER(String cDEM_USER) {
      this.CDEM_USER = cDEM_USER;
   }
   /**
    * @return the cDEM_DHDO
    */
   public Date getCDEM_DHDO() {
      return this.CDEM_DHDO;
   }
   /**
    * @param cDEM_DHDO the cDEM_DHDO to set
    */
   public void setCDEM_DHDO(Date cDEM_DHDO) {
      this.CDEM_DHDO = cDEM_DHDO;
   }
   
   
}
