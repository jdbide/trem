'use strict';

/**
 * Contrôleur qui gère la page import du chapitre "Train manager systeme", 
 *
 */
socle_app.controller("importTmsCtrl", ["$rootScope", "$scope", "importTmsService",
                                 function($rootScope, $scope, importTmsService) {
	$scope.title = "Import";
	$scope.datas = null;
	$scope.authentificationDb = false;
	$scope.currentData = null;
	$scope.authError = null;
	$scope.reponse = null;
	
	function constructor () {
		importTmsService.getDataByServer().then(
			function(datas) {
				$scope.datas = datas;				
			}, function() {
				alert("Erreur serveur!!");
			}
		);		
	}
		
	
	$scope.authentificateExternDb = function (data) {
		console.log(data);
		$scope.currentData = data;
		$scope.authentificationDb = !$scope.authentificationDb;
	}
	
	$scope.executeImport = function (username, password) {
		if (username == null || password == null || username.size == 0 || password.size == 0) {			
			username = null;
			password = null;
			
			$scope.authError = "veuillez inserer vos identifiant !";
		} else {
			$scope.authentificationDb = !$scope.authentificationDb;

			importTmsService.executeImport($scope.currentData, username, password).then(function(reponse) {
				$scope.reponse = reponse;
				username = null;
				password = null;
			}, function(reponse) {
				$scope.reponse = reponse;
				username = null;
				password = null;
			});
		}
	}
		
	constructor ();	
}])