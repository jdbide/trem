'use strict';

/**
 * Service se chargeant des fonctionnalité de la page control du module trainManagerSysteme
 */
socle_app.service('resultTmsService', ['jsonFactory', 'loadingService', '$q', function(jsonFactory, loadingService, $q) {
	var reponse = {
		status : null,
		message : null,
		data : null
	};
	
	var datas = null;
	var noData = false;
	

	function initResponse () {
		reponse.status=null;
    	reponse.message=null;
    	reponse.data=null;
	}
	
	function initDatas () {
		datas=null;
	}

	
	self = this;
	
	self.initDatas = function () {
		datas=null;
	}
	
	self.initReponse = function () {
    	reponse.status=null;
    	reponse.message=null;
    	reponse.data=null;
    }

	self.getReponse = function () {
    	return reponse;
    }
	
	self.getResult = function (data) {
		loadingService.show();
		initResponse();
		initDatas();
		var deffered  = $q.defer();
		var promissJsonFactory = jsonFactory.putJson("webService/app/filtrePdt/", data);
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
	
	self.getDatas = function () {
		return datas;
	}
	
	self.setDatas = function (data) {
		datas = data;
	}
	
    return self;
}]);