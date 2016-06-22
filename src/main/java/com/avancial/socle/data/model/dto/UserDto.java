/**
 * 
 */
package com.avancial.socle.data.model.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hamza.laterem
 * 
 * Bean représente les données envoyés du serveur vers l'app concernant l'utlisateur connecté
 *
 */
public class UserDto {
   /*
    * Infos user
    */
   private String loginUser;
   private String mailUser;
   private String nomUser;
   private String prenomUser;
   private String robotUser;
   
   /*
    * Rôles
    */
   private List<Long>   idRoles;
   private List<String> labelRoles;
   /*
    * Ect, Uo, Equipe
    */

   /**
    * 
    */
   public UserDto() {
      this.idRoles = new ArrayList<Long>();
      this.labelRoles = new ArrayList<String>();
   }

   /**
    * @return the loginUser
    */
   public String getLoginUser() {
      return loginUser;
   }

   /**
    * @param loginUser the loginUser to set
    */
   public void setLoginUser(String loginUser) {
      this.loginUser = loginUser;
   }

   /**
    * @return the mailUser
    */
   public String getMailUser() {
      return mailUser;
   }

   /**
    * @param mailUser the mailUser to set
    */
   public void setMailUser(String mailUser) {
      this.mailUser = mailUser;
   }

   /**
    * @return the nomUser
    */
   public String getNomUser() {
      return nomUser;
   }

   /**
    * @param nomUser the nomUser to set
    */
   public void setNomUser(String nomUser) {
      this.nomUser = nomUser;
   }

   /**
    * @return the prenomUser
    */
   public String getPrenomUser() {
      return prenomUser;
   }

   /**
    * @param prenomUser the prenomUser to set
    */
   public void setPrenomUser(String prenomUser) {
      this.prenomUser = prenomUser;
   }

   /**
    * @return the robotUser
    */
   public String getRobotUser() {
      return robotUser;
   }

   /**
    * @param robotUser the robotUser to set
    */
   public void setRobotUser(String robotUser) {
      this.robotUser = robotUser;
   }

   /**
    * @return the idRoles
    */
   public List<Long> getIdRoles() {
      return idRoles;
   }

   /**
    * @param idRoles the idRoles to set
    */
   public void setIdRoles(List<Long> idRoles) {
      this.idRoles = idRoles;
   }

   /**
    * @return the labelRoles
    */
   public List<String> getLabelRoles() {
      return labelRoles;
   }

   /**
    * @param labelRoles the labelRoles to set
    */
   public void setLabelRoles(List<String> labelRoles) {
      this.labelRoles = labelRoles;
   }

}
