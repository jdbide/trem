'use strict';

socle_app.filter('identity', function() {
	return function(input) {
		return input;
	};
});