'use strict';

/**
 * Contrôleur qui gère la page search du chapitre "Train manager systeme / search", 
 *
 */
socle_app.controller("searchTmsCtrl", ['$rootScope', '$scope', 'loadingService', '$q', 'partitionTmsService', 'searchService', 'searchTmsService',
                                       function($rootScope, $scope, loadingService, $q, partitionTmsService, searchService, searchTmsService) {
	$scope.search = null;
	
	/*
	 * Récuperation des trainTranch / partition
	 */
	function getOtherDataRef() {
		console.warn("==> Récupération getOtherDataRef <==");
		if ($scope.search.partitionSelected == null) {
			return;
		}
		
		//var deffered  = $q.defer();
		loadingService.show();
		// Recuperation des données de refs
		searchTmsService.getDataFormByIdPartition($scope.search.partitionSelected.idCompagnieEnvironnement).then(
			function() {
				var reponse = searchTmsService.getReponse();
				console.warn("==> Récupération getDataFormByIdPartition <==");
				if (reponse.status) {
					searchService.setTrains(reponse.data.train);
					searchService.setTranches(reponse.data.tranches);
					searchTmsService.getDataFormByIdCompagnie($scope.search.partitionSelected.idCompagnie).then(
						function() {
							console.warn("==> Récupération getDataFormByIdCompagnie <==");
							reponse = searchTmsService.getReponse();
							if (reponse.status) {
								searchService.setOd(reponse.data.routes);
								searchService.setStops(reponse.data.routes);
								searchService.setTosp(reponse.data.routes);
								searchService.setRm(reponse.data.rm);
								searchService.setEquipement(reponse.data.equipement);
								//deffered.resolve();
								console.warn("loadingService.hide();");
								loadingService.hide();
							} else {
								alert("Erreur " + reponse.message);
								loadingService.hide();
								//deffered.reject();
							}
						}, function() {
							alert("Erreur serveur!!");
							loadingService.hide();
							//deffered.reject();
						}
					);
				} else {
					alert("Erreur " + reponse.message);
					loadingService.hide();
					//deffered.reject();
				}
				
			}, function() {
				alert("Erreur serveur!!");
				loadingService.hide();
				//deffered.reject();
			}
		);
	}
	/*
	 * Constructeur de la page de search, il récupère la liste des partition
	 */
	function constructor () {
		if (!searchService.isEmpty()) {
			console.warn("searchService.isEmpty is not empty");
			console.log(searchService.getSearch());
			$scope.search = searchService.getSearch();
		} else {
			// Recuperation de la liste des partitions
			partitionTmsService.allPartitionWithJeuDonneesActive().then(
				function(datas) {
					console.warn("==> Récupération des partitions <==");
					searchService.setPartition(datas)
					searchService.initPartitionSelected();
					$scope.search = searchService.getSearch();
					getOtherDataRef();
				}, function() {
					alert("Erreur serveur!!");
				}
			);
		}
			
	}
	
	constructor ();
}]);