'use strict';

/**
 * Appelle un WebService pour récupérer le JSON qu'il envoie
 */
socle_app.factory('jsonFactory',['$http', function($http) {
	var jsonFactory = [];
	
    jsonFactory.getJson = function (urlWebservice) {
        return $http({
                method: "get",
                url: urlWebservice,
                async: false,
                dataType: 'json',
                crossDomain: false,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                    'Accept':'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8',            
                }
        });
        
    };
    
    return jsonFactory;
}]);