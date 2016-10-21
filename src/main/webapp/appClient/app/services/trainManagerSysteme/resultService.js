'use strict';

/**
 * Service se chargeant des fonctionnalité de la page control du module
 * trainManagerSysteme
 */
socle_app.service('resultService', function() {
	
//	var tr = {
//		currentTrain:null, 
//		currentTranche:null,
//		currentRunDate : null,
//		currentRM : null,
//		currentEquip : null,
//		currentCapacity : null,
//		currentStatut : null,
//		currentStops : null,
//		currentSat : null,
//		currentTosp : null,
//		currentLoadedOn : null,
//		currentLastModif : null,
//	};

	var tbody = [];
	
	var self = this;
	
//	self.newLine = function () {
//		return tr;
//	}
//	
	self.addLine = function (newTr) {
		tbody.push(newTr);
	}
//	
//	self.setCurrentTrain = function (currentTrain) {
//		tr.currentTrain = currentTrain;
//	}
//	
//	self.setCurrentTranche = function (currentTranche) {
//		tr.currentTranche = currentTranche;
//	}
//	
//	self.setCurrentRunDate = function (currentRunDate) {
//		tr.currentRunDate = currentRunDate;
//	}
//	
//	self.setCurrentRM = function (currentRM) {
//		tr.currentRM = currentRM;
//	}
//	self.setCurrentEquip = function (currentEquip) {
//		tr.currentEquip = currentEquip;
//	}
//	
//	self.setCurrentCapacity = function (currentCapacity) {
//		tr.currentCapacity = currentCapacity;
//	}
//
//	self.setCurrentStatut = function (currentStatut) {
//		tr.currentStatut = currentStatut;
//	}
//	
//	self.setCurrentStops = function (currentStops) {
//		tr.currentStops = currentStops;
//	}
//	
//	self.setCurrentSat = function (currentSat) {
//		tr.currentSat = currentSat;
//	}
//	
//	self.setCurrentTosp = function (currentTosp) {
//		tr.currentTosp = currentTosp;
//	}
//	
//	self.setCurrentLoadedOn = function (currentLoadedOn) {
//		tr.currentLoadedOn = currentLoadedOn;
//	}
//	
//	self.setCurrentLastModif = function (currentLastModif) {
//		tr.currentLastModif = currentLastModif;
//	}
	
	self.getTbody = function () {
		return tbody;
	}
	
//	self.getTr = function () {
//		return tr;
//	} 

	return self;

});