package com.avancial.app.data.databean.motrice;

import java.util.Date;

import javax.annotation.concurrent.Immutable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(TMDVOITEntityId.class)
@Table(name = "TMDVOIT", schema = "F$MDRP1")
@Immutable
public class TMDVOITEntity {

   @Id
   private String  VOIT_TRCH_COD_CIE;
   @Id
   private String  VOIT_TRCH_NUM_TRA1;
   @Id
   private String  VOIT_TRCH_IND_FER;
   @Id
   private Double    VOIT_TRCH_NUM;
   @Id
   private Double    VOIT_NUM;
   @Id
   private String  VOIT_NUM_RESA;
   @Id
   private Double    VOIT_NUM_VOIT;
   @Id
   private String  VOIT_COD_ORIG;
   @Id
   private Double    VOIT_SENS_ORIG;
   @Id
   private Double    VOIT_NUM_ORIG;
   @Id
   private Double    VOIT_TYVO_NUM_TYP;
   @Id
   private String  VOIT_CIES_COD_GERE;
   @Id
   private String  VOIT_IND_ORIG;
   @Id
   private String VOIT_REGI_UTIL;
   @Id
   private Double    VOIT_ROUL_NUM;
   @Id
   private Double    VOIT_INDCE_CLASST;
   private String  VOIT_USER;
   private Date    VOIT_DHDO;

   public String getVOIT_TRCH_COD_CIE() {
      return this.VOIT_TRCH_COD_CIE;
   }

   public void setVOIT_TRCH_COD_CIE(String vOIT_TRCH_COD_CIE) {
      this.VOIT_TRCH_COD_CIE = vOIT_TRCH_COD_CIE;
   }

   public String getVOIT_TRCH_NUM_TRA1() {
      return this.VOIT_TRCH_NUM_TRA1;
   }

   public void setVOIT_TRCH_NUM_TRA1(String vOIT_TRCH_NUM_TRA1) {
      this.VOIT_TRCH_NUM_TRA1 = vOIT_TRCH_NUM_TRA1;
   }

   public String getVOIT_TRCH_IND_FER() {
      return this.VOIT_TRCH_IND_FER;
   }

   public void setVOIT_TRCH_IND_FER(String vOIT_TRCH_IND_FER) {
      this.VOIT_TRCH_IND_FER = vOIT_TRCH_IND_FER;
   }

   public Double getVOIT_TRCH_NUM() {
      return this.VOIT_TRCH_NUM;
   }

   public void setVOIT_TRCH_NUM(Double vOIT_TRCH_NUM) {
      this.VOIT_TRCH_NUM = vOIT_TRCH_NUM;
   }

   public Double getVOIT_NUM() {
      return this.VOIT_NUM;
   }

   public void setVOIT_NUM(Double vOIT_NUM) {
      this.VOIT_NUM = vOIT_NUM;
   }

   public String getVOIT_NUM_RESA() {
      return this.VOIT_NUM_RESA;
   }

   public void setVOIT_NUM_RESA(String vOIT_NUM_RESA) {
      this.VOIT_NUM_RESA = vOIT_NUM_RESA;
   }

   public Double getVOIT_NUM_VOIT() {
      return this.VOIT_NUM_VOIT;
   }

   public void setVOIT_NUM_VOIT(Double vOIT_NUM_VOIT) {
      this.VOIT_NUM_VOIT = vOIT_NUM_VOIT;
   }

   public String getVOIT_COD_ORIG() {
      return this.VOIT_COD_ORIG;
   }

   public void setVOIT_COD_ORIG(String vOIT_COD_ORIG) {
      this.VOIT_COD_ORIG = vOIT_COD_ORIG;
   }

   public Double getVOIT_SENS_ORIG() {
      return this.VOIT_SENS_ORIG;
   }

   public void setVOIT_SENS_ORIG(Double vOIT_SENS_ORIG) {
      this.VOIT_SENS_ORIG = vOIT_SENS_ORIG;
   }

   public Double getVOIT_NUM_ORIG() {
      return this.VOIT_NUM_ORIG;
   }

   public void setVOIT_NUM_ORIG(Double vOIT_NUM_ORIG) {
      this.VOIT_NUM_ORIG = vOIT_NUM_ORIG;
   }

   public Double getVOIT_TYVO_NUM_TYP() {
      return this.VOIT_TYVO_NUM_TYP;
   }

   public void setVOIT_TYVO_NUM_TYP(Double vOIT_TYVO_NUM_TYP) {
      this.VOIT_TYVO_NUM_TYP = vOIT_TYVO_NUM_TYP;
   }

   public String getVOIT_CIES_COD_GERE() {
      return this.VOIT_CIES_COD_GERE;
   }

   public void setVOIT_CIES_COD_GERE(String vOIT_CIES_COD_GERE) {
      this.VOIT_CIES_COD_GERE = vOIT_CIES_COD_GERE;
   }

   public String getVOIT_IND_ORIG() {
      return this.VOIT_IND_ORIG;
   }

   public void setVOIT_IND_ORIG(String vOIT_IND_ORIG) {
      this.VOIT_IND_ORIG = vOIT_IND_ORIG;
   }

   public String getVOIT_REGI_UTIL() {
      return this.VOIT_REGI_UTIL;
   }

   public void setVOIT_REGI_UTIL(String vOIT_REGI_UTIL) {
      this.VOIT_REGI_UTIL = vOIT_REGI_UTIL;
   }

   public String getVOIT_USER() {
      return this.VOIT_USER;
   }

   public void setVOIT_USER(String vOIT_USER) {
      this.VOIT_USER = vOIT_USER;
   }

   public Date getVOIT_DHDO() {
      return this.VOIT_DHDO;
   }

   public void setVOIT_DHDO(Date vOIT_DHDO) {
      this.VOIT_DHDO = vOIT_DHDO;
   }

   public Double getVOIT_ROUL_NUM() {
      return this.VOIT_ROUL_NUM;
   }

   public void setVOIT_ROUL_NUM(Double vOIT_ROUL_NUM) {
      this.VOIT_ROUL_NUM = vOIT_ROUL_NUM;
   }

   public Double getVOIT_INDCE_CLASST() {
      return this.VOIT_INDCE_CLASST;
   }

   public void setVOIT_INDCE_CLASST(Double vOIT_INDCE_CLASST) {
      this.VOIT_INDCE_CLASST = vOIT_INDCE_CLASST;
   }
}
