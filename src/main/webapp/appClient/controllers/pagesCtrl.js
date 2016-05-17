'use strict';

socle_app.controller("pagesCtrl", ["$scope", "NgTableParams", function($scope, NgTableParams) {
	$scope.title = "Les pages";

	$scope.cols = [ {
		field : "libelle",
		title : "Libellé",
		sortable : "libelle",
		show : true
	}, {
		field : "chapitre",
		title : "Chapitre",
		sortable : "chapitre",
		show : true
	}, {
		field : "mess",
		title : "Ex",
		sortable: "mess",
		show : true
	} ];

	$scope.params = new NgTableParams({
		// initial sort order
		sorting : {
			libelle : "asc"
		}
	}, {
		dataset : [ 
        {libelle: "Pages", chapitre: "IHM", mess: "salut"},
        {libelle: "Chapitres", chapitre: "IHM", mess: "coucou"},
        {libelle: "Rubriques", chapitre: "IHM", mess: "bonjour"} ]
	});
	
}]);