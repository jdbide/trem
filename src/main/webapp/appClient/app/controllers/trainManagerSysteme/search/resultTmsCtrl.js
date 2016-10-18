'use strict';

/**
 * Contrôleur qui gère la page search du chapitre "Train manager systeme / search", 
 *
 */
socle_app.controller("resultTmsCtrl", ['$rootScope', '$scope', 'loadingService', '$q', 'resultService', 'searchService', 'resultTmsService',
                                       function($rootScope, $scope, loadingService, $q, resultService, searchService, resultTmsService) {
	$scope.datas = resultTmsService.getReponse().data;
	$scope.tab = resultService.getTbody();	
	
	
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
						
						//On boucle sur les trains du plan de transport
						angular.forEach(datas.trains, function(train, key) {	  
							currentTrain = train.numeroTrain;
							//On boucle sur les tranches du train
							angular.forEach(train.tranches, function(tranche, key) {	  
							currentTranche = tranche.numeroTranche;
								//On boucle sur la liste de jours du régime
								angular.forEach(tranche.regime.listejours, function(jour, key) {	
									if(tranche.attributs.)
									
								});
								
							});
							
						});
						
						
						
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
		resultTmsService.initReponse();
		$rootScope.changePage();
	}
	
	$scope.modifSearch = function () {
		$scope.datas = null;
		resultTmsService.initReponse();
		$rootScope.isModif = true;
		$rootScope.changePage();
	}
}]);