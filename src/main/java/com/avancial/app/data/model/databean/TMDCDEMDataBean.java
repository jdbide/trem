package com.avancial.app.data.model.databean;

import java.util.Date;

import javax.annotation.concurrent.Immutable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VMDCDEM0", schema = "F$MDRP1")
@Immutable
public class TMDCDEMDataBean {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private String  CDEM_TRA1_COD_CIE;
   private String  CDEM_TRA1_NUM_TRA1;
   private String  CDEM_TRA1_IND_FER;
   private Long    CDEM_NUM_CONDITION;
   private String  CDEM_LIBS_TYCO_COD;
   private Boolean CDEM_REGI;
   private String  CDEM_USER;
   private Date    CDEM_DHDO;
   /**
    * @return the cDEM_TRA1_COD_CIE
    */
   public String getCDEM_TRA1_COD_CIE() {
      return CDEM_TRA1_COD_CIE;
   }
   /**
    * @param cDEM_TRA1_COD_CIE the cDEM_TRA1_COD_CIE to set
    */
   public void setCDEM_TRA1_COD_CIE(String cDEM_TRA1_COD_CIE) {
      CDEM_TRA1_COD_CIE = cDEM_TRA1_COD_CIE;
   }
   /**
    * @return the cDEM_TRA1_NUM_TRA1
    */
   public String getCDEM_TRA1_NUM_TRA1() {
      return CDEM_TRA1_NUM_TRA1;
   }
   /**
    * @param cDEM_TRA1_NUM_TRA1 the cDEM_TRA1_NUM_TRA1 to set
    */
   public void setCDEM_TRA1_NUM_TRA1(String cDEM_TRA1_NUM_TRA1) {
      CDEM_TRA1_NUM_TRA1 = cDEM_TRA1_NUM_TRA1;
   }
   /**
    * @return the cDEM_TRA1_IND_FER
    */
   public String getCDEM_TRA1_IND_FER() {
      return CDEM_TRA1_IND_FER;
   }
   /**
    * @param cDEM_TRA1_IND_FER the cDEM_TRA1_IND_FER to set
    */
   public void setCDEM_TRA1_IND_FER(String cDEM_TRA1_IND_FER) {
      CDEM_TRA1_IND_FER = cDEM_TRA1_IND_FER;
   }
   /**
    * @return the cDEM_NUM_CONDITION
    */
   public Long getCDEM_NUM_CONDITION() {
      return CDEM_NUM_CONDITION;
   }
   /**
    * @param cDEM_NUM_CONDITION the cDEM_NUM_CONDITION to set
    */
   public void setCDEM_NUM_CONDITION(Long cDEM_NUM_CONDITION) {
      CDEM_NUM_CONDITION = cDEM_NUM_CONDITION;
   }
   /**
    * @return the cDEM_LIBS_TYCO_COD
    */
   public String getCDEM_LIBS_TYCO_COD() {
      return CDEM_LIBS_TYCO_COD;
   }
   /**
    * @param cDEM_LIBS_TYCO_COD the cDEM_LIBS_TYCO_COD to set
    */
   public void setCDEM_LIBS_TYCO_COD(String cDEM_LIBS_TYCO_COD) {
      CDEM_LIBS_TYCO_COD = cDEM_LIBS_TYCO_COD;
   }
   /**
    * @return the cDEM_REGI
    */
   public Boolean getCDEM_REGI() {
      return CDEM_REGI;
   }
   /**
    * @param cDEM_REGI the cDEM_REGI to set
    */
   public void setCDEM_REGI(Boolean cDEM_REGI) {
      CDEM_REGI = cDEM_REGI;
   }
   /**
    * @return the cDEM_USER
    */
   public String getCDEM_USER() {
      return CDEM_USER;
   }
   /**
    * @param cDEM_USER the cDEM_USER to set
    */
   public void setCDEM_USER(String cDEM_USER) {
      CDEM_USER = cDEM_USER;
   }
   /**
    * @return the cDEM_DHDO
    */
   public Date getCDEM_DHDO() {
      return CDEM_DHDO;
   }
   /**
    * @param cDEM_DHDO the cDEM_DHDO to set
    */
   public void setCDEM_DHDO(Date cDEM_DHDO) {
      CDEM_DHDO = cDEM_DHDO;
   }
   
   
}
