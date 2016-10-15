/**
 * 
 */
package com.avancial.app.data.dto;

import java.util.Date;

import com.avancial.app.data.databean.EStatusControl;

/**
 * @author hamza.laterem
 *
 */
public class JeuDonneesControlDto {
   private int    idJeuDonneesControl;
   private String titleJeuDonneesControl = "";
   private Date   dateCreateJeuDonneesControl = new Date();
   private Date   dateLastUpdateJeuDonneesControl = new Date();
   private int    idUtilisateurCreateJeuDonneesControl;
   private int    idUtilisateurLastUpdateJeuDonneesControl;
   private String pathFileImportJeuDonneesControlTimeTable = "";
   private String pathFileImportJeuDonneesControlYield = "";
   private String pathFileReportJeuDonneesControl = "";
   private EStatusControl statusJeuDonneesControl = EStatusControl.START;
   private String statusJeuDonnees = "";
   private int    idJeuDonnees;
   private int    idCompagnieEnvironnement;

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
      return idJeuDonneesControl;
   }

   /**
    * @param idJeuDonneesControl the idJeuDonneesControl to set
    */
   public void setIdJeuDonneesControl(int idJeuDonneesControl) {
      this.idJeuDonneesControl = idJeuDonneesControl;
   }

   /**
    * @return the titleJeuDonneesControl
    */
   public String getTitleJeuDonneesControl() {
      return titleJeuDonneesControl;
   }

   /**
    * @param titleJeuDonneesControl the titleJeuDonneesControl to set
    */
   public void setTitleJeuDonneesControl(String titleJeuDonneesControl) {
      this.titleJeuDonneesControl = titleJeuDonneesControl;
   }

   /**
    * @return the dateCreateJeuDonneesControl
    */
   public Date getDateCreateJeuDonneesControl() {
      return dateCreateJeuDonneesControl;
   }

   /**
    * @param dateCreateJeuDonneesControl the dateCreateJeuDonneesControl to set
    */
   public void setDateCreateJeuDonneesControl(Date dateCreateJeuDonneesControl) {
      this.dateCreateJeuDonneesControl = dateCreateJeuDonneesControl;
   }

   /**
    * @return the dateLastUpdateJeuDonneesControl
    */
   public Date getDateLastUpdateJeuDonneesControl() {
      return dateLastUpdateJeuDonneesControl;
   }

   /**
    * @param dateLastUpdateJeuDonneesControl the dateLastUpdateJeuDonneesControl to set
    */
   public void setDateLastUpdateJeuDonneesControl(Date dateLastUpdateJeuDonneesControl) {
      this.dateLastUpdateJeuDonneesControl = dateLastUpdateJeuDonneesControl;
   }

   /**
    * @return the idUtilisateurCreateJeuDonneesControl
    */
   public int getIdUtilisateurCreateJeuDonneesControl() {
      return idUtilisateurCreateJeuDonneesControl;
   }

   /**
    * @param idUtilisateurCreateJeuDonneesControl the idUtilisateurCreateJeuDonneesControl to set
    */
   public void setIdUtilisateurCreateJeuDonneesControl(int idUtilisateurCreateJeuDonneesControl) {
      this.idUtilisateurCreateJeuDonneesControl = idUtilisateurCreateJeuDonneesControl;
   }

   /**
    * @return the idUtilisateurLastUpdateJeuDonneesControl
    */
   public int getIdUtilisateurLastUpdateJeuDonneesControl() {
      return idUtilisateurLastUpdateJeuDonneesControl;
   }

   /**
    * @param idUtilisateurLastUpdateJeuDonneesControl the idUtilisateurLastUpdateJeuDonneesControl to set
    */
   public void setIdUtilisateurLastUpdateJeuDonneesControl(int idUtilisateurLastUpdateJeuDonneesControl) {
      this.idUtilisateurLastUpdateJeuDonneesControl = idUtilisateurLastUpdateJeuDonneesControl;
   }

   /**
    * @return the pathFileImportJeuDonneesControlTimeTable
    */
   public String getPathFileImportJeuDonneesControlTimeTable() {
      return pathFileImportJeuDonneesControlTimeTable;
   }

   /**
    * @param pathFileImportJeuDonneesControlTimeTable the pathFileImportJeuDonneesControlTimeTable to set
    */
   public void setPathFileImportJeuDonneesControlTimeTable(String pathFileImportJeuDonneesControlTimeTable) {
      this.pathFileImportJeuDonneesControlTimeTable = pathFileImportJeuDonneesControlTimeTable;
   }

   /**
    * @return the pathFileImportJeuDonneesControlYield
    */
   public String getPathFileImportJeuDonneesControlYield() {
      return pathFileImportJeuDonneesControlYield;
   }

   /**
    * @param pathFileImportJeuDonneesControlYield the pathFileImportJeuDonneesControlYield to set
    */
   public void setPathFileImportJeuDonneesControlYield(String pathFileImportJeuDonneesControlYield) {
      this.pathFileImportJeuDonneesControlYield = pathFileImportJeuDonneesControlYield;
   }

   /**
    * @return the pathFileReportJeuDonneesControl
    */
   public String getPathFileReportJeuDonneesControl() {
      return pathFileReportJeuDonneesControl;
   }

   /**
    * @param pathFileReportJeuDonneesControl the pathFileReportJeuDonneesControl to set
    */
   public void setPathFileReportJeuDonneesControl(String pathFileReportJeuDonneesControl) {
      this.pathFileReportJeuDonneesControl = pathFileReportJeuDonneesControl;
   }

   /**
    * @return the statusJeuDonneesControl
    */
   public EStatusControl getStatusJeuDonneesControl() {
      return statusJeuDonneesControl;
   }

   /**
    * @param statusJeuDonneesControl the statusJeuDonneesControl to set
    */
   public void setStatusJeuDonneesControl(EStatusControl statusJeuDonneesControl) {
      this.statusJeuDonneesControl = statusJeuDonneesControl;
   }

   /**
    * @return the statusJeuDonnees
    */
   public String getStatusJeuDonnees() {
      return statusJeuDonnees;
   }

   /**
    * @param statusJeuDonnees the statusJeuDonnees to set
    */
   public void setStatusJeuDonnees(String statusJeuDonnees) {
      this.statusJeuDonnees = statusJeuDonnees;
   }

   /**
    * @return the idJeuDonnees
    */
   public int getIdJeuDonnees() {
      return idJeuDonnees;
   }

   /**
    * @param idJeuDonnees the idJeuDonnees to set
    */
   public void setIdJeuDonnees(int idJeuDonnees) {
      this.idJeuDonnees = idJeuDonnees;
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

}
