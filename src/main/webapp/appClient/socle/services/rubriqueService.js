'use strict';

/**
 *
 */
socle_app.service('rubriqueService', ['jsonFactory', 'loadingService', '$q', 
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
		
		var promissJsonFactory = jsonFactory.getJson("webService/rubriques");
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
	
	self.addRubrique = function (data) {
		console.log("===>");
		console.log(data);
		loadingService.show();
		var deffered = $q.defer();
		
		var promissJsonFactory = jsonFactory.postJson("webService/rubriques", data);
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
	
	
	self.deleteRubrique = function (idRubrique) {
		loadingService.show();
		var deffered = $q.defer();
		
		var promissJsonFactory = jsonFactory.deleteJson("webService/rubriques/"+idRubrique, null);
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
	
	self.editRubrique = function (data) {
		loadingService.show();
		var deffered = $q.defer();
		
		var promissJsonFactory = jsonFactory.putJson("webService/rubriques/"+data.id,data);
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