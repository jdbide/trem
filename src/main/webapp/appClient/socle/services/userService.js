'use strict';

/**
 *
 */
socle_app.service('userService', ['jsonFactory', 'loadingService', '$q', 
                                      function(jsonFactory, loadingService, $q) {
	var reponse= {
		status : null,
		message : null,
		data : null
	};
	
	self = this;
	
	self.getDataByServer = function () {
		loadingService.show();
		var deffered = $q.defer();
		
		var promissJsonFactory = jsonFactory.getJson("webService/users");
		promissJsonFactory
			.success(function (data) {
				loadingService.hide();
				reponse.data = data.data;
				deffered.resolve();
			})
			.error(function (data, status, headers, config) {
				loadingService.hide();
				deffered.reject();
			});
		return promissJsonFactory;
	}
	
	self.addUser = function (data) {
		console.log("===>");
		console.log(data);
		loadingService.show();
		var deffered = $q.defer();
		
		var promissJsonFactory = jsonFactory.postJson("webService/users", data);
		promissJsonFactory
			.success(function (data) {
				loadingService.hide();
				reponse = data;
				deffered.resolve();
			})
			.error(function (data, status, headers, config) {
				loadingService.hide();
				deffered.reject();
			});
		return promissJsonFactory;
	}
	
	
	self.deleteUser = function (idUser) {
		loadingService.show();
		var deffered = $q.defer();
		
		var promissJsonFactory = jsonFactory.deleteJson("webService/users/"+idUser, null);
		promissJsonFactory
			.success(function (data) {
				loadingService.hide();
				reponse = data;
				deffered.resolve();
			})
			.error(function (data, status, headers, config) {
				loadingService.hide();
				deffered.reject();
			});
		return promissJsonFactory;
	}
	
	self.editUser = function (data) {
		loadingService.show();
		var deffered = $q.defer();
		
		var promissJsonFactory = jsonFactory.putJson("webService/users/"+data.id,data);
		promissJsonFactory
			.success(function (data) {
				loadingService.hide();
				reponse = data;
				deffered.resolve();
			})
			.error(function (data, status, headers, config) {
				loadingService.hide();
				deffered.reject();
			});
		return promissJsonFactory;
	}
	
	self.getReponse = function () {
		return reponse;
	}
	

	return self;
	
}]);