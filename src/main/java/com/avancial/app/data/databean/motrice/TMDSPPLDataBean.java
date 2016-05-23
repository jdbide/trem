package com.avancial.app.data.databean.motrice;

import java.util.Date;

import javax.annotation.concurrent.Immutable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TMDSPPL", schema = "F$MDRP2")
@Immutable
public class TMDSPPLDataBean {
   
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private String SPPL_VOIT_COD_CIE;
   private String SPPL_VOIT_NUM_TRA1;
   private String SPPL_VOIT_IND_FER;
   private Long SPPL_VOIT_TRCH_NUM;
   private Long SPPL_VOIT_NUM;
   private Long SPPL_PCDD_NUM_COMP;
   private Long SPPL_PCDD_NUM_PLAC;
   private String SPPL_SPEC_COD;
   private Boolean SPPL_REGI;
   private String SPPL_USER;
   private Date  SPPL_DHDO;
   
   
   /**
    * @return the sPPL_VOIT_COD_CIE
    */
   public String getSPPL_VOIT_COD_CIE() {
      return this.SPPL_VOIT_COD_CIE;
   }
   /**
    * @param sPPL_VOIT_COD_CIE the sPPL_VOIT_COD_CIE to set
    */
   public void setSPPL_VOIT_COD_CIE(String sPPL_VOIT_COD_CIE) {
      this.SPPL_VOIT_COD_CIE = sPPL_VOIT_COD_CIE;
   }
   /**
    * @return the sPPL_VOIT_NUM_TRA1
    */
   public String getSPPL_VOIT_NUM_TRA1() {
      return this.SPPL_VOIT_NUM_TRA1;
   }
   /**
    * @param sPPL_VOIT_NUM_TRA1 the sPPL_VOIT_NUM_TRA1 to set
    */
   public void setSPPL_VOIT_NUM_TRA1(String sPPL_VOIT_NUM_TRA1) {
      this.SPPL_VOIT_NUM_TRA1 = sPPL_VOIT_NUM_TRA1;
   }
   /**
    * @return the sPPL_VOIT_IND_FER
    */
   public String getSPPL_VOIT_IND_FER() {
      return this.SPPL_VOIT_IND_FER;
   }
   /**
    * @param sPPL_VOIT_IND_FER the sPPL_VOIT_IND_FER to set
    */
   public void setSPPL_VOIT_IND_FER(String sPPL_VOIT_IND_FER) {
      this.SPPL_VOIT_IND_FER = sPPL_VOIT_IND_FER;
   }
   /**
    * @return the sPPL_VOIT_TRCH_NUM
    */
   public Long getSPPL_VOIT_TRCH_NUM() {
      return this.SPPL_VOIT_TRCH_NUM;
   }
   /**
    * @param sPPL_VOIT_TRCH_NUM the sPPL_VOIT_TRCH_NUM to set
    */
   public void setSPPL_VOIT_TRCH_NUM(Long sPPL_VOIT_TRCH_NUM) {
      this.SPPL_VOIT_TRCH_NUM = sPPL_VOIT_TRCH_NUM;
   }
   /**
    * @return the sPPL_VOIT_NUM
    */
   public Long getSPPL_VOIT_NUM() {
      return this.SPPL_VOIT_NUM;
   }
   /**
    * @param sPPL_VOIT_NUM the sPPL_VOIT_NUM to set
    */
   public void setSPPL_VOIT_NUM(Long sPPL_VOIT_NUM) {
      this.SPPL_VOIT_NUM = sPPL_VOIT_NUM;
   }
   /**
    * @return the sPPL_PCDD_NUM_COMP
    */
   public Long getSPPL_PCDD_NUM_COMP() {
      return this.SPPL_PCDD_NUM_COMP;
   }
   /**
    * @param sPPL_PCDD_NUM_COMP the sPPL_PCDD_NUM_COMP to set
    */
   public void setSPPL_PCDD_NUM_COMP(Long sPPL_PCDD_NUM_COMP) {
      this.SPPL_PCDD_NUM_COMP = sPPL_PCDD_NUM_COMP;
   }
   /**
    * @return the sPPL_PCDD_NUM_PLAC
    */
   public Long getSPPL_PCDD_NUM_PLAC() {
      return this.SPPL_PCDD_NUM_PLAC;
   }
   /**
    * @param sPPL_PCDD_NUM_PLAC the sPPL_PCDD_NUM_PLAC to set
    */
   public void setSPPL_PCDD_NUM_PLAC(Long sPPL_PCDD_NUM_PLAC) {
      this.SPPL_PCDD_NUM_PLAC = sPPL_PCDD_NUM_PLAC;
   }
   /**
    * @return the sPPL_SPEC_COD
    */
   public String getSPPL_SPEC_COD() {
      return this.SPPL_SPEC_COD;
   }
   /**
    * @param sPPL_SPEC_COD the sPPL_SPEC_COD to set
    */
   public void setSPPL_SPEC_COD(String sPPL_SPEC_COD) {
      this.SPPL_SPEC_COD = sPPL_SPEC_COD;
   }
   /**
    * @return the sPPL_REGI
    */
   public Boolean getSPPL_REGI() {
      return this.SPPL_REGI;
   }
   /**
    * @param sPPL_REGI the sPPL_REGI to set
    */
   public void setSPPL_REGI(Boolean sPPL_REGI) {
      this.SPPL_REGI = sPPL_REGI;
   }
   /**
    * @return the sPPL_USER
    */
   public String getSPPL_USER() {
      return this.SPPL_USER;
   }
   /**
    * @param sPPL_USER the sPPL_USER to set
    */
   public void setSPPL_USER(String sPPL_USER) {
      this.SPPL_USER = sPPL_USER;
   }
   /**
    * @return the sPPL_DHDO
    */
   public Date getSPPL_DHDO() {
      return this.SPPL_DHDO;
   }
   /**
    * @param sPPL_DHDO the sPPL_DHDO to set
    */
   public void setSPPL_DHDO(Date sPPL_DHDO) {
      this.SPPL_DHDO = sPPL_DHDO;
   }
   
   
}
