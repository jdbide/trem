'use strict';

socle_app.directive("socleTable", function() {
	return {
		restrict : 'E',
		scope : {
			tableTitre : '=',
			tableParametres : '=',
			colonnes : '='
		},
		templateUrl : 'views/modals/table.xhtml'
	};
});