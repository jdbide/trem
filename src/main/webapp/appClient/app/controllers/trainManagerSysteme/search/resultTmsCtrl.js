'use strict';

/**
 * Contrôleur qui gère la page search du chapitre "Train manager systeme / search", 
 *
 */
socle_app.controller("resultTmsCtrl", ['$rootScope', '$scope', 'loadingService', '$q', 'resultService', 'searchService', 'resultTmsService',
                                       'ClassSousRegime',function($rootScope, $scope, loadingService, $q, resultService, searchService, resultTmsService,ClassSousRegime) {
	$scope.datas = resultTmsService.getReponse().data;
	$scope.tab = resultService.getTbody();	
	
	function constructor() {
		console.log("-----------resultTmsCtrl + " + ClassSousRegime.Desserte);
		var data = searchService.searchToResult();
		if ($scope.datas == null) {
			resultTmsService.getResult(data).then(
				function() {
					console.warn("==> Récupération des resultat de la recherche <==");
					var reponse = resultTmsService.getReponse();
					if (reponse.status) {
						$scope.datas = reponse.data;		
						
						//On boucle sur les trains du plan de transport
						angular.forEach($scope.datas.trains, function(train, key) {	  
							//On boucle sur les tranches du train
							angular.forEach(train.tranches, function(tranche, key) {	  
								//On boucle sur la liste de jours du régime
								angular.forEach(tranche.regime.listejours, function(jour, key) {	
									//if(tranche.attributs.ClassSousRegime.Desserte[0]);
									alert(tranche.attributs.ClassSousRegime.Desserte[0].regime.codeRegime);
									
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