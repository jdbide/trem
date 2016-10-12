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
	
	self.getPartition = function () {
    	loadingService.show();
        var deffered  = $q.defer();

        var promissJsonFactory = jsonFactory.getJson("webService/app/controlTms/");
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
        var promissJsonFactory = jsonFactory.postJson("webService/app/controlTms/", idPartition);
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
		file.upload = jsonFactory.uploadFile("webService/app/controlTms/uploadFile/"+idJeuDonneesControl+"/"+typeFile, file)
		file.upload.then(function (response) {
			file.result = response.message;
			//alert(file.result);
		}, function (response) {
			alert(response);
			console.log(response);
			if (!response.status)
				alert("Error upload file : " + response.message);
			
			}
		
		
		);

		file.upload.progress(function (evt) {
			file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
		});
	}
	
	self.getReponse = function () {
    	return reponse;
    }
    
    
    
    return self;
	
}]);