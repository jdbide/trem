'use strict';

/**
 * Service gérant le menu de pages de l'application
 */
socle_app.service('menuService', ['jsonFactory', '$q', function(jsonFactory, $q) {
	var menu = [];
	
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
    var libelleRubriqueCourante = "";
    self.setRubriqueCourante = function(libelle) {
    	libelleRubriqueCourante = libelle;
    }
    self.getRubriqueCourante = function() {
    	return libelleRubriqueCourante;
    } 

    /**
     * Chapitre de la page affichée
     */
    var libelleChapitreCourant = "";
    self.setChapitreCourant = function(libelle) {
    	libelleChapitreCourant = libelle;
    }
    self.getChapitreCourant = function() {
    	return libelleChapitreCourant;
    } 
    
    /**
     * Page affichée
     */
    var libellePageCourante = "";
    self.setPageCourante = function(libelle) {
    	libellePageCourante = libelle;
    }
    self.getPageCourante = function() {
    	return libellePageCourante;
    } 
    
    return self;
}]);