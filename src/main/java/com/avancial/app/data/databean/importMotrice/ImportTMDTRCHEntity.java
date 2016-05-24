package com.avancial.app.data.databean.importMotrice;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_import_TMDTRCH")
@NamedQuery(name = "ImportTMDTRCH.getAll", query= "SELECT t FROM ImportTMDTRCHEntity t")
public class ImportTMDTRCHEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Double    idTMDTRCH;
   private String  TRCH_TRA1_COD_CIE;
   private String  TRCH_TRA1_NUM_TRA1;
   private String  TRCH_TRA1_IND_FER;
   private Double    TRCH_NUM;
   private String  TRCH_COD_SENS_AUTO;
   private String  TRCH_IND_TGV;
   private String  TRCH_IND_VAL_DC;
   private Date    TRCH_DAT_DER_MOD;
   private String  TRCH_INPT_RR_ORIG;
   private String  TRCH_INPT_RR_DEST;
   private String TRCH_REGI_VAL;
   private String  TRCH_USER;
   private Date    TRCH_DHDO;
   private String TRCH_REGI_VAL_DC;
   private String  TRCH_LIBS_SEMA_COD;

   public Double getIdTMDTRCH() {
      return this.idTMDTRCH;
   }

   public void setIdTMDTRCH(Double idTMDTRCH) {
      this.idTMDTRCH = idTMDTRCH;
   }

   public String getTRCH_TRA1_COD_CIE() {
      return this.TRCH_TRA1_COD_CIE;
   }

   public void setTRCH_TRA1_COD_CIE(String tRCH_TRA1_COD_CIE) {
      this.TRCH_TRA1_COD_CIE = tRCH_TRA1_COD_CIE;
   }

   public String getTRCH_TRA1_NUM_TRA1() {
      return this.TRCH_TRA1_NUM_TRA1;
   }

   public void setTRCH_TRA1_NUM_TRA1(String tRCH_TRA1_NUM_TRA1) {
      this.TRCH_TRA1_NUM_TRA1 = tRCH_TRA1_NUM_TRA1;
   }

   public String getTRCH_TRA1_IND_FER() {
      return this.TRCH_TRA1_IND_FER;
   }

   public void setTRCH_TRA1_IND_FER(String tRCH_TRA1_IND_FER) {
      this.TRCH_TRA1_IND_FER = tRCH_TRA1_IND_FER;
   }

   public Double getTRCH_NUM() {
      return this.TRCH_NUM;
   }

   public void setTRCH_NUM(Double tRCH_NUM) {
      this.TRCH_NUM = tRCH_NUM;
   }

   public String getTRCH_COD_SENS_AUTO() {
      return this.TRCH_COD_SENS_AUTO;
   }

   public void setTRCH_COD_SENS_AUTO(String tRCH_COD_SENS_AUTO) {
      this.TRCH_COD_SENS_AUTO = tRCH_COD_SENS_AUTO;
   }

   public String getTRCH_IND_TGV() {
      return this.TRCH_IND_TGV;
   }

   public void setTRCH_IND_TGV(String tRCH_IND_TGV) {
      this.TRCH_IND_TGV = tRCH_IND_TGV;
   }

   public String getTRCH_IND_VAL_DC() {
      return this.TRCH_IND_VAL_DC;
   }

   public void setTRCH_IND_VAL_DC(String tRCH_IND_VAL_DC) {
      this.TRCH_IND_VAL_DC = tRCH_IND_VAL_DC;
   }

   public Date getTRCH_DAT_DER_MOD() {
      return this.TRCH_DAT_DER_MOD;
   }

   public void setTRCH_DAT_DER_MOD(Date tRCH_DAT_DER_MOD) {
      this.TRCH_DAT_DER_MOD = tRCH_DAT_DER_MOD;
   }

   public String getTRCH_INPT_RR_ORIG() {
      return this.TRCH_INPT_RR_ORIG;
   }

   public void setTRCH_INPT_RR_ORIG(String tRCH_INPT_RR_ORIG) {
      this.TRCH_INPT_RR_ORIG = tRCH_INPT_RR_ORIG;
   }

   public String getTRCH_INPT_RR_DEST() {
      return this.TRCH_INPT_RR_DEST;
   }

   public void setTRCH_INPT_RR_DEST(String tRCH_INPT_RR_DEST) {
      this.TRCH_INPT_RR_DEST = tRCH_INPT_RR_DEST;
   }

   public String getTRCH_REGI_VAL() {
      return this.TRCH_REGI_VAL;
   }

   public void setTRCH_REGI_VAL(String tRCH_REGI_VAL) {
      this.TRCH_REGI_VAL = tRCH_REGI_VAL;
   }

   public String getTRCH_USER() {
      return this.TRCH_USER;
   }

   public void setTRCH_USER(String tRCH_USER) {
      this.TRCH_USER = tRCH_USER;
   }

   public Date getTRCH_DHDO() {
      return this.TRCH_DHDO;
   }

   public void setTRCH_DHDO(Date tRCH_DHDO) {
      this.TRCH_DHDO = tRCH_DHDO;
   }

   public String getTRCH_REGI_VAL_DC() {
      return this.TRCH_REGI_VAL_DC;
   }

   public void setTRCH_REGI_VAL_DC(String tRCH_REGI_VAL_DC) {
      this.TRCH_REGI_VAL_DC = tRCH_REGI_VAL_DC;
   }

   public String getTRCH_LIBS_SEMA_COD() {
      return this.TRCH_LIBS_SEMA_COD;
   }

   public void setTRCH_LIBS_SEMA_COD(String tRCH_LIBS_SEMA_COD) {
      this.TRCH_LIBS_SEMA_COD = tRCH_LIBS_SEMA_COD;
   }
}
