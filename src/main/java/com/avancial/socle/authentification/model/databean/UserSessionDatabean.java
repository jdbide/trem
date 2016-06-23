package com.avancial.socle.authentification.model.databean;

import java.util.ArrayList;
import java.util.List;
/**
 * Les données de l'utilisateur qui seront sauvegardé en session
 * 
 * @author hamza.laterem
 *
 */
public class UserSessionDatabean {
   /*
    * Infos user
    */
   private Long idUser;   
   private String username;   
   private String nomUser;
   private String prenomUser;
   private String mailUser;
   private String cpUser;   
   private boolean robotUser;
   
   /*
    * Rôles
    */
   private List<Long> idRoles;
   private List<String> labelRoles;
   /*
    * Ect, Uo, Equipe
    */
   private String libelleEct;
   private String libelleUo;
   private String libelleEquipe;
   
   public UserSessionDatabean() {
      this.idRoles = new ArrayList<Long>();
      this.labelRoles = new ArrayList<String>();
   }

   public void addRole(String role) {
      this.labelRoles.add(role);
   }
   
   public void addRole(Long role) {
      this.idRoles.add(role);
   }
   
   /**
    * @return the username
    */
   public String getUsername() {
      return this.username;
   }
   
   /**
    * @param username the username to set
    */
   public void setUsername(String username) {
      this.username = username;
   }

   /**
    * @return the idUser
    */
   public Long getIdUser() {
      return this.idUser;
   }

   /**
    * @param idUser the idUser to set
    */
   public void setIdUser(Long idUser) {
      this.idUser = idUser;
   }

   /**
    * @return the idRoles
    */
   public List<Long> getIdRoles() {
      return this.idRoles;
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
      return this.labelRoles;
   }

   /**
    * @param labelRoles the labelRoles to set
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
    * @param libelleEct the libelleEct to set
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
    * @param libelleUo the libelleUo to set
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
    * @param libelleEquipe the libelleEquipe to set
    */
   public void setLibelleEquipe(String libelleEquipe) {
      this.libelleEquipe = libelleEquipe;
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
    * @return the cpUser
    */
   public String getCpUser() {
      return cpUser;
   }

   /**
    * @param cpUser the cpUser to set
    */
   public void setCpUser(String cpUser) {
      this.cpUser = cpUser;
   }   

   /**
    * @return the robotUser
    */
   public boolean isRobotUser() {
      return robotUser;
   }

   /**
    * @param robotUser the robotUser to set
    */
   public void setRobotUser(boolean robotUser) {
      this.robotUser = robotUser;
   }

}
