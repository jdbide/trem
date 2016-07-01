'use strict';

/**
 * Contrôleur global de l'application, utilisé au niveau
 * de la directive ng-app
 */
socle_app.controller("globalCtrl", ["$scope", "$rootScope", 'envService', 'userService', 'menuService', 
                                    function($scope, $rootScope, envService, userService, menuService) {
	function constructor () {
		$scope.welcome = "Socle 2";
		console.log(envService.get());
		// Récupération des infos de l'utilisateur
		userService.getDataByServer().then(function() {
			$scope.user = userService.getUser();
		}, function() {
			alert("Probleme au niveau de l'utilisateur");
		});	
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