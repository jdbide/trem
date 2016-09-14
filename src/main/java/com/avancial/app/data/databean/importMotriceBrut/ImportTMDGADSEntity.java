package com.avancial.app.data.databean.importMotriceBrut;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_import_TMDGADS")
@NamedQuery(name = "ImportTMDGADS.getAll", query = "SELECT t FROM ImportTMDGADSEntity t")
public class ImportTMDGADSEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long   idTMDGADS;
   private String GADS_DSTR_COD_CIE;
   private String GADS_DSTR_NUM_TRA1;
   private String GADS_DSTR_IND_FER;
   private String GADS_DSTR_NUM;
   private String GADS_NUM_GAR;
   private String GADS_INPT_RR_GAR;
   private String GADS_DEB_ARRET;
   private String GADS_FIN_ARRET;
   private String GADS_VAL_PARITE;
   private String GADS_TYP_ARRET;
   private String GADS_CAST_COD_STAT;
   private String GADS_IND_PT_FRONT;
   private String GADS_IND_CIRC_THO;
   private String GADS_USER;
   private String GADS_DHDO;

   public Long getIdTMDGADS() {
      return this.idTMDGADS;
   }

   public void setIdTMDGADS(Long idTMDGADS) {
      this.idTMDGADS = idTMDGADS;
   }

   public String getGADS_DSTR_COD_CIE() {
      return this.GADS_DSTR_COD_CIE;
   }

   public void setGADS_DSTR_COD_CIE(String gADS_DSTR_COD_CIE) {
      this.GADS_DSTR_COD_CIE = gADS_DSTR_COD_CIE;
   }

   public String getGADS_DSTR_NUM_TRA1() {
      return this.GADS_DSTR_NUM_TRA1;
   }

   public void setGADS_DSTR_NUM_TRA1(String gADS_DSTR_NUM_TRA1) {
      this.GADS_DSTR_NUM_TRA1 = gADS_DSTR_NUM_TRA1;
   }

   public String getGADS_DSTR_IND_FER() {
      return this.GADS_DSTR_IND_FER;
   }

   public void setGADS_DSTR_IND_FER(String gADS_DSTR_IND_FER) {
      this.GADS_DSTR_IND_FER = gADS_DSTR_IND_FER;
   }

   public String getGADS_DSTR_NUM() {
      return this.GADS_DSTR_NUM;
   }

   public void setGADS_DSTR_NUM(String gADS_DSTR_NUM) {
      this.GADS_DSTR_NUM = gADS_DSTR_NUM;
   }

   public String getGADS_NUM_GAR() {
      return this.GADS_NUM_GAR;
   }

   public void setGADS_NUM_GAR(String gADS_NUM_GAR) {
      this.GADS_NUM_GAR = gADS_NUM_GAR;
   }

   public String getGADS_INPT_RR_GAR() {
      return this.GADS_INPT_RR_GAR;
   }

   public void setGADS_INPT_RR_GAR(String gADS_INPT_RR_GAR) {
      this.GADS_INPT_RR_GAR = gADS_INPT_RR_GAR;
   }

   public String getGADS_DEB_ARRET() {
      return this.GADS_DEB_ARRET;
   }

   public void setGADS_DEB_ARRET(String gADS_DEB_ARRET) {
      this.GADS_DEB_ARRET = gADS_DEB_ARRET;
   }

   public String getGADS_FIN_ARRET() {
      return this.GADS_FIN_ARRET;
   }

   public void setGADS_FIN_ARRET(String gADS_FIN_ARRET) {
      this.GADS_FIN_ARRET = gADS_FIN_ARRET;
   }

   public String getGADS_VAL_PARITE() {
      return this.GADS_VAL_PARITE;
   }

   public void setGADS_VAL_PARITE(String gADS_VAL_PARITE) {
      this.GADS_VAL_PARITE = gADS_VAL_PARITE;
   }

   public String getGADS_TYP_ARRET() {
      return this.GADS_TYP_ARRET;
   }

   public void setGADS_TYP_ARRET(String gADS_TYP_ARRET) {
      this.GADS_TYP_ARRET = gADS_TYP_ARRET;
   }

   public String getGADS_CAST_COD_STAT() {
      return this.GADS_CAST_COD_STAT;
   }

   public void setGADS_CAST_COD_STAT(String gADS_CAST_COD_STAT) {
      this.GADS_CAST_COD_STAT = gADS_CAST_COD_STAT;
   }

   public String getGADS_IND_PT_FRONT() {
      return this.GADS_IND_PT_FRONT;
   }

   public void setGADS_IND_PT_FRONT(String gADS_IND_PT_FRONT) {
      this.GADS_IND_PT_FRONT = gADS_IND_PT_FRONT;
   }

   public String getGADS_IND_CIRC_THO() {
      return this.GADS_IND_CIRC_THO;
   }

   public void setGADS_IND_CIRC_THO(String gADS_IND_CIRC_THO) {
      this.GADS_IND_CIRC_THO = gADS_IND_CIRC_THO;
   }

   public String getGADS_USER() {
      return this.GADS_USER;
   }

   public void setGADS_USER(String gADS_USER) {
      this.GADS_USER = gADS_USER;
   }

   public String getGADS_DHDO() {
      return this.GADS_DHDO;
   }

   public void setGADS_DHDO(String gADS_DHDO) {
      this.GADS_DHDO = gADS_DHDO;
   }
}
