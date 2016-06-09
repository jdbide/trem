'use strict';

/**
 * Appelle un WebService pour récupérer le JSON qu'il envoie
 */
socle_app.factory('jsonFactory',['$http', 'envService',function($http, envService) {
	var jsonFactory = [];
	
    jsonFactory.getJson = function (urlWebservice) {
        return $http({
                method: "get",
                url: envService.read('apiUrl')+urlWebservice,
                async: false,
                dataType: 'json',
                crossDomain: false,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                    'Accept':'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8',            
                }
        });
        
    };
    
    jsonFactory.postJson = function (urlWebservice, data) {
        return $http({
                method: "post",
                url: envService.read('apiUrl')+urlWebservice,
                async: false,
                dataType: 'json',
                crossDomain: false,
                contentType:"application/json; charset=utf-8",
                data: data             
        });
        
    };
    
    return jsonFactory;
}]);