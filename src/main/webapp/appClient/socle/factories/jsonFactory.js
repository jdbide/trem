'use strict';

/**
 * Appelle un WebService pour récupérer le JSON qu'il envoie
 */
socle_app.factory('jsonFactory',['$http', 'envService',function($http, envService) {
	var jsonFactory = [];
	
    jsonFactory.getJson = function (nameWebservice) {
        return $http({
                method: "get",
                url: envService.read('apiUrl')+nameWebservice,
                async: false,
                dataType: 'json',
                crossDomain: false,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                    'Accept':'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8',            
                }
        });
        
    };
    
    jsonFactory.postJson = function (nameWebservice, data) {
        return $http({
                method: "post",
                url: envService.read('apiUrl')+nameWebservice,
                async: false,
                dataType: 'json',
                crossDomain: false,
                contentType:"application/json; charset=utf-8",
                data: data             
        });
        
    };
    
    jsonFactory.putJson = function (nameWebservice, data) {
        return $http({
                method: "put",
                url: envService.read('apiUrl')+nameWebservice,
                async: false,
                dataType: 'json',
                crossDomain: false,
                contentType:"application/json; charset=utf-8",
                data: data             
        });
        
    };
    
    jsonFactory.deleteJson = function (nameWebservice, data) {
        return $http({
                method: "delete",
                url: envService.read('apiUrl')+nameWebservice,
                async: false,
                dataType: 'json',
                crossDomain: false,
                contentType:"application/json; charset=utf-8",
                data: data             
        });
        
    };
    
    return jsonFactory;
}]);