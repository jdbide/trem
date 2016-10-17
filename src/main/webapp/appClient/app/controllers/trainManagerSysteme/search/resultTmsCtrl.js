'use strict';

/**
 * Contrôleur qui gère la page search du chapitre "Train manager systeme / search", 
 *
 */
socle_app.controller("resultTmsCtrl", ['$rootScope', '$scope', 'loadingService', '$q', 'searchService', 'resultTmsService',
                                       function($rootScope, $scope, loadingService, $q, searchService, resultTmsService) {
	$scope.datas = null;
	
	
	function constructor() {
		console.log("-----------resultTmsCtrl");
		var data = searchService.searchToResult();
		if ($scope.datas == null) {
			resultTmsService.getResult(data).then(
				function() {
					console.warn("==> Récupération des resultat de la recherche <==");
					var reponse = resultTmsService.getReponse();
					if (reponse.status) {
						$scope.datas = reponse.data;
						console.log($scope.datas);
					} else {
						alert("Erreur : " + reponse.message);
					}
					
				}, function() {
					alert("Erreur serveur!!");
				}
			);
		}
	}

	constructor();
	
	$scope.newSearch = function () {
		searchService.initNewSearch();
		$scope.datas = null;
		$rootScope.changePage();
	}
	
	$scope.modifSearch = function () {
		$scope.datas = null;
		$rootScope.isModif = true;
		$rootScope.changePage();
	}
}]);