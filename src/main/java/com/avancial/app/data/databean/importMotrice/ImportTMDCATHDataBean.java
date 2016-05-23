package com.avancial.app.data.databean.importMotrice;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_import_TMDCATH")
public class ImportTMDCATHDataBean {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long   idTMDCATH;
   private String CATH_TRCH_COD_CIE;
   private String CATH_TRCH_NUM_TRA1;
   private String CATH_TRCH_IND_FER;
   private Long   CATH_TRCH_NUM;
   private Long   CATH_NUM;
   private String CATH_CIRR_COD_CIE;
   private String CATH_REGI;
   private String CATH_ETAT_TRCH;
   private String CATH_EN_RESA;
   private String CATH_SSIM;
   private String CATH_USER;
   private Date   CATH_DHDO;

   public Long getIdTMDCATH() {
      return this.idTMDCATH;
   }

   public void setIdTMDCATH(Long idTMDCATH) {
      this.idTMDCATH = idTMDCATH;
   }

   public String getCATH_TRCH_COD_CIE() {
      return this.CATH_TRCH_COD_CIE;
   }

   public void setCATH_TRCH_COD_CIE(String cATH_TRCH_COD_CIE) {
      this.CATH_TRCH_COD_CIE = cATH_TRCH_COD_CIE;
   }

   public String getCATH_TRCH_NUM_TRA1() {
      return this.CATH_TRCH_NUM_TRA1;
   }

   public void setCATH_TRCH_NUM_TRA1(String cATH_TRCH_NUM_TRA1) {
      this.CATH_TRCH_NUM_TRA1 = cATH_TRCH_NUM_TRA1;
   }

   public String getCATH_TRCH_IND_FER() {
      return this.CATH_TRCH_IND_FER;
   }

   public void setCATH_TRCH_IND_FER(String cATH_TRCH_IND_FER) {
      this.CATH_TRCH_IND_FER = cATH_TRCH_IND_FER;
   }

   public Long getCATH_TRCH_NUM() {
      return this.CATH_TRCH_NUM;
   }

   public void setCATH_TRCH_NUM(Long cATH_TRCH_NUM) {
      this.CATH_TRCH_NUM = cATH_TRCH_NUM;
   }

   public Long getCATH_NUM() {
      return this.CATH_NUM;
   }

   public void setCATH_NUM(Long cATH_NUM) {
      this.CATH_NUM = cATH_NUM;
   }

   public String getCATH_CIRR_COD_CIE() {
      return this.CATH_CIRR_COD_CIE;
   }

   public void setCATH_CIRR_COD_CIE(String cATH_CIRR_COD_CIE) {
      this.CATH_CIRR_COD_CIE = cATH_CIRR_COD_CIE;
   }

   public String getCATH_REGI() {
      return this.CATH_REGI;
   }

   public void setCATH_REGI(String cATH_REGI) {
      this.CATH_REGI = cATH_REGI;
   }

   public String getCATH_ETAT_TRCH() {
      return this.CATH_ETAT_TRCH;
   }

   public void setCATH_ETAT_TRCH(String cATH_ETAT_TRCH) {
      this.CATH_ETAT_TRCH = cATH_ETAT_TRCH;
   }

   public String getCATH_EN_RESA() {
      return this.CATH_EN_RESA;
   }

   public void setCATH_EN_RESA(String cATH_EN_RESA) {
      this.CATH_EN_RESA = cATH_EN_RESA;
   }

   public String getCATH_SSIM() {
      return this.CATH_SSIM;
   }

   public void setCATH_SSIM(String cATH_SSIM) {
      this.CATH_SSIM = cATH_SSIM;
   }

   public String getCATH_USER() {
      return this.CATH_USER;
   }

   public void setCATH_USER(String cATH_USER) {
      this.CATH_USER = cATH_USER;
   }

   public Date getCATH_DHDO() {
      return this.CATH_DHDO;
   }

   public void setCATH_DHDO(Date cATH_DHDO) {
      this.CATH_DHDO = cATH_DHDO;
   }

}
