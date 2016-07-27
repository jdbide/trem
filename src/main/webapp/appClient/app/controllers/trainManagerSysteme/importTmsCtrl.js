'use strict';

/**
 * Contrôleur qui gère la page import du chapitre "Train manager systeme", 
 *
 */
socle_app.controller("importTmsCtrl", ["$scope", "importTmsService",
                                 function($scope, importTmsService) {
	$scope.title = "Import";
	$scope.datas = null;
	
	function constructor () {
		importTmsService.getDataByServer().then(
			function(datas) {
				$scope.datas = datas;
				console.log($scope.datas);
			}, function() {
				alert("Erreur serveur!!");
			}
		);
		
		
	}
		
	constructor ();	
}])