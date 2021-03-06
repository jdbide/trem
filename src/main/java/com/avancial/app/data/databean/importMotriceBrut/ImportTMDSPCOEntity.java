package com.avancial.app.data.databean.importMotriceBrut;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_import_TMDSPCO")
@NamedQuery(name = "ImportTMDSPCO.getAll", query = "SELECT t FROM ImportTMDSPCOEntity t")
public class ImportTMDSPCOEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long   idTMDSPCO;
   private String SPCO_VOIT_COD_CIE;
   private String SPCO_VOIT_NUM_TRA1;
   private String SPCO_VOIT_IND_FER;
   private String SPCO_VOIT_TRCH_NUM;
   private String SPCO_VOIT_NUM;
   private String SPCO_COMP_NUM;
   private String SPCO_SPEC_COD;
   private String SPCO_REGI;
   private String SPCO_USER;
   private String SPCO_DHDO;

   /**
    * @return the idTMDSPCO
    */
   public Long getIdTMDSPCO() {
      return this.idTMDSPCO;
   }

   /**
    * @param idTMDSPCO
    *           the idTMDSPCO to set
    */
   public void setIdTMDSPCO(Long idTMDSPCO) {
      this.idTMDSPCO = idTMDSPCO;
   }

   /**
    * @return the sPCO_VOIT_COD_CIE
    */
   public String getSPCO_VOIT_COD_CIE() {
      return this.SPCO_VOIT_COD_CIE;
   }

   /**
    * @param sPCO_VOIT_COD_CIE
    *           the sPCO_VOIT_COD_CIE to set
    */
   public void setSPCO_VOIT_COD_CIE(String sPCO_VOIT_COD_CIE) {
      this.SPCO_VOIT_COD_CIE = sPCO_VOIT_COD_CIE;
   }

   /**
    * @return the sPCO_VOIT_NUM_TRA1
    */
   public String getSPCO_VOIT_NUM_TRA1() {
      return this.SPCO_VOIT_NUM_TRA1;
   }

   /**
    * @param sPCO_VOIT_NUM_TRA1
    *           the sPCO_VOIT_NUM_TRA1 to set
    */
   public void setSPCO_VOIT_NUM_TRA1(String sPCO_VOIT_NUM_TRA1) {
      this.SPCO_VOIT_NUM_TRA1 = sPCO_VOIT_NUM_TRA1;
   }

   /**
    * @return the sPCO_VOIT_IND_FER
    */
   public String getSPCO_VOIT_IND_FER() {
      return this.SPCO_VOIT_IND_FER;
   }

   /**
    * @param sPCO_VOIT_IND_FER
    *           the sPCO_VOIT_IND_FER to set
    */
   public void setSPCO_VOIT_IND_FER(String sPCO_VOIT_IND_FER) {
      this.SPCO_VOIT_IND_FER = sPCO_VOIT_IND_FER;
   }

   /**
    * @return the sPCO_VOIT_TRCH_NUM
    */
   public String getSPCO_VOIT_TRCH_NUM() {
      return this.SPCO_VOIT_TRCH_NUM;
   }

   /**
    * @param sPCO_VOIT_TRCH_NUM
    *           the sPCO_VOIT_TRCH_NUM to set
    */
   public void setSPCO_VOIT_TRCH_NUM(String sPCO_VOIT_TRCH_NUM) {
      this.SPCO_VOIT_TRCH_NUM = sPCO_VOIT_TRCH_NUM;
   }

   /**
    * @return the sPCO_VOIT_NUM
    */
   public String getSPCO_VOIT_NUM() {
      return this.SPCO_VOIT_NUM;
   }

   /**
    * @param sPCO_VOIT_NUM
    *           the sPCO_VOIT_NUM to set
    */
   public void setSPCO_VOIT_NUM(String sPCO_VOIT_NUM) {
      this.SPCO_VOIT_NUM = sPCO_VOIT_NUM;
   }

   /**
    * @return the sPCO_COMP_NUM
    */
   public String getSPCO_COMP_NUM() {
      return this.SPCO_COMP_NUM;
   }

   /**
    * @param sPCO_COMP_NUM
    *           the sPCO_COMP_NUM to set
    */
   public void setSPCO_COMP_NUM(String sPCO_COMP_NUM) {
      this.SPCO_COMP_NUM = sPCO_COMP_NUM;
   }

   /**
    * @return the sPCO_SPEC_COD
    */
   public String getSPCO_SPEC_COD() {
      return this.SPCO_SPEC_COD;
   }

   /**
    * @param sPCO_SPEC_COD
    *           the sPCO_SPEC_COD to set
    */
   public void setSPCO_SPEC_COD(String sPCO_SPEC_COD) {
      this.SPCO_SPEC_COD = sPCO_SPEC_COD;
   }

   /**
    * @return the sPCO_REGI
    */
   public String getSPCO_REGI() {
      return this.SPCO_REGI;
   }

   /**
    * @param sPCO_REGI
    *           the sPCO_REGI to set
    */
   public void setSPCO_REGI(String sPCO_REGI) {
      this.SPCO_REGI = sPCO_REGI;
   }

   /**
    * @return the sPCO_USER
    */
   public String getSPCO_USER() {
      return this.SPCO_USER;
   }

   /**
    * @param sPCO_USER
    *           the sPCO_USER to set
    */
   public void setSPCO_USER(String sPCO_USER) {
      this.SPCO_USER = sPCO_USER;
   }

   /**
    * @return the sPCO_DHDO
    */
   public String getSPCO_DHDO() {
      return this.SPCO_DHDO;
   }

   /**
    * @param sPCO_DHDO
    *           the sPCO_DHDO to set
    */
   public void setSPCO_DHDO(String sPCO_DHDO) {
      this.SPCO_DHDO = sPCO_DHDO;
   }

}
