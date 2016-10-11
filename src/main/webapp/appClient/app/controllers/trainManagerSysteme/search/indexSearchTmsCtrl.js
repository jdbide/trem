'use strict';

/**
 * Contrôleur qui gère la page search du chapitre "Train manager systeme", 
 *
 */
socle_app.controller("indexSearchTmsCtrl", ['$rootScope', '$scope', '$q', 'readerFileJson', 'loadingService', 'traitementPageSearchService',
                                            function($rootScope, $scope, $q, readerFileJson, loadingService, traitementPageSearchService) {
	console.log("==> indexSearchTmsCtrl <==");
	var URL_FILE_CONFIG_LOCAL = 'app/controllers/trainManagerSysteme/config/pageSearch.json';

	$scope.currentPage = null;

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
			loadingService.hide();
			deffered.reject();
		});
        
        return deffered.promise;
	}

	/*
	 * Constructor
	 */
	function constructor () {
		if (traitementPageSearchService.isInit()) {
			console.warn("traitementPageSearchService.isInit() is init");
			$scope.currentPage = traitementPageSearchService.getCurrentPage();
		} else {
			loadingService.show();
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
}]);