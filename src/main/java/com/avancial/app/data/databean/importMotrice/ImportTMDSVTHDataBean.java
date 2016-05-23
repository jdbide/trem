package com.avancial.app.data.databean.importMotrice;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_import_TMDSVTH")
public class ImportTMDSVTHDataBean {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long    idTMDSVTH;
   private String  SVTH_TRCH_COD_CIE;
   private String  SVTH_TRCH_NUM_TRA1;
   private String  SVTH_TRCH_IND_FER;
   private Long    SVTH_TRCH_NUM;
   private String  SVTH_LIBS_SERV_COD;
   private Long    SVTH_NUM;
   private String  SVTH_COD_ORIGINE;
   private String  SVTH_INPT_RR_D;
   private String  SVTH_INPT_RR_F;
   private Boolean SVTH_REGI;
   private String  SVTH_IND_SPTH;
   private String  SVTH_TYP_CLAS;
   private String  SVTH_USER;
   private Date    SVTH_DHDO;

   /**
    * @return the idTMDSVTH
    */
   public Long getIdTMDSVTH() {
      return this.idTMDSVTH;
   }

   /**
    * @param idTMDSVTH
    *           the idTMDSVTH to set
    */
   public void setIdTMDSVTH(Long idTMDSVTH) {
      this.idTMDSVTH = idTMDSVTH;
   }

   /**
    * @return the sVTH_TRCH_COD_CIE
    */
   public String getSVTH_TRCH_COD_CIE() {
      return this.SVTH_TRCH_COD_CIE;
   }

   /**
    * @param sVTH_TRCH_COD_CIE
    *           the sVTH_TRCH_COD_CIE to set
    */
   public void setSVTH_TRCH_COD_CIE(String sVTH_TRCH_COD_CIE) {
      this.SVTH_TRCH_COD_CIE = sVTH_TRCH_COD_CIE;
   }

   /**
    * @return the sVTH_TRCH_NUM_TRA1
    */
   public String getSVTH_TRCH_NUM_TRA1() {
      return this.SVTH_TRCH_NUM_TRA1;
   }

   /**
    * @param sVTH_TRCH_NUM_TRA1
    *           the sVTH_TRCH_NUM_TRA1 to set
    */
   public void setSVTH_TRCH_NUM_TRA1(String sVTH_TRCH_NUM_TRA1) {
      this.SVTH_TRCH_NUM_TRA1 = sVTH_TRCH_NUM_TRA1;
   }

   /**
    * @return the sVTH_TRCH_IND_FER
    */
   public String getSVTH_TRCH_IND_FER() {
      return this.SVTH_TRCH_IND_FER;
   }

   /**
    * @param sVTH_TRCH_IND_FER
    *           the sVTH_TRCH_IND_FER to set
    */
   public void setSVTH_TRCH_IND_FER(String sVTH_TRCH_IND_FER) {
      this.SVTH_TRCH_IND_FER = sVTH_TRCH_IND_FER;
   }

   /**
    * @return the sVTH_TRCH_NUM
    */
   public Long getSVTH_TRCH_NUM() {
      return this.SVTH_TRCH_NUM;
   }

   /**
    * @param sVTH_TRCH_NUM
    *           the sVTH_TRCH_NUM to set
    */
   public void setSVTH_TRCH_NUM(Long sVTH_TRCH_NUM) {
      this.SVTH_TRCH_NUM = sVTH_TRCH_NUM;
   }

   /**
    * @return the sVTH_LIBS_SERV_COD
    */
   public String getSVTH_LIBS_SERV_COD() {
      return this.SVTH_LIBS_SERV_COD;
   }

   /**
    * @param sVTH_LIBS_SERV_COD
    *           the sVTH_LIBS_SERV_COD to set
    */
   public void setSVTH_LIBS_SERV_COD(String sVTH_LIBS_SERV_COD) {
      this.SVTH_LIBS_SERV_COD = sVTH_LIBS_SERV_COD;
   }

   /**
    * @return the sVTH_NUM
    */
   public Long getSVTH_NUM() {
      return this.SVTH_NUM;
   }

   /**
    * @param sVTH_NUM
    *           the sVTH_NUM to set
    */
   public void setSVTH_NUM(Long sVTH_NUM) {
      this.SVTH_NUM = sVTH_NUM;
   }

   /**
    * @return the sVTH_COD_ORIGINE
    */
   public String getSVTH_COD_ORIGINE() {
      return this.SVTH_COD_ORIGINE;
   }

   /**
    * @param sVTH_COD_ORIGINE
    *           the sVTH_COD_ORIGINE to set
    */
   public void setSVTH_COD_ORIGINE(String sVTH_COD_ORIGINE) {
      this.SVTH_COD_ORIGINE = sVTH_COD_ORIGINE;
   }

   /**
    * @return the sVTH_INPT_RR_D
    */
   public String getSVTH_INPT_RR_D() {
      return this.SVTH_INPT_RR_D;
   }

   /**
    * @param sVTH_INPT_RR_D
    *           the sVTH_INPT_RR_D to set
    */
   public void setSVTH_INPT_RR_D(String sVTH_INPT_RR_D) {
      this.SVTH_INPT_RR_D = sVTH_INPT_RR_D;
   }

   /**
    * @return the sVTH_INPT_RR_F
    */
   public String getSVTH_INPT_RR_F() {
      return this.SVTH_INPT_RR_F;
   }

   /**
    * @param sVTH_INPT_RR_F
    *           the sVTH_INPT_RR_F to set
    */
   public void setSVTH_INPT_RR_F(String sVTH_INPT_RR_F) {
      this.SVTH_INPT_RR_F = sVTH_INPT_RR_F;
   }

   /**
    * @return the sVTH_REGI
    */
   public Boolean getSVTH_REGI() {
      return this.SVTH_REGI;
   }

   /**
    * @param sVTH_REGI
    *           the sVTH_REGI to set
    */
   public void setSVTH_REGI(Boolean sVTH_REGI) {
      this.SVTH_REGI = sVTH_REGI;
   }

   /**
    * @return the sVTH_IND_SPTH
    */
   public String getSVTH_IND_SPTH() {
      return this.SVTH_IND_SPTH;
   }

   /**
    * @param sVTH_IND_SPTH
    *           the sVTH_IND_SPTH to set
    */
   public void setSVTH_IND_SPTH(String sVTH_IND_SPTH) {
      this.SVTH_IND_SPTH = sVTH_IND_SPTH;
   }

   /**
    * @return the sVTH_TYP_CLAS
    */
   public String getSVTH_TYP_CLAS() {
      return this.SVTH_TYP_CLAS;
   }

   /**
    * @param sVTH_TYP_CLAS
    *           the sVTH_TYP_CLAS to set
    */
   public void setSVTH_TYP_CLAS(String sVTH_TYP_CLAS) {
      this.SVTH_TYP_CLAS = sVTH_TYP_CLAS;
   }

   /**
    * @return the sVTH_USER
    */
   public String getSVTH_USER() {
      return this.SVTH_USER;
   }

   /**
    * @param sVTH_USER
    *           the sVTH_USER to set
    */
   public void setSVTH_USER(String sVTH_USER) {
      this.SVTH_USER = sVTH_USER;
   }

   /**
    * @return the sVTH_DHDO
    */
   public Date getSVTH_DHDO() {
      return this.SVTH_DHDO;
   }

   /**
    * @param sVTH_DHDO
    *           the sVTH_DHDO to set
    */
   public void setSVTH_DHDO(Date sVTH_DHDO) {
      this.SVTH_DHDO = sVTH_DHDO;
   }

}
