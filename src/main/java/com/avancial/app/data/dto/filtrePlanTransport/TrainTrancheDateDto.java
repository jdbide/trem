/**
 * 
 */
package com.avancial.app.data.dto.filtrePlanTransport;

import java.util.Date;
import java.util.HashMap;

/**
 * Dto representant une ligne du resultat de search.
 * Clef: n° Train, n° Tranche, Date du jour
 * @author hamza.laterem
 *
 */
public class TrainTrancheDateDto {

   private Date dateJour;
   private String numeroTrain;
   private String origine;
   private String destination;
   private String mandatoryBooking;
   private String validForRR;
   private String numeroTranche;
   private String status;
   private String compagnie;
   private String indicateurDistribution;
   private HashMap<String,String> globalClass;
   private String codeSat;
   private String codeTosp;
   private Date loadedOn;
   //TODO private LastModifDto lastModif;
   private RMCodeDto rmCode;
   private ServicesDto service;
   private CompositionDto composition;
   private StopsDto stops;
   
   
   /**
    * constructeur vide
    * @author gabriel.gagnier
    */
   public TrainTrancheDateDto() {
   }


   /**
    * @return the dateJour
    */
   public Date getDateJour() {
      return dateJour;
   }


   /**
    * @param dateJour the dateJour to set
    */
   public void setDateJour(Date dateJour) {
      this.dateJour = dateJour;
   }


   /**
    * @return the numeroTrain
    */
   public String getNumeroTrain() {
      return numeroTrain;
   }


   /**
    * @param numeroTrain the numeroTrain to set
    */
   public void setNumeroTrain(String numeroTrain) {
      this.numeroTrain = numeroTrain;
   }


   /**
    * @return the origine
    */
   public String getOrigine() {
      return origine;
   }


   /**
    * @param origine the origine to set
    */
   public void setOrigine(String origine) {
      this.origine = origine;
   }


   /**
    * @return the destination
    */
   public String getDestination() {
      return destination;
   }


   /**
    * @param destination the destination to set
    */
   public void setDestination(String destination) {
      this.destination = destination;
   }


   /**
    * @return the mandatoryBooking
    */
   public String getMandatoryBooking() {
      return mandatoryBooking;
   }


   /**
    * @param mandatoryBooking the mandatoryBooking to set
    */
   public void setMandatoryBooking(String mandatoryBooking) {
      this.mandatoryBooking = mandatoryBooking;
   }


   /**
    * @return the validForRR
    */
   public String getValidForRR() {
      return validForRR;
   }


   /**
    * @param validForRR the validForRR to set
    */
   public void setValidForRR(String validForRR) {
      this.validForRR = validForRR;
   }


   /**
    * @return the numeroTranche
    */
   public String getNumeroTranche() {
      return numeroTranche;
   }


   /**
    * @param numeroTranche the numeroTranche to set
    */
   public void setNumeroTranche(String numeroTranche) {
      this.numeroTranche = numeroTranche;
   }


   /**
    * @return the status
    */
   public String getStatus() {
      return status;
   }


   /**
    * @param status the status to set
    */
   public void setStatus(String status) {
      this.status = status;
   }


   /**
    * @return the compagnie
    */
   public String getCompagnie() {
      return compagnie;
   }


   /**
    * @param compagnie the compagnie to set
    */
   public void setCompagnie(String compagnie) {
      this.compagnie = compagnie;
   }


   /**
    * @return the indicateurDistribution
    */
   public String getIndicateurDistribution() {
      return indicateurDistribution;
   }


   /**
    * @param indicateurDistribution the indicateurDistribution to set
    */
   public void setIndicateurDistribution(String indicateurDistribution) {
      this.indicateurDistribution = indicateurDistribution;
   }


   /**
    * @return the globalClass
    */
   public HashMap<String, String> getGlobalClass() {
      return globalClass;
   }


   /**
    * @param globalClass the globalClass to set
    */
   public void setGlobalClass(HashMap<String, String> globalClass) {
      this.globalClass = globalClass;
   }


   /**
    * @return the codeSat
    */
   public String getCodeSat() {
      return codeSat;
   }


   /**
    * @param codeSat the codeSat to set
    */
   public void setCodeSat(String codeSat) {
      this.codeSat = codeSat;
   }


   /**
    * @return the codeTosp
    */
   public String getCodeTosp() {
      return codeTosp;
   }


   /**
    * @param codeTosp the codeTosp to set
    */
   public void setCodeTosp(String codeTosp) {
      this.codeTosp = codeTosp;
   }


   /**
    * @return the loadedOn
    */
   public Date getLoadedOn() {
      return loadedOn;
   }


   /**
    * @param loadedOn the loadedOn to set
    */
   public void setLoadedOn(Date loadedOn) {
      this.loadedOn = loadedOn;
   }


   /**
    * @return the rmCode
    */
   public RMCodeDto getRmCode() {
      return rmCode;
   }


   /**
    * @param rmCode the rmCode to set
    */
   public void setRmCode(RMCodeDto rmCode) {
      this.rmCode = rmCode;
   }


   /**
    * @return the service
    */
   public ServicesDto getService() {
      return service;
   }


   /**
    * @param service the service to set
    */
   public void setService(ServicesDto service) {
      this.service = service;
   }


   /**
    * @return the composition
    */
   public CompositionDto getComposition() {
      return composition;
   }


   /**
    * @param composition the composition to set
    */
   public void setComposition(CompositionDto composition) {
      this.composition = composition;
   }


   /**
    * @return the stops
    */
   public StopsDto getStops() {
      return stops;
   }


   /**
    * @param stops the stops to set
    */
   public void setStops(StopsDto stops) {
      this.stops = stops;
   }

}
