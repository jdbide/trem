'use strict';

socle_app.config(['$routeProvider', function($routeProvider) {
	$routeProvider
	.when('/', {
		templateUrl: 'views/partials/accueil.xhtml',
		controller: 'globalCtrl'
	})
	.when('/pages', {
		templateUrl: 'views/partials/socleTable.xhtml',
		controller: 'pagesCtrl'
	})
	.when('/import', {
		templateUrl: 'views/partials/importJeu.xhtml',
		controller: 'importJeuCtrl'
	})
	.when('/tables', {
		templateUrl: 'views/partials/socleTable.xhtml',
		controller: 'tablesCtrl'
	})
	.otherwise({
		redirectTo: '/'
	});
}]);