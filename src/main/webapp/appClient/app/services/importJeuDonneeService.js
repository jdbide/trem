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
	
	var userForm = {
	        username : null,
	        password : null,
	    }
	
	/**
    *
    * Private Methodes
    */
   var initUserForm = function () {
		userForm.username = null;
		userForm.password = null;
   }
	
	var getDataSended = function () {
		return {
	        	   "idApplication":1,
	        	   "username":userForm.username,
	        	   "password":userForm.password
	        };
	}
	
	var self = this;
	
	/**
    *
    * Public Methodes
    */
   self.getUserform = function () {
       return userForm;
   }
	
	self.startTreatement = function () {
    	loadingService.show();
    	
        var deffered  = $q.defer();
        console.log(getDataSended());
        var promissJsonFactory = jsonFactory.postJson("webService/importJeu",  getDataSended());
        promissJsonFactory
            .success(function (data, status, headers, config) {
            	reponse.status = data.status;
            	reponse.message = data.message;
            	initUserForm();
            	loadingService.hide();            	
                deffered.resolve();
            })
            .error(function (data, status, headers, config) {
            	initUserForm();
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