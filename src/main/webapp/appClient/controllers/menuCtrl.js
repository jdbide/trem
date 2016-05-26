'use strict';

/**
 * Contrôleur pour les données de menu de l'application
 */
socle_app.controller('menuCtrl', [ '$scope', 'menuService', 'pageAccueil', function($scope, menuService, pageAccueil) {
	$scope.menu = {
		rubriques: null
	};
	
	function constructor() {
		menuService.getDataByServer().then(function() {
			$scope.menu.rubriques = menuService.getMenu();
			
			/* Page d'accueil */
			menuService.setCheminCourant(pageAccueil.rubrique, pageAccueil.chapitre, pageAccueil.page);
		});
	}

	constructor();
	
	$scope.setCurrentPage = function(indexRubrique, indexChapitre, indexPage) {
		menuService.setCheminCourant(
				$scope.menu.rubriques[indexRubrique].libelleIhmRubrique, 
				$scope.menu.rubriques[indexRubrique].chapitres[indexChapitre].libelleIhmChapitre, 
				$scope.menu.rubriques[indexRubrique].chapitres[indexChapitre].pages[indexPage].libelleIhmPage);
	};
	
	$scope.chapitreCourant = menuService.getChapitreCourant; 
	$scope.pageCourante = menuService.getPageCourante; 
	
} ]);
