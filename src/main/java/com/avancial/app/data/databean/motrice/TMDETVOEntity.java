package com.avancial.app.data.databean.motrice;

import java.util.Date;

import javax.annotation.concurrent.Immutable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(TMDETVOEntityId.class)
@Table(name = "TMDETVO", schema = "F$MDRP1")
@Immutable
public class TMDETVOEntity {

   @Id
   private String  ETVO_VOIT_COD_CIE;
   @Id
   private String  ETVO_VOIT_NUM_TRA1;
   @Id
   private String  ETVO_VOIT_IND_FER;
   @Id
   private Double    ETVO_VOIT_TRCH_NUM;
   @Id
   private Double    ETVO_VOIT_NUM;
   @Id
   private Double    ETVO_NUM;
   @Id
   private String  ETVO_ETAT_VOIT;
   @Id
   private String ETVO_REGI;
   private String  ETVO_USER;
   private Date    ETVO_DHDO;
   /**
    * @return the eTVO_VOIT_COD_CIE
    */
   public String getETVO_VOIT_COD_CIE() {
      return this.ETVO_VOIT_COD_CIE;
   }
   /**
    * @param eTVO_VOIT_COD_CIE the eTVO_VOIT_COD_CIE to set
    */
   public void setETVO_VOIT_COD_CIE(String eTVO_VOIT_COD_CIE) {
      this.ETVO_VOIT_COD_CIE = eTVO_VOIT_COD_CIE;
   }
   /**
    * @return the eTVO_VOIT_NUM_TRA1
    */
   public String getETVO_VOIT_NUM_TRA1() {
      return this.ETVO_VOIT_NUM_TRA1;
   }
   /**
    * @param eTVO_VOIT_NUM_TRA1 the eTVO_VOIT_NUM_TRA1 to set
    */
   public void setETVO_VOIT_NUM_TRA1(String eTVO_VOIT_NUM_TRA1) {
      this.ETVO_VOIT_NUM_TRA1 = eTVO_VOIT_NUM_TRA1;
   }
   /**
    * @return the eTVO_VOIT_IND_FER
    */
   public String getETVO_VOIT_IND_FER() {
      return this.ETVO_VOIT_IND_FER;
   }
   /**
    * @param eTVO_VOIT_IND_FER the eTVO_VOIT_IND_FER to set
    */
   public void setETVO_VOIT_IND_FER(String eTVO_VOIT_IND_FER) {
      this.ETVO_VOIT_IND_FER = eTVO_VOIT_IND_FER;
   }
   /**
    * @return the eTVO_VOIT_TRCH_NUM
    */
   public Double getETVO_VOIT_TRCH_NUM() {
      return this.ETVO_VOIT_TRCH_NUM;
   }
   /**
    * @param eTVO_VOIT_TRCH_NUM the eTVO_VOIT_TRCH_NUM to set
    */
   public void setETVO_VOIT_TRCH_NUM(Double eTVO_VOIT_TRCH_NUM) {
      this.ETVO_VOIT_TRCH_NUM = eTVO_VOIT_TRCH_NUM;
   }
   /**
    * @return the eTVO_VOIT_NUM
    */
   public Double getETVO_VOIT_NUM() {
      return this.ETVO_VOIT_NUM;
   }
   /**
    * @param eTVO_VOIT_NUM the eTVO_VOIT_NUM to set
    */
   public void setETVO_VOIT_NUM(Double eTVO_VOIT_NUM) {
      this.ETVO_VOIT_NUM = eTVO_VOIT_NUM;
   }
   /**
    * @return the eTVO_NUM
    */
   public Double getETVO_NUM() {
      return this.ETVO_NUM;
   }
   /**
    * @param eTVO_NUM the eTVO_NUM to set
    */
   public void setETVO_NUM(Double eTVO_NUM) {
      this.ETVO_NUM = eTVO_NUM;
   }
   /**
    * @return the eTVO_ETAT_VOIT
    */
   public String getETVO_ETAT_VOIT() {
      return this.ETVO_ETAT_VOIT;
   }
   /**
    * @param eTVO_ETAT_VOIT the eTVO_ETAT_VOIT to set
    */
   public void setETVO_ETAT_VOIT(String eTVO_ETAT_VOIT) {
      this.ETVO_ETAT_VOIT = eTVO_ETAT_VOIT;
   }
   /**
    * @return the eTVO_REGI
    */
   public String getETVO_REGI() {
      return this.ETVO_REGI;
   }
   /**
    * @param eTVO_REGI the eTVO_REGI to set
    */
   public void setETVO_REGI(String eTVO_REGI) {
      this.ETVO_REGI = eTVO_REGI;
   }
   /**
    * @return the eTVO_USER
    */
   public String getETVO_USER() {
      return this.ETVO_USER;
   }
   /**
    * @param eTVO_USER the eTVO_USER to set
    */
   public void setETVO_USER(String eTVO_USER) {
      this.ETVO_USER = eTVO_USER;
   }
   /**
    * @return the eTVO_DHDO
    */
   public Date getETVO_DHDO() {
      return this.ETVO_DHDO;
   }
   /**
    * @param eTVO_DHDO the eTVO_DHDO to set
    */
   public void setETVO_DHDO(Date eTVO_DHDO) {
      this.ETVO_DHDO = eTVO_DHDO;
   }
   
   
}
