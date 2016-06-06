package com.avancial.socle.authentification.dto;

import java.util.ArrayList;
import java.util.List;
/**
 * Les données de l'utilisateur qui seront sauvegardé en session
 * et envoyé au front
 * 
 * @author hamza.laterem
 *
 */
public class UserSessionDto {
   private Long idUser;   
   private String username;
   private List<Long> idRoles;
   private List<String> labelRoles;
   
   
   public UserSessionDto() {
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
}
