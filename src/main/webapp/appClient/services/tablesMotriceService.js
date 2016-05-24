'use strict';

/**
 * Service se chargeant de renvoyer l'import d'une table
 */
socle_app.service('TremasTablesMotriceWebService', ['jsonFactory', 'loadingService', '$q', function(jsonFactory, loadingService, $q) {
	var tablesMotrice = [];
	
    var self = this;
    
    self.getDataByServer = function () {
    	loadingService.show();
        var deffered  = $q.defer();

        var promissJsonFactory = jsonFactory.getJson("http://localhost:8080/tremas/webService/app/tablesMotrice");
        promissJsonFactory
            .success(function (data, status, headers, config) {
            	tablesMotrice = data;
            	loadingService.hide();
                deffered.resolve();
            })
            .error(function (data, status, headers, config) {
            	loadingService.hide();
                deffered.reject();
        });
        
        return deffered.promise;
    }
    
    self.getTablesMotrice = function() {
    	return tablesMotrice;
    }

    return self;
}]);