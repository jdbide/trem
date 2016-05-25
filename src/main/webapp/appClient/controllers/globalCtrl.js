'use strict';

/**
 * Contrôleur global de l'application, utilisé au niveau
 * de la directive ng-app
 */
socle_app.controller("globalCtrl", ["$scope", function($scope) {
	$scope.welcome = "Socle 2";
}])