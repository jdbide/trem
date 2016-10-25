package com.avancial.app.data.dto;

import java.util.ArrayList;
import java.util.List;

import com.avancial.app.data.objetsMetier.PlanTransport.OrigineDestination;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;

/*
* 
* @author jeandaniel.bide
*
                     */
public class ResponseControlData {

	   private String erreurFatale;
   private List<String> parsingErrorList;
   private List<String> validationErrorList;
   private List<String> extractionErrorList;
   private Regime dateInterval;
   private List<OrigineDestination> origineDestinations;


   public ResponseControlData() {
      parsingErrorList = new ArrayList<>();
      validationErrorList = new ArrayList<>();
      extractionErrorList = new ArrayList<>();
      erreurFatale = "";
   }


public String getErreurFatale() {
	return erreurFatale;
}


public void setErreurFatale(String erreurFatale) {
	this.erreurFatale = erreurFatale;
}


public List<String> getParsingErrorList() {
	return parsingErrorList;
}


public void setParsingErrorList(List<String> parsingErrorList) {
	this.parsingErrorList = parsingErrorList;
}


public List<String> getValidationErrorList() {
	return validationErrorList;
}


public void setValidationErrorList(List<String> validationErrorList) {
	this.validationErrorList = validationErrorList;
}


public List<String> getExtractionErrorList() {
	return extractionErrorList;
}


public void setExtractionErrorList(List<String> extractionErrorList) {
	this.extractionErrorList = extractionErrorList;
}


public Regime getDateInterval() {
	return dateInterval;
}


public void setDateInterval(Regime dateInterval) {
	this.dateInterval = dateInterval;
}


public List<OrigineDestination> getOrigineDestinations() {
	return origineDestinations;
}


public void setOrigineDestinations(List<OrigineDestination> origineDestinations) {
	this.origineDestinations = origineDestinations;
}
   
   



}
