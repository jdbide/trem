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
		
		listOd:null,
		odSelected:null,
		roundTrip:false,
		
		listStops:null,
		stopsSelected:[],
		
		listTosp:null,
		tospSelected:null,
		
		listRm:null,
		rmSelected:null,
		
		listEquipement:null,
		equipementSelected:null,
		
		status:[{id:"O", value:"Open"}, {id:"F", value:"Close"}],
		statusSelected:null,
		
		date:{startDate: moment(), endDate: moment()},
		
		isModif:false
	}
	
	function getIdsStops () {
		var res = new Array();
		
		angular.forEach(search.stopsSelected, function(stop, key) {
			console.log(stop.idMotriceRefGare);
			res.push(stop.idMotriceRefGare);
		});
		
		return res;
	}
	
	self = this;
	
	/*
	 * Recuperation de l'objet search
	 */
	self.getSearch = function () {
		return search;
	}
	
	self.setModif = function (isModif) {
		search.isModif = isModif;
	}
	
	self.isModif = function () {
		console.warn("=======> "+search.isModif);
		return search.isModif;
	}
	
	self.initSearch = function () {
//		console.log("search.partitions.lenght : " + search.partitions.lenght);
//		if (search.partitions.lenght > 0) {
//			search.partitionSelected=search.partitions[0];
//		} else {
//			search.partitionSelected = null;
//		}
				
		search.trains=[];
		search.trainsSelected=null;
		
		search.tranches=[];
		search.tranchesSelected=null;
		
		search.listOd=null;
		search.odSelected=null;
		search.roundTrip=false;
		
		search.listStops=null;
		search.stopsSelected=null;
		
		search.listTosp=null;
		search.tospSelected=null;
		
		search.listRm=null;
		search.rmSelected=null;
		
		search.listEquipement=null;
		search.equipementSelected=null;
		
		search.status=[{id:"O", value:"Open"}, {id:"F", value:"Close"}];
		search.statusSelected=null;
		
		search.date = {startDate: moment(), endDate: moment()};
	}
	
	self.initNewSearch = function () {
		search.partitions=null;
		
		search.partitionSelected=null;
				
		search.trains=[];
		search.trainsSelected=null;
		
		search.tranches=[];
		search.tranchesSelected=null;
		
		search.listOd=null;
		search.odSelected=null;
		search.roundTrip=false;
		
		search.listStops=null;
		search.stopsSelected=null;
		
		search.listTosp=null;
		search.tospSelected=null;
		
		search.listRm=null;
		search.rmSelected=null;
		
		search.listEquipement=null;
		search.equipementSelected=null;
		
		search.status=[{id:"O", value:"Open"}, {id:"F", value:"Close"}];
		search.statusSelected=null;
		
		search.date = {startDate: moment(), endDate: moment()};
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
	
	self.initStops = function () {
		search.listStops=null;
		search.stopsSelected=null;
	}
	
	self.changeOdData = function (ods) {
		search.listStops=ods;
	}
	
	self.searchToResult = function () {
		var res = {};
		
		res.idCompagnieEnvironnement = ((search.partitionSelected != null) ? search.partitionSelected.idCompagnieEnvironnement : null);
		res.numerosTrains = search.trainsSelected;		
		res.numerosTranches = search.tranchesSelected;	
		
		res.debutPeriode = search.date.startDate;
		res.finPeriode = search.date.endDate;
		
		res.idOrigineDestination = ((search.odSelected != null) ? search.odSelected.idMotriceRefOd : null);
		res.roundTrip = search.roundTrip;
		
		res.idTosp = ((search.tospSelected != null) ? search.tospSelected.idMotriceRefTosp : null);
		
		res.idCodeRM = ( (search.rmSelected != null) ? search.rmSelected.idMotriceRefRameCode : null);
		
		res.idCodeEquipement = ((search.equipementSelected!= null) ? search.equipementSelected.idMotriceRefEqpType : null);
		
		res.status = ((search.statusSelected != null) ? search.statusSelected.id : null);
		
		res.idsStops = getIdsStops();
		
		return res;
	}

	return self;
}]);