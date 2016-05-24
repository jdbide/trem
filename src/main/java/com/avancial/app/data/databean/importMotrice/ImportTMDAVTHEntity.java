package com.avancial.app.data.databean.importMotrice;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_import_TMDAVTH")
@NamedQuery(name = "ImportTMDAVTH.getAll", query= "SELECT t FROM ImportTMDAVTHEntity t")
public class ImportTMDAVTHEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long   idTMDAVTH;
   private String AVTH_TRCH_COD_CIE;
   private String AVTH_TRCH_NUM_TRA1;
   private String AVTH_TRCH_IND_FER;
   private Long   AVTH_TRCH_NUM;
   private String AVTH_LIBS_AVAL_COD;
   private String AVTH_USER;
   private Date   AVTH_DHDO;

   public Long getIdTMDAVTH() {
      return this.idTMDAVTH;
   }

   public void setIdTMDAVTH(Long idTMDAVTH) {
      this.idTMDAVTH = idTMDAVTH;
   }

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
