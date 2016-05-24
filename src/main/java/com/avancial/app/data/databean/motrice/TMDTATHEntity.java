package com.avancial.app.data.databean.motrice;

import java.util.Date;

import javax.annotation.concurrent.Immutable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(TMDTATHEntityId.class)
@Table(name = "TMDTATH", schema = "F$MDRP1")
@Immutable
public class TMDTATHEntity {

   @Id
   private String  TATH_TRCH_COD_CIE;
   @Id
   private String  TATH_TRCH_NUM_TRA1;
   @Id
   private String  TATH_TRCH_IND_FER;
   @Id
   private Double    TATH_TRCH_NUM;
   @Id
   private String  TATH_TYP_TAX;
   @Id
   private String  TATH_CD_VAL;
   @Id
   private String  TATH_INPT_RR_D;
   @Id
   private String  TATH_INPT_RR_F;
   @Id
   private String  TATH_PRIX_HORS_SYS;
   @Id
   private Date    TATH_DHDO;
   private Boolean TATH_REGI;
   private String  TATH_USER;

   /**
    * @return the tATH_TRCH_COD_CIE
    */
   public String getTATH_TRCH_COD_CIE() {
      return this.TATH_TRCH_COD_CIE;
   }

   /**
    * @param tATH_TRCH_COD_CIE
    *           the tATH_TRCH_COD_CIE to set
    */
   public void setTATH_TRCH_COD_CIE(String tATH_TRCH_COD_CIE) {
      this.TATH_TRCH_COD_CIE = tATH_TRCH_COD_CIE;
   }

   /**
    * @return the tATH_TRCH_NUM_TRA1
    */
   public String getTATH_TRCH_NUM_TRA1() {
      return this.TATH_TRCH_NUM_TRA1;
   }

   /**
    * @param tATH_TRCH_NUM_TRA1
    *           the tATH_TRCH_NUM_TRA1 to set
    */
   public void setTATH_TRCH_NUM_TRA1(String tATH_TRCH_NUM_TRA1) {
      this.TATH_TRCH_NUM_TRA1 = tATH_TRCH_NUM_TRA1;
   }

   /**
    * @return the tATH_TRCH_IND_FER
    */
   public String getTATH_TRCH_IND_FER() {
      return this.TATH_TRCH_IND_FER;
   }

   /**
    * @param tATH_TRCH_IND_FER
    *           the tATH_TRCH_IND_FER to set
    */
   public void setTATH_TRCH_IND_FER(String tATH_TRCH_IND_FER) {
      this.TATH_TRCH_IND_FER = tATH_TRCH_IND_FER;
   }

   /**
    * @return the tATH_TRCH_NUM
    */
   public Double getTATH_TRCH_NUM() {
      return this.TATH_TRCH_NUM;
   }

   /**
    * @param tATH_TRCH_NUM
    *           the tATH_TRCH_NUM to set
    */
   public void setTATH_TRCH_NUM(Double tATH_TRCH_NUM) {
      this.TATH_TRCH_NUM = tATH_TRCH_NUM;
   }

   /**
    * @return the tATH_TYP_TAX
    */
   public String getTATH_TYP_TAX() {
      return this.TATH_TYP_TAX;
   }

   /**
    * @param tATH_TYP_TAX
    *           the tATH_TYP_TAX to set
    */
   public void setTATH_TYP_TAX(String tATH_TYP_TAX) {
      this.TATH_TYP_TAX = tATH_TYP_TAX;
   }

   /**
    * @return the tATH_CD_VAL
    */
   public String getTATH_CD_VAL() {
      return this.TATH_CD_VAL;
   }

   /**
    * @param tATH_CD_VAL
    *           the tATH_CD_VAL to set
    */
   public void setTATH_CD_VAL(String tATH_CD_VAL) {
      this.TATH_CD_VAL = tATH_CD_VAL;
   }

   /**
    * @return the tATH_INPT_RR_D
    */
   public String getTATH_INPT_RR_D() {
      return this.TATH_INPT_RR_D;
   }

   /**
    * @param tATH_INPT_RR_D
    *           the tATH_INPT_RR_D to set
    */
   public void setTATH_INPT_RR_D(String tATH_INPT_RR_D) {
      this.TATH_INPT_RR_D = tATH_INPT_RR_D;
   }

   /**
    * @return the tATH_INPT_RR_F
    */
   public String getTATH_INPT_RR_F() {
      return this.TATH_INPT_RR_F;
   }

   /**
    * @param tATH_INPT_RR_F
    *           the tATH_INPT_RR_F to set
    */
   public void setTATH_INPT_RR_F(String tATH_INPT_RR_F) {
      this.TATH_INPT_RR_F = tATH_INPT_RR_F;
   }

   /**
    * @return the tATH_PRIX_HORS_SYS
    */
   public String getTATH_PRIX_HORS_SYS() {
      return this.TATH_PRIX_HORS_SYS;
   }

   /**
    * @param tATH_PRIX_HORS_SYS
    *           the tATH_PRIX_HORS_SYS to set
    */
   public void setTATH_PRIX_HORS_SYS(String tATH_PRIX_HORS_SYS) {
      this.TATH_PRIX_HORS_SYS = tATH_PRIX_HORS_SYS;
   }

   /**
    * @return the tATH_REGI
    */
   public Boolean getTATH_REGI() {
      return this.TATH_REGI;
   }

   /**
    * @param tATH_REGI
    *           the tATH_REGI to set
    */
   public void setTATH_REGI(Boolean tATH_REGI) {
      this.TATH_REGI = tATH_REGI;
   }

   /**
    * @return the tATH_USER
    */
   public String getTATH_USER() {
      return this.TATH_USER;
   }

   /**
    * @param tATH_USER
    *           the tATH_USER to set
    */
   public void setTATH_USER(String tATH_USER) {
      this.TATH_USER = tATH_USER;
   }

   /**
    * @return the tATH_DHDO
    */
   public Date getTATH_DHDO() {
      return this.TATH_DHDO;
   }

   /**
    * @param tATH_DHDO
    *           the tATH_DHDO to set
    */
   public void setTATH_DHDO(Date tATH_DHDO) {
      this.TATH_DHDO = tATH_DHDO;
   }

}
