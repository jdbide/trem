package com.avancial.app.data.databean.importMotrice;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_import_TMDTRA1")
@NamedQuery(name = "ImportTMDTRA1.getAll", query= "SELECT t FROM ImportTMDTRA1DataBean t")
public class ImportTMDTRA1DataBean {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long    idTMDTRA1;
   private String  TRA1_CIES_COD_CIE;
   private String  TRA1_NUM_TRAIN;
   private String  TRA1_IND_FER_ROUTE;
   private String  TRA1_LIBS_GERE_COD;
   private String  TRA1_NOM_TRAIN;
   private String  TRA1_AUT_MOD_THOR;
   private String  TRA1_NAT_MOD_THOR;
   private Date    TRA1_DAT_MOD_THOR;
   private Date    TRA1_DAT_DER_MOD;
   private String  TRA1_IND_COMPO;
   private String  TRA1_IND_CDEM;
   private String  TRA1_IND_ECOLE;
   private Boolean TRA1_REGI_VAL;
   private Boolean TRA1_REGI_VAL_TRTH;
   private String  TRA1_USER;
   private Date    TRA1_DHDO;
   private Boolean TRA1_REGI_NON_EXTR;

   public Long getIdTMDTRA1() {
      return this.idTMDTRA1;
   }

   public void setIdTMDTRA1(Long idTMDTRA1) {
      this.idTMDTRA1 = idTMDTRA1;
   }

   public String getTRA1_CIES_COD_CIE() {
      return this.TRA1_CIES_COD_CIE;
   }

   public void setTRA1_CIES_COD_CIE(String tRA1_CIES_COD_CIE) {
      this.TRA1_CIES_COD_CIE = tRA1_CIES_COD_CIE;
   }

   public String getTRA1_NUM_TRAIN() {
      return this.TRA1_NUM_TRAIN;
   }

   public void setTRA1_NUM_TRAIN(String tRA1_NUM_TRAIN) {
      this.TRA1_NUM_TRAIN = tRA1_NUM_TRAIN;
   }

   public String getTRA1_IND_FER_ROUTE() {
      return this.TRA1_IND_FER_ROUTE;
   }

   public void setTRA1_IND_FER_ROUTE(String tRA1_IND_FER_ROUTE) {
      this.TRA1_IND_FER_ROUTE = tRA1_IND_FER_ROUTE;
   }

   public String getTRA1_LIBS_GERE_COD() {
      return this.TRA1_LIBS_GERE_COD;
   }

   public void setTRA1_LIBS_GERE_COD(String tRA1_LIBS_GERE_COD) {
      this.TRA1_LIBS_GERE_COD = tRA1_LIBS_GERE_COD;
   }

   public String getTRA1_NOM_TRAIN() {
      return this.TRA1_NOM_TRAIN;
   }

   public void setTRA1_NOM_TRAIN(String tRA1_NOM_TRAIN) {
      this.TRA1_NOM_TRAIN = tRA1_NOM_TRAIN;
   }

   public String getTRA1_AUT_MOD_THOR() {
      return this.TRA1_AUT_MOD_THOR;
   }

   public void setTRA1_AUT_MOD_THOR(String tRA1_AUT_MOD_THOR) {
      this.TRA1_AUT_MOD_THOR = tRA1_AUT_MOD_THOR;
   }

   public String getTRA1_NAT_MOD_THOR() {
      return this.TRA1_NAT_MOD_THOR;
   }

   public void setTRA1_NAT_MOD_THOR(String tRA1_NAT_MOD_THOR) {
      this.TRA1_NAT_MOD_THOR = tRA1_NAT_MOD_THOR;
   }

   public Date getTRA1_DAT_MOD_THOR() {
      return this.TRA1_DAT_MOD_THOR;
   }

   public void setTRA1_DAT_MOD_THOR(Date tRA1_DAT_MOD_THOR) {
      this.TRA1_DAT_MOD_THOR = tRA1_DAT_MOD_THOR;
   }

   public Date getTRA1_DAT_DER_MOD() {
      return this.TRA1_DAT_DER_MOD;
   }

   public void setTRA1_DAT_DER_MOD(Date tRA1_DAT_DER_MOD) {
      this.TRA1_DAT_DER_MOD = tRA1_DAT_DER_MOD;
   }

   public String getTRA1_IND_COMPO() {
      return this.TRA1_IND_COMPO;
   }

   public void setTRA1_IND_COMPO(String tRA1_IND_COMPO) {
      this.TRA1_IND_COMPO = tRA1_IND_COMPO;
   }

   public String getTRA1_IND_CDEM() {
      return this.TRA1_IND_CDEM;
   }

   public void setTRA1_IND_CDEM(String tRA1_IND_CDEM) {
      this.TRA1_IND_CDEM = tRA1_IND_CDEM;
   }

   public String getTRA1_IND_ECOLE() {
      return this.TRA1_IND_ECOLE;
   }

   public void setTRA1_IND_ECOLE(String tRA1_IND_ECOLE) {
      this.TRA1_IND_ECOLE = tRA1_IND_ECOLE;
   }

   public Boolean getTRA1_REGI_VAL() {
      return this.TRA1_REGI_VAL;
   }

   public void setTRA1_REGI_VAL(Boolean tRA1_REGI_VAL) {
      this.TRA1_REGI_VAL = tRA1_REGI_VAL;
   }

   public Boolean getTRA1_REGI_VAL_TRTH() {
      return this.TRA1_REGI_VAL_TRTH;
   }

   public void setTRA1_REGI_VAL_TRTH(Boolean tRA1_REGI_VAL_TRTH) {
      this.TRA1_REGI_VAL_TRTH = tRA1_REGI_VAL_TRTH;
   }

   public String getTRA1_USER() {
      return this.TRA1_USER;
   }

   public void setTRA1_USER(String tRA1_USER) {
      this.TRA1_USER = tRA1_USER;
   }

   public Date getTRA1_DHDO() {
      return this.TRA1_DHDO;
   }

   public void setTRA1_DHDO(Date tRA1_DHDO) {
      this.TRA1_DHDO = tRA1_DHDO;
   }

   public Boolean getTRA1_REGI_NON_EXTR() {
      return this.TRA1_REGI_NON_EXTR;
   }

   public void setTRA1_REGI_NON_EXTR(Boolean tRA1_REGI_NON_EXTR) {
      this.TRA1_REGI_NON_EXTR = tRA1_REGI_NON_EXTR;
   }
}
