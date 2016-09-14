'use strict';

/**
 * Socle_app : Gestion de la navigation 
 */
socle_app.config(['$routeProvider', function($routeProvider) {
	$routeProvider
	.when('/', {
		templateUrl: 'socle/views/partials/accueil.xhtml',
	})
	.when('/pages', {
		templateUrl: 'socle/views/partials/pages.xhtml',
		controller: 'pagesCtrl'
	})
	.when('/rubrique', {
		templateUrl: 'socle/views/partials/rubrique.xhtml',
		controller: 'rubriqueCtrl'
	})
	.when('/chapitre', {
		templateUrl: 'socle/views/partials/chapitre.xhtml',
		controller: 'chapitreCtrl'
	})
	.when('/user', {
		templateUrl: 'socle/views/partials/user.xhtml',
		controller: 'userCtrl'
	})
	.otherwise({
		redirectTo: '/'
	});
}]);