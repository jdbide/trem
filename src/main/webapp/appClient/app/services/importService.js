'use strict';

/**
 * Service se chargeant de renvoyer l'import d'une table motrice
 */
socle_app.service('importService', ['jsonFactory', 'loadingService', '$q', function(jsonFactory, loadingService, $q) {
	var cols = [];
	var datas = [];
	
    var self = this;
    
    self.getDataByServer = function (entityName) {
    	loadingService.show();
        var deffered  = $q.defer();

        var promissJsonFactory = jsonFactory.getJson("webService/app/tablesImport/" + entityName);
        promissJsonFactory
            .success(function (data, status, headers, config) {
            	cols = data.cols;
            	datas = data.dataset;
            	loadingService.hide();
                deffered.resolve();
            })
            .error(function (data, status, headers, config) {
            	loadingService.hide();
                deffered.reject();
        });
        
        return deffered.promise;
    }
    
    self.getCols = function() {
    	return cols;
    }

    self.getDatas = function() {
    	return datas;
    }
    
    return self;
}]);