'use strict';

socle_app.controller("tablesCtrl", ["$scope", "$filter", "importService", "tablesMotriceService", "NgTableParams", 
                                    function($scope, $filter, importService, tablesMotriceService, NgTableParams) {
	$scope.columns = null;
	$scope.cols = [];
	$scope.params = null;
	$scope.tablesMotrice = [];
	
	function constructor() {
		/* Récupération des tables pour le select */
		tablesMotriceService.getDataByServer().then(function() {
			var tables = tablesMotriceService.getTablesMotrice();
			
			$scope.tablesMotrice = [];
			for (var i = 0; i < tables.length; i++) {
				$scope.tablesMotrice.push({
					label: tables[i].libelleTablesMotrice,
<<<<<<< HEAD
					value: tables[i].libelleTablesMotrice
=======
					value: tables[i].entityImportTablesMotrice
>>>>>>> 43be8616e14867fe84f447abeba580ba70564865
				});
			}
		});

	}

	constructor();
	
	$scope.title = "Table d'import";
	$scope.selectName = "selectTable";
	$scope.selectId = "idSelectTable";
	$scope.selectedOption = "";
	$scope.labelSelect = "Veuillez choisir une table : ";
	
	$scope.displayTable = function(nomTable) {
		if (nomTable.value != null) {
			importService.getDataByServer($scope.selectedOption.value).then(function() {
				var columns = importService.getCols();
				$scope.dataset = importService.getDatas();
		
				$scope.cols = [];
				for (var i = 0; i < columns.length; i++) {
					var col = columns[i];
					var prefix = col.substr(0, 4);
					col = col.replace(prefix, prefix.toLowerCase());
					$scope.cols.push({
						field: col,
						title: col,
						sortable: col,
						show: (col.indexOf("id") < 0)
					});
				}
				
				$scope.params = new NgTableParams({
					count: 20
				}, {
					dataset : $scope.dataset
				});
			});
		} else {
			$scope.params = new NgTableParams({}, {});
			$scope.cols = [];
			$scope.dataset = null;
		}
		
	}
	
}]);
