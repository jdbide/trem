'use strict';

/**
 * Contrôleur pour les données de menu de l'application
 */
socle_app.controller('menuCtrl', [ '$scope', 'menuService', function($scope, menuService) {
	$scope.menu = {
			rubriques: null
	};
	
	function constructor() {
		menuService.getDataByServer().then(function() {
			$scope.menu.rubriques = menuService.getMenu();
		});
	}

	constructor();
	
} ]);
