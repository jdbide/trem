'use strict';

/**
 * Filtre identité, qui renvoie exactement l'objet en entrée
 */
socle_app.filter('identity', function() {
	return function(input) {
		return input;
	};
});