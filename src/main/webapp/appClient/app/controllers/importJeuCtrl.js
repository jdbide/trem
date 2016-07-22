'use strict';

socle_app.controller("importJeuCtrl", ["$scope", "$filter", "importJeuDonneeService", "NgTableParams", 
                                       function($scope, $filter, importJeuDonneeService, NgTableParams) {
	
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
	
	function applyComparer(actual, expression) {
		if (expression instanceof Date) {
			return dateComparer(actual, expression);
		} else {
			var strValue = actual + '';
			return strValue.contains(expression);
		}
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
				for (var i = 0; i < columns.length; i++) {
					var col = columns[i];
					var type = col.fieldType;
					if (type === "Date") {
						col.format = {
							name : "date",
							params : ["yyyy-MM-dd HH:mm:ss"]
						}
					} else {
						col.format = {
							name : "identity"
						}
					}
					$scope.cols.push(col);
				}
				
				$scope.params = new NgTableParams({
					count: 20
				}, {
					filterOptions: {
						filterComparator: applyComparer
					},
					dataset : datas
				});
			}
		});
	}
	
	constructor();
	
	$scope.format = function(model, filter) {
		var paramsArray = ([model]).concat(filter.params);
		return $filter(filter.name).apply(this, paramsArray);
	};
	
}]);
