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
	})
	.otherwise({
		redirectTo: '/'
	});
}]);