'use strict';

/**
 * Contrôleur global de l'application, utilisé au niveau de la directive ng-app
 */
socle_app.controller("globalCtrl", ["$scope", "$rootScope", 'envService', 'userService', 'userInfoService', 'menuService', 'paramService',
                                    function($scope, $rootScope, envService, userService, userInfoService, menuService, paramService) {
	function constructor () {
		
	}
	
	$rootScope.$on("$locationChangeStart", function(event, next, current) {
		var nextPage = next.substr(next.indexOf('#'));
		if (nextPage != sessionStorage.lienPage) {
			menuService.setCheminCourantFromLien(nextPage);
			menuService.savePageLien(nextPage);
		}
	});
	
	// Récupération paramètres
	paramService.getDataByServer('app','libelle_projet').then(function() {
		$scope.libelle_projet = paramService.getParam();
	}, function() {
		alert("Impossible de récupérer le libellé du projet");
	});		
	
	// Récupération paramètres
	paramService.getDataByServer('app','nom_projet').then(function() {
		$scope.nom_projet = paramService.getParam();
	}, function() {
		alert("Impossible de récupérer le nom du projet");
	});	
	
	userInfoService.getDataByServer().then(function() {
		$scope.user = userInfoService.getUser();
	}, function() {
		alert("Probleme au niveau de l'utilisateur");
 	});	

	constructor ();	
}])