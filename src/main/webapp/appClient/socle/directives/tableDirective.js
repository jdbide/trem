'use strict';

/**
 * Directive pour l'affichage d'une table générique
 */
socle_app.directive("socleTable", function() {
	return {
		restrict : 'E',
		scope : {
			/**
			 * Titre affiché au-dessus de la table
			 */
			tableTitre : '=',
			/**
			 * Objet renvoyé par le constructeur NgTableParams
			 * du module externe ng-table
			 */
			tableParametres : '=',
			/**
			 * Objet décrivant les colonnes de la table
			 * sous ce format: {
			 * 	field: nom du champ de donnée,
			 * 	title: titre affiché de la colonne,
			 * 	sortable: (optionnel) nom du champ sur lequel le tri est effectué,
			 * 	filter: (optionnel) {nom_champ_filtré: nature_filtre(exemples: "text", "number")},
			 * 	show: true ssi la colonne est affichée,
			 * 	format: (optionnel) {name: nom du filtre à utiliser pour afficher la donnée, params: tableau de paramètres du filtre}
			 * }
			 */
			colonnes : '=',
			/**
			 * Lien vers la fonction éventuelle qui va appliquer le filtre indiqué dans colonnes.format.
			 * La fonction sera:
			 * 	function(model, filter) {
			 * 		var paramsArray = ([model]).concat(filter.params);
			 * 		return $filter(filter.name).apply(this, paramsArray);
			 * 	}
			 */
			tableFormat: '&',
			/**
			 * Booléen indiquant si la table est vide
			 */
			tableVide: '='
		},
		templateUrl : 'views/modals/table.xhtml'
	};
});