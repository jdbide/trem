'use strict';

socle_app.controller("tablesCtrl", ["$scope", "$filter", "importService", "tablesMotriceService", "NgTableParams", 
                                    function($scope, $filter, importService, tablesMotriceService, NgTableParams) {
	$scope.cols = [];
	$scope.params = null;
	$scope.tableVide = false;
	$scope.tablesMotrice = [];
	
	function initTableDirective() {
		$scope.cols = [];
		$scope.params = null;
	}
	
	function constructor() {
		/* Récupération des tables pour le select */
		tablesMotriceService.getDataByServer().then(function() {
			var tables = tablesMotriceService.getTablesMotrice();
			
			$scope.tablesMotrice = [];
			for (var i = 0; i < tables.length; i++) {
				$scope.tablesMotrice.push({
					label: tables[i].libelleTablesMotrice,
					value: tables[i].libelleTablesMotrice
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
				var datas = importService.getDatas();
		
				initTableDirective();
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
								params : ["yyyy-MM-dd h:mm:ss"]
							}
						} else {
							col.format = {
								name : "identity",
								params : []
							}
						}
						$scope.cols.push(col);
					}
					
					$scope.params = new NgTableParams({
						count: 20
					}, {
						dataset : datas
					});
				}
			});
		} else {
			initTableDirective();
		}
		
	};
	
    $scope.format = function(model, filter) {
    	var paramsArray = ([model]).concat(filter.params);
    	return $filter(filter.name).apply(this, paramsArray);
    };
	
}]);
