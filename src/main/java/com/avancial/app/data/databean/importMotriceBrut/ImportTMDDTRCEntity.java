package com.avancial.app.data.databean.importMotriceBrut;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_import_TMDDTRC")
@NamedQuery(name = "ImportTMDDTRC.getAll", query = "SELECT t FROM ImportTMDDTRCEntity t")
public class ImportTMDDTRCEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long   idTMDDTRC;
   private String DTRC_TRCH_COD_CIE;
   private String DTRC_TRCH_NUM_TRCH;
   private String DTRC_TRCH_IND_FER;
   private String DTRC_TRCH_NUM;
   private String DTRC_CODE;
   private String DTRC_REGI;
   private String DTRC_USER;
   private String DTRC_DHDO;

   /**
    * @return the idTMDDTRC
    */
   public Long getIdTMDDTRC() {
      return idTMDDTRC;
   }

   /**
    * @param idTMDDTRC
    *           the idTMDDTRC to set
    */
   public void setIdTMDDTRC(Long idTMDDTRC) {
      this.idTMDDTRC = idTMDDTRC;
   }

   /**
    * @return the dTRC_TRCH_COD_CIE
    */
   public String getDTRC_TRCH_COD_CIE() {
      return DTRC_TRCH_COD_CIE;
   }

   /**
    * @param dTRC_TRCH_COD_CIE
    *           the dTRC_TRCH_COD_CIE to set
    */
   public void setDTRC_TRCH_COD_CIE(String dTRC_TRCH_COD_CIE) {
      DTRC_TRCH_COD_CIE = dTRC_TRCH_COD_CIE;
   }

   /**
    * @return the dTRC_TRCH_NUM_TRCH
    */
   public String getDTRC_TRCH_NUM_TRCH() {
      return DTRC_TRCH_NUM_TRCH;
   }

   /**
    * @param dTRC_TRCH_NUM_TRCH
    *           the dTRC_TRCH_NUM_TRCH to set
    */
   public void setDTRC_TRCH_NUM_TRCH(String dTRC_TRCH_NUM_TRCH) {
      DTRC_TRCH_NUM_TRCH = dTRC_TRCH_NUM_TRCH;
   }

   /**
    * @return the dTRC_TRCH_IND_FER
    */
   public String getDTRC_TRCH_IND_FER() {
      return DTRC_TRCH_IND_FER;
   }

   /**
    * @param dTRC_TRCH_IND_FER
    *           the dTRC_TRCH_IND_FER to set
    */
   public void setDTRC_TRCH_IND_FER(String dTRC_TRCH_IND_FER) {
      DTRC_TRCH_IND_FER = dTRC_TRCH_IND_FER;
   }

   /**
    * @return the dTRC_TRCH_NUM
    */
   public String getDTRC_TRCH_NUM() {
      return DTRC_TRCH_NUM;
   }

   /**
    * @param dTRC_TRCH_NUM
    *           the dTRC_TRCH_NUM to set
    */
   public void setDTRC_TRCH_NUM(String dTRC_TRCH_NUM) {
      DTRC_TRCH_NUM = dTRC_TRCH_NUM;
   }

   /**
    * @return the dTRC_CODE
    */
   public String getDTRC_CODE() {
      return DTRC_CODE;
   }

   /**
    * @param dTRC_CODE
    *           the dTRC_CODE to set
    */
   public void setDTRC_CODE(String dTRC_CODE) {
      DTRC_CODE = dTRC_CODE;
   }

   /**
    * @return the dTRC_REGI
    */
   public String getDTRC_REGI() {
      return DTRC_REGI;
   }

   /**
    * @param dTRC_REGI
    *           the dTRC_REGI to set
    */
   public void setDTRC_REGI(String dTRC_REGI) {
      DTRC_REGI = dTRC_REGI;
   }

   /**
    * @return the dTRC_USER
    */
   public String getDTRC_USER() {
      return DTRC_USER;
   }

   /**
    * @param dTRC_USER
    *           the dTRC_USER to set
    */
   public void setDTRC_USER(String dTRC_USER) {
      DTRC_USER = dTRC_USER;
   }

   /**
    * @return the dTRC_DHDO
    */
   public String getDTRC_DHDO() {
      return DTRC_DHDO;
   }

   /**
    * @param dTRC_DHDO
    *           the dTRC_DHDO to set
    */
   public void setDTRC_DHDO(String dTRC_DHDO) {
      DTRC_DHDO = dTRC_DHDO;
   }

}
