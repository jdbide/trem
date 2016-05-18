package com.avancial.app.data.model.databean;

import java.util.Date;

import javax.annotation.concurrent.Immutable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VMDRAME0", schema = "F$MDRP1")
@Immutable
public class TMDRAMEDataBean {
   
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private String RAME_TRCH_COD_CIE;
   private String RAME_TRCH_NUM_TRA1;
   private String RAME_TRCH_IND_FER;
   private Long RAME_TRCH_NUM;
   private String RAME_RAMC_COD;
   private Long RAME_NUM;
   private String RAME_NUM_PREM_VOIT;
   private Boolean RAME_REGI;
   private String RAME_USER;
   private Date RAME_DHDO;
   /**
    * @return the rAME_TRCH_COD_CIE
    */
   public String getRAME_TRCH_COD_CIE() {
      return RAME_TRCH_COD_CIE;
   }
   /**
    * @param rAME_TRCH_COD_CIE the rAME_TRCH_COD_CIE to set
    */
   public void setRAME_TRCH_COD_CIE(String rAME_TRCH_COD_CIE) {
      RAME_TRCH_COD_CIE = rAME_TRCH_COD_CIE;
   }
   /**
    * @return the rAME_TRCH_NUM_TRA1
    */
   public String getRAME_TRCH_NUM_TRA1() {
      return RAME_TRCH_NUM_TRA1;
   }
   /**
    * @param rAME_TRCH_NUM_TRA1 the rAME_TRCH_NUM_TRA1 to set
    */
   public void setRAME_TRCH_NUM_TRA1(String rAME_TRCH_NUM_TRA1) {
      RAME_TRCH_NUM_TRA1 = rAME_TRCH_NUM_TRA1;
   }
   /**
    * @return the rAME_TRCH_IND_FER
    */
   public String getRAME_TRCH_IND_FER() {
      return RAME_TRCH_IND_FER;
   }
   /**
    * @param rAME_TRCH_IND_FER the rAME_TRCH_IND_FER to set
    */
   public void setRAME_TRCH_IND_FER(String rAME_TRCH_IND_FER) {
      RAME_TRCH_IND_FER = rAME_TRCH_IND_FER;
   }
   /**
    * @return the rAME_TRCH_NUM
    */
   public Long getRAME_TRCH_NUM() {
      return RAME_TRCH_NUM;
   }
   /**
    * @param rAME_TRCH_NUM the rAME_TRCH_NUM to set
    */
   public void setRAME_TRCH_NUM(Long rAME_TRCH_NUM) {
      RAME_TRCH_NUM = rAME_TRCH_NUM;
   }
   /**
    * @return the rAME_RAMC_COD
    */
   public String getRAME_RAMC_COD() {
      return RAME_RAMC_COD;
   }
   /**
    * @param rAME_RAMC_COD the rAME_RAMC_COD to set
    */
   public void setRAME_RAMC_COD(String rAME_RAMC_COD) {
      RAME_RAMC_COD = rAME_RAMC_COD;
   }
   /**
    * @return the rAME_NUM
    */
   public Long getRAME_NUM() {
      return RAME_NUM;
   }
   /**
    * @param rAME_NUM the rAME_NUM to set
    */
   public void setRAME_NUM(Long rAME_NUM) {
      RAME_NUM = rAME_NUM;
   }
   /**
    * @return the rAME_NUM_PREM_VOIT
    */
   public String getRAME_NUM_PREM_VOIT() {
      return RAME_NUM_PREM_VOIT;
   }
   /**
    * @param rAME_NUM_PREM_VOIT the rAME_NUM_PREM_VOIT to set
    */
   public void setRAME_NUM_PREM_VOIT(String rAME_NUM_PREM_VOIT) {
      RAME_NUM_PREM_VOIT = rAME_NUM_PREM_VOIT;
   }
   /**
    * @return the rAME_REGI
    */
   public Boolean getRAME_REGI() {
      return RAME_REGI;
   }
   /**
    * @param rAME_REGI the rAME_REGI to set
    */
   public void setRAME_REGI(Boolean rAME_REGI) {
      RAME_REGI = rAME_REGI;
   }
   /**
    * @return the rAME_USER
    */
   public String getRAME_USER() {
      return RAME_USER;
   }
   /**
    * @param rAME_USER the rAME_USER to set
    */
   public void setRAME_USER(String rAME_USER) {
      RAME_USER = rAME_USER;
   }
   /**
    * @return the rAME_DHDO
    */
   public Date getRAME_DHDO() {
      return RAME_DHDO;
   }
   /**
    * @param rAME_DHDO the rAME_DHDO to set
    */
   public void setRAME_DHDO(Date rAME_DHDO) {
      RAME_DHDO = rAME_DHDO;
   }
   
   
}
