package com.avancial.app.data.dto;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.RequestScoped;

import com.avancial.app.data.objetsMetier.PlanTransport.OrigineDestination;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;

/*
* 
* @author jeandaniel.bide
*
                     */
@RequestScoped
public class UploadFileResponseDto {

	private String erreurFatale;
	
	private List<OrigineDestination> OriginesDestinations;
	private Regime DateInterval;
	
	
	private List<String> parsingError;
	private List<String> validationError;
	private List<String> extractionError;

	public UploadFileResponseDto() {
		parsingError = new ArrayList<>();
		validationError = new ArrayList<>();
		extractionError = new ArrayList<>();
		erreurFatale = "";
	}


	public String getErreurFatale() {
		return erreurFatale;
	}

	public void setErreurFatale(String erreurFatale) {
		this.erreurFatale = erreurFatale;
	}

	public List<OrigineDestination> getOriginesDestinations() {
		return OriginesDestinations;
	}

	public void setOriginesDestinations(List<OrigineDestination> originesDestinations) {
		OriginesDestinations = originesDestinations;
	}

	public Regime getDateInterval() {
		return DateInterval;
	}

	public void setDateInterval(Regime dateInterval) {
		DateInterval = dateInterval;
	}

	public List<String> getParsingError() {
		return parsingError;
	}

	public void setParsingError(List<String> parsingError) {
		this.parsingError = parsingError;
	}

	public List<String> getValidationError() {
		return validationError;
	}

	public void setValidationError(List<String> validationError) {
		this.validationError = validationError;
	}

	public List<String> getExtractionError() {
		return extractionError;
	}

	public void setExtractionError(List<String> extractionError) {
		this.extractionError = extractionError;
	}
	
	

}
