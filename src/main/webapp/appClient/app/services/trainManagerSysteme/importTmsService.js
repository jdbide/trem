'use strict';

/**
 * Service se chargeant des fonctionnalité de la page import du module trainManagerSysteme
 */
socle_app.service('importTmsService', ['jsonFactory', 'loadingService', '$q', function(jsonFactory, loadingService, $q) {
	var reponse= 
	{
		status : null,
		message : null,
		data : null
	};

	function initDataDraft(currentData) {
    	currentData.dateImportJeuDonneesBrouillon = null;
    	currentData.importJeuDonneesBrouillonBy = null;
    	currentData.statusJeudonneeBrouillon = null;
    	currentData.idJeuDonneeBrouillon = null;
    	currentData.pathValidateJeuDonneesBrouillon = null;
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
    
    self.executeImport = function (currentData) {
    	loadingService.show();    	
    	
        var deffered  = $q.defer();
        var promissJsonFactory = jsonFactory.postJson("webService/app/importTms", currentData);
        promissJsonFactory
            .success(function (data, status, headers, config) {
            	reponse.status = data.status;
            	reponse.message = data.message;
            	loadingService.hide(); 
                deffered.resolve();
            })
            .error(function (data, status, headers, config) {
            	reponse.message = data.message;
            	reponse.status = data.status;
            	loadingService.hide();
                deffered.reject();
        });
        
        return deffered.promise;
    }

    self.executeDeleteDraft = function (currentData) {
    	loadingService.show();    	
    	
        var deffered  = $q.defer();
        var promissJsonFactory = jsonFactory.deleteJson("webService/app/importTms/deleteDraft", currentData);
        promissJsonFactory
            .success(function (data, status, headers, config) {
            	reponse.status = data.status;
            	reponse.message = data.message;
            	initDataDraft(currentData);
            	
            	loadingService.hide();
                deffered.resolve();
            })
            .error(function (data, status, headers, config) {
            	reponse.message = data.message;
            	reponse.status = data.status;
            	loadingService.hide();
                deffered.reject();
        });
        
        return deffered.promise;
    }

    self.executeValidateDraft = function (currentData) {
    	loadingService.show();

        var deffered  = $q.defer();
        var promissJsonFactory = jsonFactory.putJson("webService/app/importTms", currentData);
        promissJsonFactory
            .success(function (data, status, headers, config) {
            	reponse.status = data.status;
            	reponse.message = data.message;
            	reponse.data = data.data;
            	loadingService.hide(); 
                deffered.resolve();
            })
            .error(function (data, status, headers, config) {
            	reponse.message = data.message;
            	reponse.status = data.status;
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