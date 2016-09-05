'use strict';

/**
 *
 */
socle_app.service('pageService', ['jsonFactory', 'loadingService', '$q', 
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
		
		var promissJsonFactory = jsonFactory.getJson("webService/pages");
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
	
	self.addPage = function (libelle,ordre) {
		loadingService.show();
		var deffered = $q.defer();
		
		var test = {
				idIhmRubrique:null,
				libelleIhmRubrique:"Test",
				actifIhmRubrique:true,
				ordreIhmRubrique:null
		};
		
		var promissJsonFactory = jsonFactory.postJson("webService/rubriques", test);
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