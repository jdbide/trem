package com.avancial.app.data.databean.importMotriceBrut;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_import_TMDKAPP")
@NamedQueries({
    @NamedQuery(name = "ImportTMDKAPP.getAll", query = "SELECT t FROM ImportTMDKAPPEntity t"),
    @NamedQuery(name = "ImportTMDKAPP.getKht", query = "SELECT t FROM ImportTMDKAPPEntity t WHERE t.KAPP_APP = 'KHT'")
})

public class ImportTMDKAPPEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long   idTMDKAPP;
   private String KAPP_APP;
   private String KAPP_CON;
   private String KAPP_TRA;
   private String KAPP_ORI;
   private String KAPP_DEX;
   private String KAPP_INF;
   private String KAPP_MOD;
   private String KAPP_QAL;
   private String KAPP_SER;
   private String KAPP_TIT;
   private String KAPP_VAL;
   private String KAPP_VES;
   private String KAPP_VER;
   private String KAPP_GLO;
   private String KAPP_USER;
   private String KAPP_DHDO;
   private String KAPP_DTR;

   public Long getIdTMDKAPP() {
      return this.idTMDKAPP;
   }

   public void setIdTMDKAPP(Long idTMDKAPP) {
      this.idTMDKAPP = idTMDKAPP;
   }

   public String getKAPP_APP() {
      return this.KAPP_APP;
   }

   public void setKAPP_APP(String kAPP_APP) {
      this.KAPP_APP = kAPP_APP;
   }

   public String getKAPP_CON() {
      return this.KAPP_CON;
   }

   public void setKAPP_CON(String kAPP_CON) {
      this.KAPP_CON = kAPP_CON;
   }

   public String getKAPP_TRA() {
      return this.KAPP_TRA;
   }

   public void setKAPP_TRA(String kAPP_TRA) {
      this.KAPP_TRA = kAPP_TRA;
   }

   public String getKAPP_ORI() {
      return this.KAPP_ORI;
   }

   public void setKAPP_ORI(String kAPP_ORI) {
      this.KAPP_ORI = kAPP_ORI;
   }

   public String getKAPP_DEX() {
      return this.KAPP_DEX;
   }

   public void setKAPP_DEX(String kAPP_DEX) {
      this.KAPP_DEX = kAPP_DEX;
   }

   public String getKAPP_INF() {
      return this.KAPP_INF;
   }

   public void setKAPP_INF(String kAPP_INF) {
      this.KAPP_INF = kAPP_INF;
   }

   public String getKAPP_MOD() {
      return this.KAPP_MOD;
   }

   public void setKAPP_MOD(String kAPP_MOD) {
      this.KAPP_MOD = kAPP_MOD;
   }

   public String getKAPP_QAL() {
      return this.KAPP_QAL;
   }

   public void setKAPP_QAL(String kAPP_QAL) {
      this.KAPP_QAL = kAPP_QAL;
   }

   public String getKAPP_SER() {
      return this.KAPP_SER;
   }

   public void setKAPP_SER(String kAPP_SER) {
      this.KAPP_SER = kAPP_SER;
   }

   public String getKAPP_TIT() {
      return this.KAPP_TIT;
   }

   public void setKAPP_TIT(String kAPP_TIT) {
      this.KAPP_TIT = kAPP_TIT;
   }

   public String getKAPP_VAL() {
      return this.KAPP_VAL;
   }

   public void setKAPP_VAL(String kAPP_VAL) {
      this.KAPP_VAL = kAPP_VAL;
   }

   public String getKAPP_VES() {
      return this.KAPP_VES;
   }

   public void setKAPP_VES(String kAPP_VES) {
      this.KAPP_VES = kAPP_VES;
   }

   public String getKAPP_VER() {
      return this.KAPP_VER;
   }

   public void setKAPP_VER(String kAPP_VER) {
      this.KAPP_VER = kAPP_VER;
   }

   public String getKAPP_GLO() {
      return this.KAPP_GLO;
   }

   public void setKAPP_GLO(String kAPP_GLO) {
      this.KAPP_GLO = kAPP_GLO;
   }

   public String getKAPP_USER() {
      return this.KAPP_USER;
   }

   public void setKAPP_USER(String kAPP_USER) {
      this.KAPP_USER = kAPP_USER;
   }

   public String getKAPP_DHDO() {
      return this.KAPP_DHDO;
   }

   public void setKAPP_DHDO(String kAPP_DHDO) {
      this.KAPP_DHDO = kAPP_DHDO;
   }

   public String getKAPP_DTR() {
      return this.KAPP_DTR;
   }

   public void setKAPP_DTR(String kAPP_DTR) {
      this.KAPP_DTR = kAPP_DTR;
   }
}
