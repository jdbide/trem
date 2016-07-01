'use strict';

/**
 * Contrôleur pour les données de menu de l'application
 */
socle_app.controller('menuCtrl', [ '$scope', 'menuService', 
                                   function($scope, menuService) {
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
		menuService.setCheminCourant(
				$scope.menu.rubriques[indexRubrique].libelleIhmRubrique, 
				$scope.menu.rubriques[indexRubrique].chapitres[indexChapitre].libelleIhmChapitre, 
				$scope.menu.rubriques[indexRubrique].chapitres[indexChapitre].pages[indexPage].libelleIhmPage
		);
		menuService.savePageLien($scope.menu.rubriques[indexRubrique].chapitres[indexChapitre].pages[indexPage].lienIhmPage);
	};
	
	$scope.chapitreCourant = menuService.getChapitreCourant; 
	$scope.pageCourante = menuService.getPageCourante;
	
} ]);
