'use strict';

/**
 * Contrôleur pour l'affichage de la page courante dans le header
 * (breadcrumb ou fil d'Ariane)
 */
socle_app.controller('breadcrumbCtrl', [ '$scope', 'menuService', function($scope, menuService) {
	$scope.rubrique = menuService.getRubriqueCourante;
	$scope.chapitre = menuService.getChapitreCourant;
	$scope.page = menuService.getPageCourante;
} ]);
