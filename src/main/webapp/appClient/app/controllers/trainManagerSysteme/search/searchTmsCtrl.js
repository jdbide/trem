'use strict';

/**
 * Contrôleur qui gère la page search du chapitre "Train manager systeme / search", 
 *	Authors : Hamza LATEREM
 */
socle_app.controller("searchTmsCtrl", ['$rootScope', '$scope', 'loadingService', '$q', 'partitionTmsService', 'searchService', 'searchTmsService',
                                       function($rootScope, $scope, loadingService, $q, partitionTmsService, searchService, searchTmsService) {
	$scope.search = searchService.getSearch();
	$scope.disabledAll = true;
	//alert("-----------resultTmsCtrl + ");
	function getAllData () {
		var deffered  = $q.defer();
		// Recuperation des données de refs
		searchTmsService.getDataFormByIdPartition($scope.search.partitionSelected.idCompagnieEnvironnement).then(
			function() {
				var reponse = searchTmsService.getReponse();
				if (reponse.status) {
					searchService.setTrains(reponse.data.trains);
					searchService.setTranches(reponse.data.tranches);
					if (reponse.data.trains == null ||  reponse.data.tranches == null) {
						$scope.disabledAll = true;
						deffered.reject("Pas d'import active pour cette partition");
					} else {
						console.warn("$scope.disabledAll = false");
						$scope.disabledAll = false;
					}
					searchTmsService.getDataFormByIdCompagnie("equipementByCompagnie", $scope.search.partitionSelected.idCompagnie).then(
						function() {
							reponse = searchTmsService.getReponse();
							if (reponse.status) {
								searchService.setEquipement(reponse.data);
								searchTmsService.getDataFormByIdCompagnie("odByCompagnie",$scope.search.partitionSelected.idCompagnie).then(
									function() {
										reponse = searchTmsService.getReponse();
										if (reponse.status) {
											searchService.setOd(reponse.data);
											searchTmsService.getDataFormByIdCompagnie("rmByCompagnie",$scope.search.partitionSelected.idCompagnie).then(
												function() {
													reponse = searchTmsService.getReponse();
													if (reponse.status) {
														searchService.setRm(reponse.data);
														searchTmsService.getDataFormByIdCompagnie("tospsByCompagnie",$scope.search.partitionSelected.idCompagnie).then(
															function() {
																reponse = searchTmsService.getReponse();
																if (reponse.status) {
																	searchService.setTosp(reponse.data);
																	searchTmsService.getDataFormByIdCompagnie("stopsByCompagnie",$scope.search.partitionSelected.idCompagnie).then(
																		function() {
																			reponse = searchTmsService.getReponse();
																			if (reponse.status) {
																				searchService.setStops(reponse.data);
																				deffered.resolve();
																			} else {
																				deffered.reject("Erreur : " + reponse.message);
																			}
																		}, function() {
																			deffered.reject("Erreur serveur");
																		}
																	);
																} else {
																	deffered.reject("Erreur : " + reponse.message);
																}
															}, function() {
																deffered.reject("Erreur serveur");
															}
														);
													} else {
														deffered.reject("Erreur : " + reponse.message);
													}
												}, function() {
													deffered.reject("Erreur serveur");
												}
											);
										} else {
											deffered.reject("Erreur : " + reponse.message);
										}
									}, function() {
										deffered.reject("Erreur serveur");
									}
								);
							} else {
								deffered.reject("Erreur : " + reponse.message);
							}
						}, function() {
							deffered.reject("Erreur serveur");
						}
					);
				} else {
					deffered.reject("Erreur : " + reponse.message);
				}
				
			}, function() {
				deffered.reject("Erreur serveur");
			}
		);
		
		return deffered.promise;
	}
	
	/*
	 * Récuperation des trainTranch / partition
	 */
	function getOtherDataRef() {
		console.warn("==> Récupération getOtherDataRef <==");
		if ($scope.search.partitionSelected == null) {
			return;
		}
		
		var deffered  = $q.defer();
		loadingService.show();
		searchService.initSearch();
		getAllData().then(
			function() {
				loadingService.hide();
				$scope.search = searchService.getSearch();
				deffered.resolve();
			}, function(msg) {
				alert(msg);
				loadingService.hide();
				deffered.reject();
			}
		);
		
		return deffered.promise;
	}
	
	function getStopsByOd() {
		loadingService.show();
		searchService.initStops();
		console.warn("== getStopsByOd ==");
		searchTmsService.getStopsByOd($scope.search.odSelected.idMotriceRefOd).then(
			function() {
				var reponse = searchTmsService.getReponse();
				if (reponse.status) {
					searchService.changeOdData(reponse.data);
				} else {
					alert("Erreur : " + reponse.message);
				}

				loadingService.hide();
			}, function(msg) {
				alert("Erreur serveur");
				loadingService.hide();
			}
		);
	}
	
	/*
	 * Constructeur de la page de search, il récupère la liste des partition
	 */
	function constructor () {
		if ($rootScope.isModif) {
			console.warn("----------> isModif")
			$scope.disabledAll = false;
			$rootScope.isModif = false;
			$scope.search = searchService.getSearch();
			console.log($scope.search);
		} else if (!searchService.isEmpty()) {
			console.warn("searchService.isEmpty is not empty");
			searchService.initPartitionSelected();
			$scope.search = searchService.getSearch();
			if ($scope.search.partitionSelected.dateLastUpdateJeuDonneesActive != null) {
				$scope.disabledAll = false;
			} else {
				$scope.disabledAll = true;
			}
			
		} else {
			// Recuperation de la liste des partitions
			partitionTmsService.allPartitionWithJeuDonneesActive().then(
				function(datas) {
					console.warn("==> Récupération des partitions <==");
					searchService.setPartition(datas);
					searchService.initPartitionSelected();
					$scope.search = searchService.getSearch();
					getOtherDataRef();
				}, function() {
					alert("Erreur serveur!!");
				}
			);
		}
	}
	
	$scope.executeSearch = function () {
		$rootScope.changePage();
//		console.warn("Affichge de l'objet searchService.getSearch()");
//		loadingService.show();
//		console.log($scope.search);
//		console.log(searchService.searchToResult());
//		searchTmsService.executeSearch(searchService.searchToResult()).then(
//			function() {
//				var reponse = searchTmsService.getReponse();
//				if (reponse.status) {
//					searchService.setStops(reponse.data);
//				} else {
//					alert("Erreur : " + reponse.message);
//				}
//				
//				loadingService.hide();
//			}, function() {
//				alert("Erreur serveur");
//				loadingService.hide();
//			}
//		);
	}
	
	$scope.resetDate = function () {
		$scope.search.date={startDate: moment(), endDate: moment()};
	}
	
	$scope.changeOd = function () {
		console.warn("====changeOd===");
		if (!searchService.isEmpty()) {
			getStopsByOd();
		}
	}
	
	$scope.clearOd = function () {
		console.warn("===clearOd====");
		$scope.search.odSelected = null;
		searchService.initStops();
		searchTmsService.getDataFormByIdCompagnie("stopsByCompagnie",$scope.search.partitionSelected.idCompagnie).then(
			function() {
				var reponse = searchTmsService.getReponse();
				if (reponse.status) {
					searchService.setStops(reponse.data);
				} else {
					alert("Erreur : " + reponse.message);
				}
			}, function() {
				alert("Erreur serveur");
			}
		);
	}
	
	$scope.changePartition = function () {
		if (searchService.isEmpty()) {
			return;
		} else {
			getOtherDataRef();
		}
	}
	
	constructor ();
}]);