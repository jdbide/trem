'use strict';

/**
 * Service se chargeant des fonctionnalité de la page control du module trainManagerSysteme
 */
socle_app.service('searchTmsService', ['jsonFactory', 'loadingService', '$q', function(jsonFactory, loadingService, $q) {
	var reponse = {
		status : null,
		message : null,
		data : null
	};
	
	

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

	self.getReponse = function () {
    	return reponse;
    }

	self.getDataFormByIdPartition = function (idCompagnieEnvironnement) {
		initResponse();
		var deffered  = $q.defer();
		var promissJsonFactory = jsonFactory.getJson("webService/app/searchTms/dataFormByPartition/"+idCompagnieEnvironnement);
        promissJsonFactory
            .success(function (datas, status, headers, config) {
            	//loadingService.hide();
            	reponse = datas;
            	deffered.resolve();
            })
            .error(function (datas, status, headers, config) {
            	//loadingService.hide();
                deffered.reject();
        });
        
        return deffered.promise;
	}
	
	self.getDataFormByIdCompagnie = function (action, idCompagnie) {
		initResponse();
		//console.log("==> " + action);
		var deffered  = $q.defer();
		var promissJsonFactory = jsonFactory.getJson("webService/app/searchTms/"+action+"/"+idCompagnie);
        promissJsonFactory
            .success(function (datas, status, headers, config) {
            	//loadingService.hide();
            	reponse = datas;
            	deffered.resolve();
            })
            .error(function (datas, status, headers, config) {
            	//loadingService.hide();
                deffered.reject();
        });
        
        return deffered.promise;
	}
	
	self.getStopsByOd = function (idMotriceRefOd) {
		initResponse();
		var deffered  = $q.defer();
		var promissJsonFactory = jsonFactory.getJson("webService/app/searchTms/stopsByOd/"+idMotriceRefOd);
        promissJsonFactory
            .success(function (datas, status, headers, config) {
            	//loadingService.hide();
            	reponse = datas;
            	deffered.resolve();
            })
            .error(function (datas, status, headers, config) {
            	//loadingService.hide();
                deffered.reject();
        });
        
        return deffered.promise;
	}
	
	self.executeSearch = function (data) {
		initResponse();
		var deffered  = $q.defer();
		var promissJsonFactory = jsonFactory.putJson("webService/app/filtrePdt/", data);
        promissJsonFactory
            .success(function (datas, status, headers, config) {
            	//loadingService.hide();
            	reponse = datas;
            	deffered.resolve();
            })
            .error(function (datas, status, headers, config) {
            	//loadingService.hide();
                deffered.reject();
        });
        
        return deffered.promise;
	}
	
    return self;
}]);