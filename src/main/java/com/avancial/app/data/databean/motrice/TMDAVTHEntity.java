package com.avancial.app.data.databean.motrice;

import java.util.Date;

import javax.annotation.concurrent.Immutable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(TMDAVTHEntityId.class)
@Table(name = "TMDAVTH", schema = "F$MDRP1")
@Immutable
public class TMDAVTHEntity {
   @Id
   private String AVTH_TRCH_COD_CIE;
   @Id
   private String AVTH_TRCH_NUM_TRA1;
   @Id
   private String AVTH_TRCH_IND_FER;
   @Id
   private Long   AVTH_TRCH_NUM;
   @Id
   private String AVTH_LIBS_AVAL_COD;
   private String AVTH_USER;
   private Date   AVTH_DHDO;

   public String getAVTH_TRCH_COD_CIE() {
      return this.AVTH_TRCH_COD_CIE;
   }

   public void setAVTH_TRCH_COD_CIE(String aVTH_TRCH_COD_CIE) {
      this.AVTH_TRCH_COD_CIE = aVTH_TRCH_COD_CIE;
   }

   public String getAVTH_TRCH_NUM_TRA1() {
      return this.AVTH_TRCH_NUM_TRA1;
   }

   public void setAVTH_TRCH_NUM_TRA1(String aVTH_TRCH_NUM_TRA1) {
      this.AVTH_TRCH_NUM_TRA1 = aVTH_TRCH_NUM_TRA1;
   }

   public String getAVTH_TRCH_IND_FER() {
      return this.AVTH_TRCH_IND_FER;
   }

   public void setAVTH_TRCH_IND_FER(String aVTH_TRCH_IND_FER) {
      this.AVTH_TRCH_IND_FER = aVTH_TRCH_IND_FER;
   }

   public Long getAVTH_TRCH_NUM() {
      return this.AVTH_TRCH_NUM;
   }

   public void setAVTH_TRCH_NUM(Long aVTH_TRCH_NUM) {
      this.AVTH_TRCH_NUM = aVTH_TRCH_NUM;
   }

   public String getAVTH_LIBS_AVAL_COD() {
      return this.AVTH_LIBS_AVAL_COD;
   }

   public void setAVTH_LIBS_AVAL_COD(String aVTH_LIBS_AVAL_COD) {
      this.AVTH_LIBS_AVAL_COD = aVTH_LIBS_AVAL_COD;
   }

   public String getAVTH_USER() {
      return this.AVTH_USER;
   }

   public void setAVTH_USER(String aVTH_USER) {
      this.AVTH_USER = aVTH_USER;
   }

   public Date getAVTH_DHDO() {
      return this.AVTH_DHDO;
   }

   public void setAVTH_DHDO(Date aVTH_DHDO) {
      this.AVTH_DHDO = aVTH_DHDO;
   }

}
