'use strict';

/**
 * Contrôleur qui gère la page result du chapitre "Train manager systeme / search",
 * Authors : Hamza LATEREM 
 *
 */
socle_app.controller("resultTmsCtrl", ['$rootScope', '$scope', 'loadingService', '$q', 'searchService', 'resultTmsService',
                                       function($rootScope, $scope, loadingService, $q, searchService, resultTmsService) {
	$scope.search = searchService.getSearch();
	$scope.data = null;
	$scope.currrentPage = 0;
	$scope.dataInPage = 101;
	$scope.nbrPage = 0;
	$scope.noData = false;
	function constructor() {
		var data = searchService.searchToResult();
		if (resultTmsService.getDatas() == null) {
			resultTmsService.getResult(data).then(
				function() {
					var reponse = resultTmsService.getReponse();
					if (reponse.status) {
						if (reponse.data == null) {
							$scope.noData = true;
						} else {
							resultTmsService.setDatas(reponse.data);
							$scope.nbrPage = Math.ceil(resultTmsService.getDatas().listTrainTrancheDateDto.length/$scope.dataInPage);
							$scope.data = resultTmsService.getDatas().listTrainTrancheDateDto.slice($scope.currrentPage*$scope.dataInPage, ((($scope.currrentPage+1)*$scope.dataInPage)-1));
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
	
	$scope.getPage = function (index) {
		$scope.currrentPage = index;
		$scope.data = null;
		$scope.data = resultTmsService.getDatas().listTrainTrancheDateDto.slice($scope.currrentPage*$scope.dataInPage, ((($scope.currrentPage+1)*$scope.dataInPage)-1));		
	}

	constructor();
	
	$scope.newSearch = function () {
		searchService.initNewSearch();
		resultTmsService.initDatas();
		resultTmsService.initReponse();
		$rootScope.changePage();
	}
	
	$scope.modifSearch = function () {
		resultTmsService.initReponse();
		resultTmsService.initDatas();
		$rootScope.isModif = true;
		$rootScope.changePage();
	}
}]);