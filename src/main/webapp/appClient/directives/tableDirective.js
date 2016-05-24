'use strict';

socle_app.directive("socleTable", function() {
	return {
		restrict : 'E',
		scope : {
			tableTitre : '=',
			tableParametres : '=',
			colonnes : '=',
			tableFormat: '&'
		},
		templateUrl : 'views/modals/table.xhtml'
	};
});