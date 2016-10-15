'use strict';

/**
 * Service se chargeant des fonctionnalité de l'objet de la recherche
 */
socle_app.service('searchService', ['jsonFactory', 'loadingService', '$q', 
                                    function(jsonFactory, loadingService, $q) {
	var search = {
		partitions:null,
		
		partitionSelected:null,

		trains:[],
		trainsSelected:[],
		
		tranches:[],
		tranchesSelected:[],
		
		dateDebut:null,
		dateFin:null,
		
		listOd:null,
		odSelected:null,
		
		listStops:null,
		listStopsSelected:null,
		
		listTosp:null,
		tospSelected:null,
		
		listRm:null,
		rmSelected:null,
		
		listEquipement:null,
		equipementSelected:null,
		
		status:null
	}
	
	self = this;
	
	/*
	 * Recuperation de l'objet search
	 */
	self.getSearch = function () {
		return search;
	}
	
	self.initSearch = function () {
		search.partitionSelected=null;
				
		search.trains=[];
		search.trainsSelected=null;
		
		search.tranches=[];
		search.tranchesSelected=null;
		
		search.dateDebut=null;
		search.dateFin=null;
		
		search.listOd=null;
		search.odSelected=null;
		
		search.listStops=null;
		search.listStopsSelected=null;
		
		search.listTosp=null;
		search.tospSelected=null;
		
		search.listRm=null;
		search.rmSelected=null;
		
		search.listEquipement=null;
		search.equipementSelected=null;
		
		search.status=null;
	}
	
	self.setPartition = function (partitions) {
		search.partitions = partitions;
	}
	
	self.setTrains = function (trains) {
		search.trains = trains;
	}
	
	self.setTranches = function (tranches) {
		search.tranches = tranches;
	}
	
	self.setOd = function (listOd) {
		search.listOd = listOd;
	}
	
	self.setStops = function (listStops) {
		search.listStops = listStops;
	}
	
	self.setTosp = function (listTosp) {
		search.listTosp = listTosp;
	}
	
	self.setRm = function (listRm) {
		search.listRm = listRm;
	}
	
	self.setEquipement = function (listEquipement) {
		search.listEquipement = listEquipement;
	}
	
	self.initPartitionSelected = function () {
		if (search.partitions != null) {
			search.partitionSelected=search.partitions[0];
		}
	}
	
	self.isEmpty = function () {
		return (search.partitions==null);
	}
	
	return self;
}]);