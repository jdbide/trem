//http://localhost:8080/tremas/webService/infoUser
'use strict';

/**
 * Service gérant les infos de l'utilisateur conntecté à l'application
 */
socle_app.service('paramService', ['jsonFactory', '$q', function(jsonFactory, $q) {
	var param = "";
	
	var self = this;
	
	self.getParam = function () {
		return param;
	}
	
	self.getDataByServer = function (type,param_name) {
		var deffered  = $q.defer();
		
		var promissJsonFactory = jsonFactory.getJson("webService/params/"+type+"/"+param_name);
        promissJsonFactory
            .success(function (data, status, headers, config) {
            	param = data['nom_projet'];
                deffered.resolve();
            })
            .error(function (data, status, headers, config) {
                deffered.reject();
        });
        
        return deffered.promise;
	}
	
	return self;
}]);