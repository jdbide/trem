'use strict';

socle_app.controller("tablesCtrl", ["$scope", "$filter", "importService", "tablesMotriceService", "generateNgTableService", "NgTableParams", 
                                    function($scope, $filter, importService, tablesMotriceService, generateNgTableService, NgTableParams) {
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
		} else {
			initTableDirective();
		}
		
	};
	
    $scope.format = generateNgTableService.format;
	
}]);
