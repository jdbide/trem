/*
 * Service se chargeant partition du module trainManagerSysteme
 */
socle_app.service('partitionTmsService', ['jsonFactory', 'loadingService', '$q', function(jsonFactory, loadingService, $q) {
	self = this;
	/*
	 * Récuperation des partition active 
	 */
	self.getPartition = function () {
    	loadingService.show();
        var deffered  = $q.defer();

        var promissJsonFactory = jsonFactory.getJson("webService/app/partition/");
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
	
	self.allPartitionWithJeuDonneesActive = function () {
    	loadingService.show();
        var deffered  = $q.defer();

        var promissJsonFactory = jsonFactory.getJson("webService/app/partition/allPartitionWithJeuDonneesActive/");
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