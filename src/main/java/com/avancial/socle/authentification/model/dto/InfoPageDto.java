package com.avancial.socle.authentification.model.dto;

import java.io.Serializable;
/**
 * Bean qui représente les informations affichés sur la page de login
 * 
 * @author hamza.laterem
 *
 */
public class InfoPageDto implements Serializable{   
   private static final long serialVersionUID = 1L;
   
   private String societeName;
   private String appName;
   private String appVersionEtEnv;
   private String msgHeaderLogin;
   private Boolean actifServerBdd;
   private String msgServerBdd;
   /* TODO Mise en place du singleton au niveau SocleInit */
   private Boolean actifJob;
   
   public InfoPageDto() {      
   }

   /**
    * @return the societeName
    */
   public String getSocieteName() {
      return societeName;
   }

   /**
    * @param societeName the societeName to set
    */
   public void setSocieteName(String societeName) {
      this.societeName = societeName;
   }

   /**
    * @return the appName
    */
   public String getAppName() {
      return appName;
   }

   /**
    * @param appName the appName to set
    */
   public void setAppName(String appName) {
      this.appName = appName;
   }

   /**
    * @return the appVersionEtEnv
    */
   public String getAppVersionEtEnv() {
      return appVersionEtEnv;
   }

   /**
    * @param appVersionEtEnv the appVersionEtEnv to set
    */
   public void setAppVersionEtEnv(String appVersionEtEnv) {
      this.appVersionEtEnv = appVersionEtEnv;
   }

   /**
    * @return the msgHeaderLogin
    */
   public String getMsgHeaderLogin() {
      return msgHeaderLogin;
   }

   /**
    * @param msgHeaderLogin the msgHeaderLogin to set
    */
   public void setMsgHeaderLogin(String msgHeaderLogin) {
      this.msgHeaderLogin = msgHeaderLogin;
   }

   /**
    * @return the actifServerBdd
    */
   public Boolean getActifServerBdd() {
      return actifServerBdd;
   }

   /**
    * @param actifServerBdd the actifServerBdd to set
    */
   public void setActifServerBdd(Boolean actifServerBdd) {
      this.actifServerBdd = actifServerBdd;
   }

   /**
    * @return the msgServerBdd
    */
   public String getMsgServerBdd() {
      return msgServerBdd;
   }

   /**
    * @param msgServerBdd the msgServerBdd to set
    */
   public void setMsgServerBdd(String msgServerBdd) {
      this.msgServerBdd = msgServerBdd;
   }

   /**
    * @return the actifJob
    */
   public Boolean getActifJob() {
      return actifJob;
   }

   /**
    * @param actifJob the actifJob to set
    */
   public void setActifJob(Boolean actifJob) {
      this.actifJob = actifJob;
   }

}
