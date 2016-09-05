'use strict';

/**
 * Contrôleur global de l'application, utilisé au niveau
 * de la directive ng-app
 */
socle_app.controller("globalCtrl", ["$scope", "$rootScope", 'envService', 'userInfoService', 'menuService', 'paramService',
                                    function($scope, $rootScope, envService, userInfoService, menuService, paramService) {
	function constructor () {
		
	}
	
	$rootScope.$on("$locationChangeStart", function(event, next, current) {
		var nextPage = next.substr(next.indexOf('#'));
		if (nextPage != sessionStorage.lienPage) {
			menuService.setCheminCourantFromLien(nextPage);
			menuService.savePageLien(nextPage);
		}
	});
		
	constructor ();	
}])