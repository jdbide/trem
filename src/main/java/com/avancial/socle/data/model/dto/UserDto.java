/**
 * 
 */
package com.avancial.socle.data.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.avancial.socle.authentification.model.User;

/**
 * @author hamza.laterem
 * 
 *         Bean représente les données envoyés du serveur vers l'app concernant l'utlisateur connecté
 *
 */
public class UserDto {
   /*
    * Infos user
    */
   private String       nomUser;
   private String       prenomUser;
   private String       mailUser;
   private String       cpUser;
   private String       robotUser;
   private String       loginUser;

   /*
    * Rôles
    */
   private List<Long>   idRoles;
   private List<String> labelRoles;
   /*
    * Ect, Uo, Equipe
    */
   private String       libelleEct;
   private String       libelleUo;
   private String       libelleEquipe;

   /**
    * 
    */
   public UserDto() {
      this.idRoles = new ArrayList<>();
      this.labelRoles = new ArrayList<>();
   }

   public void setUserInfoFromUser(User user) {

      this.setCpUser(user.getCpUser());
      this.setNomUser(user.getNomUser());
      this.setPrenomUser(user.getPrenomUser());
      this.setLoginUser(user.getLoginUser());

   }

   /**
    * @return the mailUser
    */
   public String getMailUser() {
      return mailUser;
   }

   /**
    * @param mailUser
    *           the mailUser to set
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
    * @param nomUser
    *           the nomUser to set
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
    * @param prenomUser
    *           the prenomUser to set
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
    * @param robotUser
    *           the robotUser to set
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
    * @param idRoles
    *           the idRoles to set
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
    * @param labelRoles
    *           the labelRoles to set
    */
   public void setLabelRoles(List<String> labelRoles) {
      this.labelRoles = labelRoles;
   }

   /**
    * @return the libelleEct
    */
   public String getLibelleEct() {
      return libelleEct;
   }

   /**
    * @param libelleEct
    *           the libelleEct to set
    */
   public void setLibelleEct(String libelleEct) {
      this.libelleEct = libelleEct;
   }

   /**
    * @return the libelleUo
    */
   public String getLibelleUo() {
      return libelleUo;
   }

   /**
    * @param libelleUo
    *           the libelleUo to set
    */
   public void setLibelleUo(String libelleUo) {
      this.libelleUo = libelleUo;
   }

   /**
    * @return the libelleEquipe
    */
   public String getLibelleEquipe() {
      return libelleEquipe;
   }

   /**
    * @param libelleEquipe
    *           the libelleEquipe to set
    */
   public void setLibelleEquipe(String libelleEquipe) {
      this.libelleEquipe = libelleEquipe;
   }

   /**
    * @return the cpUser
    */
   public String getCpUser() {
      return cpUser;
   }

   /**
    * @param cpUser
    *           the cpUser to set
    */
   public void setCpUser(String cpUser) {
      this.cpUser = cpUser;
   }

   public String getLoginUser() {
      return this.loginUser;
   }

   public void setLoginUser(String loginUser) {
      this.loginUser = loginUser;
   }

}
