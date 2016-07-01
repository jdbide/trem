'use strict';

/**
 * Contrôleur global de l'application, utilisé au niveau
 * de la directive ng-app
 */
socle_app.controller("globalCtrl", ["$scope", 'envService', 'userService', function($scope, envService, userService) {
	function constructor () {
		$scope.welcome = "Socle 2";
		console.log(envService.get());
		// Récupération des infos de l'utilisateur
		userService.getDataByServer().then(function() {
			$scope.user = userService.getUser();
		}, function() {
			alert("Probleme au niveau de l'utilisateur");
		});	
	}
	
	constructor ();	
}])