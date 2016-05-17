'use strict';

/**
 * Service se chargeant de renvoyer le menu de l'application
 */
socle_app.service('menuService', ['menuFactory', '$q', function(menuFactory, $q) {
	var menu = [];
	
    var self = this;
    
    self.getDataByServer = function () {        
        var deffered  = $q.defer();

        var promissMenuFactory = menuFactory.getMenu();
        promissMenuFactory
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

    return self;
}]);