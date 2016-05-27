package com.avancial.app.data.databean.importMotrice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_import_TMDPARE")
@NamedQuery(name = "ImportTMDPARE.getAll", query = "SELECT t FROM ImportTMDPAREEntity t")
public class ImportTMDPAREEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long   idTMDPARE;
   private String PARE_SVTH_COD_CIE;
   private String PARE_SVTH_NUM_TRA1;
   private String PARE_SVTH_IND_FER;
   private String PARE_SVTH_TRCH_NUM;
   private String PARE_SVTH_SERV_COD;
   private String PARE_SVTH_NUM;
   private String PARE_NUM_REST;
   private String PARE_TYRE_COD_REP;
   private String PARE_RESP_VOIT_NUM;
   private String PARE_VRES_NUM_SERV;
   private String PARE_H_DEB_SERV;
   private String PARE_H_FIN_SERV;
   private String PARE_REGI;
   private String PARE_NB_REPAS;
   private String PARE_IND_PLACE;
   private String PARE_IND_VOIT_REST;
   private String PARE_USER;
   private String PARE_DHDO;

   public Long getIdTMDPARE() {
      return this.idTMDPARE;
   }

   public void setIdTMDPARE(Long idTMDPARE) {
      this.idTMDPARE = idTMDPARE;
   }

   public String getPARE_SVTH_COD_CIE() {
      return this.PARE_SVTH_COD_CIE;
   }

   public void setPARE_SVTH_COD_CIE(String pARE_SVTH_COD_CIE) {
      this.PARE_SVTH_COD_CIE = pARE_SVTH_COD_CIE;
   }

   public String getPARE_SVTH_NUM_TRA1() {
      return this.PARE_SVTH_NUM_TRA1;
   }

   public void setPARE_SVTH_NUM_TRA1(String pARE_SVTH_NUM_TRA1) {
      this.PARE_SVTH_NUM_TRA1 = pARE_SVTH_NUM_TRA1;
   }

   public String getPARE_SVTH_IND_FER() {
      return this.PARE_SVTH_IND_FER;
   }

   public void setPARE_SVTH_IND_FER(String pARE_SVTH_IND_FER) {
      this.PARE_SVTH_IND_FER = pARE_SVTH_IND_FER;
   }

   public String getPARE_SVTH_TRCH_NUM() {
      return this.PARE_SVTH_TRCH_NUM;
   }

   public void setPARE_SVTH_TRCH_NUM(String pARE_SVTH_TRCH_NUM) {
      this.PARE_SVTH_TRCH_NUM = pARE_SVTH_TRCH_NUM;
   }

   public String getPARE_SVTH_SERV_COD() {
      return this.PARE_SVTH_SERV_COD;
   }

   public void setPARE_SVTH_SERV_COD(String pARE_SVTH_SERV_COD) {
      this.PARE_SVTH_SERV_COD = pARE_SVTH_SERV_COD;
   }

   public String getPARE_SVTH_NUM() {
      return this.PARE_SVTH_NUM;
   }

   public void setPARE_SVTH_NUM(String pARE_SVTH_NUM) {
      this.PARE_SVTH_NUM = pARE_SVTH_NUM;
   }

   public String getPARE_NUM_REST() {
      return this.PARE_NUM_REST;
   }

   public void setPARE_NUM_REST(String pARE_NUM_REST) {
      this.PARE_NUM_REST = pARE_NUM_REST;
   }

   public String getPARE_TYRE_COD_REP() {
      return this.PARE_TYRE_COD_REP;
   }

   public void setPARE_TYRE_COD_REP(String pARE_TYRE_COD_REP) {
      this.PARE_TYRE_COD_REP = pARE_TYRE_COD_REP;
   }

   public String getPARE_RESP_VOIT_NUM() {
      return this.PARE_RESP_VOIT_NUM;
   }

   public void setPARE_RESP_VOIT_NUM(String pARE_RESP_VOIT_NUM) {
      this.PARE_RESP_VOIT_NUM = pARE_RESP_VOIT_NUM;
   }

   public String getPARE_VRES_NUM_SERV() {
      return this.PARE_VRES_NUM_SERV;
   }

   public void setPARE_VRES_NUM_SERV(String pARE_VRES_NUM_SERV) {
      this.PARE_VRES_NUM_SERV = pARE_VRES_NUM_SERV;
   }

   public String getPARE_H_DEB_SERV() {
      return this.PARE_H_DEB_SERV;
   }

   public void setPARE_H_DEB_SERV(String pARE_H_DEB_SERV) {
      this.PARE_H_DEB_SERV = pARE_H_DEB_SERV;
   }

   public String getPARE_H_FIN_SERV() {
      return this.PARE_H_FIN_SERV;
   }

   public void setPARE_H_FIN_SERV(String pARE_H_FIN_SERV) {
      this.PARE_H_FIN_SERV = pARE_H_FIN_SERV;
   }

   public String getPARE_REGI() {
      return this.PARE_REGI;
   }

   public void setPARE_REGI(String pARE_REGI) {
      this.PARE_REGI = pARE_REGI;
   }

   public String getPARE_NB_REPAS() {
      return this.PARE_NB_REPAS;
   }

   public void setPARE_NB_REPAS(String pARE_NB_REPAS) {
      this.PARE_NB_REPAS = pARE_NB_REPAS;
   }

   public String getPARE_IND_PLACE() {
      return this.PARE_IND_PLACE;
   }

   public void setPARE_IND_PLACE(String pARE_IND_PLACE) {
      this.PARE_IND_PLACE = pARE_IND_PLACE;
   }

   public String getPARE_IND_VOIT_REST() {
      return this.PARE_IND_VOIT_REST;
   }

   public void setPARE_IND_VOIT_REST(String pARE_IND_VOIT_REST) {
      this.PARE_IND_VOIT_REST = pARE_IND_VOIT_REST;
   }

   public String getPARE_USER() {
      return this.PARE_USER;
   }

   public void setPARE_USER(String pARE_USER) {
      this.PARE_USER = pARE_USER;
   }

   public String getPARE_DHDO() {
      return this.PARE_DHDO;
   }

   public void setPARE_DHDO(String pARE_DHDO) {
      this.PARE_DHDO = pARE_DHDO;
   }
}
