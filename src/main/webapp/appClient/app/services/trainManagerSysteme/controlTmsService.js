'use strict';

/**
 * Service se chargeant des fonctionnalité de la page control du module trainManagerSysteme
 */
socle_app.service('controlTmsService', ['jsonFactory', 'loadingService', '$q', function(jsonFactory, loadingService, $q) {
	var reponse= 
	{
		status : null,
		message : null,
		data : null
	};
	
	var progressImport= {
		endTraitement : null,
		traitementOk : null,
		lastMsg : null,
		msgErr : null,
	}
	
	function initProgressImport() {
		progressImport.endTraitement = null;
		progressImport.traitementOk = null;
		progressImport.lastMsg = null;
		progressImport.msgErr = null;
	}
	
	function initResponse () {
		reponse.status=null;
    	reponse.message=null;
    	reponse.data=null;
	}
	
	self = this;
	
	self.initReponse = function () {
    	reponse.status=null;
    	reponse.message=null;
    	reponse.data=null;
    }
	
	self.getDataByIdPartition = function (idPartition) {
    	loadingService.show();
        var deffered  = $q.defer();
        var promissJsonFactory = jsonFactory.getJson("webService/app/controlTms/getDatasByIdPartition/"+idPartition);
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
	
	self.createControl = function (idPartition) {
		loadingService.show();
		initResponse();
		var deffered  = $q.defer();
        var promissJsonFactory = jsonFactory.postJson("webService/app/controlTms/createControl/", idPartition);
        promissJsonFactory
            .success(function (datas, status, headers, config) {
            	loadingService.hide();
            	reponse = datas;
            	deffered.resolve();
            })
            .error(function (datas, status, headers, config) {
            	loadingService.hide();
                deffered.reject();
        });
        
        return deffered.promise;
	}
	
	self.getImportParIdPartition = function (idPartition) {
		loadingService.show();
		initResponse();
		var deffered  = $q.defer();
        var promissJsonFactory = jsonFactory.getJson("webService/app/controlTms/getImportParPartition/"+idPartition);
        promissJsonFactory
            .success(function (datas, status, headers, config) {
            	reponse = datas;
            	loadingService.hide();
            	deffered.resolve();
            })
            .error(function (datas, status, headers, config) {
            	loadingService.hide();
                deffered.reject();
        });
        
        return deffered.promise;
	}
	
	self.deleteControl = function (idJeuDonneesControl) {
		loadingService.show();
		initResponse();
		var deffered  = $q.defer();
        var promissJsonFactory = jsonFactory.deleteJson("webService/app/controlTms/"+idJeuDonneesControl, null);
        promissJsonFactory
            .success(function (datas, status, headers, config) {
            	reponse = datas;
            	loadingService.hide();
            	deffered.resolve();
            })
            .error(function (datas, status, headers, config) {
            	loadingService.hide();
                deffered.reject();
        });
        
        return deffered.promise;
	}
	
	self.uploadUsing$http = function (file, idJeuDonneesControl, typeFile) {
		var deffered  = $q.defer();
		file.upload = jsonFactory.uploadFile("webService/app/controlTms/uploadFile/"+idJeuDonneesControl+"/"+typeFile, file);
		initResponse();
		file.upload.then(
			function (response) {
				file.result = response.message;
				reponse = response.data;
				
				deffered.resolve();		
			}, function () {
				//alert("Erreur serveur !");
				deffered.reject();
				
			}
		);
		file.upload.progress(function (evt) {
			file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
		});
		
		return deffered.promise;
	}
	
	self.getReponse = function () {
    	return reponse;
    }
    
    
    
    return self;
	
}]);