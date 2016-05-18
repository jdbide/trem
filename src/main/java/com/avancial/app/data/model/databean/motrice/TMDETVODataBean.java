package com.avancial.app.data.model.databean.motrice;

import java.util.Date;

import javax.annotation.concurrent.Immutable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VMDETVO0", schema = "F$MDRP2")
@Immutable
public class TMDETVODataBean {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private String  ETVO_VOIT_COD_CIE;
   private String  ETVO_VOIT_NUM_TRA1;
   private String  ETVO_VOIT_IND_FER;
   private Long    ETVO_VOIT_TRCH_NUM;
   private Long    ETVO_VOIT_NUM;
   private Long    ETVO_NUM;
   private String  ETVO_ETAT_VOIT;
   private Boolean ETVO_REGI;
   private String  ETVO_USER;
   private Date    ETCO_DHDO;
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
   public Long getETVO_VOIT_TRCH_NUM() {
      return this.ETVO_VOIT_TRCH_NUM;
   }
   /**
    * @param eTVO_VOIT_TRCH_NUM the eTVO_VOIT_TRCH_NUM to set
    */
   public void setETVO_VOIT_TRCH_NUM(Long eTVO_VOIT_TRCH_NUM) {
      this.ETVO_VOIT_TRCH_NUM = eTVO_VOIT_TRCH_NUM;
   }
   /**
    * @return the eTVO_VOIT_NUM
    */
   public Long getETVO_VOIT_NUM() {
      return this.ETVO_VOIT_NUM;
   }
   /**
    * @param eTVO_VOIT_NUM the eTVO_VOIT_NUM to set
    */
   public void setETVO_VOIT_NUM(Long eTVO_VOIT_NUM) {
      this.ETVO_VOIT_NUM = eTVO_VOIT_NUM;
   }
   /**
    * @return the eTVO_NUM
    */
   public Long getETVO_NUM() {
      return this.ETVO_NUM;
   }
   /**
    * @param eTVO_NUM the eTVO_NUM to set
    */
   public void setETVO_NUM(Long eTVO_NUM) {
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
   public Boolean getETVO_REGI() {
      return this.ETVO_REGI;
   }
   /**
    * @param eTVO_REGI the eTVO_REGI to set
    */
   public void setETVO_REGI(Boolean eTVO_REGI) {
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
    * @return the eTCO_DHDO
    */
   public Date getETCO_DHDO() {
      return this.ETCO_DHDO;
   }
   /**
    * @param eTCO_DHDO the eTCO_DHDO to set
    */
   public void setETCO_DHDO(Date eTCO_DHDO) {
      this.ETCO_DHDO = eTCO_DHDO;
   }
   
   
}
