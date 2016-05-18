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
	
	$scope.setCurrentPage = function(indexRubrique, indexChapitre, indexPage) {
		menuService.setRubriqueCourante($scope.menu.rubriques[indexRubrique].libelleIhmRubrique);
		menuService.setChapitreCourant($scope.menu.rubriques[indexRubrique].chapitres[indexChapitre].libelleIhmChapitre);
		menuService.setPageCourante($scope.menu.rubriques[indexRubrique].chapitres[indexChapitre].pages[indexPage].libelleIhmPage);
	};
	
} ]);
