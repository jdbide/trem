package com.avancial.app.data.dto;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.Status;

/**
 * Ce Bean représente les données à afficher sur la page import du module Train Management System.
 * 
 * @author hamza.laterem
 *
 */
public class ImportTmsDto implements Serializable{

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private int idCompagnieEnvironnement;
   private String libelleCompagnie;
   private String libelleEnvironnement;
   
   private int idJeuDonneesActif;   
   private Date dateImportJeuDonneesActif;
   private String importJeuDonneesActifBy;
   private Date dateValidateJeuDonneesActif;
   private String validateJeuDonneesActifBy;
   private String pathValidateJeuDonneesActif;
   private Status statusJeudonneeActif;
   
   private int idJeuDonneeBrouillon;
   private Date dateImportJeuDonneesBrouillon;
   private String importJeuDonneesBrouillonBy;
   private String pathValidateJeuDonneesBrouillon;
   private Status statusJeudonneeBrouillon;
   
   private String username;
   private String password;
   
   public ImportTmsDto() {
      // TODO Auto-generated constructor stub
   }
   
   @JsonIgnore
   public void mergeByJeuDonneesBrouillon (JeuDonneeEntity jeuDonneeEntityBrouillon, String nameImportJeuDonneesBrouillonBy) {
      this.setIdCompagnieEnvironnement(jeuDonneeEntityBrouillon.getCompagnieEnvironnement().getIdCompagnieEnvironnement());
      this.setLibelleCompagnie(jeuDonneeEntityBrouillon.getCompagnieEnvironnement().getLibelleCompagnie());
      this.setLibelleEnvironnement(jeuDonneeEntityBrouillon.getCompagnieEnvironnement().getLibelleEnvironnement());
      this.setIdJeuDonneeBrouillon(jeuDonneeEntityBrouillon.getIdJeuDonnees());
      // TODO a modifier avec la date fin d'import brouillon
      this.setDateImportJeuDonneesBrouillon(jeuDonneeEntityBrouillon.getDateCreateJeuDonnees());
      this.setImportJeuDonneesBrouillonBy(nameImportJeuDonneesBrouillonBy);
      // TODO
      this.setPathValidateJeuDonneesBrouillon("TODO");
      
      this.setStatusJeudonneeBrouillon(jeuDonneeEntityBrouillon.getStatusJeuDonnees());
   }
   
   @JsonIgnore
   public void mergeByJeuDonneesActif (JeuDonneeEntity jeuDonneeEntityActif, String nameValidateJeuDonneeBy) {
      this.setIdJeuDonneesActif(jeuDonneeEntityActif.getIdJeuDonnees());
      this.setDateImportJeuDonneesActif(new Date());
      this.setImportJeuDonneesActifBy(nameValidateJeuDonneeBy);
      this.setDateValidateJeuDonneesActif(new Date());
      this.setValidateJeuDonneesActifBy(nameValidateJeuDonneeBy);
      this.setPathValidateJeuDonneesActif("TODO");  
      this.setStatusJeudonneeBrouillon(jeuDonneeEntityActif.getStatusJeuDonnees());
   }
   
   /**
    * TODO à supprimer
    * @param compagnieEnvironnementEntity
    */
   @JsonIgnore
   public void mergeByCompagnieEnvironnement (CompagnieEnvironnementEntity compagnieEnvironnementEntity) {
      this.setIdCompagnieEnvironnement(compagnieEnvironnementEntity.getIdCompagnieEnvironnement());
      this.setLibelleCompagnie(compagnieEnvironnementEntity.getLibelleCompagnie());
      this.setLibelleEnvironnement(compagnieEnvironnementEntity.getLibelleEnvironnement());
   }

   /**
    * @return the idCompagnieEnvironnement
    */
   public int getIdCompagnieEnvironnement() {
      return idCompagnieEnvironnement;
   }

   /**
    * @param idCompagnieEnvironnement the idCompagnieEnvironnement to set
    */
   public void setIdCompagnieEnvironnement(int idCompagnieEnvironnement) {
      this.idCompagnieEnvironnement = idCompagnieEnvironnement;
   }

   /**
    * @return the libelleCompagnie
    */
   public String getLibelleCompagnie() {
      return libelleCompagnie;
   }

   /**
    * @param libelleCompagnie the libelleCompagnie to set
    */
   public void setLibelleCompagnie(String libelleCompagnie) {
      this.libelleCompagnie = libelleCompagnie;
   }

   /**
    * @return the libelleEnvironnement
    */
   public String getLibelleEnvironnement() {
      return libelleEnvironnement;
   }

   /**
    * @param libelleEnvironnement the libelleEnvironnement to set
    */
   public void setLibelleEnvironnement(String libelleEnvironnement) {
      this.libelleEnvironnement = libelleEnvironnement;
   }

   /**
    * @return the idJeuDonneesActif
    */
   public int getIdJeuDonneesActif() {
      return idJeuDonneesActif;
   }

   /**
    * @param idJeuDonneesActif the idJeuDonneesActif to set
    */
   public void setIdJeuDonneesActif(int idJeuDonneesActif) {
      this.idJeuDonneesActif = idJeuDonneesActif;
   }

   /**
    * @return the dateImportJeuDonneesActif
    */
   public Date getDateImportJeuDonneesActif() {
      return dateImportJeuDonneesActif;
   }

   /**
    * @param dateImportJeuDonneesActif the dateImportJeuDonneesActif to set
    */
   public void setDateImportJeuDonneesActif(Date dateImportJeuDonneesActif) {
      this.dateImportJeuDonneesActif = dateImportJeuDonneesActif;
   }

   /**
    * @return the importJeuDonneesActifBy
    */
   public String getImportJeuDonneesActifBy() {
      return importJeuDonneesActifBy;
   }

   /**
    * @param importJeuDonneesActifBy the importJeuDonneesActifBy to set
    */
   public void setImportJeuDonneesActifBy(String importJeuDonneesActifBy) {
      this.importJeuDonneesActifBy = importJeuDonneesActifBy;
   }

   /**
    * @return the dateValidateJeuDonneesActif
    */
   public Date getDateValidateJeuDonneesActif() {
      return dateValidateJeuDonneesActif;
   }

   /**
    * @param dateValidateJeuDonneesActif the dateValidateJeuDonneesActif to set
    */
   public void setDateValidateJeuDonneesActif(Date dateValidateJeuDonneesActif) {
      this.dateValidateJeuDonneesActif = dateValidateJeuDonneesActif;
   }

   /**
    * @return the validateJeuDonneesActifBy
    */
   public String getValidateJeuDonneesActifBy() {
      return validateJeuDonneesActifBy;
   }

   /**
    * @param validateJeuDonneesActifBy the validateJeuDonneesActifBy to set
    */
   public void setValidateJeuDonneesActifBy(String validateJeuDonneesActifBy) {
      this.validateJeuDonneesActifBy = validateJeuDonneesActifBy;
   }

   /**
    * @return the pathValidateJeuDonneesActif
    */
   public String getPathValidateJeuDonneesActif() {
      return pathValidateJeuDonneesActif;
   }

   /**
    * @param pathValidateJeuDonneesActif the pathValidateJeuDonneesActif to set
    */
   public void setPathValidateJeuDonneesActif(String pathValidateJeuDonneesActif) {
      this.pathValidateJeuDonneesActif = pathValidateJeuDonneesActif;
   }

   /**
    * @return the idJeuDonneeBrouillon
    */
   public int getIdJeuDonneeBrouillon() {
      return idJeuDonneeBrouillon;
   }

   /**
    * @param idJeuDonneeBrouillon the idJeuDonneeBrouillon to set
    */
   public void setIdJeuDonneeBrouillon(int idJeuDonneeBrouillon) {
      this.idJeuDonneeBrouillon = idJeuDonneeBrouillon;
   }

   /**
    * @return the dateImportJeuDonneesBrouillon
    */
   public Date getDateImportJeuDonneesBrouillon() {
      return dateImportJeuDonneesBrouillon;
   }

   /**
    * @param dateImportJeuDonneesBrouillon the dateImportJeuDonneesBrouillon to set
    */
   public void setDateImportJeuDonneesBrouillon(Date dateImportJeuDonneesBrouillon) {
      this.dateImportJeuDonneesBrouillon = dateImportJeuDonneesBrouillon;
   }

   /**
    * @return the importJeuDonneesBrouillonBy
    */
   public String getImportJeuDonneesBrouillonBy() {
      return importJeuDonneesBrouillonBy;
   }

   /**
    * @param importJeuDonneesBrouillonBy the importJeuDonneesBrouillonBy to set
    */
   public void setImportJeuDonneesBrouillonBy(String importJeuDonneesBrouillonBy) {
      this.importJeuDonneesBrouillonBy = importJeuDonneesBrouillonBy;
   }

   /**
    * @return the pathValidateJeuDonneesBrouillon
    */
   public String getPathValidateJeuDonneesBrouillon() {
      return pathValidateJeuDonneesBrouillon;
   }

   /**
    * @param pathValidateJeuDonneesBrouillon the pathValidateJeuDonneesBrouillon to set
    */
   public void setPathValidateJeuDonneesBrouillon(String pathValidateJeuDonneesBrouillon) {
      this.pathValidateJeuDonneesBrouillon = pathValidateJeuDonneesBrouillon;
   }   

   /**
    * @return the statusJeudonneeActif
    */
   public Status getStatusJeudonneeActif() {
      return statusJeudonneeActif;
   }

   /**
    * @param statusJeudonneeActif the statusJeudonneeActif to set
    */
   public void setStatusJeudonneeActif(Status statusJeudonneeActif) {
      this.statusJeudonneeActif = statusJeudonneeActif;
   }

   /**
    * @return the statusJeudonneeBrouillon
    */
   public Status getStatusJeudonneeBrouillon() {
      return statusJeudonneeBrouillon;
   }

   /**
    * @param statusJeudonneeBrouillon the statusJeudonneeBrouillon to set
    */
   public void setStatusJeudonneeBrouillon(Status statusJeudonneeBrouillon) {
      this.statusJeudonneeBrouillon = statusJeudonneeBrouillon;
   }

   /**
    * @return the username
    */
   public String getUsername() {
      return username;
   }

   /**
    * @param username the username to set
    */
   public void setUsername(String username) {
      this.username = username;
   }

   /**
    * @return the password
    */
   public String getPassword() {
      return password;
   }

   /**
    * @param password the password to set
    */
   public void setPassword(String password) {
      this.password = password;
   }

}
