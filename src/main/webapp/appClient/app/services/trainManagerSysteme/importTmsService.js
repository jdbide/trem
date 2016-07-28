'use strict';

/**
 * Service se chargeant des fonctionnalité de la page import du module trainManagerSysteme
 */
socle_app.service('importTmsService', ['jsonFactory', 'loadingService', '$q', function(jsonFactory, loadingService, $q) {
	var reponse= 
	{
		status : null,
		message : null
	};
	
	var getDataSended = function (currentData,username,password) {
		return {
				   "importTmsDto" : currentData,
	        	   "username":username,
	        	   "password":password
	        };
	}
	
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
    
    self.executeImport = function (currentData, username, password) {
    	loadingService.show();
    	console.log(getDataSended(currentData, username, password));
        var deffered  = $q.defer();
        var promissJsonFactory = jsonFactory.postJson("webService/app/importTms/execute",   /*getDataSended(currentData, username, password)*/);
        promissJsonFactory
            .success(function (data, status, headers, config) {
            	reponse.status = data.status;
            	reponse.message = data.message;
            	
            	loadingService.hide(); 
                deffered.resolve();
            })
            .error(function (data, status, headers, config) {
            	reponse.message = data.message;
            	
            	loadingService.hide();
                deffered.reject();
        });
        
        return deffered.promise;
    }
    
    self.getReponse = function () {
    	return reponse;
    }
        
    return self;
}]);