'use strict';

/**
 * Service pour lire les données d'un fichier json
 */
socle_app.service('readerFileJson', ['$http', '$q', function($http, $q) {
    var self = this;
    
    self.get = function (url) {
    	var deffered  = $q.defer();
    	$http.get(url)
    	.success (function(data) {
    		deffered.resolve(data);
    	})
    	.error (function() {
    		deffered.reject();
    	});
    	
    	return deffered.promise;
    }
    
    return self;
}]);