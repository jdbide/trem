'use strict';

/**
 * App : Gestion de la navigation
 */
socle_app.config(['$routeProvider', function($routeProvider) {
	$routeProvider
	.when('/tables', {
		templateUrl: 'app/views/partials/tablesMotrice.xhtml',
		controller: 'tablesCtrl'
	})
	.when('/trainMS', {
		templateUrl: 'app/views/partials/trainManagerSysteme/accueil.xhtml',
		controller: 'trainTmsCtrl'
	})
	.when('/compareDrafts', {
		templateUrl: 'app/views/partials/trainManagerSysteme/compareDrafts.xhtml',
		controller: 'compareDraftsCtrl'
	});
}]);