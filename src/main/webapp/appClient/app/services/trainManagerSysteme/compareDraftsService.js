'use strict';

/**
 * Service se chargeant des fonctionnalités de la page comparant les jd en base
 */
socle_app.service('compareDraftsService', ['jsonFactory', 'loadingService', '$q', function(jsonFactory, loadingService, $q) {
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

        var promissJsonFactory = jsonFactory.getJson("webService/app/compareDrafts/");
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
	
	self.getJeuDonneesParIdCompagnieEnvironnement = function (id) {
    	loadingService.show();
        var deffered  = $q.defer();
        var promissJsonFactory = jsonFactory.getJson("webService/app/compareDrafts/getAllJeuDonneesoParIdCompagnieEnvironnement/"+id);
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
	
	self.compareJd = function (id1,id2) {
		
    	loadingService.show();
        var deffered  = $q.defer();
        var promissJsonFactory = jsonFactory.getJson("webService/app/compareDrafts/compareJd/"+id1+"/"+id2);
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
    return self;
	
}]);