package com.avancial.app.data.databean.motrice;

import java.util.Date;

import javax.annotation.concurrent.Immutable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(TMDRAMEEntityId.class)
@Table(name = "TMDRAME", schema = "F$MDRP1")
@Immutable
public class TMDRAMEEntity {

   @Id
   private String  RAME_TRCH_COD_CIE;
   @Id
   private String  RAME_TRCH_NUM_TRA1;
   @Id
   private String  RAME_TRCH_IND_FER;
   @Id
   private Double    RAME_TRCH_NUM;
   @Id
   private String  RAME_RAMC_COD;
   @Id
   private Double    RAME_NUM;
   @Id
   private String  RAME_NUM_PREM_VOIT;
   @Id
   private Boolean RAME_REGI;
   private String  RAME_USER;
   private Date    RAME_DHDO;

   /**
    * @return the rAME_TRCH_COD_CIE
    */
   public String getRAME_TRCH_COD_CIE() {
      return this.RAME_TRCH_COD_CIE;
   }

   /**
    * @param rAME_TRCH_COD_CIE
    *           the rAME_TRCH_COD_CIE to set
    */
   public void setRAME_TRCH_COD_CIE(String rAME_TRCH_COD_CIE) {
      this.RAME_TRCH_COD_CIE = rAME_TRCH_COD_CIE;
   }

   /**
    * @return the rAME_TRCH_NUM_TRA1
    */
   public String getRAME_TRCH_NUM_TRA1() {
      return this.RAME_TRCH_NUM_TRA1;
   }

   /**
    * @param rAME_TRCH_NUM_TRA1
    *           the rAME_TRCH_NUM_TRA1 to set
    */
   public void setRAME_TRCH_NUM_TRA1(String rAME_TRCH_NUM_TRA1) {
      this.RAME_TRCH_NUM_TRA1 = rAME_TRCH_NUM_TRA1;
   }

   /**
    * @return the rAME_TRCH_IND_FER
    */
   public String getRAME_TRCH_IND_FER() {
      return this.RAME_TRCH_IND_FER;
   }

   /**
    * @param rAME_TRCH_IND_FER
    *           the rAME_TRCH_IND_FER to set
    */
   public void setRAME_TRCH_IND_FER(String rAME_TRCH_IND_FER) {
      this.RAME_TRCH_IND_FER = rAME_TRCH_IND_FER;
   }

   /**
    * @return the rAME_TRCH_NUM
    */
   public Double getRAME_TRCH_NUM() {
      return this.RAME_TRCH_NUM;
   }

   /**
    * @param rAME_TRCH_NUM
    *           the rAME_TRCH_NUM to set
    */
   public void setRAME_TRCH_NUM(Double rAME_TRCH_NUM) {
      this.RAME_TRCH_NUM = rAME_TRCH_NUM;
   }

   /**
    * @return the rAME_RAMC_COD
    */
   public String getRAME_RAMC_COD() {
      return this.RAME_RAMC_COD;
   }

   /**
    * @param rAME_RAMC_COD
    *           the rAME_RAMC_COD to set
    */
   public void setRAME_RAMC_COD(String rAME_RAMC_COD) {
      this.RAME_RAMC_COD = rAME_RAMC_COD;
   }

   /**
    * @return the rAME_NUM
    */
   public Double getRAME_NUM() {
      return this.RAME_NUM;
   }

   /**
    * @param rAME_NUM
    *           the rAME_NUM to set
    */
   public void setRAME_NUM(Double rAME_NUM) {
      this.RAME_NUM = rAME_NUM;
   }

   /**
    * @return the rAME_NUM_PREM_VOIT
    */
   public String getRAME_NUM_PREM_VOIT() {
      return this.RAME_NUM_PREM_VOIT;
   }

   /**
    * @param rAME_NUM_PREM_VOIT
    *           the rAME_NUM_PREM_VOIT to set
    */
   public void setRAME_NUM_PREM_VOIT(String rAME_NUM_PREM_VOIT) {
      this.RAME_NUM_PREM_VOIT = rAME_NUM_PREM_VOIT;
   }

   /**
    * @return the rAME_REGI
    */
   public Boolean getRAME_REGI() {
      return this.RAME_REGI;
   }

   /**
    * @param rAME_REGI
    *           the rAME_REGI to set
    */
   public void setRAME_REGI(Boolean rAME_REGI) {
      this.RAME_REGI = rAME_REGI;
   }

   /**
    * @return the rAME_USER
    */
   public String getRAME_USER() {
      return this.RAME_USER;
   }

   /**
    * @param rAME_USER
    *           the rAME_USER to set
    */
   public void setRAME_USER(String rAME_USER) {
      this.RAME_USER = rAME_USER;
   }

   /**
    * @return the rAME_DHDO
    */
   public Date getRAME_DHDO() {
      return this.RAME_DHDO;
   }

   /**
    * @param rAME_DHDO
    *           the rAME_DHDO to set
    */
   public void setRAME_DHDO(Date rAME_DHDO) {
      this.RAME_DHDO = rAME_DHDO;
   }

}
