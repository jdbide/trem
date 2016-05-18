'use strict';

socle_app.controller("importJeuCtrl", ["$scope", "importService", function($scope, importService) {
	$scope.jeu = {
		libelle: "",
		nomTechnique: "",
		commentaire: ""
	};
	
	$scope.importJeu = function() {
		console.log($scope.jeu);
		$scope.jeu = {
				libelle: "",
				nomTechnique: "",
				commentaire: ""
		};
		//importService
	};
	
	$scope.title = "Importation des données";
	
}]);
