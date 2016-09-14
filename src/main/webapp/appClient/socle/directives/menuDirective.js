'use strict';

/**
 * Directive pour l'affichage du menu des pages sur plusieurs niveaux
 * (Rubrique > Chapitre > Page)
 */
socle_app.directive('socleMenu', function() {
	return {
		restrict: 'E',
		templateUrl: "socle/views/modals/menu.xhtml"
	};
});