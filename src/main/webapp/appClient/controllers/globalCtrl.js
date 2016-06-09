'use strict';

/**
 * Contrôleur global de l'application, utilisé au niveau
 * de la directive ng-app
 */
socle_app.controller("globalCtrl", ["$scope", 'envService', function($scope, envService) {
	$scope.welcome = "Socle 2";
	console.log(envService.get());
}])