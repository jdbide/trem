'use strict';

socle_app.controller("importJeuCtrl", ["$scope", "importJeuDonneeService", function($scope, importJeuDonneeService) {
	
	$scope.title = "Importation des données";
	
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
	
}]);
