/**
 * 
 */
package com.avancial.app.data.dto;

import java.util.Date;

import com.avancial.app.data.databean.JeuDonneesControlEntity;
import com.avancial.app.data.databean.Status;
import com.avancial.app.data.databean.StatusControl;

/**
 * @author hamza.laterem
 *
 */
public class JeuDonneesControlDto {
   private int           idJeuDonneesControl;
   private int           idJeuDonnees;
   private Date          dateCreateJeuDonneesControl;
   private Date          dateLastUpdateJeuDonneesControl;
   private int           nameUtilisateurCreateJeuDonneesControl;
   private int           idUtilisateurCreateJeuDonneesControl;
   private String        pathFileImportJeuDonneesControl;
   private String        pathFileReportJeuDonneesControl;
   private StatusControl statusJeuDonneesControl;
   private Status        statusJeuDonnee;

   /**
    * 
    */
   public JeuDonneesControlDto() {
      // TODO Auto-generated constructor stub
   }

   /**
    * @return the idJeuDonneesControl
    */
   public int getIdJeuDonneesControl() {
      return this.idJeuDonneesControl;
   }

   /**
    * @param idJeuDonneesControl
    *           the idJeuDonneesControl to set
    */
   public void setIdJeuDonneesControl(int idJeuDonneesControl) {
      this.idJeuDonneesControl = idJeuDonneesControl;
   }

   /**
    * @return the dateCreateJeuDonneesControl
    */
   public Date getDateCreateJeuDonneesControl() {
      return this.dateCreateJeuDonneesControl;
   }

   /**
    * @param dateCreateJeuDonneesControl
    *           the dateCreateJeuDonneesControl to set
    */
   public void setDateCreateJeuDonneesControl(Date dateCreateJeuDonneesControl) {
      this.dateCreateJeuDonneesControl = dateCreateJeuDonneesControl;
   }

   /**
    * @return the dateLastUpdateJeuDonneesControl
    */
   public Date getDateLastUpdateJeuDonneesControl() {
      return this.dateLastUpdateJeuDonneesControl;
   }

   /**
    * @param dateLastUpdateJeuDonneesControl
    *           the dateLastUpdateJeuDonneesControl to set
    */
   public void setDateLastUpdateJeuDonneesControl(Date dateLastUpdateJeuDonneesControl) {
      this.dateLastUpdateJeuDonneesControl = dateLastUpdateJeuDonneesControl;
   }

   /**
    * @return the nameUtilisateurCreateJeuDonneesControl
    */
   public int getNameUtilisateurCreateJeuDonneesControl() {
      return this.nameUtilisateurCreateJeuDonneesControl;
   }

   /**
    * @param nameUtilisateurCreateJeuDonneesControl
    *           the nameUtilisateurCreateJeuDonneesControl to set
    */
   public void setNameUtilisateurCreateJeuDonneesControl(int nameUtilisateurCreateJeuDonneesControl) {
      this.nameUtilisateurCreateJeuDonneesControl = nameUtilisateurCreateJeuDonneesControl;
   }

   /**
    * @return the idUtilisateurCreateJeuDonneesControl
    */
   public int getIdUtilisateurCreateJeuDonneesControl() {
      return this.idUtilisateurCreateJeuDonneesControl;
   }

   /**
    * @param idUtilisateurCreateJeuDonneesControl
    *           the idUtilisateurCreateJeuDonneesControl to set
    */
   public void setIdUtilisateurCreateJeuDonneesControl(int idUtilisateurCreateJeuDonneesControl) {
      this.idUtilisateurCreateJeuDonneesControl = idUtilisateurCreateJeuDonneesControl;
   }

   /**
    * @return the pathFileImportJeuDonneesControl
    */
   public String getPathFileImportJeuDonneesControl() {
      return this.pathFileImportJeuDonneesControl;
   }

   /**
    * @param pathFileImportJeuDonneesControl
    *           the pathFileImportJeuDonneesControl to set
    */
   public void setPathFileImportJeuDonneesControl(String pathFileImportJeuDonneesControl) {
      this.pathFileImportJeuDonneesControl = pathFileImportJeuDonneesControl;
   }

   /**
    * @return the pathFileReportJeuDonneesControl
    */
   public String getPathFileReportJeuDonneesControl() {
      return this.pathFileReportJeuDonneesControl;
   }

   /**
    * @param pathFileReportJeuDonneesControl
    *           the pathFileReportJeuDonneesControl to set
    */
   public void setPathFileReportJeuDonneesControl(String pathFileReportJeuDonneesControl) {
      this.pathFileReportJeuDonneesControl = pathFileReportJeuDonneesControl;
   }

   /**
    * @return the statusJeuDonneesControl
    */
   public StatusControl getStatusJeuDonneesControl() {
      return this.statusJeuDonneesControl;
   }

   /**
    * @param statusJeuDonneesControl
    *           the statusJeuDonneesControl to set
    */
   public void setStatusJeuDonneesControl(StatusControl statusJeuDonneesControl) {
      this.statusJeuDonneesControl = statusJeuDonneesControl;
   }

   /**
    * @return the statusJeuDonnee
    */
   public Status getStatusJeuDonnee() {
      return this.statusJeuDonnee;
   }

   /**
    * @param statusJeuDonnee
    *           the statusJeuDonnee to set
    */
   public void setStatusJeuDonnee(Status statusJeuDonnee) {
      this.statusJeuDonnee = statusJeuDonnee;
   }

   /**
    * @return the idJeuDonnees
    */
   public int getIdJeuDonnees() {
      return idJeuDonnees;
   }

   /**
    * @param idJeuDonnees
    *           the idJeuDonnees to set
    */
   public void setIdJeuDonnees(int idJeuDonnees) {
      this.idJeuDonnees = idJeuDonnees;
   }

   public void entityToDto(JeuDonneesControlEntity jeuDonneesControl) {
      this.setIdJeuDonneesControl(jeuDonneesControl.getIdJeuDonneesControl());
      this.setIdJeuDonnees(jeuDonneesControl.getJeuDonnee().getIdJeuDonnees());
      this.setDateCreateJeuDonneesControl(jeuDonneesControl.getDateCreateJeuDonneesControl());
      this.setDateLastUpdateJeuDonneesControl(jeuDonneesControl.getDateLastUpdateJeuDonneesControl());
      this.setIdUtilisateurCreateJeuDonneesControl(jeuDonneesControl.getIdUtilisateurCreateJeuDonneesControl());
      this.setPathFileImportJeuDonneesControl(jeuDonneesControl.getPathFileImportJeuDonneesControl());
      this.setPathFileReportJeuDonneesControl(jeuDonneesControl.getPathFileReportJeuDonneesControl());
      this.setStatusJeuDonneesControl(jeuDonneesControl.getStatusJeuDonneesControl());
      this.setStatusJeuDonnee(jeuDonneesControl.getJeuDonnee().getStatusJeuDonnees());
   }

}
