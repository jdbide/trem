/*
 * Service se chargeant partition du module trainManagerSysteme
 */
socle_app.service('updateLibelleService', ['jsonFactory', 'loadingService', '$q', function(jsonFactory, loadingService, $q) {
	
	var reponse= 
	{
		status : null,
		message : null,
		data : null
	};
	
	self = this;
	

	initReponse = function () {
    	reponse.status=null;
    	reponse.message=null;
    	reponse.data=null;
    }
	
	/*
	 * Récuperation des gares de la compagnie sélectionnée
	 */
	self.getAllCompagnies = function () {
    	loadingService.show();
        var deffered  = $q.defer();

        var promissJsonFactory = jsonFactory.getJson("webService/app/updateLibelle/getAllCompagnies");
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
	/*
	 * Récuperation des gares de la compagnie sélectionnée
	 */
	self.getAllGaresByCompagnie = function (idCompagnie) {
    	loadingService.show();
    	initReponse();
        var deffered  = $q.defer();

        var promissJsonFactory = jsonFactory.getJson("webService/app/updateLibelle/getAllGaresByCompagnie/"+idCompagnie);
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
	
	self.saveRow = function(id,label){
		loadingService.show();
    	initReponse();
        var deffered  = $q.defer();

        var promissJsonFactory = jsonFactory.getJson("webService/app/updateLibelle/update/"+id+"/"+label);
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

	
	self.getReponse = function () {
    	return reponse;
    }
	
	return self;
}]);