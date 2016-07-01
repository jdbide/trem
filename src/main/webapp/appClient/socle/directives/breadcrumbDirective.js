'use strict';

/**
 * Directive d'affichage du fil d'Ariane (valeurs des rubrique-chapitre-page courants
 * dans le menuService)
 */
socle_app.directive('socleBreadcrumb', function() {
	return {
		restrict: 'E',
		templateUrl: "socle/views/modals/breadcrumb.xhtml"
	};
});