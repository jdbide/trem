'use strict';

socle_app.controller("importJeuCtrl", ["$scope", function($scope) {
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
	};
	
	$scope.title = "Importation des données";
	
}]);
