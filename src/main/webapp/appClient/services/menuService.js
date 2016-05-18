'use strict';

/**
 * Service se chargeant de renvoyer le menu de l'application
 */
socle_app.service('menuService', ['jsonFactory', '$q', function(jsonFactory, $q) {
	var menu = [];
	
    var self = this;
    
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
    
    var libelleRubriqueCourante = "";
    self.setRubriqueCourante = function(libelle) {
    	libelleRubriqueCourante = libelle;
    }
    self.getRubriqueCourante = function() {
    	return libelleRubriqueCourante;
    } 

    var libelleChapitreCourant = "";
    self.setChapitreCourant = function(libelle) {
    	libelleChapitreCourant = libelle;
    }
    self.getChapitreCourant = function() {
    	return libelleChapitreCourant;
    } 
    
    var libellePageCourante = "";
    self.setPageCourante = function(libelle) {
    	libellePageCourante = libelle;
    }
    self.getPageCourante = function() {
    	return libellePageCourante;
    } 
    
    return self;
}]);