'use strict';

/**
 * Socle_app : Gestion de la navigation 
 */
socle_app.config(['$routeProvider', function($routeProvider) {
	$routeProvider
	.when('/', {
		templateUrl: 'socle/views/partials/accueil.xhtml'
	})
	.when('/pages', {
		templateUrl: 'socle/views/partials/socleTable.xhtml',
		controller: 'pagesCtrl'
	})
	.otherwise({
		redirectTo: '/'
	});
}]);