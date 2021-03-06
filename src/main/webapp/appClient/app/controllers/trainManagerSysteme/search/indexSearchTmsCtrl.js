'use strict';

/**
 * Contrôleur qui gère la gestion du passage de la page search à la page result
 * Authors : Hamza LATEREM 
 *
 */
socle_app.controller("indexSearchTmsCtrl", ['$rootScope', '$scope', '$q', 'readerFileJson', 'loadingService', 'traitementPageSearchService',
                                            function($rootScope, $scope, $q, readerFileJson, loadingService, traitementPageSearchService) {
	var URL_FILE_CONFIG_LOCAL = 'app/controllers/trainManagerSysteme/config/pageSearch.json';

	$scope.currentPage = traitementPageSearchService.getCurrentPage();
	
	$rootScope.isModif = false;

	/*
	 * init des pages;
	 * Il recupere du fichier config (@see URL_FILE_CONFIG_LOCAL) les données des deux pages
	 * et il inialise currentPage
	 */
	function initPage() {
        var deffered  = $q.defer();
        
        readerFileJson.get(URL_FILE_CONFIG_LOCAL).then(function(datas) {
        	traitementPageSearchService.setPages(datas);
			// Iniatialisation de l'objet currentPage
        	traitementPageSearchService.init();
        	$scope.currentPage = traitementPageSearchService.getCurrentPage();
        	deffered.resolve();
		}, function () {
			alert("Erreur de chargement de données");
			deffered.reject();
		});
        
        return deffered.promise;
	}

	/*
	 * Constructor
	 */
	function constructor () {
		loadingService.show();
		if (traitementPageSearchService.isInit()) {
			$scope.currentPage = traitementPageSearchService.getCurrentPage();
			loadingService.hide();
		} else {
			var promissTraitementPage = initPage();
			
			promissTraitementPage.then(
				function(datas) {
					$scope.currentPage = traitementPageSearchService.getCurrentPage();
					loadingService.hide();
				}, function() {
					loadingService.hide();
					alert("Erreur chargement de page!!");
				}
			);
		}
	}
	
	constructor ();
	
	$rootScope.changePage = function () {
		console.warn("------------------------> changePage");
		traitementPageSearchService.changePage();
		$scope.currentPage = traitementPageSearchService.getCurrentPage();
	}
}]);