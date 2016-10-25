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
	
	self.add = function (row) {
		loadingService.show();
		var deffered = $q.defer();
		console.log("Adding "+row);
		
		var page={
			id:null,
			libelle:row.libelle,
			actif:row.actif,
			toutRole:row.toutRole,
			publique:row.publique,
			ordre:row.ordre,
			idChapitre:row.idChapitre,
			lien:row.lien
	};
		
		
		var promissJsonFactory = jsonFactory.postJson("webService/pages", page);
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
	
	
	self.delete = function (row) {
		loadingService.show();
		var deffered = $q.defer();
		
		var promissJsonFactory = jsonFactory.deleteJson("webService/pages/"+row.id, null);
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
	
	self.update = function (data) {
		loadingService.show();
		var deffered = $q.defer();
		
		var promissJsonFactory = jsonFactory.putJson("webService/pages/"+data.id,data);
		promissJsonFactory
			.success(function (data) {
				loadingService.hide();
				reponse = data;
				deffered.resolve();
			})
			.error(function (data, status, headers, config) {
				loadingService.hide();
				reponse = data;
				deffered.reject();
			});
		
		return promissJsonFactory;
	}
	
	self.getReponse = function () {
		return reponse;
	}
	
	
	
	
	return self;
	
}]);