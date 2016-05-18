'use strict';

/**
 * Service se chargeant de renvoyer l'import d'une table
 */
socle_app.service('importService', ['jsonFactory', 'loadingService', '$q', function(jsonFactory, loadingService, $q) {
	var cols = [];
	var datas = [];
	
    var self = this;
    
    self.getDataByServer = function () {
    	loadingService.show();
        var deffered  = $q.defer();

        var promissJsonFactory = jsonFactory.getJson("http://localhost:8080/tremas/webService/import");
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