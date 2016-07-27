'use strict';

/**
 * Service se chargeant des fonctionnalité de la page import du module trainManagerSysteme
 */
socle_app.service('importTmsService', ['jsonFactory', 'loadingService', '$q', function(jsonFactory, loadingService, $q) {	
	
    var self = this;
    
    self.getDataByServer = function () {
    	loadingService.show();
        var deffered  = $q.defer();

        var promissJsonFactory = jsonFactory.getJson("webService/app/importTms/");
        promissJsonFactory
            .success(function (datas, status, headers, config) {            	
            	loadingService.hide();
                deffered.resolve(datas);
            })
            .error(function (datas, status, headers, config) {
            	loadingService.hide();
                deffered.reject();
        });
        
        return deffered.promise;
    }
        
    return self;
}]);