'use strict';

/**
 * Contrôleur qui gère la page "Control" du chapitre "Train manager systeme", 
 *
 */
socle_app.controller("controlTmsCtrl", ["$rootScope", "$scope", "envService", '$interval',"controlTmsService",
                                 function($rootScope, $scope, envService, $interval, controlTmsService) {
	$scope.title = "Control";
	$scope.partitions = null;
	$scope.selectedPartition = null;
	$scope.startControl = -1;
	$scope.createControl = false;
	
	/**
	 * Constructeur du controlleur, il récupère la liste List<ImportTmsDto>
	 */
	function constructor () {
		controlTmsService.getPartition().then(
			function(datas) {
				$scope.partitions = datas;
				$scope.selectedPartition = datas[0].idCompagnieEnvironnement;
				controlTmsService.getDataByIdPartition($scope.selectedPartition).then(
					function(datas) {
						$scope.datas = datas;
					}, function() {
						alert("Erreur serveur!!");
					}
				);
			}, function() {
				alert("Erreur serveur!!");
			}
		);
	}
	
	$scope.changeSelectedPartition = function () {
		controlTmsService.getDataByIdPartition($scope.selectedPartition).then(
			function(datas) {
				$scope.datas = datas;
			}, function() {
				alert("Erreur serveur!!");
			}
		);
	}
	
	/**
	 */
	$scope.startModalCreateControl = function () {
//		controlTmsService.getJeuDonneesByIdPartitionAndStatus($scope.selectedPartition, $scope.selectedStatusDataSet).then(
//			function(datas) {
//				$scope.dataSets = datas;
//			}, function() {
//				alert("Erreur serveur!!");
//			}
//		);
		$scope.createControl = !$scope.createControl;
	}

	constructor();
}]);