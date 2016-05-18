'use strict';

socle_app.controller("tablesCtrl", ["$scope", "$filter", "importService", "NgTableParams", function($scope, $filter, importService, NgTableParams) {
	function constructor() {
		importService.getDataByServer().then(function() {
			$scope.columns = importService.getCols();
			$scope.dataset = importService.getDatas();

			$scope.cols = [];
			for (var i = 0; i < $scope.columns.length; i++) {
				var col = $scope.columns[i];
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
				page: 1,
				count: 20
			}, {
				paginationMaxBlocks: 2,
				paginationMinBlocks: 2,
				dataset : $scope.dataset
			});
		});
	}

	constructor();
	
	$scope.title = "Test affichage";
}]);
