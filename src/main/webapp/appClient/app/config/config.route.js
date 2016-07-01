'use strict';

/**
 * App : Gestion de la navigation
 */
socle_app.config(['$routeProvider', function($routeProvider) {
	$routeProvider	
	.when('/import', {
		templateUrl: 'app/views/partials/importJeu.xhtml',
		controller: 'importJeuCtrl'
	})
	.when('/tables', {
		templateUrl: 'app/views/partials/tablesMotrice.xhtml',
		controller: 'tablesCtrl'
	});
}]);