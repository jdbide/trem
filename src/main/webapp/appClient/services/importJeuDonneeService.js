'use strict';

/**
 * Service se chargeant de renvoyer l'import d'une table
 */
socle_app.service('importJeuDonneeService', ['jsonFactory', 'loadingService', '$q', function(jsonFactory, loadingService, $q) {
	var reponse= 
		{
		 status : null,
		 message : null
		};
	
	var self = this;
	
	self.startTreatement = function () {
    	loadingService.show();
        var deffered  = $q.defer();

        var promissJsonFactory = jsonFactory.postJson("http://localhost:8080/tremas/webService/importJeu", 1);
        promissJsonFactory
            .success(function (data, status, headers, config) {
            	reponse.status = data.status;
            	reponse.message = data.message;
            	loadingService.hide();
                deffered.resolve();
            })
            .error(function (data, status, headers, config) {
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