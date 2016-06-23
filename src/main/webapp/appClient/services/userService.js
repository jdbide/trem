//http://localhost:8080/tremas/webService/infoUser
'use strict';

/**
 * Service gérant les infos de l'utilisateur conntecté à l'application
 */
socle_app.service('userService', ['jsonFactory', '$q', function(jsonFactory, $q) {
	var user = [];
	
	var self = this;
	
	self.getUser = function () {
		return user;
	}
	
	self.getDataByServer = function () {
		var deffered  = $q.defer();
		
		var promissJsonFactory = jsonFactory.getJson("webService/infoUser");
        promissJsonFactory
            .success(function (data, status, headers, config) {
            	user = data['userInfo'];
                deffered.resolve();
            })
            .error(function (data, status, headers, config) {
                deffered.reject();
        });
        
        return deffered.promise;
	}
	
	return self;
}]);