'use strict';

socle_app.controller("importJeuCtrl", ["$scope", "importJeuDonneeService", function($scope, importJeuDonneeService) {
	
	$scope.title = "Importation des données";
	
	$scope.reponse = null;
	
	$scope.importJeu = function() {
		importJeuDonneeService.startTreatement().then(function() {
			$scope.reponse = importJeuDonneeService.getReponse();
		}, function() {
			$scope.reponse = importJeuDonneeService.getReponse();
		});
	};		
}]);
