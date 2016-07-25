'use strict';

socle_app.controller("importJeuCtrl", ["$scope", "$filter", "importJeuDonneeService", "NgTableParams", "generateNgTableService",
                                       function($scope, $filter, importJeuDonneeService, NgTableParams, generateNgTableService) {
	
	$scope.title = "Importation des données";
	$scope.cols = [];
	$scope.params = null;
	$scope.tableVide = false;
	
	$scope.reponse = null;
	$scope.showModalLogin = false;
	
	$scope.userform = importJeuDonneeService.getUserform();
	
	$scope.modalLogin = function() {
		$scope.showModalLogin = !$scope.showModalLogin;
	};
	
	$scope.importJeu = function() {
		$scope.showModalLogin = false;
		importJeuDonneeService.startTreatement().then(function() {
			$scope.reponse = importJeuDonneeService.getReponse();
		}, function() {
			$scope.reponse = importJeuDonneeService.getReponse();
		});		
    }

	function constructor() {
		/* Récupération des tables pour le select */
		importJeuDonneeService.getDataJeuDonnees().then(function() {
			var columns = importJeuDonneeService.getCols();
			var datas = importJeuDonneeService.getDatas();
	
			if (datas.length === 0) {
				$scope.tableVide = true;
			} else {
				$scope.tableVide = false;
				$scope.cols = generateNgTableService.getTableColumns(columns);
				$scope.params = new NgTableParams({
					count: 20
				}, {
					filterOptions: {
						filterComparator: generateNgTableService.applyComparer
					},
					dataset : datas
				});
			}
		});
	}
	
	constructor();
	
	$scope.format = generateNgTableService.format;
	
}]);
