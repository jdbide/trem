'use strict';

socle_app.config(['$routeProvider', function($routeProvider) {
	$routeProvider
	.when('/', {
		templateUrl: 'views/partials/accueil.xhtml',
		controller: 'globalCtrl'
	})
	.when('/pages', {
		templateUrl: 'views/partials/pages.xhtml',
		controller: 'pagesCtrl'
	})
	.when('/test', {
		templateUrl: 'views/partials/test.xhtml',
		controller: 'testCtrl'
	})
	.otherwise({
		redirectTo: '/'
	});
}]);