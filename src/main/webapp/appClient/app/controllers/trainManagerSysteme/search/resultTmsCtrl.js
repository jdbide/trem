'use strict';

/**
 * Contrôleur qui gère la page result du chapitre "Train manager systeme / search",
 * Authors : Hamza LATEREM 
 *
 */
socle_app.controller("resultTmsCtrl", ['$rootScope', '$scope', 'loadingService', '$q', 'searchService', 'resultTmsService',
                                       function($rootScope, $scope, loadingService, $q, searchService, resultTmsService) {
	// Formulaire de la recherche
	$scope.search = searchService.getSearch();
	// data : FiltrePlanTransportDto
	$scope.data = null;
	// currentPage pour la gestion des la pagination
	$scope.currrentPage = 0;
	// Nombre de ligne a afficher
	$scope.dataInPage = 101;
	// Nombre de page 
	$scope.nbrPage = 0;
	// Boolean 
	$scope.noData = false;
	// Modal pour trainInfo
	$scope.trainModal = false;
	// Modal pour trancheInfo
	$scope.trancheModal = false;
	// Modal pour stopsInfo
	$scope.stopsModal = false;
	// Modal pour sserviceInfo
	$scope.serviceModal = false;
	// Modal pour rmCodeInfo
	$scope.rmCodeModal = false;
	// Modal pour compositionInfo
	$scope.compoModal = false;
	// Row selectionné pour l'affichage des données au niveau du modal
	$scope.dataSelected = null;

	
	$scope.selectedRowTest = function () {
		console.log($scope.dataSelected);
		
		console.log($scope.trainModal);
	}
	
	$scope.showModal = function (data, nameModal) {
		$scope.dataSelected = data;

		if (nameModal == "trainModal")
			$scope.trainModal = !$scope.trainModal;
		else if (nameModal == "trancheModal")
			$scope.trancheModal = !$scope.trancheModal;
		else if (nameModal == "rmCodeModal")
			$scope.rmCodeModal = !$scope.rmCodeModal;
		else if (nameModal == "serviceModal")
			$scope.serviceModal = !$scope.serviceModal;
		else if (nameModal == "compoModal")
			$scope.compoModal = !$scope.compoModal;
		else if (nameModal == "stopsModal")
			$scope.stopsModal = !$scope.stopsModal;
	}

	/*
	 * Constructeur
	 * Récuperation des données (Liste de FiltrePlanTransportDto) depuis le serveur
	 * Cette page est appelé à partir de la page de "search"
	 */
	function constructor() {
		var data = searchService.searchToResult();
		if (resultTmsService.getDatas() == null) {
			resultTmsService.getResult(data).then(
				function() {
					var reponse = resultTmsService.getReponse();
					if (reponse.status) {
						resultTmsService.setDatas(reponse.data);
						if (resultTmsService.getDatas() != null && resultTmsService.getDatas().listTrainTrancheDateDto != null) {
							$scope.nbrPage = Math.ceil(resultTmsService.getDatas().listTrainTrancheDateDto.length/$scope.dataInPage);
							$scope.data = resultTmsService.getDatas().listTrainTrancheDateDto.slice($scope.currrentPage*$scope.dataInPage, ((($scope.currrentPage+1)*$scope.dataInPage)-1));
						} else {
							$scope.noData = true;
						}
					} else {
						alert("Erreur : " + reponse.message);
					}
				}, function() {
					alert("Erreur serveur!!");
				}
			);
		} else {
			$scope.nbrPage = Math.ceil(resultTmsService.getDatas().listTrainTrancheDateDto.length/$scope.dataInPage);
			$scope.data = resultTmsService.getDatas().listTrainTrancheDateDto.slice($scope.currrentPage*$scope.dataInPage, ((($scope.currrentPage+1)*$scope.dataInPage)-1));
		}
	}
	
	/*
	 * Pagination : recuperation des données de la page
	 * Avec l'index de la page
	 */
	$scope.getPage = function (index) {
		$scope.currrentPage = index;
		$scope.data = null;
		$scope.data = resultTmsService.getDatas().listTrainTrancheDateDto.slice($scope.currrentPage*$scope.dataInPage, ((($scope.currrentPage+1)*$scope.dataInPage)-1));		
	}

	/*
	 * Button nouvelle recherche
	 * Methode à executer aprés le ng-click
	 * On initialise le formulaire de la recherche
	 * et on initialise les donnéees a affciher
	 */
	$scope.newSearch = function () {
		searchService.initNewSearch();
		resultTmsService.initDatas();
		resultTmsService.initReponse();
		$rootScope.changePage();
	}

	/*
	 * Button modification de la recherche
	 * Methode à executer aprés le ng-click
	 * On garde le formulaire de la recherche et
	 * on initialise les données a afficher
	 */
	$scope.modifSearch = function () {
		resultTmsService.initReponse();
		resultTmsService.initDatas();
		$rootScope.isModif = true;
		$rootScope.changePage();
	}
	
	// call constructeur
	constructor();
}]);