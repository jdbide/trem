'use strict';

/**
 * Service gérant le menu de pages de l'application
 */
socle_app.service('menuService', ['jsonFactory', '$q', '$sessionStorage', 'pageAccueil', function(jsonFactory, $q, $sessionStorage, pageAccueil) {
	var menu = [];
	if (!$sessionStorage.rubrique) {
		$sessionStorage.rubrique = pageAccueil.rubrique;
	}
	if (!$sessionStorage.chapitre) {
		$sessionStorage.chapitre = pageAccueil.chapitre;
	}
	if (!$sessionStorage.page) {
		$sessionStorage.page = pageAccueil.page;
	}
	
    var self = this;
    
    /**
     * Retourne tous les menus de l'application:
     * hiérarchie Rubriques > Chapitres > Pages
     */
    self.getDataByServer = function () {        
        var deffered  = $q.defer();

        var promissJsonFactory = jsonFactory.getJson("http://localhost:8080/tremas/webService/menu");
        promissJsonFactory
            .success(function (data, status, headers, config) {
            	menu = data;
                deffered.resolve();
            })
            .error(function (data, status, headers, config) {
                deffered.reject();
        });
        
        return deffered.promise;
    }
    
    self.getMenu = function() {
    	return menu;
    }
    
    /**
     * Rubrique de la page affichée
     */
    self.getRubriqueCourante = function() {
    	return $sessionStorage.rubrique;
    } 

    /**
     * Chapitre de la page affichée
     */
    self.getChapitreCourant = function() {
    	return $sessionStorage.chapitre;
    } 
    
    /**
     * Page affichée
     */
    self.getPageCourante = function() {
    	return $sessionStorage.page;
    }
    
    self.setCheminCourant = function(libelleRubrique, libelleChapitre, libellePage) {
    	$sessionStorage.rubrique = libelleRubrique;
    	$sessionStorage.chapitre = libelleChapitre;
    	$sessionStorage.page = libellePage;
    }
    
    return self;
}]);