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

        var promissJsonFactory = jsonFactory.getJson("webService/socle/menu");
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
    	return sessionStorage.rubrique;
    } 

    /**
     * Chapitre de la page affichée
     */
    self.getChapitreCourant = function() {
    	return sessionStorage.chapitre;
    } 
    
    /**
     * Page affichée
     */
    self.getPageCourante = function() {
    	return sessionStorage.page;
    }
    
    self.setCheminCourant = function(libelleRubrique, libelleChapitre, libellePage) {
    	sessionStorage.rubrique = libelleRubrique;
    	sessionStorage.chapitre = libelleChapitre;
    	sessionStorage.page = libellePage;
    }
    
    self.setCheminCourantFromLien = function(lienPage) {
    	var rubrique, chapitre, page;
    	var pageTrouvee = false;
    	for (var i = 0; i < menu.length; i++) {
    		rubrique = menu[i].libelleIhmRubrique;
    		for (var j = 0; j < menu[i].chapitres.length; j++) {
    			chapitre = menu[i].chapitres[j].libelleIhmChapitre;
    			for (var k = 0; k < menu[i].chapitres[j].pages.length; k++) {
    				page = menu[i].chapitres[j].pages[k].libelleIhmPage;
    				if (menu[i].chapitres[j].pages[k].lienIhmPage === lienPage) {
    					pageTrouvee = true;
    					break;
    				}
    			}
    			if (pageTrouvee) {
    				break;
    			}
    		}
    		if (pageTrouvee) {
    			break;
    		}
    	}
    	if (rubrique && chapitre && page) {
    		this.setCheminCourant(rubrique, chapitre, page);
    	}
    }
    
    self.savePageLien = function(lienPage) {
    	sessionStorage.lienPage = lienPage;
    }
    
    return self;
}]);